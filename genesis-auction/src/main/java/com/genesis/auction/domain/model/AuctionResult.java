package com.genesis.auction.domain.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class AuctionResult {
	
	private String winner;
	private BigDecimal bidValue;
	private BigDecimal totalCollection;
		
}
