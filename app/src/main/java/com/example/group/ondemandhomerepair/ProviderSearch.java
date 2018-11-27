package com.example.group.ondemandhomerepair;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.w3c.dom.Text;


public class ProviderSearch extends AppCompatActivity {

    private EditText mSearchField;
    private Button mSearchBtn;
    private RecyclerView mResultList;
    private DatabaseReference mUserDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_search);

        mUserDatabase = FirebaseDatabase.getInstance().getReference("Services");

        mSearchField = (EditText) findViewById(R.id.search_field);
        mSearchBtn = (Button) findViewById(R.id.button2);
        mResultList = (RecyclerView) findViewById(R.id.result_list);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));


        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText = mSearchField.getText().toString();
                firebaseUserSearch(searchText);
            }
        });

    }

    private void firebaseUserSearch(String searchText) {

        Toast.makeText(ProviderSearch.this, "Started Search", Toast.LENGTH_LONG).show();

        Query firebaseSearchQuery = mUserDatabase.orderByChild("serviceName").startAt(searchText).endAt(searchText + "\uf8ff");

        FirebaseRecyclerAdapter<Service, ServicesViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Service, ServicesViewHolder>(

                Service.class,
                R.layout.list_layout,
                ServicesViewHolder.class,
                firebaseSearchQuery
        ) {
            @Override
            protected void populateViewHolder(ServicesViewHolder viewHolder, Service model, int position) {
                viewHolder.setDetails(model.getServiceName(), model.getHourlyRate());
            }
        };

        mResultList.setAdapter(firebaseRecyclerAdapter);

    }

    //View Holder Class

    public static class ServicesViewHolder extends RecyclerView.ViewHolder{
        View mView;

        public ServicesViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;
        }

        public void setDetails(String serviceName, String hourlyRate){

            TextView service_name = (TextView) mView.findViewById(R.id.ServiceName);
            //TextView rating = (TextView) mView.findViewById(R.id.Rating);
            TextView hourly_rate = (TextView) mView.findViewById(R.id.Hourly_Rate);

            service_name.setText(serviceName);
            hourly_rate.setText(hourlyRate);
        }
    }


}
