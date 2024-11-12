package com.sandbox.thewineris.winner;

public class WinnerDTO {
	private final String userName;
	private final String wineName;
	private final String wineValue;
	private final String lotteryId;
	private final String lotteryDate;

	public WinnerDTO(Winner winner) {
		this.userName = winner.getEntry().getUserName();
		this.wineName = winner.getWine().getName();
		this.wineValue = String.valueOf(winner.getWine().getValue());
		this.lotteryId = String.valueOf(winner.getLottery().getId());
		this.lotteryDate = winner.getLottery().getDrawDate().toString();
	}

	public String getUserName() {
		return userName;
	}

	public String getWineName() {
		return wineName;
	}

	public String getWineValue() {
		return wineValue;
	}

	public String getLotteryId() {
		return lotteryId;
	}

	public String getLotteryDate() {
		return lotteryDate;
	}
}
