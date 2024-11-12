package com.sandbox.thewineris.exceptions;

public class LotteryNotFoundException extends RuntimeException {
	public LotteryNotFoundException(String message) {
		super(message);
	}
}
