package com.toolrental.service;

import java.time.DayOfWeek;
import java.time.LocalDate;

import com.toolrental.model.Tool;

public class WeekendChargeRule implements ChargeRule {
    @Override
    public boolean isChargeDay(LocalDate date, Tool tool) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) && tool.isWeekendCharge();
    }
}
