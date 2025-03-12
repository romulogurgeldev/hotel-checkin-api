package com.hotelcheckin.backend.service;

import com.hotelcheckin.backend.model.CheckIn;
import org.springframework.stereotype.Service;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.LocalTime;

@Service
public class CheckInService {

    private static final double WEEKDAY_RATE = 120.0;
    private static final double WEEKEND_RATE = 150.0;
    private static final double WEEKDAY_PARKING_RATE = 15.0;
    private static final double WEEKEND_PARKING_RATE = 20.0;

    public double calculateTotalAmount(CheckIn checkIn) {
        LocalDateTime entryDate = checkIn.getEntryDate();
        LocalDateTime exitDate = checkIn.getExitDate();

        double totalAmount = 0.0;

        if (exitDate.toLocalTime().isAfter(LocalDateTime.of(exitDate.toLocalDate(), LocalTime.of(16, 30)).toLocalTime())) {
            exitDate = exitDate.plusDays(1); // Cobra uma diária extra
        }

        for (LocalDateTime date = entryDate; date.isBefore(exitDate); date = date.plusDays(1)) {
            if (isWeekend(date)) {
                totalAmount += WEEKEND_RATE;
                if (checkIn.isVehicleAdded()) {
                    totalAmount += WEEKEND_PARKING_RATE;
                }
            } else {
                totalAmount += WEEKDAY_RATE;
                if (checkIn.isVehicleAdded()) {
                    totalAmount += WEEKDAY_PARKING_RATE;
                }
            }
        }

        return totalAmount;
    }

    private boolean isWeekend(LocalDateTime date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }
}