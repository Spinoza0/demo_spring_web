package com.evolentacourses.demospringweb;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Person {
    @Id
    @GeneratedValue
    private int id;

    private String firstname;
    private String surname;
    private String lastname;
    private LocalDate birthday;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Message> messages;

    public Person() {
    }

    @SuppressWarnings({"unused"})
    public Person(
            int id,
            String firstname,
            String surname,
            String lastname,
            LocalDate birthday,
            List<Message> messages) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.messages = messages;
    }

    public boolean deleteMessage(int messageId) {
        return messages != null && messages.removeIf(msg -> msg.getId() == messageId);
    }

    public Message getMessage(int id) {
        return messages.stream().filter(message -> message.getId() == id).findFirst().orElse(null);
    }

    public Message updateMessage(int id, Message message) {
        Message existingMessage = getMessage(id);
        if (existingMessage != null) {
            existingMessage.updateMessage(message);
        }
        return existingMessage;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public void setId(int id) {
        this.id = id;
    }

    @SuppressWarnings({"unused"})
    public int getId() {
        return id;
    }

    public List<Message> getMessages() {
        return messages;
    }

    @SuppressWarnings({"unused"})
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @SuppressWarnings({"unused"})
    public String getFirstname() {
        return firstname;
    }

    @SuppressWarnings({"unused"})
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @SuppressWarnings({"unused"})
    public String getSurname() {
        return surname;
    }

    @SuppressWarnings({"unused"})
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @SuppressWarnings({"unused"})
    public String getLastname() {
        return lastname;
    }

    @SuppressWarnings({"unused"})
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @SuppressWarnings({"unused"})
    public LocalDate getBirthday() {
        return birthday;
    }

    @SuppressWarnings({"unused"})
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}