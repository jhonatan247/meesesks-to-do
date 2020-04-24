package com.siigo.www.work_list.CardViewRecyclerView;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.siigo.www.work_list.Logic.Task;
import com.siigo.www.work_list.Logic.TasksList;
import com.siigo.www.work_list.R;
import com.siigo.www.work_list.UI.ListOfListsActivity;
import com.siigo.www.work_list.UI.TasksListActivity;

import java.util.ArrayList;

public class ListCustomAdapter extends RecyclerView.Adapter<ListCustomAdapter.ListCustomViewHolder> {

    ArrayList<TasksList> taskListArray;
    public ListCustomAdapter(ArrayList<TasksList> taskListArray){ this.taskListArray = taskListArray;}

    @NonNull
    @Override
    public ListCustomAdapter.ListCustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_task_list_card, viewGroup, false);
        return new ListCustomViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ListCustomViewHolder customViewHolder, int i) {
        customViewHolder.paint(taskListArray.get(i), i);

    }


    @Override
    public int getItemCount() {
        return taskListArray.size();
    }



    static class ListCustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView taskNameTextView;
        CardView cardView;
        TasksList taskList;
        Context context;
        int index;

        public ListCustomViewHolder(@NonNull View itemView) {
            super(itemView);
            taskNameTextView = itemView.findViewById(R.id.list_name_text_view);
            cardView = itemView.findViewById(R.id.task_list_cardview);
            context = itemView.getContext();
            cardView.setOnClickListener(this);
        }

        void paint (TasksList taskList, int index) {
            this.taskList = taskList;
            taskNameTextView.setText(taskList.toString());
            this.index = index;
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, taskList.toString(), Toast.LENGTH_SHORT).show();
            Intent goToTasksList = new Intent(context, TasksListActivity.class);
            goToTasksList.putExtra(ListOfListsActivity.TASKS_LIST_INDEX, index);
            context.startActivity(goToTasksList);

        }
    }
}



