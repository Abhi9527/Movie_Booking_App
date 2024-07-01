package com.example.screenconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreenService {
    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private SeatRepository seatRepository;

    public List<Screen> getAllScreens() {
        return screenRepository.findAll();
    }

    public List<Seat> getAvailableSeats(Screen screen) {
        return seatRepository.findByScreenAndBookedFalse(screen);
    }


}
