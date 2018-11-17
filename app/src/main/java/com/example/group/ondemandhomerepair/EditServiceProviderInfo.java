package com.example.group.ondemandhomerepair;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import static com.example.group.ondemandhomerepair.LogIn.EXTRA_TEXT1;

public class EditServiceProviderInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_service_provider_info);

        final Intent intent = getIntent();
        final String providerUser = intent.getStringExtra(EXTRA_TEXT1);  // retrieve provider name from welcome page intent

        EditText providerCompany = (EditText)findViewById(R.id.providerNameText);   // all input components from page
        EditText providerPhone = (EditText)findViewById(R.id.providerPhoneText);
        EditText providerAddress = (EditText)findViewById(R.id.providerAddressText);
        EditText providerDescription = (EditText)findViewById(R.id.providerDescriptText);
        RadioGroup isLicensed = (RadioGroup)findViewById(R.id.checkLicenserb);

        Button addService = (Button)findViewById(R.id.providerAddServiceButt);
        Button changeTimes = (Button)findViewById(R.id.changeTimesButt);

        addService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(EditServiceProviderInfo.this, editServiceProviderServices.class);
                intent.putExtra(EXTRA_TEXT1, providerUser);
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
