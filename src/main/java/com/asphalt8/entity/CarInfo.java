package com.asphalt8.entity;

import java.util.List;

public class CarInfo {

	private Car car;
	private CarFeature carFeature;
	private CarIntroduction carIntroduction;
	private CarStat carStat;
	private List<CarMedia> carImages;
	private CarMedia carVideo;

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public CarFeature getCarFeature() {
		return carFeature;
	}

	public void setCarFeature(CarFeature carFeature) {
		this.carFeature = carFeature;
	}

	public CarIntroduction getCarIntroduction() {
		return carIntroduction;
	}

	public void setCarIntroduction(CarIntroduction carIntroduction) {
		this.carIntroduction = carIntroduction;
	}

	public CarStat getCarStat() {
		return carStat;
	}

	public void setCarStat(CarStat carStat) {
		this.carStat = carStat;
	}

	public List<CarMedia> getCarImages() {
		return carImages;
	}

	public void setCarImages(List<CarMedia> carImages) {
		this.carImages = carImages;
	}

	public CarMedia getCarVideo() {
		return carVideo;
	}

	public void setCarVideo(CarMedia carVideo) {
		this.carVideo = carVideo;
	}

	@Override
	public String toString() {
		return "CarInfo [car=" + car + ", carFeature=" + carFeature
				+ ", carIntroduction=" + carIntroduction + ", carStat="
				+ carStat + ", carImages=" + carImages + ", carVideo="
				+ carVideo + "]";
	}

}
