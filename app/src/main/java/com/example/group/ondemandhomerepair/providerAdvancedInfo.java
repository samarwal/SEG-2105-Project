package com.example.group.ondemandhomerepair;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceConfigurationError;

import static com.example.group.ondemandhomerepair.LogIn.EXTRA_TEXT1;


public class providerAdvancedInfo extends AppCompatActivity {

    String providerID = "not changed";
    final ArrayList<String> providerServiceList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_advanced_info);

        final Intent intent = getIntent();
        final String providerUsername = intent.getStringExtra(EXTRA_TEXT1);
        Spinner myServiceList = (Spinner)findViewById(R.id.myproviderServices);



        FirebaseDatabase.getInstance().getReference().child("Users").addListenerForSingleValueEvent(    // get the ID of the provider in firebase
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                            if(providerUsername.equals(String.valueOf(dsp.child("username").getValue().toString())) && dsp.child("roleType").getValue().toString().equals("Service Provider")) {  //find the provider in firebase and get ID
                                providerID = dsp.getKey();
                            }
                        }
                        for (DataSnapshot dsp : dataSnapshot.child(providerID).child("myServices").getChildren()) {
                            Service temp = new Service("a", 0);
                            temp.setServiceName(String.valueOf(dsp.child("serviceName").getValue()));
                            temp.setHourlyRate(Integer.valueOf(dsp.child("hourlyRate").getValue(int.class)));
                            providerServiceList.add(temp.toString());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(                // fill the spinner in the page with contents
                this, android.R.layout.simple_spinner_item, providerServiceList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        myServiceList.setAdapter(adapter);
    }
}
