package data.entity;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category extends Model {

    @Id
    private long id;
    private String title;
    private String description;

    public Category(){

    }

    public Category(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Category(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
