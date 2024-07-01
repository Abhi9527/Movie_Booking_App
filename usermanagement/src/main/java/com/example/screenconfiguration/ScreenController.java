package com.example.screenconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/screens")
public class ScreenController {
    @Autowired
    private ScreenService screenService;

    @GetMapping
    public ResponseEntity<List<Screen>> getAllScreens() {
        List<Screen> screens = screenService.getAllScreens();
        return ResponseEntity.ok().body(screens);
    }

    @GetMapping("/{screenId}/seats")
    public ResponseEntity<List<Seat>> getAvailableSeats(@PathVariable Long screenId) {
        Screen screen = screenService.getScreenById(screenId);
        if (screen == null) {
            return ResponseEntity.notFound().build();
        }
        List<Seat> availableSeats = screenService.getAvailableSeats(screen);
        return ResponseEntity.ok().body(availableSeats);
    }

    // Other endpoints for booking, etc.
}
