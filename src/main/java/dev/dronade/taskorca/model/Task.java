package dev.dronade.taskorca.model;


import java.sql.Timestamp;

public class Task {
    private int userId;
    private int taskId;
    private Timestamp created_at;
    private String due_date;
    private String title;
    private String details;

    public Task(Integer userId, Timestamp created_at, String due_date, String title, String details) {
        this.userId = userId;
        this.created_at = created_at;
        this.due_date = due_date;
        this.title = title;
        this.details = details;
    }

    public Task() {

    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
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

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
