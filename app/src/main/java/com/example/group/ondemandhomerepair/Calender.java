package com.example.group.ondemandhomerepair;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.group.ondemandhomerepair.LogIn.EXTRA_TEXT1;

public class Calender extends Activity {
    private EditText startHour;
    private EditText startMinute;
    private EditText endHour;
    private EditText endMinute;
    private int year;
    private int month;
    private int day;
    private String providerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        CalendarView calendarView = findViewById(R.id.calendarView);
        startHour = findViewById(R.id.startHour);
        startMinute = findViewById(R.id.startMinute);
        endHour = findViewById(R.id.endHour);
        endMinute = findViewById(R.id.endMiinute);
        Button addTime = findViewById(R.id.addTime);
        Intent intent = getIntent();
        final String providerName = intent.getStringExtra(EXTRA_TEXT1);

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

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                //stuff that runs when the new date is clicked
                year = i;
                month = i1;
                day = i2;
                //date = i1+"/"+i2+"/"+i;
                //i = year / i1 = month /  12 = day
            }
        });

        addTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(statusValidate()){
                    Timeslot timeslot = new Timeslot(Integer.valueOf(startHour.getText().toString().trim()), Integer.valueOf(startMinute.getText().toString().trim()), Integer.valueOf(endHour.getText().toString().trim()), Integer.valueOf(endMinute.getText().toString().trim()));
                    timeslot.setYear(year);
                    timeslot.setMonth(month);
                    timeslot.setDay(day);
                    FirebaseDatabase.getInstance().getReference("Users").child(providerID).child("myTimes").push().setValue(timeslot);
                }
            }

        });
    }
    public boolean statusValidate() {

        if (startHour.getText().toString().equals("") || startMinute.getText().toString().equals("") || endMinute.getText().toString().equals("") || endHour.getText().toString().equals("")) {
            Toast.makeText(Calender.this, "Time Component was left Empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!startHour.getText().toString().trim().matches( "[0-9]+") || !endHour.getText().toString().trim().matches( "[0-9]+") || !startMinute.getText().toString().trim().matches( "[0-9]+") || !endMinute.getText().toString().trim().matches( "[0-9]+")) {
            Toast.makeText(Calender.this, "Times are Non-numberic", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Integer.valueOf(startHour.getText().toString().trim()) > 23 || Integer.valueOf(startHour.getText().toString().trim()) > 23) {
            Toast.makeText(Calender.this, "Hours are Invalid", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Integer.valueOf(startMinute.getText().toString().trim()) > 59 || Integer.valueOf(endMinute.getText().toString().trim()) > 59) {
            Toast.makeText(Calender.this, "Minutes are Invalid", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
