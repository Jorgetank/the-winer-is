package com.sandbox.thewineris.lotteryentry;

import com.sandbox.thewineris.winelottery.WineLottery;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class LotteryEntry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private WineLottery lottery;

	@Column(nullable = false)
	private String userName;

	@Column(nullable = false)
	private Integer lotNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public WineLottery getLottery() {
		return lottery;
	}

	public void setLottery(WineLottery lottery) {
		this.lottery = lottery;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userId) {
		this.userName = userId;
	}

	public Integer getLotNumber() {
		return lotNumber;
	}

	public void setLotNumber(Integer lotNumber) {
		this.lotNumber = lotNumber;
	}
}