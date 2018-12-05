package com.example.group.ondemandhomerepair;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class BookService extends AppCompatActivity {

    private String provName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_service);

        String user = getIntent().getStringExtra("userName");
        String provider = getIntent().getStringExtra("providerName");
        String service = getIntent().getStringExtra("serviceName");
        String times = getIntent().getStringExtra("times");

        final Booking booking = new Booking(user, provider, service, times);

        final TextView providerNameTextView = (TextView) findViewById(R.id.txtProvName);
        final TextView serviceNameTextView = (TextView) findViewById(R.id.txtServName);
        Button bookServiceButton = (Button) findViewById(R.id.btnBookService);

        providerNameTextView.setText(provider);
        serviceNameTextView.setText(service);

        //If "Book Service" button is pressed, then go to firebase and add booking under user's history

        bookServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("History")
                        .setValue(booking).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(BookService.this, "Service Booked", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(BookService.this, MainActivity.class));
                        } else {
                            Toast.makeText(BookService.this, "Booking Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
