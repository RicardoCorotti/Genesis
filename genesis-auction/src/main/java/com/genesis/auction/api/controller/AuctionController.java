package com.genesis.auction.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.genesis.auction.domain.model.Auction;
import com.genesis.auction.domain.model.AuctionResult;
import com.genesis.auction.domain.service.AuctionService;

@RestController
@RequestMapping("/auctions")
public class AuctionController {

	@Autowired
	private AuctionService auctionService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Auction> add(@RequestBody Auction auction) {
			auction = auctionService.add();
			return ResponseEntity.ok(auction);
	}
	
	@GetMapping
	public ResponseEntity<Auction> list() {
		return ResponseEntity.ok(auctionService.get());
	}
	
	@GetMapping("/result")
	public ResponseEntity<AuctionResult> getResult() {
		return ResponseEntity.ok(auctionService.getResult());
	}
		
}
