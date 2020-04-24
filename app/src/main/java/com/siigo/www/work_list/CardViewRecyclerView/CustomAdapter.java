package com.siigo.www.work_list.CardViewRecyclerView;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.siigo.www.work_list.Logic.ListOfLists;
import com.siigo.www.work_list.Logic.Task;
import com.siigo.www.work_list.Logic.TasksList;
import com.siigo.www.work_list.UI.ListOfListsActivity;
import com.siigo.www.work_list.UI.TasksListActivity;
import com.siigo.www.work_list.R;



import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {


    ArrayList<Task> taskArray;
    public CustomAdapter(ArrayList<Task> taskArray){
        this.taskArray = taskArray;
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_task_cardview, viewGroup, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        customViewHolder.paint(taskArray.get(i));

    }

    @Override
    public int getItemCount() {
        return taskArray.size();
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView taskNameTextView;
        Context context;
        CardView cardView;
        Task task;


        public CustomViewHolder(View itemView) {
            super(itemView);
            taskNameTextView = itemView.findViewById(R.id.task_name_text_view);
            cardView = itemView.findViewById(R.id.task_card_view);
            cardView.setOnClickListener(this);
            context = itemView.getContext();

        }

        void paint (Task task) {
            this.task = task;
            taskNameTextView.setText(task.toString());
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, task.getTaskName(), Toast.LENGTH_SHORT).show();
        }
    }

}