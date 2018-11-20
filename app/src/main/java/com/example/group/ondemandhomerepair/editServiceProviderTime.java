package com.example.group.ondemandhomerepair;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.group.ondemandhomerepair.LogIn.EXTRA_TEXT1;

public class editServiceProviderTime extends Activity {

    private EditText monday,tuesday,wednesday,thursday,friday,saturday,sunday;
    private Button SetTimesButton;
    String providerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_service_provider_time);


        monday = findViewById(R.id.mondayTime);
        tuesday = findViewById(R.id.tuesdayTime);
        wednesday = findViewById(R.id.wednesdayTime);
        thursday = findViewById(R.id.thursdayTime);
        friday = findViewById(R.id.fridayTime);
        saturday = findViewById(R.id.saturdayTime);
        sunday = findViewById(R.id.sundayTime);
        SetTimesButton = findViewById(R.id.setTimesButt);

        Intent intent = getIntent();
        final String providerName = intent.getStringExtra(EXTRA_TEXT1);





        //TODO This does not work for some reason. the code appears the exact same as in editServiceProviderServices.java but causes to crash if its not uncommented. other than this it should work
        FirebaseDatabase.getInstance().getReference().child("Users").addListenerForSingleValueEvent(    // get the ID of the provider in firebase
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                            if(providerName.equals(String.valueOf(dsp.child("username").getValue().toString())) && dsp.child("roleType").getValue().toString().equals("Service Provider")){  //find the provider in firebase and get ID  //TODO this line is what makes it crash
                                providerID = dsp.getKey();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );


        SetTimesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Monday = monday.getText().toString().trim();
                final String Tuesday = tuesday.getText().toString().trim();
                final String Wednesday = wednesday.getText().toString().trim();
                final String Thursday = thursday.getText().toString().trim();
                final String Friday = friday.getText().toString().trim();
                final String Saturday = saturday.getText().toString().trim();
                final String Sunday = sunday.getText().toString().trim();
                if(statusValidate()){ // check if the text is empty

                    FirebaseDatabase.getInstance().getReference("Users").child(providerID).child("myTimes").push().setValue(Monday);             // add the service to firebase under providers list of services
                    FirebaseDatabase.getInstance().getReference("Users").child(providerID).child("myTimes").push().setValue(Tuesday);
                    FirebaseDatabase.getInstance().getReference("Users").child(providerID).child("myTimes").push().setValue(Wednesday);
                    FirebaseDatabase.getInstance().getReference("Users").child(providerID).child("myTimes").push().setValue(Thursday);
                    FirebaseDatabase.getInstance().getReference("Users").child(providerID).child("myTimes").push().setValue(Friday);
                    FirebaseDatabase.getInstance().getReference("Users").child(providerID).child("myTimes").push().setValue(Saturday);
                    FirebaseDatabase.getInstance().getReference("Users").child(providerID).child("myTimes").push().setValue(Sunday);

                }
            }
        });
        /*
        providerDeleteServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Validate(providerSerivceinput.getText().toString().trim())){ // check if the text is empty
                    FirebaseDatabase.getInstance().getReference("Users").child(providerID).child("myServices").addListenerForSingleValueEvent(    // get the info of the provider
                            new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                                        if(providerSerivceinput.getText().toString().trim().equals(dsp.getValue().toString())){
                                            FirebaseDatabase.getInstance().getReference("Users").child(providerID)
                                                    .child("myServices").child(dsp.getKey()).removeValue();

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
        });*/


    }
    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_service_provider_time);

        monday = findViewById(R.id.mondayTime);
        tuesday = findViewById(R.id.tuesdayTime);
        wednesday = findViewById(R.id.wednesdayTime);
        thursday = findViewById(R.id.thursdayTime);
        friday = findViewById(R.id.fridayTime);
        saturday = findViewById(R.id.saturdayTime);
        sunday = findViewById(R.id.sundayTime);
        SetTimesButton = findViewById(R.id.setTimesButt);

        SetTimesButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                if (statusValidate()){
                    final String Monday = monday.getText().toString().trim();
                    final String Tuesday = tuesday.getText().toString().trim();
                    final String Wednesday = wednesday.getText().toString().trim();
                    final String Thursday = thursday.getText().toString().trim();
                    final String Firday = friday.getText().toString().trim();
                    final String Saturday = saturday.getText().toString().trim();
                    final String Sunday = sunday.getText().toString().trim();


                    User user = new User(
                            "username",
                            "Service Provider"

                    );

                    FirebaseDatabase.getInstance().getReference("Services")
                            .push().setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(editServiceProviderTime.this, "Availability change successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(editServiceProviderTime.this, editServiceProviderTime.class));
                            } else {
                                Toast.makeText(editServiceProviderTime.this, "Availability change failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }*/
    public boolean statusValidate() {

        if (monday.getText().toString().equals("")) {
            Toast.makeText(editServiceProviderTime.this, "Monday is Empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (tuesday.getText().toString().equals("")) {
            Toast.makeText(editServiceProviderTime.this, "Tuesday is Empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (wednesday.getText().toString().equals("")) {
            Toast.makeText(editServiceProviderTime.this, "Wednesday is Empty", Toast.LENGTH_SHORT).show();;
            return false;
        }
        if (thursday.getText().toString().equals("")) {
            Toast.makeText(editServiceProviderTime.this, "Thursday is Empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (friday.getText().toString().equals("")) {
            Toast.makeText(editServiceProviderTime.this, "Friday is Empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (saturday.getText().toString().equals("")) {
            Toast.makeText(editServiceProviderTime.this, "Saturday is Empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (sunday.getText().toString().equals("")) {
            Toast.makeText(editServiceProviderTime.this, "Sunday is Empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        //if statement for firebase account duplicates
        //Use Object.getText.toString to compare with any string values in the database for username, password, and radioButton string.
        return true;
    }

}
