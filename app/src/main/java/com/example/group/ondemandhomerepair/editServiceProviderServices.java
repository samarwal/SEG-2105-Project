package com.example.group.ondemandhomerepair;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.group.ondemandhomerepair.LogIn.EXTRA_TEXT1;

public class editServiceProviderServices extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_service_provider_services);

        Intent intent = getIntent();
        String providerName = intent.getStringExtra(EXTRA_TEXT1);
        Spinner serviceDropDown = (Spinner) findViewById(R.id.providerServicesSpinner);
        TextView providerSerivceAdd = (TextView)findViewById(R.id.providerServiceText);
        Button providerAddServiceButton = (Button)findViewById(R.id.providerAddServiceButt);

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
        serviceDropDown.setAdapter(adapter);                                // set spinner adapter




    }


}
