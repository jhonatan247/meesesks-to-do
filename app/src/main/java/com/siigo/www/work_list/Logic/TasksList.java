package com.siigo.www.work_list.Logic;

import android.app.ActivityManager;


import java.util.ArrayList;


public class TasksList {

    private ArrayList<Task> tasksList;
    private String tasksListName;

    public TasksList(String tasksListName) {
        this.tasksList = new ArrayList<>();
        this.tasksListName = tasksListName;
    }

    public ArrayList<Task> getTasksList() {
        return tasksList;
    }

    public void setTasksList(ArrayList<Task> tasksList) {
        this.tasksList = tasksList;
    }

    public String getTasksListName() {
        return tasksListName;
    }

    public void setTasksListName(String tasksListName) {
        this.tasksListName = tasksListName;
    }

    public String toString(){
        return tasksListName;
    }

    public void addTask(String TaskName) throws Exception{
        for(Task element : tasksList){
            if(element.getTaskName().equals(TaskName) ){
                throw new Exception("se rompió");
            }
        }
        
        Task newTask = new Task(TaskName);
        tasksList.add(newTask);
        
    }
    
    public void addTask(String taskName, String taskHour, String taskDate, String taskDescription){
        Task newTask = new Task(taskName, taskHour, taskDate, taskDescription);
        tasksList.add(newTask);
    }
}
