package com.example.group.ondemandhomerepair;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class DeletingServicePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleting_service_page);

        Button deletingButton = (Button)findViewById(R.id.deletingServiceButt);
    }
}
