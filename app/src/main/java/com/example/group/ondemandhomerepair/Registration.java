package com.example.group.ondemandhomerepair;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Registration extends AppCompatActivity {
    private EditText username, password, passwordConfirm;
    private Button registerButton;
    private RadioButton radButton;
    private RadioGroup radGroup;
    private TextView errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        passwordConfirm = findViewById(R.id.confirmPassword);
        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                buttonRegisterClick();
            }
        });
        radGroup = findViewById(R.id.radioGroup);
        errorMessage = findViewById(R.id.errorMessage);
    }

    public void buttonRegisterClick(){
        if(statusValidate()){
            Intent intent = new Intent(this, LogIn.class);
            startActivity(intent);
        }
    }
    public boolean statusValidate(){
        if (password.getText().toString().equals(passwordConfirm.getText().toString()) != true){
            errorMessage.setText("! Passwords do not match");
            return false;
        }
        if (username.getText().toString().equals("")){
            errorMessage.setText("! Username is empty");
            return false;
        }
        if (password.getText().toString().equals("")) {
            errorMessage.setText("! Password is empty");
            return false;
        }
        if (radGroup.getCheckedRadioButtonId() == -1){
            errorMessage.setText("! Account type not selected");
            return false;
        }
        else{
            radButton = findViewById(radGroup.getCheckedRadioButtonId());
        }
        //if statement for firebase account duplicates
        //Use Object.getText.toString to compare with any string values in the database for username, password, and radioButton string.
        return true;
    }


}
