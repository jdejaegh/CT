package be.unamur.ct.download.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;


/**
 * Entity class used to represent a slice of a log server in the application.
 * A slice of a server is a range of certificates on the server.
 * Slices are used to split the downloading process among several threads.
 * This class is used by JPA to create the corresponding SQL table in the database.
 * The class contains variables needed to represent a slice and basic getters, setters and toString methods
 */
@Entity
public class Slice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long startSlice;
    private long endSlice;
    private long next;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "server_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Server server;

    public Slice() {
    }

    public Slice(long start, long end, long next) {
        this.startSlice = start;
        this.endSlice = end;
        this.next = next;
    }

    public Slice(long start, long end, long next, Server server) {
        this.startSlice = start;
        this.endSlice = end;
        this.next = next;
        this.server = server;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStartSlice() {
        return startSlice;
    }

    public void setStartSlice(long startSlice) {
        this.startSlice = startSlice;
    }

    public long getEndSlice() {
        return endSlice;
    }

    public void setEndSlice(long endSlice) {
        this.endSlice = endSlice;
    }

    public long getNext() {
        return next;
    }

    public void setNext(long next) {
        this.next = next;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }


    @Override
    public String toString() {
        return "Slice{" +
                "id=" + id +
                ", startSlice=" + startSlice +
                ", endSlice=" + endSlice +
                ", next=" + next +
                ", serverId=" + server.getId() +
                '}';
    }
}
