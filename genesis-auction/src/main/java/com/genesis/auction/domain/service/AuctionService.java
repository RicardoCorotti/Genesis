package com.genesis.auction.domain.service;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.NavigableSet;
import java.util.concurrent.ConcurrentSkipListMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genesis.auction.domain.exception.NoWinnerException;
import com.genesis.auction.domain.model.Auction;
import com.genesis.auction.domain.model.AuctionResult;
import com.genesis.auction.domain.model.Bid;

@Service
public class AuctionService {

	@Autowired 
	Auction auction;
	
	@Autowired 
	AuctionResult result;
	
	public Auction add() {
		auction.setBids(new ConcurrentSkipListMap<BigDecimal, List<Bid>>());
		auction.setNumberOfBids(0);
		auction.setTotalCollection(new BigDecimal(0));
		
		return auction;
	}
	
	public Auction get() {
		return auction;		
	}
	
	public AuctionResult getResult() {

		Iterator<ConcurrentSkipListMap.Entry<BigDecimal, List<Bid>>> iterator = auction.getBids().entrySet().iterator();
		
		boolean winnerFound = false;
		
		while (iterator.hasNext()) {
			
			ConcurrentSkipListMap.Entry<BigDecimal, List<Bid>> entry = iterator.next(); 
			BigDecimal bidValue = (BigDecimal) entry.getKey();
			List<Bid> bids = (List) entry.getValue();
			
			if (bids.size() == 1) {
				winnerFound = true;
				result.setWinner(bids.get(0).getBidderName());
				result.setBidValue(bids.get(0).getBidValue());
				result.setTotalCollection(auction.getTotalCollection());
				break;
			}
		}
		
		if (!winnerFound) {
			throw new NoWinnerException("It was not possible to determine the winner!");
		}
		
		return result;
		

	}
	
}