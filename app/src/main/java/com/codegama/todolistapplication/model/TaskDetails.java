package com.codegama.todolistapplication.model;

public class TaskDetails {
    private int id;
    private String nameTask;

    public TaskDetails() {
    }

    public TaskDetails(int id, String nameTask) {
        this.id = id;
        this.nameTask = nameTask;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    @Override
    public String toString() {
        return "TaskDetails{" +
                "id=" + id +
                ", nameTask='" + nameTask + '\'' +
                '}';
    }
}
