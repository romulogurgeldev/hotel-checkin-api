package com.hotelcheckin.backend.service;

import com.hotelcheckin.backend.model.Person;
import com.hotelcheckin.backend.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }

    public List<Person> search(String name, String document) {
        return personRepository.findByNameContainingOrDocumentContaining(name, document);
    }
}