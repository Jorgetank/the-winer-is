package com.sandbox.thewineris.winelottery;

import org.springframework.stereotype.Service;

@Service
public class WineLotteryService {
	WineLotteryRepository wineLotteryRepository;

	public WineLotteryService(WineLotteryRepository wineLotteryRepository) {
		this.wineLotteryRepository = wineLotteryRepository;
	}

	public WineLottery saveWineLottery(WineLottery wineLottery) {
		return wineLotteryRepository.save(wineLottery);
	}

	public WineLottery findWineLotteryById(Long lotteryId) {
		return wineLotteryRepository.findById(lotteryId).orElse(null);
	}

	public void updateWineLottery(WineLottery wineLottery) {
		wineLotteryRepository.save(wineLottery);
	}
}
