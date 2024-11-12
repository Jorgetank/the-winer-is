package com.sandbox.thewineris.exceptions;

public class LotteryAlreadyDrawnException extends RuntimeException {
	public LotteryAlreadyDrawnException(String message) {
		super(message);
	}
}
