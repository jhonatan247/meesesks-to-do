package com.siigo.www.work_list.Listeners;

import com.siigo.www.work_list.Logic.ListOfLists;
import com.siigo.www.work_list.Logic.TasksList;

public interface OnDestroyListListener {
    void destroy(TasksList listToDestroy);
}
