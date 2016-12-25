package com.asphalt8.entity;

public class CarIntroduction extends AbstractCar {

	private String description;
	private String appearances1;
	private String appearances2;
	private String appearances3;
	private String design1;
	private String design2;
	private String design3;

	public CarIntroduction() {
		super();
		this.description = "";
		this.appearances1 = "";
		this.appearances2 = "";
		this.appearances3 = "";
		this.design1 = "";
		this.design2 = "";
		this.design3 = "";
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAppearances1() {
		return appearances1;
	}

	public void setAppearances1(String appearances1) {
		this.appearances1 = appearances1;
	}

	public String getAppearances2() {
		return appearances2;
	}

	public void setAppearances2(String appearances2) {
		this.appearances2 = appearances2;
	}

	public String getAppearances3() {
		return appearances3;
	}

	public void setAppearances3(String appearances3) {
		this.appearances3 = appearances3;
	}

	public String getDesign1() {
		return design1;
	}

	public void setDesign1(String design1) {
		this.design1 = design1;
	}

	public String getDesign2() {
		return design2;
	}

	public void setDesign2(String design2) {
		this.design2 = design2;
	}

	public String getDesign3() {
		return design3;
	}

	public void setDesign3(String design3) {
		this.design3 = design3;
	}

	@Override
	public String toString() {
		return "CarIntroduction [carId=" + carId + ", carName=" + carName
				+ ", description=" + description + ", appearances1="
				+ appearances1 + ", appearances2=" + appearances2
				+ ", appearances3=" + appearances3 + ", design1=" + design1
				+ ", design2=" + design2 + ", design3=" + design3 + "]";
	}

}
