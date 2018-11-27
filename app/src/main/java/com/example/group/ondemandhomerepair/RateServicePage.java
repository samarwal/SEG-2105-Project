package com.example.group.ondemandhomerepair;

import android.content.Intent;
import android.media.Rating;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class RateServicePage extends AppCompatActivity {

    private float rating;
    private String providerName;
    private String serviceName;
    //Transfer the Provider's name and the Service's Name from the User's History Activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_service);

        final RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        Button submitButton = (Button) findViewById(R.id.btnSubmit);
        final TextView providerTextView = (TextView) findViewById(R.id.txtProviderName);
        final TextView serviceTextView = (TextView) findViewById(R.id.txtServiceName);
        final TextView ratingResultTextView = (TextView) findViewById(R.id.txtRatingResult);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingResultTextView.setText("Your rating is: " + ratingBar.getRating());
                rating = ratingBar.getRating();
            }
        });
    }



}
