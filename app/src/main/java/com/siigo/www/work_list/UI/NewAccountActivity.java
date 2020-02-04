package com.siigo.www.work_list.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.siigo.www.work_list.R;

import java.util.ArrayList;


public class NewAccountActivity extends AppCompatActivity {

    EditText registerUserNameEditText;
    EditText registerUserEmailEditText;
    EditText registerUserPasswordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        registerUserNameEditText = findViewById(R.id.register_user_name_edit_text);
        registerUserEmailEditText = findViewById(R.id.register_user_email_edit_text);
        registerUserPasswordEditText = findViewById(R.id.register_user_password_edit_text);
    }

    public void GoToLogin (View view){
        Intent GoToLoginIntent = new Intent(this, LoginActivity.class);
        startActivity(GoToLoginIntent);
    }
}



