package com.asphalt8.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.asphalt8.entity.CarFeature;
import com.asphalt8.service.TopRankService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class RankController {

	private static Logger logger = Logger.getLogger(RankController.class);

	@Autowired
	private TopRankService topRankService;

	@RequestMapping("rank")
	public String topRank(Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug("Top 10 ranking...");
		}

		List<CarFeature> topAcceleration = topRankService
				.queryTopAcceleration();
		List<CarFeature> topNitroSpeed = topRankService.queryTopNitroSpeed();
		List<CarFeature> topNitro = topRankService.queryTopNitro();
		List<CarFeature> topHandling = topRankService.queryTopHandling();
		List<CarFeature> topSpeed = topRankService.queryTopSpeed();

		logger.debug("topAcceleration" + topAcceleration.get(0));
		logger.debug("topNitroSpeed" + topNitroSpeed.get(0));
		logger.debug("topNitro" + topNitro.get(0));
		logger.debug("topHandling" + topHandling.get(0));
		logger.debug("topSpeed" + topSpeed.get(0));

		try {

			String topAccelerationJsonStr = objectToJson(topAcceleration);
			String topNitroSpeedJsonStr = objectToJson(topNitroSpeed);
			String topNitroJsonStr = objectToJson(topNitro);
			String topHandlingJsonStr = objectToJson(topHandling);
			String topSpeedJsonStr = objectToJson(topSpeed);

			model.addAttribute("topAccelerationJsonStr", topAccelerationJsonStr);
			model.addAttribute("topNitroSpeedJsonStr", topNitroSpeedJsonStr);
			model.addAttribute("topNitroJsonStr", topNitroJsonStr);
			model.addAttribute("topHandlingJsonStr", topHandlingJsonStr);
			model.addAttribute("topSpeedJsonStr", topSpeedJsonStr);

		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(), e);
		}

		return "rank";

	}

	private String objectToJson(List<CarFeature> objects)
			throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		String jsonStr = mapper.writeValueAsString(objects);

		return jsonStr;
	}
}
