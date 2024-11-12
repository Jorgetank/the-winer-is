package com.sandbox.thewineris.lotteryentry;

import com.sandbox.thewineris.winelottery.WineLottery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LotteryEntryRepository extends JpaRepository<LotteryEntry, Long> {

	boolean existsByLotteryAndLotNumber(WineLottery winelottery, int lotNumber);

	List<LotteryEntry> findByLottery(WineLottery wineLottery);
}