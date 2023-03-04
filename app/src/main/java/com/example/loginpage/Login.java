package com.example.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText mEmail,mPassword;
    Button mLoginBtn;
    TextView mCreateBtn;
    ProgressBar progressBar;

    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressbar);
        mLoginBtn = findViewById(R.id.LoginBtn);
        mCreateBtn = findViewById(R.id.createtext);

        fAuth = FirebaseAuth.getInstance();


        mCreateBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(),Register.class)));



        mLoginBtn.setOnClickListener(view -> {

            String email = mEmail.getText().toString();
            String password = mPassword.getText().toString();

            if (TextUtils.isEmpty(email))
            {
                mEmail.setError("Email is Required");
                return;
            }

            if (TextUtils.isEmpty(password))
            {
                mPassword.setError("Password is Required");
                return;
            }

            if (password.length() < 6)
            {
                mPassword.setError("Password Must Be >=6 character");
                return;
            }

            progressBar.setVisibility(View.VISIBLE);


            fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {

                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Log In Succesful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }

                else
                {
                    Toast.makeText(getApplicationContext(), "error"+ task.getException(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }

            });

        });


    }
}
