package com.siigo.www.work_list.UI;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.siigo.www.work_list.Logic.ListOfLists;
import com.siigo.www.work_list.Logic.Task;
import com.siigo.www.work_list.Logic.TasksList;
import com.siigo.www.work_list.R;

import java.sql.Timestamp;
import java.util.Date;

public class TaskActivity extends AppCompatActivity {

    Context context;
    private TasksList tasksList;
    private Task task;
    private int taskListIndex;
    private int taskIndex;
    TextView taskNameTextView;
    EditText taskNameEditText;
    EditText taskDescriptionEditText;
    EditText taskDateEditText;
    EditText taskHourEditText;
    FloatingActionButton taskButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        getSupportActionBar().setTitle("New task");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        taskNameTextView = findViewById(R.id.task_name_text_view);
        taskNameEditText = findViewById(R.id.task_name_edit_text);
        taskDescriptionEditText = findViewById(R.id.task_description_edit_text);
        taskDateEditText = findViewById(R.id.task_date_edit_text);
        taskHourEditText = findViewById(R.id.task_hour_edit_text);
        taskButton = findViewById(R.id.create_task_fab);


        taskListIndex = getIntent().getIntExtra("TASKS_LIST_INDEX", -1);
        taskIndex = getIntent().getIntExtra("TASK_INDEX", -1);


    }


    protected void onResume() {
        super.onResume();

        if (taskListIndex != -1 && taskIndex != -1) {
            getSupportActionBar().setTitle("Edit task");
            taskButton.setImageResource(R.drawable.lapiz);
            task = ListOfListsActivity.listOfLists.getListOfList().get(taskListIndex).getTasksList().get(taskIndex);
            String task_name = task.getTaskName();
            String task_description = task.getTaskDescription();
            String task_date = task.getTaskDate();
            String task_hour = task.getTaskHour();

            if (task_name != null) {
                taskNameTextView.setText(task_name);
                taskNameEditText.setText(task_name);
            }

            if (task_description != null) {
                taskDescriptionEditText.setText(task_description);
            }
            if (task_date != null) {
                taskDateEditText.setText(task_date);
            }
            if (task_hour != null) {
                taskHourEditText.setText(task_hour);
            }


        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    public void createTask(View view) {
        context = this;

        Toast.makeText(this, taskNameEditText.getText(), Toast.LENGTH_SHORT).show();


        if (taskListIndex != -1 && taskIndex != -1) {

            task.setTaskName(taskNameEditText.getText().toString());
            task.setTaskDescription(taskDescriptionEditText.getText().toString());
            task.setTaskDate(taskDateEditText.getText().toString());
            task.setTaskHour(taskHourEditText.getText().toString());

            onBackPressed();

        } else {
            try {

                int tasksListIndex = getIntent().getIntExtra(ListOfListsActivity.TASKS_LIST_INDEX, -1);
                tasksList = ListOfListsActivity.listOfLists.getListOfList().get(tasksListIndex);
                tasksList.addTask(taskNameEditText.getText().toString(), taskHourEditText.getText().toString(), taskDateEditText.getText().toString(), taskDescriptionEditText.getText().toString());

                onBackPressed();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

    }


}
