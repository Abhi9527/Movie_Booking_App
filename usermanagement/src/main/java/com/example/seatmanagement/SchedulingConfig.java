package com.example.seatmanagement;

import com.example.booking_system.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulingConfig {

    @Autowired
    private SeatService seatService;

    @Scheduled(fixedRate = 60000) // Run every 1 minute
    public void releaseExpiredLocks() {
        seatService.releaseExpiredLocks();
    }
}
