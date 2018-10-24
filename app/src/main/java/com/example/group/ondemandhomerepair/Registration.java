package com.example.group.ondemandhomerepair;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {
    private EditText username, password, passwordConfirm;
    private Button registerButton;
    private RadioButton radButton;
    private RadioGroup radGroup;
    private TextView errorMessage;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //Get Firebase auth instance
        mAuth = FirebaseAuth.getInstance();

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        passwordConfirm = findViewById(R.id.confirmPassword);
        registerButton = findViewById(R.id.registerButton);
        radGroup = findViewById(R.id.radioGroup);
        errorMessage = findViewById(R.id.errorMessage);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (statusValidate()){
                    final String Username = username.getText().toString().trim();
                    String Password = password.getText().toString().trim();
                    final String roleType = radButton.getText().toString();

                    mAuth.createUserWithEmailAndPassword(Username,Password).addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                User user = new User(
                                        Username,
                                        roleType
                                );

                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(Registration.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                            //startActivity(new Intent(Registration.this, MainActivity.class));
                                        } else {
                                            Toast.makeText(Registration.this, "Registration f", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(Registration.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }
            }
        });

    }

    public boolean statusValidate() {

        /*if (username.getText().toString().equals(passwordConfirm.getText().toString()) != true) {
            errorMessage.setText("! Passwords do not match");
            return false;
        }*/
        if (username.getText().toString().equals("")) {
            errorMessage.setText("! Username is empty");
            return false;
        }
        if (password.getText().toString().trim().equals("")) {
            errorMessage.setText("! Password is empty");
            return false;
        }
        if (password.getText().toString().trim().length() < 6) {
            errorMessage.setText("Password too short, enter minimum 6 character");
            return false;
        }
        if (radGroup.getCheckedRadioButtonId() == -1) {
            errorMessage.setText("! Account type not selected");
            return false;
        } else {
            radButton = findViewById(radGroup.getCheckedRadioButtonId());
        }
        //if statement for firebase account duplicates
        //Use Object.getText.toString to compare with any string values in the database for username, password, and radioButton string.
        return true;
    }


}
