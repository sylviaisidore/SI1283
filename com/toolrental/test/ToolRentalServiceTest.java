package com.toolrental.test;

import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDate;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.toolrental.exception.InvalidDiscountException;
import com.toolrental.exception.InvalidRentalPeriodException;
import com.toolrental.exception.ToolNotFoundException;
import com.toolrental.model.ToolRentalAgreement;
import com.toolrental.service.ToolRentalService;
import com.toolrental.util.ValidationMessages;

class ToolRentalServiceTest {
	
	private ToolRentalService toolRentalService;
	
	@BeforeEach
	void setUp() throws Exception {
		toolRentalService = new ToolRentalService();
	}

	@Test
	public void checkoutJAKR_test1() {
		assertThrows(InvalidDiscountException.class, () -> {
			toolRentalService.checkout("JAKR", 5, 101, LocalDate.of(2015, 9, 3));
			});
		
	}
	
	@Test
	public void checkoutJAKR_invalidRentalPeriod() {
		assertThrows(InvalidRentalPeriodException.class, () -> {
			toolRentalService.checkout("JAKR", 0, 5, LocalDate.of(2015, 9, 3));
			});
		
	}
	
	@Test
	public void checkout_invalidRentalToolCode() {
		Exception exception = assertThrows(ToolNotFoundException.class, () -> {
			toolRentalService.checkout("TESTCODE", 1, 2, LocalDate.of(2015, 9, 3));
			});
		assertEquals(ValidationMessages.INVALID_TOOL_CODE, exception.getMessage());

	}
	
	@Test
	public void checkoutJAKD_invalidDiscount() {
		assertThrows(InvalidDiscountException.class, () -> {
			toolRentalService.checkout("JAKD", 1, -15, LocalDate.of(2015, 9, 3));
			});
		
	}
	
	@Test
	public void checkoutCHNS_invalidRentalPeriod() {
		assertThrows(InvalidRentalPeriodException.class, () -> {
			toolRentalService.checkout("JAKD", -8, 15, LocalDate.of(2015, 9, 3));
			});
		
	}
	
	@Test
	public void checkoutLADW_test2() {
		ToolRentalAgreement agreement = toolRentalService.checkout("LADW", 3, 10, LocalDate.of(2020, 7, 2));
		System.out.println("Rental Agreement:\n"+agreement.toString());
		assertEquals("LADW", agreement.getToolCode());
		assertEquals("Ladder", agreement.getToolType());
		assertEquals("Werner", agreement.getBrand());
		assertEquals(3, agreement.getRentalDays());
		assertEquals(LocalDate.of(2020, 7, 2), agreement.getCheckoutDate());
		assertEquals(LocalDate.of(2020, 7, 5), agreement.getDueDate());
		assertEquals(1.99, agreement.getDailyRentalCharge());
		assertEquals(2, agreement.getChargeDays());
		assertEquals(toolRentalService.convertToBigDecimal(3.98), agreement.getPreDiscountCharge());
		assertEquals(10, agreement.getDiscountPercent());
		assertEquals(toolRentalService.convertToBigDecimal(0.40), agreement.getDiscountAmount());
		assertEquals(toolRentalService.convertToBigDecimal(3.58), agreement.getFinalCharge());
	}
	
	@Test
	public void checkoutCHNS_test3() {
		ToolRentalAgreement agreement = toolRentalService.checkout("CHNS", 5, 25, LocalDate.of(2015, 7, 2));
		System.out.println("Rental Agreement:\n"+agreement.toString());
		assertEquals("CHNS", agreement.getToolCode());
		assertEquals("Chainsaw", agreement.getToolType());
		assertEquals("Stihl", agreement.getBrand());
		assertEquals(5, agreement.getRentalDays());
		assertEquals(LocalDate.of(2015, 7, 2), agreement.getCheckoutDate());
		assertEquals(LocalDate.of(2015, 7, 7), agreement.getDueDate());
		assertEquals(1.49, agreement.getDailyRentalCharge());
		assertEquals(3, agreement.getChargeDays());
		assertEquals(toolRentalService.convertToBigDecimal(4.47), agreement.getPreDiscountCharge());
		assertEquals(25, agreement.getDiscountPercent());
		assertEquals(toolRentalService.convertToBigDecimal(1.12), agreement.getDiscountAmount());
		assertEquals(toolRentalService.convertToBigDecimal(3.35), agreement.getFinalCharge());
	}

	@Test
	public void checkoutJAKD_test4() {
		ToolRentalAgreement agreement = toolRentalService.checkout("JAKD", 6, 0, LocalDate.of(2015, 9, 3));
		System.out.println("Rental Agreement:\n"+agreement.toString());
		assertEquals("JAKD", agreement.getToolCode());
		assertEquals("Jackhammer", agreement.getToolType());
		assertEquals("DeWalt", agreement.getBrand());
		assertEquals(6, agreement.getRentalDays());
		assertEquals(LocalDate.of(2015, 9, 3), agreement.getCheckoutDate());
		assertEquals(LocalDate.of(2015, 9, 9), agreement.getDueDate());
		assertEquals(2.99, agreement.getDailyRentalCharge());
		assertEquals(3, agreement.getChargeDays());
		assertEquals(toolRentalService.convertToBigDecimal(8.97), agreement.getPreDiscountCharge());
		assertEquals(0, agreement.getDiscountPercent());
		assertEquals(toolRentalService.convertToBigDecimal(0.00), agreement.getDiscountAmount());
		assertEquals(toolRentalService.convertToBigDecimal(8.97), agreement.getFinalCharge());
	}

