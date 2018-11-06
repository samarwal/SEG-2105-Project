package com.example.group.ondemandhomerepair;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.Intent;

public class WelcomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        Spinner accountsList = (Spinner)findViewById(R.id.accountList); // find drop down and set as invisible
        accountsList.setVisibility(View.GONE);

        Intent intent = getIntent();                                    // create intent by taking from previous intent

        TextView userName = (TextView)findViewById(R.id.userField);        // get text views
        TextView userType = (TextView)findViewById(R.id.typeField);

        userName.setText(intent.getStringExtra(LogIn.EXTRA_TEXT1));           // get username from EXTRA_TEXT1 of intent
        userType.setText(intent.getStringExtra(LogIn.EXTRA_TEXT2));           // get userType from EXTRA_TEXT@ of intent

        if(userName.getText().equals("admin")){                             // have list of users appear if user is admin
            accountsList.setVisibility(View.VISIBLE);
        }
    }
}
