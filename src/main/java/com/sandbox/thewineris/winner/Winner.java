package com.sandbox.thewineris.winner;

import com.sandbox.thewineris.lotteryentry.LotteryEntry;
import com.sandbox.thewineris.wine.Wine;
import com.sandbox.thewineris.winelottery.WineLottery;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Winner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	private WineLottery lottery;

	@OneToOne
	private LotteryEntry entry;

	@OneToOne
	private Wine wine;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public WineLottery getLottery() {
		return lottery;
	}

	public void setLottery(WineLottery lottery) {
		this.lottery = lottery;
	}

	public LotteryEntry getEntry() {
		return entry;
	}

	public void setEntry(LotteryEntry entry) {
		this.entry = entry;
	}

	public Wine getWine() {
		return wine;
	}

	public void setWine(Wine wine) {
		this.wine = wine;
	}
}
