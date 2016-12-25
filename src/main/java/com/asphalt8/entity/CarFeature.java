package com.asphalt8.entity;

import java.math.BigDecimal;

/**
 * 
 * @author Asphalt8 车辆性能类
 *
 */

public class CarFeature extends AbstractCar implements Cloneable {

	private int totalBaseRank = 0; // totalBaseRank =
									// baseUpgradeRank+baseProRank
	private int totalMaxRank = 0; // totalMaxRank = maxUpgradeRank+maxProRank
	private int baseUpgradeRank = 0;
	private int maxUpgradeRank = 0;
	private int baseProRank = 0;
	private int maxProRank = 0;
	private double baseAcceleration = 0;
	private double maxAcceleration = 0;
	private double accelerationRate;// accelerationRate=maxAcceleration/baseAcceleration
	private double baseSpeed = 0;
	private double maxSpeed = 0;
	private double speedRate;// speedRate=baseSpeed/maxSpeed
	private double baseHandling = 0;
	private double maxHandling = 0;
	private double handlingRate;// handlingRate=baseHandling/maxHandling
	private double baseNitro = 0;
	private double maxNitro = 0;
	private double nitroRate;// nitroRate=baseNitro/maxNitro
	private double baseNitroSpeed = 0;
	private double maxNitroSpeed = 0;
	private int scale = 3;
	private String hundred = "100";
	private String rateMock = "30";

	public CarFeature() {
		super();
	}

	public CarFeature(int carId, int baseUpgradeRank, int maxUpgradeRank,
			int baseProRank, int maxProRank, double baseAcceleration,
			double maxAcceleration, double baseSpeed, double maxSpeed,
			double baseHandling, double maxHandling, double baseNitro,
			double maxNitro) {
		super();
		this.carId = carId;
		this.baseUpgradeRank = baseUpgradeRank;
		this.maxUpgradeRank = maxUpgradeRank;
		this.baseProRank = baseProRank;
		this.maxProRank = maxProRank;
		this.baseAcceleration = baseAcceleration;
		this.maxAcceleration = maxAcceleration;
		this.baseSpeed = baseSpeed;
		this.maxSpeed = maxSpeed;
		this.baseHandling = baseHandling;
		this.maxHandling = maxHandling;
		this.baseNitro = baseNitro;
		this.maxNitro = maxNitro;
	}

	public int getBaseUpgradeRank() {
		return baseUpgradeRank;
	}

	public void setBaseUpgradeRank(int baseUpgradeRank) {
		this.baseUpgradeRank = baseUpgradeRank;
	}

	public int getMaxUpgradeRank() {
		return maxUpgradeRank;
	}

	public void setMaxUpgradeRank(int maxUpgradeRank) {
		this.maxUpgradeRank = maxUpgradeRank;
	}

	public int getBaseProRank() {
		return baseProRank;
	}

	public void setBaseProRank(int baseProRank) {
		this.baseProRank = baseProRank;
	}

	public int getMaxProRank() {
		return maxProRank;
	}

	public void setMaxProRank(int maxProRank) {
		this.maxProRank = maxProRank;
	}

	public double getBaseAcceleration() {
		return baseAcceleration;
	}

	public void setBaseAcceleration(double baseAcceleration) {
		this.baseAcceleration = baseAcceleration;
	}

	public double getMaxAcceleration() {
		return maxAcceleration;
	}

	public void setMaxAcceleration(double maxAcceleration) {
		this.maxAcceleration = maxAcceleration;
	}

	public double getBaseSpeed() {
		return baseSpeed;
	}

