package com.sandbox.thewineris.lotteryentry;

import java.util.List;

public class BuyLotResponse {
	private LotteryEntry entry;
	private List<Integer> takenLots;

	public BuyLotResponse(LotteryEntry entry, List<Integer> takenLots) {
		this.entry = entry;
		this.takenLots = takenLots;
	}

	public LotteryEntry getEntry() {
		return entry;
	}

	public void setEntry(LotteryEntry entry) {
		this.entry = entry;
	}

	public List<Integer> getTakenLots() {
		return takenLots;
	}

	public void setTakenLots(List<Integer> takenLots) {
		this.takenLots = takenLots;
	}
}