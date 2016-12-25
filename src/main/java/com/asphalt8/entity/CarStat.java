package com.asphalt8.entity;

public class CarStat extends AbstractCar {

	private int raceCount;
	private int broughtCount;
	private int likeCount;
	private int awardCount;

	public CarStat() {
		super();
		this.raceCount = 0;
		this.broughtCount = 0;
		this.likeCount = 0;
		this.awardCount = 0;
	}

	public int getRaceCount() {
		return raceCount;
	}

	public void setRaceCount(int raceCount) {
		this.raceCount = raceCount;
	}

	public int getBroughtCount() {
		return broughtCount;
	}

	public void setBroughtCount(int broughtCount) {
		this.broughtCount = broughtCount;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getAwardCount() {
		return awardCount;
	}

	public void setAwardCount(int awardCount) {
		this.awardCount = awardCount;
	}

	@Override
	public String toString() {
		return "CarStat [raceCount=" + raceCount + ", broughtCount="
				+ broughtCount + ", likeCount=" + likeCount + ", awardCount="
				+ awardCount + ", carId=" + carId + ", carName=" + carName
				+ "]";
	}

}
