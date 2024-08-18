package com.toolrental.service;

import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import com.toolrental.exception.InvalidDiscountException;
import com.toolrental.exception.InvalidRentalPeriodException;
import com.toolrental.exception.ToolNotFoundException;
import com.toolrental.exception.ToolRentalServiceException;
import com.toolrental.model.Tool;
import com.toolrental.model.ToolRentalAgreement;
import com.toolrental.util.HolidayCharge;
import com.toolrental.util.ToolRentalConstants;
import com.toolrental.util.ValidationMessages;
import com.toolrental.util.WeekendCharge;

public class ToolRentalService {
	
	private Map<String, Tool> tools;
    private static final Logger logger = Logger.getLogger(ToolRentalService.class.getName());

	

	public ToolRentalService() {
		tools = new HashMap<>();
		//Data set for testing
		tools.put("CHNS", new Tool("CHNS", "Chainsaw", "Stihl", 1.49, HolidayCharge.TRUE.getValue(),  WeekendCharge.FALSE.getValue()));
		tools.put("LADW", new Tool("LADW", "Ladder", "Werner", 1.99,  HolidayCharge.FALSE.getValue(),  WeekendCharge.TRUE.getValue()));
		tools.put("JAKD", new Tool("JAKD", "Jackhammer", "DeWalt", 2.99,  HolidayCharge.FALSE.getValue(), WeekendCharge.FALSE.getValue()));
		tools.put("JAKR", new Tool("JAKR", "Jackhammer", "Ridgid", 2.99,  HolidayCharge.FALSE.getValue(), WeekendCharge.FALSE.getValue()));
	
	}
	
	//checkout a tool for renting
	public ToolRentalAgreement checkout(String toolCode, int rentalDays, int discountPercent, LocalDate checkoutDate) {
		try {
			Tool tool = Optional.ofNullable(tools)
			        .map(t -> t.get(toolCode))
			        .orElseThrow(() -> new ToolNotFoundException(ValidationMessages.INVALID_TOOL_CODE));
	
			if (rentalDays < ToolRentalConstants.ONE) {
				throw new InvalidRentalPeriodException(ValidationMessages.RENTAL_DAYS_OUT_OF_RANGE);
			}
			if (discountPercent < ToolRentalConstants.ZERO || discountPercent > ToolRentalConstants.HUNDRED) {
				throw new InvalidDiscountException(ValidationMessages.DISCOUNT_OUT_OF_RANGE);
			}
	
			
			LocalDate dueDate = checkoutDate.plusDays(rentalDays);
			int chargebleDays = calculateChargeableDays(checkoutDate, rentalDays, tool);
			BigDecimal preDiscountCharge = roundToCents(BigDecimal.valueOf(chargebleDays * tool.getDailyCharge()));
			BigDecimal discountAmount = preDiscountCharge.multiply(BigDecimal.valueOf(discountPercent)).divide(BigDecimal.valueOf(ToolRentalConstants.HUNDRED),ToolRentalConstants.ROUND_SCALE,ToolRentalConstants.ROUND_SCALE_TYPE);
			BigDecimal finalCharge = preDiscountCharge.subtract(discountAmount);
			
	
			var rentalAgreement = new ToolRentalAgreement()
			.setToolCode(tool.getToolCode())
			.setToolType(tool.getToolType())
			.setBrand(tool.getBrand())
			.setRentalDays(rentalDays)
			.setCheckoutDate(checkoutDate)
			.setDueDate(dueDate)
			.setDailyRentalCharge(tool.getDailyCharge())
			.setChargeDays(chargebleDays)
			.setPreDiscountCharge(preDiscountCharge)
			.setDiscountPercent(discountPercent)
			.setDiscountAmount(discountAmount)
			.setFinalCharge(finalCharge);
		
			return rentalAgreement;
		} catch (Exception e) {
	        logger.severe(e.getMessage());
	        throw new ToolRentalServiceException(ValidationMessages.CHECKOUT_FAILED, e);
	    }
		
	}
	
	//calculate the chargeable days for a tool based on weekends and holidays
	private int calculateChargeableDays(LocalDate startDate, int rentalDays, Tool tool) {
		int chargeDays = 0;
		LocalDate currDate = startDate;
		 while (rentalDays > 0) {
			 currDate = currDate.plusDays(1);
			  if (isChargeDay(currDate, tool)) {
				  chargeDays++;
			  }
			  rentalDays--;
		}

		
		return chargeDays;
	}
	
	//check if the day is charged
	private boolean isChargeDay(LocalDate date, Tool tool) {
		HolidayService holidayService = new HolidayService();
	    List<ChargeRule> rules = List.of(
	        new WeekendChargeRule(),
	        new HolidayChargeRule(holidayService),
	        new WeekdayChargeRule(holidayService)
	    );

	    return rules.stream().anyMatch(rule -> rule.isChargeDay(date, tool));
	}

	private BigDecimal roundToCents(BigDecimal amount) {
	
		return amount.setScale(ToolRentalConstants.ROUND_SCALE,ToolRentalConstants.ROUND_SCALE_TYPE);

	}
	
	public BigDecimal convertToBigDecimal(double value) {
		
		return BigDecimal.valueOf(value).setScale(ToolRentalConstants.ROUND_SCALE,ToolRentalConstants.ROUND_SCALE_TYPE);

	}


}
