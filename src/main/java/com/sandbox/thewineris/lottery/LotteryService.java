package com.sandbox.thewineris.lottery;

import com.sandbox.thewineris.exceptions.InsufficientEntriesException;
import com.sandbox.thewineris.exceptions.InvalidLotNumber;
import com.sandbox.thewineris.exceptions.LotteryAlreadyDrawnException;
import com.sandbox.thewineris.exceptions.LotteryNotFoundException;
import com.sandbox.thewineris.exceptions.NoEntriesFoundException;
import com.sandbox.thewineris.exceptions.NoPrizesFoundException;
import com.sandbox.thewineris.lotteryentry.LotteryEntry;
import com.sandbox.thewineris.lotteryentry.LotteryEntryService;
import com.sandbox.thewineris.wine.Wine;
import com.sandbox.thewineris.wine.WineService;
import com.sandbox.thewineris.winelottery.WineLottery;
import com.sandbox.thewineris.winelottery.WineLotteryService;
import com.sandbox.thewineris.winner.Winner;
import com.sandbox.thewineris.winner.WinnerDTO;
import com.sandbox.thewineris.winner.WinnerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class LotteryService {
	private final LotteryEntryService lotteryEntryService;
	private final WineLotteryService wineLotteryService;
	private final WineService wineService;
	private final WinnerService winnerService;

	public LotteryService( LotteryEntryService lotteryEntryService, WineLotteryService wineLotteryService,
	                       WineService wineService, WinnerService winnerService) {
		this.lotteryEntryService = lotteryEntryService;
		this.wineLotteryService = wineLotteryService;
		this.wineService = wineService;
		this.winnerService = winnerService;
	}

	@Transactional
	public WineLottery createNewLottery(LocalDate drawDate) {
		WineLottery lottery = new WineLottery();
		lottery.setDrawDate(drawDate);
		lottery = wineLotteryService.saveWineLottery(lottery);

		var prizes = wineService.findWinesForLottery();

		for (Wine prize : prizes) {
			prize.setLottery(lottery);
			wineService.saveWine(prize);
		}
		return lottery;
	}

	public LotteryEntry buyLot(Long lotteryId, String userId, Integer lotNumber) {
		WineLottery wineLottery = wineLotteryService.findWineLotteryById(lotteryId);
		if (wineLottery == null) {
			throw new LotteryNotFoundException("Lottery not found");
		}

		if (lotNumber < 1 || lotNumber > 100) {
			throw new InvalidLotNumber("Lot number must be between 1 and 100");
		}

		boolean lotTaken = lotteryEntryService.existsByWineLotteryAndLotNumber(wineLottery, lotNumber);

		if (lotTaken) {
			throw new RuntimeException("Lot number %s already taken".formatted(lotNumber));
		}
		LotteryEntry lotteryEntry = new LotteryEntry();
		lotteryEntry.setLottery(wineLottery);
		lotteryEntry.setUserName(userId);
		lotteryEntry.setLotNumber(lotNumber);

		return lotteryEntryService.saveLotteryEntry(lotteryEntry);
	}

	@Transactional
	public List<WinnerDTO> drawWinner(long lotteryId) {
		WineLottery wineLottery = wineLotteryService.findWineLotteryById(lotteryId);
		if (wineLottery == null) {
			throw new LotteryNotFoundException("Lottery not found with id: " + lotteryId);
		}
		if (wineLottery.isFinished()) {
			throw new LotteryAlreadyDrawnException("Lottery already drawn for id: " + lotteryId);
		}
		List<LotteryEntry> entries = lotteryEntryService.findEntriesByLottery(wineLottery);
		List<Wine> prizes = wineService.findPrizesByLottery(wineLottery);

		if (entries == null || entries.isEmpty()) {
			throw new NoEntriesFoundException("No entries found for lottery id: " + lotteryId);
		}
		if (prizes == null || prizes.isEmpty()) {
			throw new NoPrizesFoundException("No prizes found for lottery id: " + lotteryId);
		}
		if (entries.size() < prizes.size()) {
			throw new InsufficientEntriesException("Not enough entries to draw all prizes for lottery id: " + lotteryId);
		}

		// TODO: Noe bedre enn shuffle for trekning?
		List<LotteryEntry> shuffledEntries = new ArrayList<>(entries);
		Collections.shuffle(shuffledEntries);
		List<WinnerDTO> results = new ArrayList<>();

		for (int i = 0; i < prizes.size(); i++) {
			Wine prize = prizes.get(i);
			LotteryEntry entry = entries.get(i);
			var winner = new Winner();
			winner.setLottery(wineLottery);
			winner.setEntry(entry);
			winner.setWine(prize);
			winnerService.saveWinner(winner);
			results.add(new WinnerDTO(winner));
		}

		wineLottery.setFinished(true);
		wineLotteryService.updateWineLottery(wineLottery);

		return results;
	}
}
