package com.genesis.auction.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.genesis.auction.domain.model.Auction;
import com.genesis.auction.domain.model.Bid;
import com.genesis.auction.domain.service.BidService;

@RestController
@RequestMapping("/bids")
public class BidController {

	@Autowired
	private BidService bidService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Bid> add(@RequestBody @Valid Bid bid) {
		bid = bidService.add(bid);
		return new ResponseEntity<Bid>(bid, HttpStatus.CREATED);
	}
	
}
