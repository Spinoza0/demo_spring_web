package com.evolentacourses.demospringweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    PersonRepository repository;

    private HttpStatus getHttpStatus(boolean status) {
        return status ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }

    public Person addPerson(Person person) {
        return repository.save(person);
    }

    public Iterable<Person> getPersons() {
        return repository.findAll();
    }

    public Optional<Person> findPersonById(int id) {
        return repository.findById(id);
    }

    public ResponseEntity<Message> getMessage(int personId, int messageId) {
        Message message = null;
        Person person = findPersonById(personId).orElse(null);
        if (person != null) {
            message = person.getMessage(messageId);
        }
        return new ResponseEntity<>(message, getHttpStatus(message != null));
    }

    public Iterable<Message> getMessages(int id) {
        Optional<Person> personOptional = repository.findById(id);
        return personOptional.<Iterable<Message>>map(Person::getMessages).orElse(null);
    }

    public void deletePerson(int id) {
        repository.deleteById(id);
    }

    public ResponseEntity<Person> updatePerson(int id, Person person) {
        HttpStatus status = repository.existsById(id) ? HttpStatus.OK : HttpStatus.CREATED;
        person.setId(id);
        return new ResponseEntity<>(repository.save(person), status);
    }

    public ResponseEntity<Message> updateMessage(int personId, int messageId, Message message) {
        Person person = repository.findById(personId).orElse(null);
        Message existingMessage = null;
        if (person != null) {
            existingMessage = person.updateMessage(messageId, message);
            if (existingMessage != null) {
                existingMessage.setTime(LocalDateTime.now());
                person = repository.save(person);
            }
        }
        return new ResponseEntity<>(existingMessage, getHttpStatus(person != null && existingMessage != null));
    }

    public ResponseEntity<Person> addMessage(int personId, Message message) {
        Person person = repository.findById(personId).orElse(null);
        if (person != null) {
            message.setPerson(person);
            message.setTime(LocalDateTime.now());
            person.addMessage(message);
            person = repository.save(person);
        }
        return new ResponseEntity<>(person, getHttpStatus(person != null));
    }

    public ResponseEntity<Person> deleteMessageFromPerson(int personId, int messageId) {
        Person person = repository.findById(personId).orElse(null);
        if (person != null && person.deleteMessage(messageId))
            person = repository.save(person);
        return new ResponseEntity<>(person, getHttpStatus(person != null));
    }
}