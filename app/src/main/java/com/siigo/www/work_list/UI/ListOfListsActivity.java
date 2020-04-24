package com.siigo.www.work_list.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.siigo.www.work_list.CardViewRecyclerView.DeleteListRecyclerAdapter;
import com.siigo.www.work_list.CardViewRecyclerView.ListCustomAdapter;
import com.siigo.www.work_list.Listeners.OnDestroyListListener;
import com.siigo.www.work_list.Logic.ListOfLists;
import com.siigo.www.work_list.Logic.TasksList;
import com.siigo.www.work_list.R;
import android.graphics.Color;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionHelper;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RFACLabelItem;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RapidFloatingActionContentLabelList;
import com.wangjie.rapidfloatingactionbutton.util.RFABTextUtil;
import java.util.ArrayList;
import java.util.List;
public class ListOfListsActivity extends AppCompatActivity implements
        RapidFloatingActionContentLabelList.OnRapidFloatingActionContentLabelListListener, OnDestroyListListener {

    public static ListOfLists listOfLists = new ListOfLists();
    public static final String TASKS_LIST_INDEX = "TASKS_LIST_INDEX";
    private RapidFloatingActionLayout rfaLayout;
    private RapidFloatingActionButton rfaBtn;
    private RapidFloatingActionHelper rfabHelper;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_lists);
        getSupportActionBar().setTitle("Your task lists");


        recyclerView = findViewById(R.id.list_of_list_recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        //MENU
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
                .setResId(R.drawable.lasergun)
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




        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListOfListsActivity.this, listOfLists.getListOfList().get(position).toString(), Toast.LENGTH_SHORT).show();
                Intent goToTasksList = new Intent(ListOfListsActivity.this, TasksListActivity.class);
                goToTasksList.putExtra(TASKS_LIST_INDEX, position);
                startActivity(goToTasksList);

            }


        };

    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<TasksList> taskListArray =listOfLists.getListOfList();
        ListCustomAdapter listCustomAdapter = new ListCustomAdapter(taskListArray);
        recyclerView.setAdapter(listCustomAdapter);
    }

    public void addList(View view) {
        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
        Intent addListIntent = new Intent(this, NewListActivity.class);
        startActivity(addListIntent);
    }


    @Override
    public void onRFACItemLabelClick(int position, RFACLabelItem item) {
        Toast.makeText(this, "clicked label: " + position, Toast.LENGTH_SHORT).show();
        rfabHelper.toggleContent();
    }

    @Override
    public void onRFACItemIconClick(int position, RFACLabelItem item) {
        if(position==0){
            Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
            Intent addListIntent = new Intent(this, NewListActivity.class);
            startActivity(addListIntent);
            rfabHelper.toggleContent();
        }
        else if(position==1){
            Toast.makeText(this, "clicked icon: " + position, Toast.LENGTH_SHORT).show();
            rfabHelper.toggleContent();
        }
        else{
            Toast.makeText(this, "clicked icon: " + position, Toast.LENGTH_SHORT).show();
            ArrayList<TasksList> taskListArray = listOfLists.getListOfList();
            DeleteListRecyclerAdapter listCustomAdapter = new DeleteListRecyclerAdapter(taskListArray, this);
            recyclerView.setAdapter(listCustomAdapter);
            rfabHelper.toggleContent();
        }

    }

    @Override
    public void destroy(TasksList listToDestroy) {
        listOfLists.getListOfList().remove(listToDestroy);

        ArrayList<TasksList> taskListArray =listOfLists.getListOfList();
        ListCustomAdapter listCustomAdapter = new ListCustomAdapter(taskListArray);
        recyclerView.setAdapter(listCustomAdapter);

    }
}
