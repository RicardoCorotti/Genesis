package com.genesis.auction.domain.exception;


public class MaximumNumberOfBidsReachedException extends BusinessException {

	private static final long serialVersionUID = 3685908781863937279L;

	public MaximumNumberOfBidsReachedException(String message) {
		super(message);
	}
	
}
