package com.sandbox.thewineris.winner;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/winner")
public class WinnerController {
	private final WinnerService winnerService;

	public WinnerController(WinnerService winnerService) {
		this.winnerService = winnerService;
	}

	@GetMapping("/winelottery/{lotteryId}")
	public String getWinners(@PathVariable Long lotteryId, Model model) {
		try {
			model.addAttribute("winners", winnerService.getWinners(lotteryId));
			model.addAttribute("lotteryId", lotteryId);
			return "winners";
		} catch (RuntimeException e) {
			model.addAttribute("error", e.getMessage());
			return "error";
		}
	}
}
