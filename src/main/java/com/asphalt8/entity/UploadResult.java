package com.asphalt8.entity;

import java.util.ArrayList;
import java.util.List;

public class UploadResult {

	private String error;
	private int imageCount;
	private String CarName;
	private List<String> imageUrlList = new ArrayList<String>();
	private String videoUrl;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public int getImageCount() {
		this.imageCount = imageUrlList.size();
		return imageCount;
	}

	public void setImageCount(int imageCount) {
		this.imageCount = imageCount;
	}

	public List<String> getImageUrlList() {
		return imageUrlList;
	}

	public void setImageUrlList(List<String> imageUrlList) {
		this.imageUrlList = imageUrlList;
	}

	public String getCarName() {
		return CarName;
	}

	public void setCarName(String carName) {
		CarName = carName;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	@Override
	public String toString() {
		return "UploadResult [error=" + error + ", imageCount=" + imageCount
				+ ", CarName=" + CarName + ", imageUrlList=" + imageUrlList
				+ ", videoUrl=" + videoUrl + "]";
	}

}
