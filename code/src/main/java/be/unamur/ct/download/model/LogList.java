package be.unamur.ct.download.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Class used to represent a list of log entries.
 * The class contains variables needed to represent a certificate and basic getters, setters and toString methods.
 * The class also implements an iterator.
 */
public class LogList implements Iterable<LogEntry> {

    @JsonProperty(value = "entries")
    private List<LogEntry> entries;

    public LogList() {
        this.entries = new ArrayList<LogEntry>();
    }

    public LogList(List<LogEntry> entries) {
        this.entries = entries;
    }

    public void addEntry(LogEntry newEntry) {
        entries.add(newEntry);
    }

    public int size() {
        return entries.size();
    }

    public LogEntry getFirst() {
        return entries.get(0);
    }

    public LogEntry get(int n) {
        return entries.get(n);
    }

    @Override
    public String toString() {
        return "LogList{" +
                "entries=" + entries +
                '}';
    }

    public Iterator<LogEntry> iterator() {
        return entries.iterator();
    }

}

