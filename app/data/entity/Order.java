package data.entity;

import com.google.gson.annotations.SerializedName;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.Date;

@Entity("order")
public class Order {

    @Id
    @SerializedName("id")
    private ObjectId id;
    private String userId;
    private String postId;
    private String comment;
    private Date date;
    private boolean completed;

    public Order(){

    }

    public Order(String userId, String postId, String comment, Date date, boolean completed){
        this(new ObjectId().toHexString(), userId, postId, comment, date, completed);
    }

    public Order(String id, String userId, String postId, String comment, Date date, boolean completed) {
        this.id = new ObjectId(id);
        this.userId = userId;
        this.postId = postId;
        this.comment = comment;
        this.date = date;
        this.completed = completed;
    }

    public String getId() {
        return id.toHexString();
    }

    public String getUserId() {
        return userId;
    }

    public String getPostId() {
        return postId;
    }

    public String getComment() {
        return comment;
    }

    public Date getDate() {
        return date;
    }

    public boolean isCompleted() {
        return completed;
    }
}
