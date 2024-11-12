package com.sandbox.thewineris.winelottery;

import com.sandbox.thewineris.lotteryentry.LotteryEntry;
import com.sandbox.thewineris.wine.Wine;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class WineLottery {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalDate drawDate;

	@Column(nullable = false)
	private boolean isFinished = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDrawDate() {
		return drawDate;
	}

	public void setDrawDate(LocalDate drawDate) {
		this.drawDate = drawDate;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean finished) {
		isFinished = finished;
	}
}
