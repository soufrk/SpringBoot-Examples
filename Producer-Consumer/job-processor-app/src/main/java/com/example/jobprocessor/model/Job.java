package com.example.jobprocessor.model;

import java.util.Date;

public class Job implements Comparable<Job> {
    private String name;
    private String status;
    private Date startedAt;
    private Date completedAt;

    public Job() {
    }

    public Job(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }

    @Override
    public int compareTo(Job o) {
        return this.name.compareTo(o.getName());
    }
}
