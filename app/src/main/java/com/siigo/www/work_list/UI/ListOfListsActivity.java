package com.siigo.www.work_list.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.siigo.www.work_list.Logic.ListOfLists;
import com.siigo.www.work_list.Logic.TasksList;
import com.siigo.www.work_list.R;

public class ListOfListsActivity extends AppCompatActivity {

    public static ListOfLists listOfLists = new ListOfLists();
    public static final String TASKS_LIST_INDEX = "TASKS_LIST_INDEX";

    private ListView listOfListContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_lists);
        getSupportActionBar().setTitle("Your task lists");
        listOfListContainer = findViewById(R.id.list_of_list_list_view);
        ArrayAdapter<TasksList> adapter = new ArrayAdapter<TasksList>(this, android.R.layout.simple_list_item_1, listOfLists.getListOfList());
        listOfListContainer.setAdapter(adapter);

        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListOfListsActivity.this, listOfLists.getListOfList().get(position).toString(), Toast.LENGTH_SHORT).show();
                Intent goToTasksList = new Intent(ListOfListsActivity.this, TasksListActivity.class);
                goToTasksList.putExtra(TASKS_LIST_INDEX, position);
                startActivity(goToTasksList);
            }
        };
        listOfListContainer.setOnItemClickListener(listener);
    }

    public void addList(View view) {
        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
        Intent addListIntent = new Intent(this, NewListActivity.class);
        startActivity(addListIntent);
    }


}
