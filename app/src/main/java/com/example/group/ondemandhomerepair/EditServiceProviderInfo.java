package com.example.group.ondemandhomerepair;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.group.ondemandhomerepair.LogIn.EXTRA_TEXT1;

public class EditServiceProviderInfo extends AppCompatActivity {

    private EditText Address, phoneNumber, Company, profileDescription;
    private Button addingProfileButt;
    private TextView errorMessage;
    private RadioGroup radGroup;
    private RadioButton radButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_service_provider_info);

        final Intent intent = getIntent();
        final String providerUser = intent.getStringExtra(EXTRA_TEXT1);  // retrieve provider name from welcome page intent

        Address = findViewById(R.id.providerAddressText);
        phoneNumber = findViewById(R.id.providerPhoneText);
        Company = findViewById(R.id.providerNameText);
        profileDescription = findViewById(R.id.providerDescriptText);
        radGroup = findViewById(R.id.checkLicenserb);
        addingProfileButt = findViewById(R.id.SetInfoButton);

        addingProfileButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (statusValidate()) {
                    final String Add = Address.getText().toString().trim();
                    final String phone = phoneNumber.getText().toString().trim();
                    final String comp = Company.getText().toString().trim();
                    final String desc = profileDescription.getText().toString().trim();

                    ProviderProfile profile = new ProviderProfile(
                            Add,
                            Integer.parseInt(phone),
                            comp,
                            desc
                    );

                    FirebaseDatabase.getInstance().getReference("ProviderProfileInfo")
                            .push().setValue(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(EditServiceProviderInfo.this, "Profile Creation successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(EditServiceProviderInfo.this, EditServiceProviderInfo.class));
                            } else {
                                Toast.makeText(EditServiceProviderInfo.this, "Profile did not create properly", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


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

    public boolean statusValidate() {

        if (Address.getText().toString().equals("")) {
            errorMessage.setText("! Address is empty");
            return false;
        }
        if (phoneNumber.getText().toString().trim().equals("")) {
            errorMessage.setText("! Phone Number is empty");
            return false;
        }
        if (Company.getText().toString().trim().equals("")) {
            errorMessage.setText("! Company is empty");
            return false;
        }
        return true;
    }
}
