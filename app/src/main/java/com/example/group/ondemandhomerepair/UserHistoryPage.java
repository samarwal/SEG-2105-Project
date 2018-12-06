package com.example.group.ondemandhomerepair;

import android.content.Intent;
import android.media.Rating;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.group.ondemandhomerepair.LogIn.EXTRA_TEXT1;

public class UserHistoryPage extends AppCompatActivity {

    //private static final Object RateServicePage = ;
    private ArrayList<String> bookings = new ArrayList<String>();
    private String userID;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_history_page);

        final Intent intent = getIntent();
        final String userName = intent.getStringExtra(EXTRA_TEXT1);

        listView = (ListView) findViewById(R.id.listView);

        FirebaseDatabase.getInstance().getReference().child("Users").addListenerForSingleValueEvent(    // get the ID of the provider in firebase
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                            if(userName.equals(String.valueOf(dsp.child("username").getValue().toString())) && dsp.child("roleType").getValue().toString().equals("Basic User")){  //find the provider in firebase and get ID
                                userID = dsp.getKey();
                                FirebaseDatabase.getInstance().getReference().child("Users").child(userID).child("bookingHistory").addListenerForSingleValueEvent( // fill the list with services
                                        new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                boolean checkMe = dataSnapshot.exists();
                                                if(checkMe){
                                                    for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                                                        Booking temp = new Booking("fill","fill", "fill", "fill");
                                                        temp.setUser(String.valueOf(dsp.child("user").getValue()));
                                                        temp.setProvider(String.valueOf(dsp.child("provider").getValue()));
                                                        temp.setService(String.valueOf(dsp.child("service").getValue()));
                                                        temp.setTimes(String.valueOf(dsp.child("times").getValue()));
                                                        temp.setTimes(String.valueOf(dsp.child("date").getValue()));
                                                        bookings.add(temp.toString());

                                                    }
                                                    //CustomAdapter customAdapter = new CustomAdapter(bookings);
                                                    ArrayAdapter<String>  basic = new ArrayAdapter<String>(UserHistoryPage.this, android.R.layout.simple_list_item_1, bookings);
                                                    basic.setDropDownViewResource(android.R.layout.activity_list_item);
                                                    listView.setAdapter(basic);
                                                }
                                                if(!checkMe){
                                                    bookings.add("No past bookings");
                                                    ArrayAdapter<String>  basic = new ArrayAdapter<String>(UserHistoryPage.this, android.R.layout.simple_list_item_1, bookings);
                                                    basic.setDropDownViewResource(android.R.layout.activity_list_item);
                                                    listView.setAdapter(basic);
                                                }

                                            }
                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {
                                                //handle databaseError
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





       ;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(view.getContext(), RateServicePage.class);
            }
        });
    }

    class CustomAdapter extends BaseAdapter {

        private ArrayList<Booking> bookingList = new ArrayList<Booking>();

        public CustomAdapter(ArrayList<Booking> bL) {
            bookingList = bL;
        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.booking_list_layout,null);

            TextView txtProvName = (TextView) view.findViewById(R.id.txtProvName);
            TextView txtServName = (TextView) view.findViewById(R.id.txtServName);
            TextView txtDate = (TextView) view.findViewById(R.id.txtDate);
            TextView txtRatePrompt = (TextView) view.findViewById(R.id.txtRatePrompt);

            txtProvName.setText(bookingList.get(i).getProvider());
            //txtServName.setText(bookings.get(i).getService());
            //txtDate.setText(bookings.get(i).getDate());
            return null;
        }
    }
}
