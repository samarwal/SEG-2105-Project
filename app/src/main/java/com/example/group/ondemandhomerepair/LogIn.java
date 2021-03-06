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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity {
    private EditText username, password;
    private RadioButton radButton;
    private RadioGroup radGroup;
    private TextView errorMessage;
    private FirebaseAuth firebaseAuth;
    public static final String EXTRA_TEXT1 = "com.example.group.ondemandhomerepair.EXTRA_TEXT1";
    public static final String EXTRA_TEXT2 = "com.example.group.ondemandhomerepair.EXTRA_TEXT2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        username = findViewById(R.id.txtUser);
        password = findViewById(R.id.txtPass);
        radGroup = findViewById(R.id.radioGroupLogin);
        errorMessage = findViewById(R.id.errorMessageLogin);
        Button loginButton = (Button) findViewById(R.id.btnLogIn);
        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                configureLogin();
            }
        });
    }

    public void configureLogin(){
            //@Override
            if(statusValidate()){
                EditText editText1 = (EditText) findViewById(R.id.txtUser);
                String user = editText1.getText().toString();

                int selectedRadio = radGroup.getCheckedRadioButtonId();
                RadioButton selectedType = (RadioButton)findViewById(selectedRadio);
                String Type = selectedType.getText().toString();

                EditText editText2 = (EditText) findViewById(R.id.txtPass);
                String pass = editText2.getText().toString();

                validate(username.getText().toString(), password.getText().toString(), Type);
            }
    }

    public boolean statusValidate(){
        if(username.getText().toString().equals("")){
            errorMessage.setText("! Username is empty");
            return false;
        }
        if(password.getText().toString().equals("")){
            errorMessage.setText("! Password is empty");
            return false;
        }
        if (radGroup.getCheckedRadioButtonId() == -1){
            errorMessage.setText("! Account type not selected");
            return false;
        }
        else{
            radButton = findViewById(radGroup.getCheckedRadioButtonId());
        }
        return true;
    }

    public void validate(final String userName, String userPassword, final String userType){

        firebaseAuth.signInWithEmailAndPassword(userName,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LogIn.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LogIn.this, WelcomePage.class);
                    intent.putExtra(EXTRA_TEXT1, userName);         // send the username and type to welcome page
                    intent.putExtra(EXTRA_TEXT2, userType);
                    startActivity (intent);

                }else{
                    Toast.makeText(LogIn.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    errorMessage.setText("");
                }
            }
        });
    }
}

