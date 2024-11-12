package com.sandbox.thewineris.lottery;

import java.time.LocalDate;

public class CreateLotteryRequest {
	private LocalDate drawDate;

	public CreateLotteryRequest() {}

	public CreateLotteryRequest(LocalDate drawDate) {
		this.drawDate = drawDate;
	}

	public LocalDate getDrawDate() {
		return drawDate;
	}

	public void setDrawDate(LocalDate drawDate) {
		this.drawDate = drawDate;
	}
}