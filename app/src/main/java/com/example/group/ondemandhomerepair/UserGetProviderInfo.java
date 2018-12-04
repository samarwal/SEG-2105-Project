package com.example.group.ondemandhomerepair;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.Intent.EXTRA_TEXT;
import static com.example.group.ondemandhomerepair.LogIn.EXTRA_TEXT1;

public class UserGetProviderInfo extends AppCompatActivity {

    TextView providerUser;
    TextView providerDisplay;
    String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_get_provider_info);

        final Intent intent = getIntent();

        providerUser = (TextView)findViewById(R.id.myTextView);
        providerDisplay= (TextView)findViewById(R.id.infoField);
        providerUser.setText(intent.getStringExtra(EXTRA_TEXT));
        final String providerUsername = intent.getStringExtra(EXTRA_TEXT);

        FirebaseDatabase.getInstance().getReference().child("Users").addListenerForSingleValueEvent( // get the ID of the provider in firebase
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                            if(providerUsername.equals(String.valueOf(dsp.child("username").getValue().toString())) && dsp.child("roleType").getValue().toString().equals("Service Provider")){  //find the provider in firebase and get ID
                                uid = dsp.getKey();
                                FirebaseDatabase.getInstance().getReference().child("ProviderProfileInfo").child(uid).addListenerForSingleValueEvent(new ValueEventListener() { // once id is obtained get provider info
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        ProviderProfile temp = new ProviderProfile();
                                        temp.setCompany(String.valueOf(dataSnapshot.child("company").getValue()));
                                        temp.setAddress(String.valueOf(dataSnapshot.child("address").getValue()));
                                        temp.setPhonenumber(Integer.valueOf(String.valueOf(dataSnapshot.child("phonenumber").getValue())));
                                        temp.setProfiledescription(String.valueOf(dataSnapshot.child("profiledescription").getValue()));
                                        providerDisplay.setText(temp.toString());
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
