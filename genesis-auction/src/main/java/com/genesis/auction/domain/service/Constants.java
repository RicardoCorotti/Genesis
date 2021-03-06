package com.genesis.auction.domain.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Constants {

	public static final BigDecimal bidFee = new BigDecimal(0.98).setScale(2, RoundingMode.CEILING);
	public static final int MAX_NUMBER_OF_BIDS_ALLOWED = 999;

}
