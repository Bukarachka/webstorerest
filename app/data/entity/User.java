package data.entity;

import com.google.gson.annotations.SerializedName;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.Date;

@Entity("user")
public class User {
    private static final long TOKEN_EXPIRES_TIME = 7 * 86400000;

    @Id
    @SerializedName("id")
    private ObjectId id;
    private String username;
    private String password;
    private String phoneNumber;
    private String name;
    private String token;
    private Date tokenExpiresDate;
    private boolean isAdmin;

    public User(){

    }

    public User(String username, String password, String phoneNumber, String name, String token) {
        this(new ObjectId().toHexString(), username, password, phoneNumber, name, token);
    }

    public User(String id, String username, String password, String phoneNumber, String name, String token) {
        this.id = new ObjectId(id);
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.name = name;
        updateToken(token);
        this.isAdmin = false;
    }

    public void updateToken(String token){
        this.token = token;
        this.tokenExpiresDate = new Date(System.currentTimeMillis() + TOKEN_EXPIRES_TIME);
    }

    public String getId() {
        return id.toHexString();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    public Date getTokenExpiresDate() {
        return tokenExpiresDate;
    }

    public boolean isAdmin() {
        return this.isAdmin;
    }
}
