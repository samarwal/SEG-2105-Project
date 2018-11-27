package com.example.group.ondemandhomerepair;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


import static com.example.group.ondemandhomerepair.LogIn.EXTRA_TEXT1;

public class WelcomePage extends AppCompatActivity {

    ListView show;
    ArrayAdapter<String> adapter;
    DatabaseReference databaseReference;
    FirebaseUser user;
    List<String> itemlist;
    String uid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        Spinner accountsList = (Spinner)findViewById(R.id.accountList); // find drop down and set as invisible
        accountsList.setVisibility(View.GONE);

        Button addServiceButton = (Button)findViewById(R.id.addServiceButt);    // find admin buttons and set to invisible
        addServiceButton.setVisibility(View.GONE);

        Button editInformationButton = (Button)findViewById(R.id.editInfoButt);    // find admin buttons and set to invisible
        editInformationButton.setVisibility(View.GONE);

        Button deleteServiceButton = (Button)findViewById(R.id.deleteServiceButt);
        deleteServiceButton.setVisibility(View.GONE);

        Button editServiceButton = (Button)findViewById(R.id.editServiceButt);
        editServiceButton.setVisibility(View.GONE);

        Button advancedInfoButton = (Button)findViewById(R.id.providerAdvancedButt);
        advancedInfoButton.setVisibility(View.GONE);

        Button searchProvider = (Button) findViewById(R.id.searchProv);
        searchProvider.setVisibility(View.GONE);

        final Intent intent = getIntent();                                    // create intent by taking from previous intent

        TextView userName = (TextView)findViewById(R.id.userField);        // get text views
        TextView userType = (TextView)findViewById(R.id.typeField);

        userName.setText(intent.getStringExtra(EXTRA_TEXT1));           // get username from EXTRA_TEXT1 of intent
        userType.setText(intent.getStringExtra(LogIn.EXTRA_TEXT2));           // get userType from EXTRA_TEXT2 of intent

        show = (ListView)findViewById(R.id.providersServices);
        show.setVisibility(View.GONE);


        user = FirebaseAuth.getInstance().getCurrentUser();
        itemlist = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        FirebaseDatabase.getInstance().getReference().child("Users").addListenerForSingleValueEvent( // get the ID of the provider in firebase
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                            if(intent.getStringExtra(EXTRA_TEXT1).equals(String.valueOf(dsp.child("username").getValue().toString())) && dsp.child("roleType").getValue().toString().equals("Service Provider")){  //find the provider in firebase and get ID
                                uid = dsp.getKey();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );

        if(userType.getText().equals("Admin")){                             // admin tools show when admin is logged in
            accountsList.setVisibility(View.VISIBLE);
            addServiceButton.setVisibility(View.VISIBLE);

            addServiceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    openAddServices();
                }
            });

            deleteServiceButton.setVisibility(View.VISIBLE);
            deleteServiceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    openDeleteServices();
                }
            });
            editServiceButton.setVisibility(View.VISIBLE);
            editServiceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    openEditServices();
                }
            });
        }

        if(userType.getText().equals("User")){
            searchProvider.setVisibility(View.VISIBLE);
            searchProvider.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    startActivity(new Intent(WelcomePage.this, ProviderSearch.class));
                }
            });
        }


        if(userType.getText().equals("Provider")){                             // provider tools show when provider is logged in

            editInformationButton.setVisibility(View.VISIBLE);
            advancedInfoButton.setVisibility(View.VISIBLE);
            show.setVisibility(View.VISIBLE);

            editInformationButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    openEditInformation(intent.getStringExtra(EXTRA_TEXT1));
                }
            });
            advancedInfoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openAdvancedInfo(intent.getStringExtra(EXTRA_TEXT1));
                }
            });

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    itemlist.clear();
                    String user_address = String.valueOf(dataSnapshot.child("ProviderProfileInfo").child(uid).child("address").getValue());
                    String user_company = String.valueOf(dataSnapshot.child("ProviderProfileInfo").child(uid).child("company").getValue());
                    String user_phonenumber = String.valueOf(dataSnapshot.child("ProviderProfileInfo").child(uid).child("phonenumber").getValue());
                    String user_desc = String.valueOf(dataSnapshot.child("ProviderProfileInfo").child(uid).child("profiledescription").getValue());
                    String user_license = String.valueOf(dataSnapshot.child("ProviderProfileInfo").child(uid).child("providerlicense").getValue());

                    itemlist.add("Address: " + user_address);
                    itemlist.add("Name: " + user_company);
                    itemlist.add("Phone Number: " + user_phonenumber);
                    itemlist.add(user_desc);
                    itemlist.add("Licensed: " + user_license);

                    adapter = new ArrayAdapter<>(WelcomePage.this, android.R.layout.simple_list_item_1, itemlist);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    show.setAdapter(adapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

    }
    public void openAddServices(){
        Intent intent = new Intent(this, AddServicePage.class);
        startActivity(intent);
    }
    public void openDeleteServices(){
        Intent intent = new Intent(this, DeletingServicePage.class);
        startActivity(intent);
    }
    public void openEditServices(){
        Intent intent = new Intent(this, EditServicePage.class);
        startActivity(intent);
    }
    public void openEditInformation(String providerUser){
        Intent intent = new Intent(this, EditServiceProviderInfo.class);
        intent.putExtra(EXTRA_TEXT1, providerUser);            // send username of provider to page where they can edit info
        startActivity(intent);
    }

    public void openAdvancedInfo(String providerUser){
        Intent intent = new Intent(this, providerAdvancedInfo.class);
        intent.putExtra(EXTRA_TEXT1, providerUser);
        startActivity(intent);
    }

    public void openProviderSearch(){
        Intent intent = new Intent(this, ProviderSearch.class);
        startActivity(intent);
    }

}
