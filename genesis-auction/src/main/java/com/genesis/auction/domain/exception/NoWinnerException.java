package com.genesis.auction.domain.exception;

public class NoWinnerException extends BusinessException {

	private static final long serialVersionUID = 1686332142580404514L;

	public NoWinnerException(String message) {
		super(message);
	}
	
}