	@Test
	public void checkoutJAKR_test5() {
		ToolRentalAgreement agreement = toolRentalService.checkout("JAKR", 9, 0, LocalDate.of(2015, 7, 2));
		System.out.println("Rental Agreement:\n"+agreement.toString());
		assertEquals("JAKR", agreement.getToolCode());
		assertEquals("Jackhammer", agreement.getToolType());
		assertEquals("Ridgid", agreement.getBrand());
		assertEquals(9, agreement.getRentalDays());
		assertEquals(LocalDate.of(2015, 7, 2), agreement.getCheckoutDate());
		assertEquals(LocalDate.of(2015, 7, 11), agreement.getDueDate());
		assertEquals(2.99, agreement.getDailyRentalCharge());
		assertEquals(5, agreement.getChargeDays());
		assertEquals(toolRentalService.convertToBigDecimal(14.95), agreement.getPreDiscountCharge());
		assertEquals(0, agreement.getDiscountPercent());
		assertEquals(toolRentalService.convertToBigDecimal(0.00), agreement.getDiscountAmount());
		assertEquals(toolRentalService.convertToBigDecimal(14.95), agreement.getFinalCharge());
	}

	@Test
	public void checkoutJAKR_test6() {
		ToolRentalAgreement agreement = toolRentalService.checkout("JAKR", 4, 50, LocalDate.of(2020, 7, 2));
		System.out.println("Rental Agreement:\n"+agreement.toString());
		assertEquals("JAKR", agreement.getToolCode());
		assertEquals("Jackhammer", agreement.getToolType());
		assertEquals("Ridgid", agreement.getBrand());
		assertEquals(4, agreement.getRentalDays());
		assertEquals(LocalDate.of(2020, 7, 2), agreement.getCheckoutDate());
		assertEquals(LocalDate.of(2020, 7, 6), agreement.getDueDate());
		assertEquals(2.99, agreement.getDailyRentalCharge());
		assertEquals(1, agreement.getChargeDays());
		assertEquals(toolRentalService.convertToBigDecimal(2.99), agreement.getPreDiscountCharge());
		assertEquals(50, agreement.getDiscountPercent());
		assertEquals(toolRentalService.convertToBigDecimal(1.50), agreement.getDiscountAmount());
		assertEquals(toolRentalService.convertToBigDecimal(1.49), agreement.getFinalCharge());
	}
	
	@Test
	public void checkoutJAKR_withNoChargeDays() {
		ToolRentalAgreement agreement = toolRentalService.checkout("JAKR", 3, 50, LocalDate.of(2015, 9, 4));
		System.out.println("Rental Agreement:\n"+agreement.toString());
		assertEquals("JAKR", agreement.getToolCode());
		assertEquals("Jackhammer", agreement.getToolType());
		assertEquals("Ridgid", agreement.getBrand());
		assertEquals(3, agreement.getRentalDays());
		assertEquals(LocalDate.of(2015, 9, 4), agreement.getCheckoutDate());
		assertEquals(LocalDate.of(2015, 9, 7), agreement.getDueDate());
		assertEquals(2.99, agreement.getDailyRentalCharge());
		assertEquals(0, agreement.getChargeDays());
		assertEquals(toolRentalService.convertToBigDecimal(0.00), agreement.getPreDiscountCharge());
		assertEquals(50, agreement.getDiscountPercent());
		assertEquals(toolRentalService.convertToBigDecimal(0.00), agreement.getDiscountAmount());
		assertEquals(toolRentalService.convertToBigDecimal(0.00), agreement.getFinalCharge());
	}
	
	@Test
	public void checkoutJAKR_withDiscount_independenceDayObsMonday() {
		ToolRentalAgreement agreement = toolRentalService.checkout("JAKR", 4, 50, LocalDate.of(2021, 7, 2));
		System.out.println("Rental Agreement:\n"+agreement.toString());
		assertEquals("JAKR", agreement.getToolCode());
		assertEquals("Jackhammer", agreement.getToolType());
		assertEquals("Ridgid", agreement.getBrand());
		assertEquals(4, agreement.getRentalDays());
		assertEquals(LocalDate.of(2021, 7, 2), agreement.getCheckoutDate());
		assertEquals(LocalDate.of(2021, 7, 6), agreement.getDueDate());
		assertEquals(2.99, agreement.getDailyRentalCharge());
		assertEquals(1, agreement.getChargeDays());
		assertEquals(toolRentalService.convertToBigDecimal(2.99), agreement.getPreDiscountCharge());
		assertEquals(50, agreement.getDiscountPercent());
		assertEquals(toolRentalService.convertToBigDecimal(1.50), agreement.getDiscountAmount());
		assertEquals(toolRentalService.convertToBigDecimal(1.49), agreement.getFinalCharge());
	}
	
	@Test
	public void checkoutJAKR_withDiscount_allDaysCharged() {
		ToolRentalAgreement agreement = toolRentalService.checkout("LADW", 5, 50, LocalDate.of(2021, 7, 5));
		System.out.println("Rental Agreement:\n"+agreement.toString());
		assertEquals("LADW", agreement.getToolCode());
		assertEquals("Ladder", agreement.getToolType());
		assertEquals("Werner", agreement.getBrand());
		assertEquals(5, agreement.getRentalDays());
		assertEquals(LocalDate.of(2021, 7, 5), agreement.getCheckoutDate());
		assertEquals(LocalDate.of(2021, 7, 10), agreement.getDueDate());
		assertEquals(1.99, agreement.getDailyRentalCharge());
		assertEquals(5, agreement.getChargeDays());
		assertEquals(toolRentalService.convertToBigDecimal(9.95), agreement.getPreDiscountCharge());
		assertEquals(50, agreement.getDiscountPercent());
		assertEquals(toolRentalService.convertToBigDecimal(4.98), agreement.getDiscountAmount());
		assertEquals(toolRentalService.convertToBigDecimal(4.97), agreement.getFinalCharge());
	}

}
