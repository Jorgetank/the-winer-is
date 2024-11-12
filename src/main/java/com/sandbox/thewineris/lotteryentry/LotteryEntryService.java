package com.sandbox.thewineris.lotteryentry;

import com.sandbox.thewineris.winelottery.WineLottery;
import com.sandbox.thewineris.winelottery.WineLotteryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LotteryEntryService {
	private final WineLotteryService wineLotteryService;
	private final LotteryEntryRepository lotteryEntryRepository;

	public LotteryEntryService(LotteryEntryRepository lotteryEntryRepository, WineLotteryService wineLotteryService) {
		this.lotteryEntryRepository = lotteryEntryRepository;
		this.wineLotteryService = wineLotteryService;
	}

	public  LotteryEntry saveLotteryEntry(LotteryEntry lotteryEntry) {
		return lotteryEntryRepository.save(lotteryEntry);
	}

	public boolean existsByWineLotteryAndLotNumber(WineLottery winelottery, int lotNumber) {
		return lotteryEntryRepository.existsByLotteryAndLotNumber(winelottery, lotNumber);
	}

	public List<LotteryEntry> findEntriesByLottery(WineLottery wineLottery) {
		return lotteryEntryRepository.findByLottery(wineLottery);
	}

	public List<Integer> findTakenLots(Long lotteryId) {
		var lottery = wineLotteryService.findWineLotteryById(lotteryId);
		if (lottery == null) {
			throw new RuntimeException("Lottery not found");
		}
		var entries = findEntriesByLottery(lottery);
		return entries.stream().map(LotteryEntry::getLotNumber).collect(Collectors.toList());

	}
}
