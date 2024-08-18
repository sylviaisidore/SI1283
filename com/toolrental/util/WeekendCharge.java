package com.toolrental.util;

public enum WeekendCharge {
	TRUE(true),
    FALSE(false);

    private final boolean value;

    WeekendCharge(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }
}
