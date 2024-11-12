package com.sandbox.thewineris.winner;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WinnerRepository extends JpaRepository<Winner, Long> {
	List<Winner> findAllByLotteryId(Long lotteryId);
}
