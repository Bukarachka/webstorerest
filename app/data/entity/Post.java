package data.entity;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Post extends Model {

    @Id
    private long id;
    private Category category;
    private String title;
    private String description;
    @ManyToOne(optional = false)
    private User seller;
    @OneToMany(mappedBy = "post")
    private List<Review> reviews;

    public Post() {
    }

    public Post(Category category, String title, String description, User seller) {
        this(category, title, description, seller, new ArrayList<>());
    }

    public Post(Category category, String title, String description, User seller, List<Review> reviews) {
        this.category = category;
        this.title = title;
        this.description = description;
        this.seller = seller;
        this.reviews = reviews;
    }

    public Post(long id, Category category, String title, String description, User seller, List<Review> reviews) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.description = description;
        this.seller = seller;
        this.reviews = reviews;
    }

    public long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public User getSeller() {
        return seller;
    }

    public List<Review> getReviews() {
        return reviews;
    }
}
