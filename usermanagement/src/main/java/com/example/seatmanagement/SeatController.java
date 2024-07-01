package com.example.booking_system.controller;

import com.example.booking_system.entity.Seat;
import com.example.booking_system.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @GetMapping
    public List<Seat> getAllSeats() {
        return seatService.getAllSeats();
    }

    @GetMapping("/{id}")
    public Seat getSeatById(@PathVariable Long id) {
        return seatService.getSeatById(id);
    }

    @PostMapping
    public Seat saveSeat(@RequestBody Seat seat) {
        return seatService.saveSeat(seat);
    }

    @PostMapping("/{id}/lock")
    public boolean lockSeat(@PathVariable Long id) {
        return seatService.lockSeat(id);
    }

    @PostMapping("/{id}/release")
    public void releaseSeat(@PathVariable Long id) {
        seatService.releaseSeat(id);
    }
}
