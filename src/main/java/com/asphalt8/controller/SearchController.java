package com.asphalt8.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.asphalt8.entity.CarMedia;
import com.asphalt8.exception.NoCarFoundException;
import com.asphalt8.service.CarMediaService;

@Controller
public class SearchController {

	private static Logger logger = Logger.getLogger(SearchController.class);

	@Resource
	CarMediaService mediaService;

	@RequestMapping("searchCar")
	public @ResponseBody List<CarMedia> searchCar(
			@Param("keyword") String keyword,
			@Param("category") String category, Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug("keyword: " + keyword);
			logger.debug("category: " + category);
		}

		List<CarMedia> cars = null;

		if (StringUtils.isEmpty(keyword) && StringUtils.isEmpty(category)) {
			if (logger.isDebugEnabled()) {
				logger.debug("搜索所有赛车");
			}
			try {
				cars = mediaService.queryAllCars();

				if (cars != null) {
					if (logger.isDebugEnabled()) {
						logger.debug("赛车总数：" + cars.size());
					}
				}

			} catch (NoCarFoundException e) {
				logger.error("没有找到任何赛车信息");
			}

		} else {
			// search by keyword
			if (!StringUtils.isEmpty(category)) {
				if (logger.isDebugEnabled()) {
					logger.debug("搜索所有" + category + "级赛车");
				}

				cars = mediaService.queryCarsByCategory(category);

			} else if (!StringUtils.isEmpty(keyword)) {
				if (logger.isDebugEnabled()) {
					logger.debug("按关键字搜索：" + keyword);
				}

				keyword = keyword.replace("+", " ");
				cars = mediaService.queryCarsByKeyword(keyword);

			}

		}

		if (logger.isDebugEnabled()) {
			logger.debug("搜索结果数目：" + cars.size());
			logger.debug("搜索结果：" + cars);
		}

		return cars;
	}
}
