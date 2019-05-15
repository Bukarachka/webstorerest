package data.entity;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Message extends Model {

    @Id
    private long id;
    private Long senderId;
    private String text;

    public Message() {
    }

    public Message(Long senderId, String text) {
        this.senderId = senderId;
        this.text = text;
    }

    public Message(long id, Long senderId, String text) {
        this.id = id;
        this.senderId = senderId;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public String getText() {
        return text;
    }
}
