package com.example.group.ondemandhomerepair;

import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class editServiceProviderServices extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_service_provider_services);

        Spinner serviceDropDown = (Spinner) findViewById(R.id.providerServicesSpinner);
        TextView providerSerivceAdd = (TextView)findViewById(R.id.providerServiceText);
        Button providerAddServiceButton = (Button)findViewById(R.id.providerAddServiceButt);

        




    }


}