	public void setBaseSpeed(double baseSpeed) {
		this.baseSpeed = baseSpeed;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public double getBaseHandling() {
		return baseHandling;
	}

	public void setBaseHandling(double baseHandling) {
		this.baseHandling = baseHandling;
	}

	public double getMaxHandling() {
		return maxHandling;
	}

	public void setMaxHandling(double maxHandling) {
		this.maxHandling = maxHandling;
	}

	public double getBaseNitro() {
		return baseNitro;
	}

	public void setBaseNitro(double baseNitro) {
		this.baseNitro = baseNitro;
	}

	public double getMaxNitro() {
		return maxNitro;
	}

	public void setMaxNitro(double maxNitro) {
		this.maxNitro = maxNitro;
	}

	public void setMaxNitro(int maxNitro) {
		this.maxNitro = maxNitro;
	}

	public int getTotalBaseRank() {
		this.totalBaseRank = baseUpgradeRank + baseProRank;
		return this.totalBaseRank;
	}

	public int getTotalMaxRank() {
		this.totalMaxRank = maxUpgradeRank + maxProRank;
		return this.totalMaxRank;
	}

	public double getAccelerationRate() {
		BigDecimal ma = new BigDecimal(Double.toString(maxAcceleration));
		BigDecimal ba = new BigDecimal(Double.toString(baseAcceleration));

		if (baseAcceleration > 0) {
			this.accelerationRate = ma
					.divide(ba, scale, BigDecimal.ROUND_HALF_UP)
					.multiply(new BigDecimal(hundred))
					.subtract(new BigDecimal(rateMock)).doubleValue();
		}
		return this.accelerationRate;
	}

	public double getSpeedRate() {
		BigDecimal bs = new BigDecimal(Double.toString(baseSpeed));
		BigDecimal ms = new BigDecimal(Double.toString(maxSpeed));
		if (maxSpeed > 0) {
			this.speedRate = bs.divide(ms, scale, BigDecimal.ROUND_HALF_UP)
					.multiply(new BigDecimal(hundred))
					.subtract(new BigDecimal(rateMock)).doubleValue();
		}
		return this.speedRate;
	}

	public double getHandlingRate() {
		BigDecimal bh = new BigDecimal(Double.toString(baseHandling));
		BigDecimal mh = new BigDecimal(Double.toString(maxHandling));
		if (maxHandling > 0) {
			this.handlingRate = bh.divide(mh, scale, BigDecimal.ROUND_HALF_UP)
					.multiply(new BigDecimal(hundred))
					.subtract(new BigDecimal(rateMock)).doubleValue();
		}
		return this.handlingRate;
	}

	public double getNitroRate() {
		BigDecimal bn = new BigDecimal(Double.toString(baseNitro));
		BigDecimal mn = new BigDecimal(Double.toString(maxNitro));

		if (maxNitro > 0) {
			this.nitroRate = bn.divide(mn, scale, BigDecimal.ROUND_HALF_UP)
					.multiply(new BigDecimal(hundred))
					.subtract(new BigDecimal(rateMock)).doubleValue();
		}
		return nitroRate;
	}

	public double getBaseNitroSpeed() {

		// 用BigDecimal解决double相加损失精度的问题

		BigDecimal baseSpeedValue = new BigDecimal(Double.toString(baseSpeed));
		BigDecimal baseNitroValue = new BigDecimal(Double.toString(baseNitro));

		this.baseNitroSpeed = baseSpeedValue.add(baseNitroValue).doubleValue();
		return this.baseNitroSpeed;
	}

	public void setBaseNitroSpeed(double baseNitroSpeed) {
		this.baseNitroSpeed = baseNitroSpeed;
	}

	public double getMaxNitroSpeed() {

		// 用BigDecimal解决double相加损失精度的问题

		BigDecimal maxSpeedValue = new BigDecimal(Double.toString(maxSpeed));
		BigDecimal maxNitroValue = new BigDecimal(Double.toString(maxNitro));

		this.maxNitroSpeed = maxSpeedValue.add(maxNitroValue).doubleValue();
		return this.maxNitroSpeed;
	}

	public void setMaxNitroSpeed(double maxNitroSpeed) {
		this.maxNitroSpeed = maxNitroSpeed;
	}

	public String getRateMock() {
		return rateMock;
	}

	public void setRateMock(String rateMock) {
		this.rateMock = rateMock;
	}

	public void setTotalBaseRank(int totalBaseRank) {
		this.totalBaseRank = totalBaseRank;
	}

	public void setTotalMaxRank(int totalMaxRank) {
		this.totalMaxRank = totalMaxRank;
	}

	@Override
	public String toString() {
		return "CarFeature [carId=" + carId + ", carName=" + carName
				+ ", totalBaseRank=" + getTotalBaseRank() + ", totalMaxRank="
				+ getTotalMaxRank() + ", baseUpgradeRank=" + baseUpgradeRank
				+ ", maxUpgradeRank=" + maxUpgradeRank + ", baseProRank="
				+ baseProRank + ", maxProRank=" + maxProRank
				+ ", baseAcceleration=" + baseAcceleration
				+ ", maxAcceleration=" + maxAcceleration + ", baseSpeed="
				+ baseSpeed + ", maxSpeed=" + maxSpeed + ", baseHandling="
				+ baseHandling + ", maxHandling=" + maxHandling
				+ ", baseNitro=" + baseNitro + ", maxNitro=" + maxNitro
				+ ", accelerationRate=" + getAccelerationRate()
				+ ", speedRate=" + getSpeedRate() + ", handlingRate="
				+ getHandlingRate() + ", nitroRate=" + getNitroRate()
				+ ", baseNitroSpeed=" + getBaseNitroSpeed()
				+ ", maxNitroSpeed=" + getMaxNitroSpeed() + "]";
	}

	@Override
	public Object clone() throws CloneNotSupportedException {

		CarFeature o = null;
		o = (CarFeature) super.clone();
		return o;

	}

}
