package com.sandbox.thewineris.lottery;

import com.sandbox.thewineris.wine.Wine;
import com.sandbox.thewineris.winelottery.WineLottery;
import com.sandbox.thewineris.winner.Winner;
import com.sandbox.thewineris.winner.WinnerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/lottery")
public class LotteryController {
	private final LotteryService lotteryService;

	public LotteryController(LotteryService lotteryService) {
		this.lotteryService = lotteryService;
	}

	@PostMapping("/create")
	public ResponseEntity<WineLottery> createLottery(@RequestBody CreateLotteryRequest request) {
		var lottery = lotteryService.createNewLottery(request.getDrawDate());
		return ResponseEntity.ok(lottery);
	}

	@PostMapping("/{lotteryId}/draw")
	public ResponseEntity<List<WinnerDTO>> drawWinners(@PathVariable Long lotteryId) {
		try {
			List<WinnerDTO> winners = lotteryService.drawWinner(lotteryId);
			return ResponseEntity.ok(winners);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
