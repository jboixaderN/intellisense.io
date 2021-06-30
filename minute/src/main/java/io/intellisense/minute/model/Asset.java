package io.intellisense.minute.model;

import java.util.List;
import java.util.Map;

public class Asset {
    private String id;
    private Map<String, Map> metricEntries;
    private List<String> timeEntry;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Map> getMetricEntries() {
        return metricEntries;
    }

    public void setMetricEntries(Map<String, Map> metricEntries) {
        this.metricEntries = metricEntries;
    }

    public List<String> getTimeEntry() {
        return timeEntry;
    }

    public void setTimeEntry(List<String> timeEntry) {
        this.timeEntry = timeEntry;
    }
}
