package com.example.seatmanagement;


import com.example.booking_system.service.PaymentService;
import com.example.bookingsystem.Booking;
import com.example.bookingsystem.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private SeatService seatService;

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @PostMapping
    public Booking saveBooking(@RequestBody Booking booking) {
        boolean paymentSuccess = paymentService.processPayment(booking.getTotalPrice());
        if (paymentSuccess) {
            seatService.lockSeat(booking.getSeatId());
            return bookingService.saveBooking(booking);
        } else {
            seatService.releaseSeat(booking.getSeatId());
            throw new RuntimeException("Payment failed");
        }
    }
}
