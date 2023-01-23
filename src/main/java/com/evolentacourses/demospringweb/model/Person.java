package com.evolentacourses.demospringweb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
}