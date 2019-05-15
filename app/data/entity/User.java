package data.entity;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class User extends Model {
    private static final long TOKEN_EXPIRES_TIME = 7 * 86400000;

    @Id
    private long id;
    private String username;
    private String password;
    private String phoneNumber;
    private String name;
    @OneToMany(mappedBy = "seller")
    private List<Post> posts;
    private String token;
    private Date tokenExpiresDate;

    public User(){

    }

    public User(String username, String password, String phoneNumber, String name, String token){
        this(username, password, phoneNumber, name, new ArrayList<>(), token);
    }

    public User(String username, String password, String phoneNumber, String name, List<Post> posts, String token) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.posts = posts;
        updateToken(token);
    }

    public User(long id, String username, String password, String phoneNumber, String name, List<Post> posts, String token) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.posts = posts;
        updateToken(token);
    }

    public void updateToken(String token){
        this.token = token;
        this.tokenExpiresDate = new Date(System.currentTimeMillis() + TOKEN_EXPIRES_TIME);
    }

    public long getId() {
        return id;
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

    public List<Post> getPosts() {
        return posts;
    }

    public String getToken() {
        return token;
    }

    public Date getTokenExpiresDate() {
        return tokenExpiresDate;
    }
}
