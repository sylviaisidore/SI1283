package com.toolrental.service;

import java.time.DayOfWeek;
import java.time.LocalDate;

import com.toolrental.model.Tool;

public class WeekdayChargeRule implements ChargeRule {

    private final HolidayService holidayService;

    public WeekdayChargeRule(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @Override
    public boolean isChargeDay(LocalDate date, Tool tool) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        boolean isWeekend = dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
        boolean isHoliday = holidayService.isHoliday(date);

        return !isWeekend && !isHoliday;
    }
}
