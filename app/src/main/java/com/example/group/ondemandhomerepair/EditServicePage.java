package com.example.group.ondemandhomerepair;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditServicePage extends AppCompatActivity {
    private Button editServiceButt;
    private Service service;
    private EditText name;
    private EditText rate;
    private TextView errorMessage;
    private Spinner serviceList;
    List<String> services;
    String id;
    DatabaseReference serviceRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_service_page);
        name = findViewById(R.id.editServiceNameText);
        rate = findViewById(R.id.editServiceWageText);
        errorMessage = findViewById(R.id.errorMessageEdit);
        editServiceButt = findViewById(R.id.editServiceButt);
        serviceList = (Spinner)findViewById(R.id.spinner2);
        services = new ArrayList<String>();
        id = "";

        //Note This is the Spinner Fill From DeletingServices Code
        FirebaseDatabase.getInstance().getReference().child("services").addListenerForSingleValueEvent( // fill the list with services
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot
                        for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                            services.add(String.valueOf(dsp.getValue()));
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(                // fill the spinner in the page with contents
                this, android.R.layout.simple_spinner_item, services);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        serviceList.setAdapter(adapter);
        //End of Spinner fill


        //THIS IS THE MAIN SECTION THAT NEEDS WORK. A BUTTON OR EVENT TO RELOAD REDO THE FOLLOWING EVERY TIME SPINNER IS CHANGED.
        //RETREIVAL OF DATA FROM FIREBASE REFERENCES NEEDS TO BE IMPLEMENTED. DIRECT getValue DOES NOT EXIST.
        //EVENTLISTENERS ARE CONFUSING
        String id = String.valueOf(serviceList.getSelectedItem());
        serviceRef = FirebaseDatabase.getInstance().getReference("service").child(id);
        //DatabaseReference nameRef = serviceRef.child("serviceName");
        //DatabaseReference wageRef = serviceRef.child("hourlyRate");
        //service = new Service(nameRef,wageRef);
        //name.setText(service.getServiceName());
        //rate.setText(service.getHourlyRate());

        //


        editServiceButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (statusValidate()){
                   service.setServiceName(name.getText().toString().trim());
                   service.setHourlyRate(Integer.valueOf(rate.getText().toString().trim()));
                   serviceRef.child("serviceName").setValue(service.getServiceName());
                   serviceRef.child("hourlyRate").setValue(service.getHourlyRate());
                }
            }
        });
    }

    public boolean statusValidate(){
            if (id == ""){
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
