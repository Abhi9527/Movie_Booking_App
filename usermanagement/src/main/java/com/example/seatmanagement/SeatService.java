package com.example.seatmanagement;


import com.example.bookingsystem.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SeatService {
    @Autowired
    private SeatRepository seatRepository;

    private static final long LOCK_DURATION = 300000; // 5 minutes in milliseconds

    public List<com.example.bookingsystem.Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    public com.example.bookingsystem.Seat getSeatById(Long id) {
        return seatRepository.findById(id).orElse(null);
    }

    public Seat saveSeat(Seat seat) {
        return seatRepository.save(seat);
    }

    public synchronized boolean lockSeat(Long seatId) {
        com.example.bookingsystem.Seat seat = seatRepository.findById(seatId).orElse(null);
        if (seat != null && !seat.isBooked() && !seat.isLocked()) {
            seat.setLocked(true);
            seat.setLockTime(new Date());
            seatRepository.save(seat);
            return true;
        }
        return false;
    }

    public synchronized void releaseSeat(Long seatId) {
        com.example.bookingsystem.Seat seat = seatRepository.findById(seatId).orElse(null);
        if (seat != null && seat.isLocked()) {
            seat.setLocked(false);
            seat.setLockTime(null);
            seatRepository.save(seat);
        }
    }

    public void releaseExpiredLocks() {
        List<com.example.bookingsystem.Seat> seats = seatRepository.findAll();
        Date now = new Date();
        for (com.example.bookingsystem.Seat seat : seats) {
            if (seat.isLocked() && now.getTime() - seat.getLockTime().getTime() > LOCK_DURATION) {
                seat.setLocked(false);
                seat.setLockTime(null);
                seatRepository.save(seat);
            }
        }
    }
}
