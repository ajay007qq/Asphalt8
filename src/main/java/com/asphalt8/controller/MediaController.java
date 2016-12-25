package com.asphalt8.controller;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.asphalt8.entity.Car;
import com.asphalt8.entity.CarMedia;
import com.asphalt8.entity.UploadResult;
import com.asphalt8.exception.NoCarFoundException;
import com.asphalt8.service.CarMediaService;
import com.asphalt8.service.CarService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class MediaController {

	@Resource
	private CarService carService;

	@Resource
	private CarMediaService mediaService;

	private static Logger logger = Logger.getLogger(MediaController.class);

	@RequestMapping("manage/fileUpload")
	public String fileUpload(
			@RequestParam("uploadFile") MultipartFile[] uploadFile,
			@RequestParam("carName") String carName,
			HttpServletRequest request, Model model)
			throws NoCarFoundException, JsonProcessingException {

		if (uploadFile != null && uploadFile.length > 0) {
			if (logger.isDebugEnabled()) {
				logger.debug("文件个数： " + uploadFile.length);
			}

			Car car = carService.queryByCarName(carName);
			int carId = car.getCarId();

			UploadResult result = new UploadResult();
			result.setCarName(carName);

			try {
				for (MultipartFile f : uploadFile) {

					String originalFileName = f.getOriginalFilename();
					String fileType = getFileType(originalFileName);

					if (logger.isDebugEnabled()) {
						logger.debug("文件名称： " + originalFileName);
						logger.debug("文件类型： " + fileType);
					}

					if (fileType.equalsIgnoreCase("jpg")
							|| fileType.equalsIgnoreCase("png")
							|| fileType.equalsIgnoreCase("gif")) {
						uploadImage(carId, carName, originalFileName, request,
								f, result);
					} else if (fileType.equalsIgnoreCase("m4v")
							|| fileType.equalsIgnoreCase("mp4")) {
						uploadVideo(carId, carName, originalFileName, request,
								f, result);
					}
				}
			} catch (Exception e) {
				logger.error("##保存文件出现错误## " + e);
				result.setError(e.getMessage().replace("\\", "\\\\"));
			}

			if (logger.isDebugEnabled()) {
				logger.debug(result);
			}

			ObjectMapper mapper = new ObjectMapper();
			String jsonResult = mapper.writeValueAsString(result);

			if (logger.isDebugEnabled()) {
				logger.debug("Json Result: " + jsonResult);
			}

			model.addAttribute("uploadResult", jsonResult);

		} else {
			logger.error("##上传文件为空##");
		}

		return "uploadResult";
	}

	private void uploadImage(int carId, String carName,
			String originalFileName, HttpServletRequest request,
			MultipartFile f, UploadResult result) throws IllegalStateException,
			IOException {
		String imageFolder = "/resources/image/" + carId;
		String newImageName = getNewImageName(carId, carName, originalFileName);
		String imageUploadPath = request.getSession().getServletContext()
				.getRealPath(imageFolder);
		String type = "image";

		upload(imageUploadPath, imageFolder, newImageName, carId, carName,
				type, f, result);

	}

	private void uploadVideo(int carId, String carName,
			String originalFileName, HttpServletRequest request,
			MultipartFile f, UploadResult result) throws IllegalStateException,
			IOException {
		String videoFolder = "/resources/video/";
		String newVideoName = getNewVideoName(carName, originalFileName);
		String imageUploadPath = request.getSession().getServletContext()
				.getRealPath(videoFolder);
		String type = "video";

		upload(imageUploadPath, videoFolder, newVideoName, carId, carName,
				type, f, result);

	}

	private String getNewVideoName(String carName, String originalFileName) {
		String suffix = originalFileName.substring(originalFileName
				.lastIndexOf("."));
		String name = carName.replaceAll(" ", "") + "-demo" + suffix;

		if (logger.isDebugEnabled()) {
			logger.debug("视频新名称： " + name);
		}
		return name;
	}

	private void upload(String uploadPath, String folder, String fileName,
			int carId, String carName, String type, MultipartFile f,
			UploadResult result) throws IllegalStateException, IOException {
		String seperator = "/";
		String fullFilePath = uploadPath + seperator + fileName;

		File pathFile = new File(uploadPath);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("上传文件： " + fullFilePath);
		}

		f.transferTo(new File(fullFilePath));

		if (logger.isDebugEnabled()) {
			logger.debug("上传状态： " + fileName + " 成功");
		}

		CarMedia carMdeia = new CarMedia(carId, carName, fileName, type);

		int ret = mediaService.insertCarMedia(carMdeia);

		if (ret == 1) {
			if (logger.isDebugEnabled()) {
				logger.debug("文件信息插入表成功： " + carMdeia);
			}
			if (type.equals("image")) {
				result.getImageUrlList().add(folder + seperator + fileName);
			} else {
				result.setVideoUrl(folder + seperator + fileName);
			}

		} else {
			if (type.equals("image")) {
				logger.error("##文件信息插入表失败## " + carMdeia);
				result.setError("文件信息插入表失败: "
						+ carMdeia.toString().replace("\\", "\\\\"));
			} else {
				result.setVideoUrl(folder + seperator + fileName);
				logger.warn("##视频已经存在## " + carMdeia);
			}
		}
	}

	private String getFileType(String name) {
		return name.substring(name.lastIndexOf(".") + 1);
	}

	private String getNewImageName(int carId, String carName,
			String originalFileName) {
		String suffix = originalFileName.substring(originalFileName
				.lastIndexOf("."));
		int imageId = mediaService.queryNextImageNumById(carId);
		String name = carName.replaceAll(" ", "") + "-" + imageId + suffix;

		if (logger.isDebugEnabled()) {
			logger.debug("图片新名称： " + name);
		}

		return name;
	}
}
