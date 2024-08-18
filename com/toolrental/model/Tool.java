package com.toolrental.model;


public class Tool {
	
	private String toolCode;
	private String toolType;
	private String brand;
	private double dailyCharge;
	private boolean holidayCharge;
	private boolean weekendCharge;
	
	public Tool(String toolCode, String toolType, String brand, double dailyCharge, boolean holidayCharge,
			boolean weekendCharge) {
		super();
		this.toolCode = toolCode;
		this.toolType = toolType;
		this.brand = brand;
		this.dailyCharge = dailyCharge;
		this.holidayCharge = holidayCharge;
		this.weekendCharge = weekendCharge;
	}

	public String getToolCode() {
		return toolCode;
	}

	public String getToolType() {
		return toolType;
	}

	public String getBrand() {
		return brand;
	}

	public double getDailyCharge() {
		return dailyCharge;
	}

	public boolean isHolidayCharge() {
		return holidayCharge;
	}

	public boolean isWeekendCharge() {
		return weekendCharge;
	}
	
	

}
