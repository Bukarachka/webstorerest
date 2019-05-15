package data.entity;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Review extends Model {

    @Id
    private long id;
    private double rate;
    private Message message;
    @ManyToOne(optional = false)
    private Post post;

    public Review() {
    }

    public Review(double rate, Message message, Post post) {
        this.rate = rate;
        this.message = message;
        this.post = post;
    }

    public Review(long id, double rate, Message message, Post post) {
        this.id = id;
        this.rate = rate;
        this.message = message;
        this.post = post;
    }

    public long getId() {
        return id;
    }

    public double getRate() {
        return rate;
    }

    public Message getMessage() {
        return message;
    }

    public Post getPost() {
        return post;
    }
}
