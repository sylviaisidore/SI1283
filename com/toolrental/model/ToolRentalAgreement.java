package com.toolrental.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ToolRentalAgreement {
	
	private String toolCode;
	private String toolType;
	private String brand;
	private int rentalDays;
	private LocalDate checkoutDate;
	private LocalDate dueDate;
	private double dailyRentalCharge;
	private int chargeDays;
	private BigDecimal preDiscountCharge;
	private int discountPercent;
	private BigDecimal discountAmount;
	private BigDecimal finalCharge;
	

	public String getToolCode() {
		return toolCode;
	}



	public String getToolType() {
		return toolType;
	}



	public String getBrand() {
		return brand;
	}



	public int getRentalDays() {
		return rentalDays;
	}



	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}



	public LocalDate getDueDate() {
		return dueDate;
	}



	public double getDailyRentalCharge() {
		return dailyRentalCharge;
	}



	public int getChargeDays() {
		return chargeDays;
	}



	public BigDecimal getPreDiscountCharge() {
		return preDiscountCharge;
	}



	public int getDiscountPercent() {
		return discountPercent;
	}



	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}



	public BigDecimal getFinalCharge() {
		return finalCharge;
	}

	public ToolRentalAgreement setToolCode(String toolCode) {
		this.toolCode = toolCode;
		return this;
	}



	public ToolRentalAgreement setToolType(String toolType) {
		this.toolType = toolType;
		return this;
	}



	public ToolRentalAgreement setBrand(String brand) {
		this.brand = brand;
		return this;
	}



	public ToolRentalAgreement setRentalDays(int rentalDays) {
		this.rentalDays = rentalDays;
		return this;
	}



	public ToolRentalAgreement setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
		return this;
	}



	public ToolRentalAgreement setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
		return this;
	}



	public ToolRentalAgreement setDailyRentalCharge(double dailyRentalCharge) {
		this.dailyRentalCharge = dailyRentalCharge;
		return this;
	}



	public ToolRentalAgreement setChargeDays(int chargeDays) {
		this.chargeDays = chargeDays;
		return this;
	}



	public ToolRentalAgreement setPreDiscountCharge(BigDecimal preDiscountCharge) {
		this.preDiscountCharge = preDiscountCharge;
		return this;
	}



	public ToolRentalAgreement setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
		return this;
	}



	public ToolRentalAgreement setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
		return this;
	}



	public ToolRentalAgreement setFinalCharge(BigDecimal finalCharge) {
		this.finalCharge = finalCharge;
		return this;
	}




	@Override
	public String toString() {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
		DecimalFormat currencyFormatter = new DecimalFormat("$#,##0.00");
		DecimalFormat percentFormatter = new DecimalFormat("0%");
		

		return String.format(
				"Tool code: %s\nTool type: %s\nBrand: %s\nRental days: %d\nCheckout date: %s\nDue date: %s\nDaily rental charge: %s\nCharge days: %d\nPre-discount charge: %s\nDiscount percent: %s\nDiscount amount: %s\nFinal charge: %s",
				toolCode, toolType, brand, rentalDays, checkoutDate.format(dateFormatter), dueDate.format(dateFormatter),
				currencyFormatter.format(dailyRentalCharge), chargeDays, currencyFormatter.format(preDiscountCharge),
				percentFormatter.format(discountPercent / 100.0), currencyFormatter.format(discountAmount), currencyFormatter.format(finalCharge)
				);
	}
	
	
	

}
