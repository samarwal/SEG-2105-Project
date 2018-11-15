package com.example.group.ondemandhomerepair;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.group.ondemandhomerepair.LogIn.EXTRA_TEXT1;

public class EditServiceProviderInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_service_provider_info);

        final Intent intent = getIntent();
        final String providerName = intent.getStringExtra(EXTRA_TEXT1);  // retrieve provider name from welcome page intent
        Button addService = (Button)findViewById(R.id.providerAddServiceButt);
        Button changeTimes = (Button)findViewById(R.id.changeTimesButt);

        addService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(EditServiceProviderInfo.this, editServiceProviderServices.class);
                intent.putExtra(EXTRA_TEXT1, providerName);
                startActivity(intent);
            }
        });

        changeTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(EditServiceProviderInfo.this, editServiceProviderTime.class);
                startActivity(intent);
            }
        });


    }
}
