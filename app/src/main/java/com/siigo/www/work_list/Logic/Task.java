package com.siigo.www.work_list.Logic;

import android.widget.TextView;



public class Task {
    private String taskName;
    private String taskHour;
    private String taskDate;
    private String taskDescription;

    public Task(String taskName, String taskHour, String taskDate, String taskDescription) {
        this.taskName = taskName;
        this.taskHour = taskHour;
        this.taskDate = taskDate;
        this.taskDescription = taskDescription;
    }

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskHour() {
        return taskHour;
    }

    public void setTaskHour(String taskHour) {
        this.taskHour = taskHour;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String toString(){return taskName;}

}
