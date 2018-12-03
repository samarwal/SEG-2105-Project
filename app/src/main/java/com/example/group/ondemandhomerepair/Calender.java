package com.example.group.ondemandhomerepair;

import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.widget.CalendarView;
import android.widget.EditText;

public class Calender extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        CalendarView calendarView = findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                //stuff that runs when the new date is clicked

                String date = i+"/"+i1+""+i2;
                //i = year / i1 = month /  12 = day
            }
        });
    }
}
