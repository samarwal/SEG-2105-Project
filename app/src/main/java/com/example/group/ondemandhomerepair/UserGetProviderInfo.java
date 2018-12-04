package com.example.group.ondemandhomerepair;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static android.content.Intent.EXTRA_TEXT;

public class UserGetProviderInfo extends AppCompatActivity {

    TextView providerUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_get_provider_info);

        final Intent intent = getIntent();

        providerUser = (TextView)findViewById(R.id.myTextView);
        providerUser.setText(intent.getStringExtra(EXTRA_TEXT));

    }
}
