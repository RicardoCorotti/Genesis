package com.genesis.auction.domain.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;

import org.springframework.stereotype.Component;

@Component
public class Auction {
	
	private int numberOfBids;
	private ConcurrentSkipListMap<BigDecimal, List<Bid>> bids;
	private BigDecimal totalCollection;
	
	public int getNumberOfBids() {
		return numberOfBids;
	}
	public void setNumberOfBids(int numberOfBids) {
		this.numberOfBids = numberOfBids;
	}
	
	public ConcurrentSkipListMap<BigDecimal, List<Bid>> getBids() {

		if (bids == null) {
			this.bids = new ConcurrentSkipListMap<BigDecimal, List<Bid>>();
			this.numberOfBids = 0;
			this.totalCollection = new BigDecimal(0);
		}
		return bids;
	}
	
	public void setBids(ConcurrentSkipListMap<BigDecimal, List<Bid>> bids) {
		this.bids = bids;
	}
	
	public BigDecimal getTotalCollection() {
		return totalCollection;
	}
	public void setTotalCollection(BigDecimal totalCollection) {
		this.totalCollection = totalCollection;
	}
		

	
		
}
