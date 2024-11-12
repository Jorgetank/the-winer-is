package com.sandbox.thewineris.winner;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WinnerService {
	private final WinnerRepository winnerRepository;

	public WinnerService(WinnerRepository winnerRepository) {
		this.winnerRepository = winnerRepository;
	}

	public Winner saveWinner(Winner winner) {
		return winnerRepository.save(winner);
	}

	public List<WinnerDTO> getWinners(Long lotteryId) {
		var winners = winnerRepository.findAllByLotteryId(lotteryId);
		return winners.stream().map(WinnerDTO::new).toList();
	}
}
