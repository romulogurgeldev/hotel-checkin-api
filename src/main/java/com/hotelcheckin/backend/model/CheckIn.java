package com.hotelcheckin.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class CheckIn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Person person;
    private LocalDateTime entryDate;
    private LocalDateTime exitDate;
    private boolean vehicleAdded;
    private Double totalAmount; // Certifique-se de que o campo existe

    // Getters e Setters
    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}