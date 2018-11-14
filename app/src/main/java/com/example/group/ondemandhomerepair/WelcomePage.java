package com.example.group.ondemandhomerepair;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.Intent;

public class WelcomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        Spinner accountsList = (Spinner)findViewById(R.id.accountList); // find drop down and set as invisible
        accountsList.setVisibility(View.GONE);

        Button addServiceButton = (Button)findViewById(R.id.addServiceButt);    // find admin buttons and set to invisible
        addServiceButton.setVisibility(View.GONE);

        Button editInformationButton = (Button)findViewById(R.id.editInfoButt);    // find admin buttons and set to invisible
        editInformationButton.setVisibility(View.GONE);

        Button deleteServiceButton = (Button)findViewById(R.id.deleteServiceButt);
        deleteServiceButton.setVisibility(View.GONE);

        Button editServiceButton = (Button)findViewById(R.id.editServiceButt);
        editServiceButton.setVisibility(View.GONE);

        Intent intent = getIntent();                                    // create intent by taking from previous intent

        TextView userName = (TextView)findViewById(R.id.userField);        // get text views
        TextView userType = (TextView)findViewById(R.id.typeField);

        userName.setText(intent.getStringExtra(LogIn.EXTRA_TEXT1));           // get username from EXTRA_TEXT1 of intent
        userType.setText(intent.getStringExtra(LogIn.EXTRA_TEXT2));           // get userType from EXTRA_TEXT@ of intent

        if(userType.getText().equals("Admin")){                             // admin tools show when admin is logged in
            accountsList.setVisibility(View.VISIBLE);
            addServiceButton.setVisibility(View.VISIBLE);

            addServiceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    openAddServices();
                }
            });

            deleteServiceButton.setVisibility(View.VISIBLE);
            deleteServiceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    openDeleteServices();
                }
            });
            editServiceButton.setVisibility(View.VISIBLE);
            editServiceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    openEditServices();
                }
            });
        }
        if(userType.getText().equals("Provider")){                             // provider tools show when provider is logged in

            editInformationButton.setVisibility(View.VISIBLE);
            editInformationButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    openEditInformation();
                }
            });
        }

    }
    public void openAddServices(){
        Intent intent = new Intent(this, AddServicePage.class);
        startActivity(intent);
    }
    public void openDeleteServices(){
        Intent intent = new Intent(this, DeletingServicePage.class);
        startActivity(intent);
    }
    public void openEditServices(){
        Intent intent = new Intent(this, EditServicePage.class);
        startActivity(intent);
    }
    public void openEditInformation(){
        Intent intent = new Intent(this, EditServiceProviderInfo.class);
        startActivity(intent);
    }
}
