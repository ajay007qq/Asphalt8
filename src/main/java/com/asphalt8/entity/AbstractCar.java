package com.asphalt8.entity;

public abstract class AbstractCar {

	protected int carId;
	protected String carName;
	protected String shortCarName;

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getShortCarName() {
		this.shortCarName = this.carName.replace(" ", "");
		return shortCarName;
	}

	public void setShortCarName(String shortCarName) {
		this.shortCarName = shortCarName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + carId;
		result = prime * result + ((carName == null) ? 0 : carName.hashCode());
		result = prime * result
				+ ((shortCarName == null) ? 0 : shortCarName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractCar other = (AbstractCar) obj;
		if (carId != other.carId)
			return false;
		if (carName == null) {
			if (other.carName != null)
				return false;
		} else if (!carName.equals(other.carName))
			return false;
		if (shortCarName == null) {
			if (other.shortCarName != null)
				return false;
		} else if (!shortCarName.equals(other.shortCarName))
			return false;
		return true;
	}

}
