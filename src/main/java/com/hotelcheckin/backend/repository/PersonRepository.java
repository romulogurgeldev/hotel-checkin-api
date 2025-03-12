package com.hotelcheckin.backend.repository;

import com.hotelcheckin.backend.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByNameContainingOrDocumentContaining(String name, String document);
}