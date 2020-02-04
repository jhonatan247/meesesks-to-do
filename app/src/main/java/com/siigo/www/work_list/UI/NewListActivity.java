package com.siigo.www.work_list.UI;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import static com.siigo.www.work_list.UI.ListOfListsActivity.listOfLists;

import com.siigo.www.work_list.R;

public class NewListActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);
        getSupportActionBar().setTitle("New task list");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    public void createList(View view){
        context = this;
        EditText ListNameEditText = findViewById(R.id.list_name_edit_text);
        Toast.makeText(this, ListNameEditText.getText(), Toast.LENGTH_SHORT).show();
        try {
            listOfLists.addList(ListNameEditText.getText().toString());
            Intent createListIntent = new Intent(this,ListOfListsActivity.class);
            startActivity(createListIntent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
