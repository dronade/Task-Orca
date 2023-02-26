package dev.dronade.taskorca.model;

public class Task {
    private long created_at;
    private long due_date;
    private String title;
    private String details;

    public Task(long created_at, long due_date, String title, String details) {
        this.created_at = created_at;
        this.due_date = due_date;
        this.title = title;
        this.details = details;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public long getDue_date() {
        return due_date;
    }

    public void setDue_date(long due_date) {
        this.due_date = due_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
