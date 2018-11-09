package com.example.group.ondemandhomerepair;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class EditServicePage extends AppCompatActivity {
    private Button editServiceButt;
    private Service service;
    private EditText name;
    private EditText rate;
    private TextView errorMessage;
    Spinner serviceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_service_page);
        name = findViewById(R.id.editServiceNameText);
        rate = findViewById(R.id.editServiceWageText);
        errorMessage = findViewById(R.id.errorMessageEdit);
        editServiceButt = findViewById(R.id.editServiceButt);
        serviceList = (Spinner)findViewById(R.id.spinner2);

        editServiceButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (statusValidate()){
                //push servuce to firebase
                }
            }
        });
    }

    public boolean statusValidate(){
            if (service == null){
                errorMessage.setText("! service was not chosen");
                return false;
            }
            if (name.getText().toString().trim().equals("")) {
                errorMessage.setText("! name is empty");
                return false;
            }
            if (rate.getText().toString().trim().equals("")) {
                errorMessage.setText("! hourly rate is empty");
                return false;
            }
            if (!rate.getText().toString().trim().matches( "[0-9]+")) {
                errorMessage.setText("! wage is non-numeric");
                return false;
            }
            return true;
    }
}
