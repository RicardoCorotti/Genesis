package com.genesis.auction.domain.model;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class Bid {

	
	@NotBlank
	private String bidderName;
		
	@Positive
	@Digits(integer = 9, fraction = 2)
	private BigDecimal bidValue;
	
	public String getBidderName() {
		return bidderName;
	}
	public void setBidderName(String bidderName) {
		this.bidderName = bidderName;
	}
	public BigDecimal getBidValue() {
		return bidValue;
	}
	public void setBidValue(BigDecimal bidValue) {
		this.bidValue = bidValue;
	}
	
	
	
	
	
}
