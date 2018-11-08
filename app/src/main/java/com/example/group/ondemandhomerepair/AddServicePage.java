package com.example.group.ondemandhomerepair;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class AddServicePage extends AppCompatActivity {

    private EditText name, rate;
    private Button addingServiceButt;
    private RadioGroup radGroup;
    private FirebaseAuth mAuth;
    private TextView errorMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service_page);

        Button addingButton =(Button)findViewById(R.id.addingServiceButt);

        mAuth = FirebaseAuth.getInstance();

        name = findViewById(R.id.serviceNameText);
        rate = findViewById(R.id.serviceWageText);
        addingServiceButt = findViewById(R.id.addingServiceButt);

        if (statusValidate()){

        }
    }

    public boolean statusValidate() {


        if (name.getText().toString().equals("")) {
            errorMessage.setText("! Username is empty");
            return false;
        }
        if (rate.getText().toString().trim().equals("")) {
            errorMessage.setText("! Password is empty");
            return false;
        }
        

        //if statement for firebase account duplicates
        //Use Object.getText.toString to compare with any string values in the database for username, password, and radioButton string.
        return true;
    }
}
