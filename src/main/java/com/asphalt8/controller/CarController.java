package com.asphalt8.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.asphalt8.entity.Car;
import com.asphalt8.entity.CarFeature;
import com.asphalt8.entity.CarInfo;
import com.asphalt8.entity.CarIntroduction;
import com.asphalt8.entity.CarMedia;
import com.asphalt8.entity.CarStat;
import com.asphalt8.exception.NoCarFoundException;
import com.asphalt8.service.CarInfoService;
import com.asphalt8.service.CarService;
import com.asphalt8.service.RedisService;
import com.asphalt8.util.RequestHelper;

@Controller
public class CarController {

	@Resource
	@Qualifier("carServiceImpl")
	private CarService carService;

	@Resource
	@Qualifier("carInfoServiceImpl")
	private CarInfoService carInfoService;

	@Resource
	@Qualifier("redisService")
	private RedisService redisService;

	@Resource
	private RequestHelper requestHelper;

	private static Logger logger = Logger.getLogger(CarController.class);

	@RequestMapping("car/{name}")
	public String showCar(@PathVariable String name, Model model)
			throws NoCarFoundException {

		CarInfo carInfo = carInfoService.queryByCarName(name);

		Car car = carInfo.getCar();
		model.addAttribute("car", car);

		if (logger.isDebugEnabled()) {
			logger.debug("car: " + car);
		}

		List<CarMedia> carImages = carInfo.getCarImages();
		model.addAttribute("carImages", carImages);
		if (logger.isDebugEnabled()) {
			logger.debug("carImages: " + carImages);
		}

		CarMedia carVideo = carInfo.getCarVideo();
		model.addAttribute("carVideo", carVideo);
		if (logger.isDebugEnabled()) {
			logger.debug("carVideo: " + carVideo);
		}

		CarFeature carFeature = carInfo.getCarFeature();
		model.addAttribute("carFeature", carFeature);

		if (logger.isDebugEnabled()) {
			logger.debug("carFeature: " + carFeature);
		}

		CarIntroduction carIntroduction = carInfo.getCarIntroduction();
		model.addAttribute("carIntroduction", carIntroduction);

		if (logger.isDebugEnabled()) {
			logger.debug("carIntroduction: " + carIntroduction);
		}

		CarStat carStat = carInfo.getCarStat();
		model.addAttribute("carStat", carStat);

		if (logger.isDebugEnabled()) {
			logger.debug("carStat: " + carIntroduction);
		}

		List<Car> carsForAd = carService.queryCarsForAdvertisment(name);
		model.addAttribute("carsForAd", carsForAd);

		if (logger.isDebugEnabled()) {
			logger.debug("carsForAd: " + carsForAd);
		}

		return "car";
	}

	@RequestMapping("manage/queryVendorsByKeyWord")
	public @ResponseBody List<String> queryVendorsByKeyWord(
			@RequestParam String word) {
		List<String> vendors = carService.queryVendorsByKeyWord(word);
		if (logger.isDebugEnabled()) {
			logger.debug("vendors: " + vendors);
		}
		return vendors;
	}

	@RequestMapping("manage/queryCarNameByKeyWord")
	public @ResponseBody List<String> queryCarNameByKeyWord(
			@RequestParam String word) {
		List<String> carNames = carService.queryCarNameByKeyWord(word);
		if (logger.isDebugEnabled()) {
			logger.debug("car name: " + carNames);
		}
		return carNames;
	}

	@RequestMapping(value = "manage/saveCar", method = RequestMethod.POST)
	public @ResponseBody int saveCar(HttpServletRequest request) {

		Integer ret = 0;
		int carId = 0;

		Car car = requestHelper.getCarFromRequest(request);

		try {
			ret = carService.save(car);
			carId = car.getCarId(); // carId = auto_increment id for a new car
			if (carId == 0) {
				// query car id if this is an existing car
				carId = carService.queryByCarName(car.getCarName()).getCarId();
				car.setCarId(carId);
			}

			if (logger.isDebugEnabled()) {
				logger.debug("save(car) return code: " + ret);
				logger.debug("car :" + car);
			}

			CarFeature carFeature = requestHelper
					.getCarFeatureFromRequest(request);
			carFeature.setCarId(carId);

			if (logger.isDebugEnabled()) {
				logger.debug("carFeature :" + carFeature);
			}

			ret = carInfoService.getCarFeatureService().save(carFeature);

			if (logger.isDebugEnabled()) {
				logger.debug("save(carFeature) return code: " + ret);
			}

			CarIntroduction carIntroduction = requestHelper
					.getCarIntroductionFromRequest(request);
			carIntroduction.setCarId(carId);

			if (logger.isDebugEnabled()) {
				logger.debug("carIntroduction :" + carIntroduction);
			}

			ret = carInfoService.getCarIntroductionServices().save(
					carIntroduction);

			if (logger.isDebugEnabled()) {
				logger.debug("save(carIntroduction) return code: " + ret);
			}

			CarStat carStat = requestHelper.getCarStatFromRequest(request);
			carStat.setCarId(carId);

			if (logger.isDebugEnabled()) {
				logger.debug("carStat :" + carStat);
			}

			ret = carInfoService.getCarStatService().save(carStat);

			if (logger.isDebugEnabled()) {
				logger.debug("save(carStat) return code: " + carStat);
			}

			/**
			 * 刷新redis,清除所有缓存数据 <br>
			 * 访问RedisService的内部类FlushRedis
			 */

			RedisService.FlushRedis flush = redisService.getFlushRedisObject();
			Thread t = new Thread(flush);
			t.start();

		} catch (Exception e) {
			logger.debug("## save car error ##");
			logger.debug("## error message: " + e + " ##");
			return 0;
		}

		return carId > 0 ? carId : ret;
	}

	@RequestMapping("manage/queryCarData")
	public @ResponseBody CarInfo queryCarData(@RequestParam String name) {
		CarInfo info = null;
		if (logger.isDebugEnabled()) {
			logger.debug("赛车名称： " + name);
		}
		try {
			info = carInfoService.queryByCarName(name);

			if (logger.isDebugEnabled()) {
				logger.debug("赛车信息： " + info);
			}

		} catch (NoCarFoundException e) {
			logger.warn("##没有查询到赛车信息##");
		}
		return info;
	}

	@RequestMapping("compare/{car1}/{car2}")
	public String compareCar(@PathVariable String car1,
			@PathVariable String car2, Model model) throws NoCarFoundException {

		CarInfo carInfo1 = carInfoService.queryByCarName(car1);
		CarInfo carInfo2 = carInfoService.queryByCarName(car2);

		CarFeature carFeature1 = carInfo1.getCarFeature();
		CarFeature carFeature2 = carInfo2.getCarFeature();
		Car carObj1 = carInfo1.getCar();
		Car carObj2 = carInfo2.getCar();

		if (logger.isDebugEnabled()) {
			logger.debug("car1 : " + carFeature1);
			logger.debug("car2 : " + carFeature2);
		}

		model.addAttribute("car1", carObj1);
		model.addAttribute("car2", carObj2);

		model.addAttribute("carFeature1", carFeature1);
		model.addAttribute("carFeature2", carFeature2);

		return "compare";
	}
}
