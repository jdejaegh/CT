package be.unamur.ct.download.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;


/**
 * Entity class used to represent a Certificate Transparency log server in the application.
 * This class is used by JPA to create the corresponding SQL table in the database.
 * The class contains variables needed to represent a log server and basic getters, setters and toString methods.
 */
@Entity
public class Server {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @Length(min = 10)
    private String url;

    private String nickname;

    public Server() {}

    public Server(@Length(min = 10) String url) {
        if (url.endsWith("/")) {
            this.url = url;
        } else {
            this.url = url + "/";
        }

        this.nickname = this.url;
    }

    public Server(@Length(min = 10) String url, String nickname) {

        if (url.endsWith("/")) {
            this.url = url;
        } else {
            this.url = url + "/";
        }

        this.nickname = nickname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Server{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
