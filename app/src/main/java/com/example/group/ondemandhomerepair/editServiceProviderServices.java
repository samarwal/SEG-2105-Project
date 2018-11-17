package com.example.group.ondemandhomerepair;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.group.ondemandhomerepair.LogIn.EXTRA_TEXT1;

public class editServiceProviderServices extends Activity {

    String providerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_service_provider_services);

        Intent intent = getIntent();
        final String providerName = intent.getStringExtra(EXTRA_TEXT1);
        Spinner serviceDropDown = (Spinner) findViewById(R.id.providerServicesSpinner);
        final EditText providerSerivceinput = (EditText)findViewById(R.id.providerServiceText);
        Button providerAddServiceButton = (Button)findViewById(R.id.providerAddServiceButt);
        Button providerDeleteServiceButton = (Button)findViewById(R.id.provderServiceDeleteButt);

        FirebaseDatabase.getInstance().getReference().child("Users").addListenerForSingleValueEvent(    // get the ID of the provider in firebase
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                            if(providerName.equals(String.valueOf(dsp.child("username").getValue().toString())) && dsp.child("roleType").getValue().toString().equals("Service Provider")){  //find the provider in firebase and get ID
                                providerID = dsp.getKey();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );

        final ArrayList<String> services = new ArrayList<String>();             // create a list of services and fill the spinner with list contents

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
        serviceDropDown.setAdapter(adapter);                                // set spinner adapter

        providerAddServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Validate(providerSerivceinput.getText().toString().trim())){ // check if the text is empty
                    for(int i = 0; i < services.size(); i++){
                        if(services.get(i).equals(providerSerivceinput.getText().toString().trim())){ // check if the service provider entered exists
                            FirebaseDatabase.getInstance().getReference("https://seg-2105-project.firebaseio.com/Users/" + providerID + "/myServices")
                                    .push().setValue(providerSerivceinput.getText().toString().trim());                     // add the service to firebase under providers list of services
                        }
                    }
                }
            }
        });

        providerDeleteServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Validate(providerSerivceinput.getText().toString().trim())){ // check if the text is empty
                    FirebaseDatabase.getInstance().getReference("https://seg-2105-project.firebaseio.com/Users/" + providerID + "/myServices").addListenerForSingleValueEvent(    // get the info of the provider
                            new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                                        if(providerSerivceinput.getText().toString().trim().equals(dsp.getValue().toString())){
                                            DatabaseReference selectedService = FirebaseDatabase.getInstance().getReference(dsp.getKey()).child(dsp.getKey());
                                            selectedService.removeValue();
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            }
                    );
                }
            }
        });


    }
    public boolean Validate(String a){  // takes the string that the provider enters and checks if it's empty

        if(a.equals("")){
            Toast.makeText(this, "Please enter valid service", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}
