package dev.dronade.taskorca.model;


import java.sql.Timestamp;

/**
 * @author Emily Canto
 *  This Class is the model file that is responsible for defining what a task is.
 */

public class Task {
    private int userID;
    private int taskID;
    private Timestamp created_at;
    private String due_date;
    private String title;
    private String details;
    private String folder;

    public Task(Integer taskID, Integer userID, Timestamp created_at, String due_date, String title, String details, String folder) {
        this.taskID = taskID;
        this.userID = userID;
        this.created_at = created_at;
        this.due_date = due_date;
        this.title = title;
        this.details = details;
        this.folder = folder;
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

    public int getUserID() {
        return this.userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFolder() {
        return this.folder;
    }
    public void setFolder(String folder) {
        this.folder = folder;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskId) {
        this.taskID = taskId;
    }
}
