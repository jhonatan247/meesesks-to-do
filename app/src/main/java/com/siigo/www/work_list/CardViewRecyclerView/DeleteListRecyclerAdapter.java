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

import com.siigo.www.work_list.Listeners.OnDestroyListListener;
import com.siigo.www.work_list.Logic.TasksList;
import com.siigo.www.work_list.R;
import com.siigo.www.work_list.UI.ListOfListsActivity;
import com.siigo.www.work_list.UI.TasksListActivity;

import java.util.ArrayList;

public class DeleteListRecyclerAdapter extends RecyclerView.Adapter<DeleteListRecyclerAdapter.ListCustomViewHolder> {

    ArrayList<TasksList> taskListArray;
    OnDestroyListListener onDestroyListListener;
    public DeleteListRecyclerAdapter(ArrayList<TasksList> taskListArray, OnDestroyListListener onDestroyListListener){
        this.taskListArray = taskListArray;
        this.onDestroyListListener = onDestroyListListener;
    }

    @NonNull
    @Override
    public DeleteListRecyclerAdapter.ListCustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_delete_task_list_card, viewGroup, false);
        return new ListCustomViewHolder(view, onDestroyListListener);
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
        OnDestroyListListener onDestroyListListener;
        int index;

        public ListCustomViewHolder(@NonNull View itemView, OnDestroyListListener onDestroyListListener) {
            super(itemView);
            taskNameTextView = itemView.findViewById(R.id.list_name_text_view);
            cardView = itemView.findViewById(R.id.task_list_cardview);
            context = itemView.getContext();
            cardView.setOnClickListener(this);
            this.onDestroyListListener = onDestroyListListener;
        }

        void paint (TasksList taskList, int index) {
            this.taskList = taskList;
            taskNameTextView.setText(taskList.toString());
            this.index = index;
        }

        @Override
        public void onClick(View v) {
            onDestroyListListener.destroy(taskList);

        }
    }
}



