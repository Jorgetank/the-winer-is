package com.sandbox.thewineris.wine;

import com.sandbox.thewineris.winelottery.WineLottery;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Wine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "lottery_id", nullable = true)
	private WineLottery lottery;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer value;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}
