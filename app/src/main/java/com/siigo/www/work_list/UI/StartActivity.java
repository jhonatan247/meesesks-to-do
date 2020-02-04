package com.siigo.www.work_list.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.siigo.www.work_list.R;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        getSupportActionBar().setTitle("Meeseeks task list");
    }

    public void GoToLogin (View view){
        Intent GoToLoginIntent = new Intent(this, LoginActivity.class);
        startActivity(GoToLoginIntent);
    }

    public void NewRegister (View view){
        Intent NewRegisterIntent = new Intent(this, NewAccountActivity.class);
        startActivity(NewRegisterIntent);
    }
}
