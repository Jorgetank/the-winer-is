package com.sandbox.thewineris.wine;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wine")
public class WineController {

	private final WineService wineService;

	public WineController(WineService wineService) {
		this.wineService = wineService;
	}

	@GetMapping(value = "/ping", produces = "text/plain")
	public ResponseEntity<String> ping() {
		return ResponseEntity.ok("pong");
	}

	@PostMapping
	public ResponseEntity<Wine> addWine(@RequestBody Wine wine) {
		Wine savedWine = wineService.addWine(wine);
		return new ResponseEntity<>(savedWine, HttpStatus.CREATED);
	}
}
