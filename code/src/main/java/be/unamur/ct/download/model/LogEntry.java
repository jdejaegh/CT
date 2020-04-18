package be.unamur.ct.download.model;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class used to represent the Base64 data downloaded from Certificate Transparency log servers.
 * This class is mainly used to represent a log entry between the downloading part and the decoding part.
 * The class contains variables needed to represent a log entry and basic getters, setters and toString methods.
 */
public class LogEntry {

    private long id;

    @JsonProperty(value = "leaf_input")
    private String leaf;

    @JsonProperty(value = "extra_data")
    private String data;

    public LogEntry() {
    }

    public LogEntry(String leaf, String data) {
        this.leaf = leaf;
        this.data = data;
    }

    public String getLeaf() {
        return leaf;
    }

    public void setLeaf(String leaf) {
        this.leaf = leaf;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LogEntry{" +
                "id=" + id +
                ", leaf='" + leaf + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}

