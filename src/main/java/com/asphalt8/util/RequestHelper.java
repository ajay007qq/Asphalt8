package com.asphalt8.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.asphalt8.entity.Car;
import com.asphalt8.entity.CarFeature;
import com.asphalt8.entity.CarIntroduction;
import com.asphalt8.entity.CarStat;

@Component
public class RequestHelper {

	public CarFeature getCarFeatureFromRequest(HttpServletRequest request) {
		CarFeature f = new CarFeature();

		String baseUpgradeRank = request.getParameter("baseUpgradeRank");
		if (!StringUtils.isEmpty(baseUpgradeRank))
			f.setBaseUpgradeRank(Integer.valueOf(baseUpgradeRank));

		String maxUpgradeRank = request.getParameter("maxUpgradeRank");
		if (!StringUtils.isEmpty(maxUpgradeRank))
			f.setMaxUpgradeRank(Integer.valueOf(maxUpgradeRank));

		String baseProRank = request.getParameter("baseProRank");
		if (!StringUtils.isEmpty(baseProRank))
			f.setBaseProRank(Integer.valueOf(baseProRank));

		String maxProRank = request.getParameter("maxProRank");
		if (!StringUtils.isEmpty(maxProRank))
			f.setMaxProRank(Integer.valueOf(maxProRank));

		String baseAcceleration = request.getParameter("baseAcceleration");
		if (!StringUtils.isEmpty(baseAcceleration))
			f.setBaseAcceleration(Double.valueOf(baseAcceleration));

		String maxAcceleration = request.getParameter("maxAcceleration");
		if (!StringUtils.isEmpty(maxAcceleration))
			f.setMaxAcceleration(Double.valueOf(maxAcceleration));

		String baseSpeed = request.getParameter("baseSpeed");
		if (!StringUtils.isEmpty(baseSpeed))
			f.setBaseSpeed(Double.valueOf(baseSpeed));

		String maxSpeed = request.getParameter("maxSpeed");
		if (!StringUtils.isEmpty(maxSpeed))
			f.setMaxSpeed(Double.valueOf(maxSpeed));

		String baseHandling = request.getParameter("baseHandling");
		if (!StringUtils.isEmpty(baseHandling))
			f.setBaseHandling(Double.valueOf(baseHandling));

		String maxHandling = request.getParameter("maxHandling");
		if (!StringUtils.isEmpty(maxHandling))
			f.setMaxHandling(Double.valueOf(maxHandling));

		String baseNitro = request.getParameter("baseNitro");
		if (!StringUtils.isEmpty(baseNitro))
			f.setBaseNitro(Double.valueOf(baseNitro));

		String maxNitro = request.getParameter("maxNitro");
		if (!StringUtils.isEmpty(maxNitro))
			f.setMaxNitro(Double.valueOf(maxNitro));

		return f;
	}

	public Car getCarFromRequest(HttpServletRequest request) {
		Car c = new Car();

		String carName = request.getParameter("carName");
		if (!StringUtils.isEmpty(carName))
			c.setCarName(carName);

		String carCategory = request.getParameter("carCategory");
		if (!StringUtils.isEmpty(carCategory))
			c.setCarCategory(carCategory);

		String vendor = request.getParameter("vendor");
		if (!StringUtils.isEmpty(vendor))
			c.setVendor(vendor);

		String price = request.getParameter("price");
		if (!StringUtils.isEmpty(price))
			c.setPrice(Integer.valueOf(price));

		return c;
	}

	public CarIntroduction getCarIntroductionFromRequest(
			HttpServletRequest request) {
		CarIntroduction ci = new CarIntroduction();

		String description = request.getParameter("description");
		if (!StringUtils.isEmpty(description))
			ci.setDescription(description);

		String appearances1 = request.getParameter("appearances1");
		if (!StringUtils.isEmpty(appearances1))
			ci.setAppearances1(appearances1);

		String appearances2 = request.getParameter("appearances2");
		if (!StringUtils.isEmpty(appearances2))
			ci.setAppearances2(appearances2);

		String appearances3 = request.getParameter("appearances3");
		if (!StringUtils.isEmpty(appearances3))
			ci.setAppearances3(appearances3);

		String design1 = request.getParameter("design1");
		if (!StringUtils.isEmpty(design1))
			ci.setDesign1(design1);

		String design2 = request.getParameter("design2");
		if (!StringUtils.isEmpty(design2))
			ci.setDesign2(design2);

		String design3 = request.getParameter("design3");
		if (!StringUtils.isEmpty(design3))
			ci.setDesign3(design3);

		return ci;
	}

	public CarStat getCarStatFromRequest(HttpServletRequest request) {
		CarStat s = new CarStat();
		String raceCount = request.getParameter("raceCount");
		if (!StringUtils.isEmpty(raceCount))
			s.setRaceCount(Integer.valueOf(raceCount));

		String broughtCount = request.getParameter("broughtCount");
		if (!StringUtils.isEmpty(broughtCount))
			s.setBroughtCount(Integer.valueOf(broughtCount));

		String likeCount = request.getParameter("likeCount");
		if (!StringUtils.isEmpty(likeCount))
			s.setLikeCount(Integer.valueOf(likeCount));

		String awardCount = request.getParameter("awardCount");
		if (!StringUtils.isEmpty(awardCount))
			s.setAwardCount(Integer.valueOf(awardCount));

		return s;
	}
}
