package com.sandbox.thewineris.wine;

import com.sandbox.thewineris.winelottery.WineLottery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WineRepository extends JpaRepository<Wine, Long> {
	List<Wine> findByLotteryOrderByValueAsc(WineLottery wineLottery);

	List<Wine> findByLotteryIsNullOrderByValueAsc();
}