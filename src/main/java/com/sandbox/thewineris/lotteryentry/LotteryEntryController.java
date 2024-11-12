package com.sandbox.thewineris.lotteryentry;

import com.sandbox.thewineris.lottery.LotteryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/entry")
public class LotteryEntryController {
	private final LotteryService lotteryService;
	private final LotteryEntryService lotteryEntryService;

	public LotteryEntryController(LotteryService lotteryService, LotteryEntryService lotteryEntryService) {
		this.lotteryService = lotteryService;
		this.lotteryEntryService = lotteryEntryService;
	}

	@PostMapping("/buy")
	public ResponseEntity<BuyLotResponse> buyLot(
			@RequestParam Long lotteryId,
			@RequestParam String userId,
			@RequestParam Integer lotNumber) {
		try {
			LotteryEntry entry = lotteryService.buyLot(lotteryId, userId, lotNumber);
			var takenLots = lotteryEntryService.findTakenLots(lotteryId);
			var response = new BuyLotResponse(entry, takenLots);
			return ResponseEntity.ok(response);
		} catch (RuntimeException e) {
			List<Integer> takenLots = lotteryEntryService.findTakenLots(lotteryId);
			var response = new BuyLotResponse(null, takenLots);
			return ResponseEntity.badRequest().body(response);
		}
	}
}
