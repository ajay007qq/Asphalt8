package com.asphalt8.entity;

/**
 * 
 * @author asphalt8 车辆entity类
 *
 */

public class Car extends AbstractCar {

	private String carCategory;
	private String vendor;
	private int price;

	public String getCarCategory() {
		return carCategory;
	}

	public void setCarCategory(String carCategory) {
		this.carCategory = carCategory;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Car [carCategory=" + carCategory + ", vendor=" + vendor
				+ ", price=" + price + ", carId=" + carId + ", carName="
				+ carName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((carCategory == null) ? 0 : carCategory.hashCode());
		result = prime * result + price;
		result = prime * result + ((vendor == null) ? 0 : vendor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (carCategory == null) {
			if (other.carCategory != null)
				return false;
		} else if (!carCategory.equals(other.carCategory))
			return false;
		if (price != other.price)
			return false;
		if (vendor == null) {
			if (other.vendor != null)
				return false;
		} else if (!vendor.equals(other.vendor))
			return false;
		return true;
	}

}
