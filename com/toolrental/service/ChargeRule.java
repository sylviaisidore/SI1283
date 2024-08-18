package com.toolrental.service;

import java.time.LocalDate;

import com.toolrental.model.Tool;

public interface ChargeRule {
    boolean isChargeDay(LocalDate date, Tool tool);
}