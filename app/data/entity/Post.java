package data.entity;

import com.google.gson.annotations.SerializedName;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("post")
public class Post {

    @Id
    @SerializedName("id")
    private ObjectId id;
    private String title;
    private String description;
    private double price;
    private String image;

    public Post(){

    }

    public Post(String title, String description, double price, String image){
        this(new ObjectId().toHexString(), title, description, price, image);
    }

    public Post(String id, String title, String description, double price, String image) {
        this.id = new ObjectId(id);
        this.title = title;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public String getId() {
        return id.toHexString();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
}
