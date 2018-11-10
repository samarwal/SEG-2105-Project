package com.example.group.ondemandhomerepair;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import org.w3c.dom.Text;

import java.util.*;


public class DeletingServicePage extends AppCompatActivity {

    TextView errorMessage = (TextView)findViewById(R.id.errorText);
    EditText baseName = (EditText)findViewById(R.id.deleteServiceText);
    Service service;
    DatabaseReference serviceRef;
    String ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleting_service_page);

        final Spinner serviceList = (Spinner)findViewById(R.id.servicesSpinner);
        final ArrayList<String> services = new ArrayList<String>();             // create a list and fill the spinner with list contents

       FirebaseDatabase.getInstance().getReference().child("Services").addListenerForSingleValueEvent( // fill the list with services
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot
                        for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                            Service temp = new Service("a",0);
                            temp.setServiceName(String.valueOf(dsp.child("serviceName").getValue()));
                            services.add(temp.getServiceName());
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
        serviceList.setAdapter(adapter);                                   // set spinner adapter to adapter with contents

        Button deletingButton = (Button)findViewById(R.id.deletingServiceButt);     // deleting services section
        deletingButton.setOnClickListener(new View.OnClickListener() {
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
                                    serviceRef.removeValue();
                                    errorMessage.setText("Removal Successful!");
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
            errorMessage.setText("! service does not exist");
            return false;
        }

        errorMessage.setText("");
        return true;
    }
}
