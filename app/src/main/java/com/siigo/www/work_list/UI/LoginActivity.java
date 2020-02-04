package com.siigo.www.work_list.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.siigo.www.work_list.R;

public class LoginActivity extends AppCompatActivity {

    EditText loginUsernameEditText;
    EditText loginPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loginUsernameEditText = findViewById(R.id.login_username_edit_text);
        loginPasswordEditText = findViewById(R.id.login_password_edit_text);
    }

    public void GoToListOfLists (View view){
        Intent GoToListOfListsIntent = new Intent(this, ListOfListsActivity.class);
        startActivity(GoToListOfListsIntent );
    }
}
