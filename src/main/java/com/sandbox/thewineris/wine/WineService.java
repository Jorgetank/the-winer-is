package com.sandbox.thewineris.wine;

import com.sandbox.thewineris.winelottery.WineLottery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WineService {
	private final WineRepository wineRepository;

	public WineService(WineRepository wineRepository) {
		this.wineRepository = wineRepository;
	}

	public Wine addWine(Wine wine) {
		return wineRepository.save(wine);
	}

	public void saveWine(Wine wine) {
		wineRepository.save(wine);
	}

	public List<Wine> findPrizesByLottery(WineLottery wineLottery) {
		return wineRepository.findByLotteryOrderByValueAsc(wineLottery);
	}

	public List<Wine> findWinesForLottery() {
		var wines = wineRepository.findByLotteryIsNullOrderByValueAsc();
		if (wines.isEmpty()) {
			throw new RuntimeException("No wines found for lottery");
		}
		var selectedWines = new ArrayList<Wine>();
		var totalValue = 0;

		for (Wine wine : wines) {
			if (totalValue + wine.getValue() <= 1000) {
				selectedWines.add(wine);
				totalValue += wine.getValue();
				// TODO: Burde man tillate at det loddes ut vin for litt mer enn 1000?
			} else {
				break;
			}
		}
		return selectedWines;
	}
}
