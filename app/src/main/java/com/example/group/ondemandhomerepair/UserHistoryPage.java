package com.example.group.ondemandhomerepair;

import android.content.Intent;
import android.media.Rating;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.google.firebase.database.FirebaseDatabase;

public class UserHistoryPage extends AppCompatActivity {

    //private static final Object RateServicePage = ;
    Booking[] bookings;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_history_page);

        ListView listView = (ListView) findViewById(R.id.listView);

        CustomAdapter customAdapter = new CustomAdapter();

        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(view.getContext(), RateServicePage.class);
            }
        });
    }

    class CustomAdapter extends BaseAdapter {


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

            txtProvName.setText(bookings[i].getProvider());
            txtServName.setText(bookings[i].getService());
            txtDate.setText(bookings[i].getDate());
            return null;
        }
    }
}
