package com.evolentacourses.demospringweb.controller;

import com.evolentacourses.demospringweb.model.Message;
import com.evolentacourses.demospringweb.model.Person;
import com.evolentacourses.demospringweb.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@SuppressWarnings({"unused"})
@RestController
public class PersonsController {
    @Autowired
    private PersonService service;

    // POST
    @PostMapping("/persons")
    public Person addPerson(@RequestBody Person person) {
        return service.addPerson(person);
    }

    @PostMapping("/persons/{id}/messages")
    public ResponseEntity<Person> addMessage(@PathVariable int id, @RequestBody Message message) {
        return service.addMessage(id, message);
    }

    // GET
    @GetMapping("/persons")
    public Iterable<Person> getPersons() {
        return service.getPersons();
    }

    @GetMapping("/persons/find")
    public Iterable<Person> getPersonByLastname(@RequestParam String lastname) {
        return service.findPersonByLastname(lastname);
    }

    @GetMapping("/persons/{id}")
    public Optional<Person> findPersonById(@PathVariable int id) {
        return service.findPersonById(id);
    }

    @GetMapping("/persons/{id}/messages")
    public Iterable<Message> getMessages(@PathVariable int id) {
        return service.getMessages(id);
    }

    @GetMapping("/persons/{personId}/messages/{messageId}")
    public ResponseEntity<Message> getMessage(@PathVariable int personId, @PathVariable int messageId) {
        return service.getMessage(personId, messageId);
    }

    @GetMapping("/")
    public String hello() {
        return "Hello, World!";
    }

    // PUT
    @PutMapping("/persons/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
        return service.updatePerson(id, person);
    }

    @PutMapping("/persons/{personId}/messages/{messageId}")
    public ResponseEntity<Message> updateMessage(
            @PathVariable int personId,
            @PathVariable int messageId,
            @RequestBody Message message) {
        return service.updateMessage(personId, messageId, message);
    }

    // DELETE
    @DeleteMapping("/persons/{id}")
    public void deletePerson(@PathVariable int id) {
        service.deletePerson(id);
    }

    @DeleteMapping("/persons/{id}/messages/{messageId}")
    public ResponseEntity<Person> deleteMessage(@PathVariable int id, @PathVariable int messageId) {
        return service.deleteMessageFromPerson(id, messageId);
    }
}