package com.siigo.www.work_list.UI;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.siigo.www.work_list.Logic.ListOfLists;
import com.siigo.www.work_list.Logic.Task;
import com.siigo.www.work_list.Logic.TasksList;
import com.siigo.www.work_list.R;

public class TasksListActivity extends AppCompatActivity {

    Context context;
    private TasksList tasksList;
    private ListView tasksListContainer;
    int tasksListIndex;
    TextView listNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tasksListContainer = findViewById(R.id.task_list_list_view);

        tasksListIndex = getIntent().getIntExtra("TASKS_LIST_INDEX", -1);



        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(TasksListActivity.this, tasksList.getTasksList().get(position).toString(), Toast.LENGTH_SHORT).show();
                Intent goToTask = new Intent(TasksListActivity.this, TaskActivity.class);
                goToTask.putExtra("TASK_INDEX", position);
                goToTask.putExtra("TASKS_LIST_INDEX", tasksListIndex);
                startActivity(goToTask);
            }
        };
        tasksListContainer.setOnItemClickListener(listener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tasksList = ListOfListsActivity.listOfLists.getListOfList().get(tasksListIndex);
        String list_name = tasksList.getTasksListName();
        listNameTextView.setText(list_name);
        getSupportActionBar().setTitle(list_name + " task list");

        Log.i("TASK_LIST_ITEM_COUNT", tasksList.getTasksList().size() + "");

        ArrayAdapter<Task> adapter = new ArrayAdapter<Task>(this, android.R.layout.simple_list_item_1, tasksList.getTasksList());
        tasksListContainer.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    public void addTask(View view) {
        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
        Intent addTaskIntent = new Intent(this, TaskActivity.class);
        addTaskIntent.putExtra(ListOfListsActivity.TASKS_LIST_INDEX, tasksListIndex);
        startActivity(addTaskIntent);
    }


}