package com.example.screenconfiguration;

import com.example.screenconfiguration.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByScreenAndBookedFalse(Screen screen);
}
