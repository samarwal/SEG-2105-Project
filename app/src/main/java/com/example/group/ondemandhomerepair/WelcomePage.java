package com.example.group.ondemandhomerepair;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class WelcomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        Spinner accountsList = (Spinner)findViewById(R.id.accountList); // find drop down and set as invisible
        accountsList.setVisibility(View.GONE);
        TextView userName = (TextView)findViewById(R.id.userField);
        TextView userType = (TextView)findViewById(R.id.typeField);
        userName.setText("username");           // username will go here gotten from log-in
        userType.setText("usertype");           // type will go here gotten from log-in
        if(userName.getText().equals("admin")){
            accountsList.setVisibility(View.VISIBLE);
        }

    }
}
