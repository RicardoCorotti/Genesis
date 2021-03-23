package com.genesis.auction.domain.exception;

public abstract class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 6711622197899717196L;

	public BusinessException(String message) {
		super(message);
	}
	
	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
