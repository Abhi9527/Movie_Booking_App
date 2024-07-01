package com.example.Screenmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreenService {
    @Autowired
    private ScreenRepository screenRepository;

    public List<Screen> getAllScreens() {
        return screenRepository.findAll();
    }

    public Screen getScreenById(Long id) {
        return screenRepository.findById(id).orElse(null);
    }

    public Screen createScreen(Screen screen) {
        return screenRepository.save(screen);
    }

    public Screen updateScreen(Long id, Screen screenDetails) {
        Screen screen = getScreenById(id);
        if (screen != null) {
            screen.setScreenName(screenDetails.getScreenName());
            screen.setMaxCapacity(screenDetails.getMaxCapacity());
            return screenRepository.save(screen);
        }
        return null;
    }

    public void deleteScreen(Long id) {
        screenRepository.deleteById(id);
    }
}
