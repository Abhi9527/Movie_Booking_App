package com.example.Screenmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/screens")
public class AdminScreenController {
    @Autowired
    private ScreenService screenService;

    @GetMapping
    public ResponseEntity<List<Screen>> getAllScreens() {
        List<Screen> screens = screenService.getAllScreens();
        return ResponseEntity.ok().body(screens);
    }

    @GetMapping("/{screenId}")
    public ResponseEntity<Screen> getScreenById(@PathVariable Long screenId) {
        Screen screen = screenService.getScreenById(screenId);
        if (screen == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(screen);
    }

    @PostMapping
    public ResponseEntity<Screen> createScreen(@RequestBody Screen screen) {
        Screen createdScreen = screenService.createScreen(screen);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdScreen);
    }

    @PutMapping("/{screenId}")
    public ResponseEntity<Screen> updateScreen(@PathVariable Long screenId, @RequestBody Screen screenDetails) {
        Screen updatedScreen = screenService.updateScreen(screenId, screenDetails);
        if (updatedScreen == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(updatedScreen);
    }

    @DeleteMapping("/{screenId}")
    public ResponseEntity<Void> deleteScreen(@PathVariable Long screenId) {
        screenService.deleteScreen(screenId);
        return ResponseEntity.noContent().build();
    }
}
