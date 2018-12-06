package com.example.group.ondemandhomerepair;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.group.ondemandhomerepair.LogIn.EXTRA_TEXT1;
import static com.example.group.ondemandhomerepair.UserHistoryPage.EXTRA_TEXT2;
import static com.example.group.ondemandhomerepair.UserHistoryPage.EXTRA_TEXT3;

public class RateServicePage extends AppCompatActivity {

    private float rating;
    private String comment;
    private String providerName;
    //private String serviceName;
    //Transfer the Provider's name and the Service's Name from the User's History Activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_service);

        final Intent intent = getIntent();
        final String userName = intent.getStringExtra(EXTRA_TEXT1);
        final String providerName = intent.getStringExtra(EXTRA_TEXT2);
        final String serviceName = intent.getStringExtra(EXTRA_TEXT3);

        final RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        Button submitButton = (Button) findViewById(R.id.btnSubmit);
        final TextView providerTextView = (TextView) findViewById(R.id.txtProviderName);
        final TextView serviceTextView = (TextView) findViewById(R.id.txtServiceName);
        final TextView ratingResultTextView = (TextView) findViewById(R.id.txtRatingResult);
        final EditText commentTextField = (EditText) findViewById(R.id.txtComment);

        providerTextView.setText(EXTRA_TEXT2);
        serviceTextView.setText(EXTRA_TEXT3);





        submitButton.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                ratingResultTextView.setText("Your rating is: " + ratingBar.getRating());
                rating = ratingBar.getRating();
                comment = commentTextField.getText().toString();

                final Rating rateOb = new Rating(rating, comment);

                FirebaseDatabase.getInstance().getReference().child("Users").addListenerForSingleValueEvent(    // get the ID of the provider in firebase
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (final DataSnapshot dsp : dataSnapshot.getChildren()) {
                                    if(providerName.equals(String.valueOf(dsp.child("username").getValue().toString())) && dsp.child("roleType").getValue().toString().equals("Service Provider")){  //find the provider in firebase and get ID
                                        final String providerID = dsp.getKey();

                                        FirebaseDatabase.getInstance().getReference().child("Users").child(providerID).child("myServices").addListenerForSingleValueEvent(    // get the ID of the provider in firebase
                                                new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        for (DataSnapshot dsp2 : dataSnapshot.getChildren()) {
                                                            if(serviceName.equals(String.valueOf(dsp2.child("serviceName").getValue().toString()))){
                                                                final String serviceID = dsp2.getKey();

                                                                FirebaseDatabase.getInstance().getReference("Users").child(providerID).child("myServices").child(serviceID)
                                                                       .child("Ratings").push().setValue(rateOb);
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
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        }
                );
            }
        });
    }



}
