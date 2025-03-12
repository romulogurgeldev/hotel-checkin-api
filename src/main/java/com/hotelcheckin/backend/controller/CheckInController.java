package com.hotelcheckin.backend.controller;

import com.hotelcheckin.backend.model.CheckIn;
import com.hotelcheckin.backend.model.Person;
import com.hotelcheckin.backend.repository.CheckInRepository;
import com.hotelcheckin.backend.repository.PersonRepository;
import com.hotelcheckin.backend.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/checkins")
public class CheckInController {

    @Autowired
    private CheckInRepository checkInRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CheckInService checkInService;

    @PostMapping
    public CheckIn checkIn(@RequestBody CheckIn checkIn) {
        Person person = personRepository.findById(checkIn.getPerson().getId())
                .orElseThrow(() -> new RuntimeException("Person not found"));

        checkIn.setPerson(person);
        checkIn.setTotalAmount(checkInService.calculateTotalAmount(checkIn));
        return checkInRepository.save(checkIn);
    }

    @GetMapping("/left")
    public List<CheckIn> getGuestsWhoLeft() {
        return checkInRepository.findByExitDateBefore(LocalDateTime.now());
    }

    @GetMapping("/present")
    public List<CheckIn> getGuestsStillPresent() {
        return checkInRepository.findByExitDateAfter(LocalDateTime.now());
    }
}