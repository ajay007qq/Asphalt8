package com.asphalt8.entity;

public class CarMedia extends AbstractCar {

	private String fileName;
	private String mediaType;

	public CarMedia() {
		super();
	}

	public CarMedia(Integer carId, String carName, String fileName,
			String mediaType) {
		this.carId = carId;
		this.carName = carName;
		this.fileName = fileName;
		this.mediaType = mediaType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	@Override
	public String toString() {
		return "CarMedia [fileName=" + fileName + ", mediaType=" + mediaType
				+ ", carId=" + carId + ", carName=" + carName + "]";
	}

}
