package com.toolrental.util;

public enum HolidayCharge {
	TRUE(true),
    FALSE(false);

    private final boolean value;

    HolidayCharge(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }
}
