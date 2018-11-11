package com.example.group.ondemandhomerepair;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class AddServicePage extends AppCompatActivity {

    private EditText name, rate;
    private Button addingServiceButt;
    private TextView errorMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service_page);

        name = findViewById(R.id.serviceNameText);
        rate = findViewById(R.id.serviceWageText);
        addingServiceButt = findViewById(R.id.addingServiceButt);

        addingServiceButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (statusValidate()){
                    final String Name = name.getText().toString().trim();
                    final String Rate = rate.getText().toString().trim();

                    Service user = new Service(
                            Name,
                            Integer.parseInt(Rate)
                    );

                    FirebaseDatabase.getInstance().getReference("Services")
                            .push().setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(AddServicePage.this, "Service addition successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(AddServicePage.this, AddServicePage.class));
                            } else {
                                Toast.makeText(AddServicePage.this, "Registration f", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }

    public boolean statusValidate() {


        if (name.getText().toString().equals("")) {
            errorMessage.setText("! name is empty");
            return false;
        }
        if (rate.getText().toString().trim().equals("")) {
            errorMessage.setText("! hourly rate is empty");
            return false;
        }
        if (!rate.getText().toString().trim().matches( "[0-9]+")){
            return false;
        }

        //if statement for firebase account duplicates
        //Use Object.getText.toString to compare with any string values in the database for username, password, and radioButton string.
        return true;
    }
}
