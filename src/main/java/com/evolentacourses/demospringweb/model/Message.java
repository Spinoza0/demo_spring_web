package com.evolentacourses.demospringweb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
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

    public void updateMessage(Message message) {
        title = message.title;
        text = message.text;
        time = message.time;
    }
}
