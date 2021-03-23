package com.genesis.auction.domain.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genesis.auction.domain.exception.MaximumNumberOfBidsReachedException;
import com.genesis.auction.domain.model.Auction;
import com.genesis.auction.domain.model.Bid;

@Service
public class BidService {

	@Autowired 
	Auction auction;
	
	public Bid add(Bid bid) {
		
		ConcurrentSkipListMap<BigDecimal, List<Bid>> bids = auction.getBids();

		if (auction.getNumberOfBids() >= Constants.MAX_NUMBER_OF_BIDS_ALLOWED) {
			throw new MaximumNumberOfBidsReachedException("Maximum number of bids allowed reached.");
		}
		
		List<Bid> bidsWithThisValue = bids.get(bid.getBidValue());
		
		if (bidsWithThisValue == null) {
			//There is no bid with this same value. 
			bidsWithThisValue = new ArrayList<Bid>();
		} 
		bidsWithThisValue.add(bid);
		bids.put(bid.getBidValue(), bidsWithThisValue);		
		
		auction.setNumberOfBids(auction.getNumberOfBids() + 1);
		auction.setTotalCollection(auction.getTotalCollection().add(Constants.bidFee));
		return bid;
				
	}
	
	
}
