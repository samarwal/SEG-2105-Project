package com.example.group.ondemandhomerepair;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.EXTRA_TEXT;
import static com.example.group.ondemandhomerepair.LogIn.EXTRA_TEXT1;
import static com.example.group.ondemandhomerepair.LogIn.EXTRA_TEXT2;

public class UserGetProviderInfo extends AppCompatActivity {

    TextView selectedTiming;
    TextView providerUser;
    TextView providerDisplay;
    ListView TimesDisplayer;
    Button bookAService;
    String uid;
    final ArrayList<String> displayTimes = new ArrayList<String>();
    final ArrayList<Timeslot> timesList = new ArrayList<Timeslot>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_get_provider_info);

        final Intent intent = getIntent();

        selectedTiming = (TextView)findViewById(R.id.textView19);
        providerUser = (TextView)findViewById(R.id.myTextView);
        providerDisplay= (TextView)findViewById(R.id.infoField);
        TimesDisplayer = (ListView)findViewById(R.id.timesListings);
        bookAService = (Button)findViewById(R.id.BookButt);
        providerUser.setText(intent.getStringExtra(EXTRA_TEXT));
        final String providerUsername = intent.getStringExtra(EXTRA_TEXT);


        TimesDisplayer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedTime = String.valueOf(adapterView.getItemAtPosition(i));
                selectedTiming.setText(selectedTime);
                //Toast.makeText(UserGetProviderInfo.this, selectedTime, Toast.LENGTH_SHORT).show();
            }
        });

        bookAService.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                final String usersName = intent.getStringExtra(EXTRA_TEXT2); // get usersname
                String providersName = String.valueOf(providerUser.getText());     //get providers anme
                String bookTimes = String.valueOf(selectedTiming.getText());
                String service = intent.getStringExtra(EXTRA_TEXT1);
                final Booking sendBook = new Booking(usersName,providersName,service,bookTimes);
                //Toast.makeText(UserGetProviderInfo.this, usersName, Toast.LENGTH_SHORT).show();
                FirebaseDatabase.getInstance().getReference("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot dsp : dataSnapshot.getChildren()){
                            if(dsp.child("username").getValue().equals(usersName)){
                                FirebaseDatabase.getInstance().getReference("Users").child(dsp.getKey()).child("bookingHistory").push().setValue(sendBook);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

        FirebaseDatabase.getInstance().getReference().child("Users").addListenerForSingleValueEvent( // get the ID of the provider in firebase
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                            if(providerUsername.equals(String.valueOf(dsp.child("username").getValue().toString())) && dsp.child("roleType").getValue().toString().equals("Service Provider")){  //find the provider in firebase and get ID
                                uid = String.valueOf(dsp.getKey());
                                FirebaseDatabase.getInstance().getReference().child("ProviderProfileInfo").child(uid).addListenerForSingleValueEvent(new ValueEventListener() { // once id is obtained get provider info
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        boolean checker = dataSnapshot.exists();
                                        if(checker) { // check if provider has information with account
                                            ProviderProfile temp = new ProviderProfile();
                                            temp.setCompany(String.valueOf(dataSnapshot.child("company").getValue()));
                                            temp.setAddress(String.valueOf(dataSnapshot.child("address").getValue()));
                                            temp.setPhonenumber(Integer.valueOf(String.valueOf(dataSnapshot.child("phonenumber").getValue())));
                                            temp.setProfiledescription(String.valueOf(dataSnapshot.child("profiledescription").getValue()));
                                            providerDisplay.setText(temp.toString());   // display providers info
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                                FirebaseDatabase.getInstance().getReference("Users").child(uid).child("myTimes").addListenerForSingleValueEvent(new ValueEventListener() { // get the times for provider
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        Boolean look = dataSnapshot.exists();   // check if provider has times
                                        if(look) {
                                            for (DataSnapshot dsp2 : dataSnapshot.getChildren()) {  // if they do display times
                                                int a = Integer.valueOf(String.valueOf(dsp2.child("sHour").getValue()));
                                                int b = Integer.valueOf(String.valueOf(dsp2.child("sMinute").getValue()));
                                                int c = Integer.valueOf(String.valueOf(dsp2.child("eHour").getValue()));
                                                int d = Integer.valueOf(String.valueOf(dsp2.child("eMinute").getValue()));

                                                Timeslot temp = new Timeslot(a, b, c, d);
                                                temp.setDay(Integer.valueOf(String.valueOf(dsp2.child("day").getValue())));
                                                temp.setMonth(Integer.valueOf(String.valueOf(dsp2.child("month").getValue())));
                                                temp.setYear(Integer.valueOf(String.valueOf(dsp2.child("year").getValue())));
                                                displayTimes.add(temp.toString());
                                                timesList.add(temp);
                                            }
                                            ArrayAdapter<String> hold = new ArrayAdapter<String>(UserGetProviderInfo.this, android.R.layout.simple_list_item_1,displayTimes);
                                            hold.setDropDownViewResource(android.R.layout.activity_list_item);
                                            TimesDisplayer.setAdapter(hold);
                                        }
                                        if(!look){                                                       //otherwise give a message saying no
                                            displayTimes.add("This provider doesn't have times");
                                            ArrayAdapter<String> hold = new ArrayAdapter<String>(UserGetProviderInfo.this, android.R.layout.simple_list_item_1,displayTimes);
                                            hold.setDropDownViewResource(android.R.layout.activity_list_item);
                                            TimesDisplayer.setAdapter(hold);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });


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