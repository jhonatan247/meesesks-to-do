package com.siigo.www.work_list.UI;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.siigo.www.work_list.CardViewRecyclerView.CustomAdapter;
import com.siigo.www.work_list.Logic.ListOfLists;
import com.siigo.www.work_list.Logic.Task;
import com.siigo.www.work_list.Logic.TasksList;
import com.siigo.www.work_list.R;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionHelper;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RFACLabelItem;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RapidFloatingActionContentLabelList;
import com.wangjie.rapidfloatingactionbutton.util.RFABTextUtil;

import java.util.ArrayList;
import java.util.List;

public class TasksListActivity extends AppCompatActivity implements RapidFloatingActionContentLabelList.OnRapidFloatingActionContentLabelListListener{

    Context context;
    public TasksList tasksList;
    private RecyclerView tasksListContainer;
    int tasksListIndex;
    private RapidFloatingActionLayout rfaLayout;
    private RapidFloatingActionButton rfaBtn;
    private RapidFloatingActionHelper rfabHelper;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tasksListContainer = findViewById(R.id.task_list_recycler_view);

        tasksListIndex = getIntent().getIntExtra("TASKS_LIST_INDEX", -1);

        recyclerView = findViewById(R.id.task_list_recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        rfaLayout = findViewById(R.id.activity_main_rfal);
        rfaBtn = findViewById(R.id.activity_main_rfab);

        RapidFloatingActionContentLabelList rfaContent = new RapidFloatingActionContentLabelList(this);
        rfaContent.setOnRapidFloatingActionContentLabelListListener(this);
        List<RFACLabelItem> items = new ArrayList<>();
        items.add(new RFACLabelItem<Integer>()
                .setLabel("Add")
                .setResId(R.mipmap.ic_launcher)
                .setIconNormalColor(Color.GREEN)
                .setIconPressedColor(0xffbf360c)
                .setWrapper(0)
        );
        items.add(new RFACLabelItem<Integer>()
                .setLabel("Edit")
                .setResId(R.mipmap.ic_launcher)
                .setIconNormalColor(0xff4e342e)
                .setIconPressedColor(0xff3e2723)
                .setLabelColor(0xff056f00)
                .setLabelSizeSp(14)
                .setWrapper(1)
        );
        items.add(new RFACLabelItem<Integer>()
                .setLabel("Delete")
                .setResId(R.mipmap.ic_launcher)
                .setIconNormalColor(0xff056f00)
                .setIconPressedColor(0xff0d5302)
                .setLabelColor(0xff056f00)
                .setWrapper(2)
        );
        rfaContent
                .setItems(items)
                .setIconShadowRadius(RFABTextUtil.dip2px(this, 5))
                .setIconShadowColor(0xff888888)
                .setIconShadowDy(RFABTextUtil.dip2px(this, 5))
        ;
        rfabHelper = new RapidFloatingActionHelper(
                this,
                rfaLayout,
                rfaBtn,
                rfaContent
        ).build();


        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                int position = recyclerView.getChildAdapterPosition(child);
                Toast.makeText(TasksListActivity.this, tasksList.getTasksList().get(position).toString(), Toast.LENGTH_SHORT).show();
                Intent goToTask = new Intent(TasksListActivity.this, TaskActivity.class);
                goToTask.putExtra("TASK_INDEX", position);
                goToTask.putExtra("TASKS_LIST_INDEX", tasksListIndex);
                startActivity(goToTask);

                return true;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }
        });

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




    }

    @Override
    protected void onResume() {
        super.onResume();
        tasksList = ListOfListsActivity.listOfLists.getListOfList().get(tasksListIndex);
        String list_name = tasksList.getTasksListName();

        getSupportActionBar().setTitle(list_name + " task list");

        Log.i("TASK_LIST_ITEM_COUNT", tasksList.getTasksList().size() + "");

        CustomAdapter customAdapter = new CustomAdapter(tasksList.getTasksList());
        recyclerView.setAdapter(customAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    public void addTask(View view) {
        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRFACItemLabelClick(int position, RFACLabelItem item) {

    }

    @Override
    public void onRFACItemIconClick(int position, RFACLabelItem item) {
        if(position==0){
            Intent addTaskIntent = new Intent(this, TaskActivity.class);
            addTaskIntent.putExtra(ListOfListsActivity.TASKS_LIST_INDEX, tasksListIndex);
            startActivity(addTaskIntent);
        }
        else if(position==1){
            Toast.makeText(this, "clicked icon: " + position, Toast.LENGTH_SHORT).show();
            rfabHelper.toggleContent();
        }
        else{
            Toast.makeText(this, "clicked icon: " + position, Toast.LENGTH_SHORT).show();
            rfabHelper.toggleContent();
        }

    }
}