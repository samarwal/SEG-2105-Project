package com.example.group.ondemandhomerepair;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class ProviderProfileActivity extends AppCompatActivity {

    private EditText Address, phoneNumber, Company, profileDescription;
    private Button addingProfileButt;
    private TextView errorMessage;
    private RadioGroup radGroup;
    private RadioButton radButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_service_provider_info);

        Address = findViewById(R.id.providerAddressText);
        phoneNumber = findViewById(R.id.providerPhoneText);
        Company = findViewById(R.id.providerNameText);
        profileDescription = findViewById(R.id.providerDescriptText);
        radGroup = findViewById(R.id.checkLicenserb);
        addingProfileButt = findViewById(R.id.button2);

        addingProfileButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (statusValidate()) {
                    final String Add = Address.getText().toString().trim();
                    final String phone = phoneNumber.getText().toString().trim();
                    final String comp = Company.getText().toString().trim();
                    final String desc = profileDescription.getText().toString().trim();
                    final String license = radButton.getText().toString();

                    ProviderProfile profile = new ProviderProfile(
                            Add,
                            Integer.parseInt(phone),
                            comp,
                            desc,
                            license
                    );

                    FirebaseDatabase.getInstance().getReference("ProviderProfileInfo")
                            .push().setValue(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ProviderProfileActivity.this, "Profile Creation successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ProviderProfileActivity.this, AddServicePage.class));
                            } else {
                                Toast.makeText(ProviderProfileActivity.this, "Profile did it create properly", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
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

