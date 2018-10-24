package com.example.group.ondemandhomerepair;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        configureLogin();
    }

    private void condfigureLogin(){
        Button loginButton = (Button) findViewById(R.id.btnLogIn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void OnClick(View view){
                startActivity(new Intent(LogIn.this, WelcomePage.class));
            }
        });
    }
}
