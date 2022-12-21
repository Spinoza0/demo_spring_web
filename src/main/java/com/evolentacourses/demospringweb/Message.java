package com.evolentacourses.demospringweb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class Message {
    @Id
    @GeneratedValue
    private int id;

    private String title;
    private String text;
    private LocalDateTime time;

    @ManyToOne
    @JsonIgnore
    private Person person;

    public Message() {
    }

    public void updateMessage(Message message) {
        title = message.title;
        text = message.text;
        time = message.time;
    }

    public int getId() {
        return id;
    }

    @SuppressWarnings({"unused"})
    public void setId(int id) {
        this.id = id;
    }

    @SuppressWarnings({"unused"})
    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @SuppressWarnings({"unused"})
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @SuppressWarnings({"unused"})
    public String getTitle() {
        return title;
    }

    @SuppressWarnings({"unused"})
    public void setTitle(String title) {
        this.title = title;
    }

    @SuppressWarnings({"unused"})
    public String getText() {
        return text;
    }

    @SuppressWarnings({"unused"})
    public void setText(String text) {
        this.text = text;
    }
}
