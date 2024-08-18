package com.toolrental.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

public class HolidayService {

    private final Set<LocalDate> holidays = new HashSet<>();

    public HolidayService() {
        //holidays.add(LocalDate.of(2024, 7, 4)); // Independence Day
        // Load more holidays from a config file or database
    }

    public boolean isHoliday(LocalDate date) {
        return holidays.contains(date) || isIndependenceDay(date) || isLaborDay(date) ;
    }

    public void addHoliday(LocalDate date) {
        holidays.add(date);
    }
    
    public boolean isIndependenceDay(LocalDate date) {
		//If independence day does not fall on weekend
		if (date.getMonth() == Month.JULY && date.getDayOfMonth() == 4 && 
				 !(date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY)) {
			return true;
		}
		
		//If independence day falls on Saturday check the Friday before and if it falls on Sunday check the Monday after
		if (date.getMonth() == Month.JULY) {
			int day = date.getDayOfMonth();
			return (day == 3 && date.getDayOfWeek() == DayOfWeek.FRIDAY) || (day == 5 && date.getDayOfWeek() == DayOfWeek.MONDAY);
		}

		return false;
	}

	public boolean isLaborDay(LocalDate date) {
		return date.getMonth() == Month.SEPTEMBER && date.getDayOfWeek() == DayOfWeek.MONDAY && date.getDayOfMonth() <= 7;
	}

}
