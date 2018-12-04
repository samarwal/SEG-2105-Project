package com.example.group.ondemandhomerepair;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class ProviderSearch extends AppCompatActivity {

    private EditText mSearchField;
    private Button mSearchBtn;
    private Button mSearchByRate;
    private Button mSearchForProvider;
    private ListView mResultList;
    private DatabaseReference mUserDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_search);

        mUserDatabase = FirebaseDatabase.getInstance().getReference("Services");

        mSearchField = (EditText) findViewById(R.id.search_field);
        mSearchBtn = (Button) findViewById(R.id.button2);
        mSearchByRate = (Button)findViewById(R.id.rateSearchButt);
        mSearchForProvider = (Button)findViewById((R.id.button5));
        mResultList = (ListView) findViewById(R.id.listings);
//        mResultList.setHasFixedSize(true);
//        mResultList.setLayoutManager(new LinearLayoutManager(this));


        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText = mSearchField.getText().toString();
                firebaseUserSearch(searchText);
            }
        });

        mSearchByRate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String searchText = mSearchField.getText().toString();
                firebaseByRateSearch(searchText);
            }
        });

        mSearchForProvider.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String searchText = mSearchField.getText().toString();
                firebaseByServiceSearch(searchText);
            }
        });

    }

    private void firebaseUserSearch(final String searchText) {

        final ArrayList<String> temp = new ArrayList<String>();

        FirebaseDatabase.getInstance().getReference("Services").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dsp : dataSnapshot.getChildren()) {
                    if (String.valueOf(dsp.child("serviceName").getValue()).equals(searchText)) {      // if service name matches searched one add it to list
                        Service value = new Service("a", 0);
                        value.setServiceName(String.valueOf(dsp.child("serviceName").getValue()));
                        value.setHourlyRate(Integer.valueOf(String.valueOf(dsp.child("hourlyRate").getValue())));
                        //Toast.makeText(ProviderSearch.this, value.toString(), Toast.LENGTH_SHORT).show();
                        temp.add(value.toString());
                    }
                    if(searchText.length() == 0){                                            // if they dont enter anything show all services
                        Service value = new Service("a", 0);
                        value.setServiceName(String.valueOf(dsp.child("serviceName").getValue()));
                        value.setHourlyRate(Integer.valueOf(String.valueOf(dsp.child("hourlyRate").getValue())));
                        temp.add(value.toString());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ArrayAdapter<String> hold = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,temp);
        hold.setDropDownViewResource(android.R.layout.activity_list_item);
        mResultList.setAdapter(hold);

//        Toast.makeText(ProviderSearch.this, "Started Search", Toast.LENGTH_LONG).show();
//
//        Query firebaseSearchQuery = mUserDatabase.orderByChild("serviceName").startAt(searchText).endAt(searchText + "\uf8ff");
//
//        FirebaseRecyclerAdapter<Service, ServicesViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Service, ServicesViewHolder>(
//
//                Service.class,
//                R.layout.list_layout,
//                ServicesViewHolder.class,
//                firebaseSearchQuery
//        ) {
//            @Override
//            protected void populateViewHolder(ServicesViewHolder viewHolder, Service model, int position) {
//                viewHolder.setDetails(model.getServiceName());
//            }
//        };
//
//        mResultList.setAdapter(firebaseRecyclerAdapter);

    }

    public void firebaseByServiceSearch(String serviceName){
        final String searchedServiceName = serviceName;
        final ArrayList<String> getProvidersList = new ArrayList<String>();
        FirebaseDatabase.getInstance().getReference("Users").addListenerForSingleValueEvent( // fill the list with services
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot
                        for (DataSnapshot dsp : dataSnapshot.getChildren()) {           // search through all users
                            if (String.valueOf(dsp.child("roleType").getValue()).equals("Service Provider")){       // find providers
                                for (DataSnapshot dsp2 : dataSnapshot.child(dsp.getKey()).child("myServices").getChildren()) {   // search through found providers services
                                    if(String.valueOf(dsp2.child("serviceName").getValue()).equals(searchedServiceName)){   // does provider provide service searched for?
                                        getProvidersList.add(String.valueOf(dsp.child("username").getValue()));    // add provider name to list
                                    }
                                }
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });
        ArrayAdapter<String> temp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,getProvidersList);
        temp.setDropDownViewResource(android.R.layout.activity_list_item);
        mResultList.setAdapter(temp);

    }

    public void firebaseByRateSearch(String serviceName){
        final String searchedServiceName = serviceName;
        final ArrayList<String> getRatingsList = new ArrayList<String>();
        FirebaseDatabase.getInstance().getReference("Users").addListenerForSingleValueEvent( // fill the list with services
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot
                        for (DataSnapshot dsp : dataSnapshot.getChildren()) {           // search through all users
                           if (String.valueOf(dsp.child("roleType").getValue()).equals("Service Provider")){       // find providers
                               for (DataSnapshot dsp2 : dataSnapshot.child(dsp.getKey()).child("myServices").getChildren()) {   // search through found providers services
                                   if(String.valueOf(dsp2.child("serviceName").getValue()).equals(searchedServiceName)){   // does provider provide service searched for?
                                       getRatingsList.add(String.valueOf(dsp.child("username").getValue()) + " " +String.valueOf(dsp2.child("rating").getValue()));    // add provider name and rating to list
                                   }
                               }
                           }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });
        ArrayAdapter<String> temp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,getRatingsList);
        temp.setDropDownViewResource(android.R.layout.activity_list_item);
        mResultList.setAdapter(temp);

    }

    //View Holder Class

    public static class ServicesViewHolder extends RecyclerView.ViewHolder{
        View mView;

        public ServicesViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;
        }

        public void setDetails(String serviceName){

            TextView service_name = (TextView) mView.findViewById(R.id.ServiceName);
            //TextView rating = (TextView) mView.findViewById(R.id.Rating);
           // TextView hourly_rate = (TextView) mView.findViewById(R.id.Hourly_Rate);

            service_name.setText(serviceName);
            //hourly_rate.setText(hourlyRate);
        }
    }



}
