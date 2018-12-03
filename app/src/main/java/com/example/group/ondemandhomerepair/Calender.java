package com.example.group.ondemandhomerepair;

import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

public class Calender extends Activity {
    private EditText startHour;
    private EditText startMinute;
    private EditText endHour;
    private EditText endMinute;
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

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                //stuff that runs when the new date is clicked

                String date = i+"/"+i1+""+i2;
                //i = year / i1 = month /  12 = day
            }
        });

        addTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(statusValidate()){
                //firebase stuff
                }
            }

        });
    }
    public boolean statusValidate() {

        if (startHour.getText().toString().equals("") || startMinute.getText().toString().equals("") || endMinute.getText().toString().equals("") || endHour.getText().toString().equals("")) {
            Toast.makeText(Calender.this, "Time Component was left Empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (startHour.getText().toString().trim().matches( "[0-9]+") || endHour.getText().toString().trim().matches( "[0-9]+") || startMinute.getText().toString().trim().matches( "[0-9]+") || endMinute.getText().toString().trim().matches( "[0-9]+")) {
            Toast.makeText(Calender.this, "Times are Non-numberic", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Integer.valueOf(startHour.getText().toString().trim()) > 23 || Integer.valueOf(startHour.getText().toString().trim()) > 23) {
            Toast.makeText(Calender.this, "Hours are Invalid", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Integer.valueOf(startMinute.getText().toString().trim()) > 59 || Integer.valueOf(endMinute.getText().toString().trim()) > 59) {
            Toast.makeText(Calender.this, "Hours are Invalid", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
