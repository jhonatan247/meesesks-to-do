package com.siigo.www.work_list.Logic;

import java.util.ArrayList;
import java.util.List;

public class ListOfLists {

    public ArrayList<TasksList> ListOfList;


    public ListOfLists(){
        this.ListOfList = new ArrayList<>();
    }

    public ArrayList<TasksList> getListOfList() {
        return ListOfList;
    }

    public void addList(String ListName) throws Exception{
        for(TasksList element : ListOfList){
            if(element.getTasksListName().equals(ListName) ){
                throw new Exception(":(");
            }
        }

        TasksList newTaskList = new TasksList(ListName);
        ListOfList.add(newTaskList);

    }
}
