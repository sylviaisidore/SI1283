package com.toolrental.service;

import java.time.LocalDate;

import com.toolrental.model.Tool;

public class HolidayChargeRule implements ChargeRule {
	
	private final HolidayService holidayService;

    public HolidayChargeRule(HolidayService holidayService) {
        this.holidayService = holidayService;
    }
    
    @Override
    public boolean isChargeDay(LocalDate date, Tool tool) {
        boolean isHoliday = holidayService.isHoliday(date); 
        return isHoliday && tool.isHolidayCharge();
    }

}
