package com.hotelcheckin.backend.repository;

import com.hotelcheckin.backend.model.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface CheckInRepository extends JpaRepository<CheckIn, Long> {
    List<CheckIn> findByExitDateBefore(LocalDateTime now); // People who have left
    List<CheckIn> findByExitDateAfter(LocalDateTime now);  // People still in the hotel
}