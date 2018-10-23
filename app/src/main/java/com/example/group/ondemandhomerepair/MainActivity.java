package com.example.group.ondemandhomerepair;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openLogin();
            }
        });
        Button RegistrationButton = (Button) findViewById(R.id.registerButton);
        RegistrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openRegistration();
            }
        });
    }
        public void openLogin(){
            Intent intent = new Intent(this, LogIn.class);
            startActivity(intent);
        }
        public void openRegistration(){
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }
}

