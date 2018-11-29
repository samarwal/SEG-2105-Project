package com.example.group.ondemandhomerepair;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    private EditText baseName;
    private TextView errorMessage;
    private Spinner serviceList;
    List<String> services;
    DatabaseReference serviceRef;
    String ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_service_page);
        name = findViewById(R.id.editServiceNameText);
        rate = findViewById(R.id.editServiceWageText);
        baseName = findViewById(R.id.editBaseName);
        errorMessage = findViewById(R.id.errorMessageEdit);
        editServiceButt = findViewById(R.id.editServiceButt);
        serviceList = (Spinner)findViewById(R.id.spinner2);
        services = new ArrayList<String>();
        ref = "";

        //Note This is the Spinner Fill From DeletingServices Code

        FirebaseDatabase.getInstance().getReference().child("Services").addListenerForSingleValueEvent( // fill the list with services
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot
                        for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                            Service temp = new Service("",0);
                            temp.setServiceName(String.valueOf(dsp.child("serviceName").getValue()));
                            temp.setHourlyRate(Integer.valueOf(String.valueOf(dsp.child("hourlyRate").getValue())));
                            //services.add(String.valueOf(dsp.getKey()));
                            services.add(temp.toString());
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }

                });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(                // fill the spinner in the page with contents
                this,android.R.layout.simple_spinner_item , services );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        serviceList.setAdapter(adapter);
        //End of Spinner fill
        /*
        serviceList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ref = String.valueOf(serviceList.getSelectedItem()); //Reference String
                serviceRef = FirebaseDatabase.getInstance().getReference().child("Services").child(ref);
                //name.setText("Nope"); //TESTING
                serviceRef.addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                service = new Service(String.valueOf(dataSnapshot.child("serviceName").getValue()), Integer.valueOf(String.valueOf(dataSnapshot.child("hourlyRate"))));
                                name.setText(service.getServiceName());
                                name.setText("Nope");
                                rate.setText(String.valueOf(service.getHourlyRate()));
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                //handle databaseError
                            }
                        });
            }
            public void onNothingSelected(AdapterView<?> parent) {
                ref = "";
            }
        });
        */


        //


        editServiceButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                FirebaseDatabase.getInstance().getReference().child("Services").addListenerForSingleValueEvent( // fill the list with services
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                //Get map of users in datasnapshot
                                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                                    if (String.valueOf(dsp.child("serviceName").getValue()).equals(baseName.getText().toString().trim())) {
                                        ref = dsp.getKey();
                                        serviceRef = FirebaseDatabase.getInstance().getReference().child("Services").child(ref);

                                    }
                                }
                                if (statusValidate()) {
                                    service = new Service(name.getText().toString().trim(), Integer.valueOf(rate.getText().toString().trim()));
                                    serviceRef.child("serviceName").setValue(service.getServiceName());
                                    serviceRef.child("hourlyRate").setValue(service.getHourlyRate());
                                    errorMessage.setText("Editing Successful!");
                                    ref = "";

                                }
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                //handle databaseError
                            }

                        });


            }
        });
    }

    public boolean statusValidate(){
            if (ref == ""){
                errorMessage.setText("! service(before) does not exist");
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
            errorMessage.setText("");
            return true;
    }
}
