package com.example.group.ondemandhomerepair;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class editServiceProviderTime extends Activity {

    private EditText monday,tuesday,wednesday,thursday,friday,saturday,sunday;
    private Button SetTimesButton;
    private TextView errorMessage;

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
            /*
            The correct way????


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

            });*/
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
                            /*,         //this does not work. talk with group
                            Monday,
                            Tuesday,
                            Wednesday,
                            Thursday,
                            Firday,
                            Saturday,
                            Sunday*/
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

    }
    public boolean statusValidate() {

        if (monday.getText().toString().equals("")) {
            errorMessage.setText("! monday is empty");
            return false;
        }
        if (tuesday.getText().toString().equals("")) {
            errorMessage.setText("! tuesday is empty");
            return false;
        }
        if (wednesday.getText().toString().equals("")) {
            errorMessage.setText("! wednesday is empty");
            return false;
        }
        if (thursday.getText().toString().equals("")) {
            errorMessage.setText("! thursday is empty");
            return false;
        }
        if (friday.getText().toString().equals("")) {
            errorMessage.setText("! friday is empty");
            return false;
        }
        if (saturday.getText().toString().equals("")) {
            errorMessage.setText("! saturday is empty");
            return false;
        }
        if (sunday.getText().toString().equals("")) {
            errorMessage.setText("! sunday is empty");
            return false;
        }

        //if statement for firebase account duplicates
        //Use Object.getText.toString to compare with any string values in the database for username, password, and radioButton string.
        return true;
    }

}
