package com.example.group.ondemandhomerepair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SetTime extends Activity {
    private EditText startHour;
    private EditText startMinute;
    private EditText endHour;
    private EditText endMinute;
    private RadioGroup radGroup;
    private int year;
    private int month;
    private int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time);

        CalendarView calendarView = findViewById(R.id.calendarSet);
        startHour = findViewById(R.id.startHour2);
        startMinute = findViewById(R.id.startMinute2);
        endHour = findViewById(R.id.startHour2);
        endMinute = findViewById(R.id.endMinute2);
        Button setTime = findViewById(R.id.setTime);
        radGroup = findViewById(R.id.radioGroup);

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

        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(statusValidate()){
                    Timeslot timeslot = new Timeslot(Integer.valueOf(startHour.getText().toString().trim()), Integer.valueOf(startMinute.getText().toString().trim()), Integer.valueOf(endHour.getText().toString().trim()), Integer.valueOf(endMinute.getText().toString().trim()));
                    timeslot.setYear(year);
                    timeslot.setMonth(month);
                    timeslot.setDay(day);
                    //DATA TRANSFER IS HERE. ENTER intent.putExtra("KEY", DATA)


                    //rating select idea start
                    int selectedRadio = radGroup.getCheckedRadioButtonId();
                    RadioButton selectedType = (RadioButton)findViewById(selectedRadio);
                    String rating = selectedType.getText().toString();//rating is a string containing an int of how many stars is the min to show up in the search

                    //rating select idea end


                    Intent intent = new Intent(SetTime.this, ProviderSearch.class);
                    intent.putExtra("1", timeslot.getYear());
                    intent.putExtra("2", timeslot.getMonth());
                    intent.putExtra("3", timeslot.getDay());
                    intent.putExtra("4", timeslot.getSHour());
                    intent.putExtra("5", timeslot.getSMinute());
                    intent.putExtra("6", timeslot.getEHour());
                    intent.putExtra("7", timeslot.getEMinute());
                    startActivity(intent);

                }
            }

        });
    }



    public boolean statusValidate() {
        if (startHour.getText().toString().equals("") || startMinute.getText().toString().equals("") || endMinute.getText().toString().equals("") || endHour.getText().toString().equals("")) {
            Toast.makeText(SetTime.this, "Time Component was left Empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!startHour.getText().toString().trim().matches("[0-9]+") || !endHour.getText().toString().trim().matches("[0-9]+") || !startMinute.getText().toString().trim().matches("[0-9]+") || !endMinute.getText().toString().trim().matches("[0-9]+")) {
            Toast.makeText(SetTime.this, "Times are Non-numberic", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Integer.valueOf(startHour.getText().toString().trim()) > 23 || Integer.valueOf(startHour.getText().toString().trim()) > 23) {
            Toast.makeText(SetTime.this, "Hours are Invalid", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Integer.valueOf(startMinute.getText().toString().trim()) > 59 || Integer.valueOf(endMinute.getText().toString().trim()) > 59) {
            Toast.makeText(SetTime.this, "Minutes are Invalid", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}