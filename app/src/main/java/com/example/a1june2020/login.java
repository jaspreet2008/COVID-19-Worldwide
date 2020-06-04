package com.example.a1june2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    EditText EmailId, password;
    Button SignInButton;
    TextView SignUpTextView;
    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        EmailId = findViewById(R.id.loginPageEmailEditText);
        password = findViewById(R.id.loginPagePasswordEditText);
        SignUpTextView = findViewById(R.id.goToSignUpPage);
        SignInButton = findViewById(R.id.signInButton);

        mAuthStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mAuth.getCurrentUser();
                if(mFirebaseUser != null){
                    Toast.makeText(login.this,"you are successfully logged in",Toast.LENGTH_SHORT);
                    Intent i = new Intent(login.this,Main5Activity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(login.this,"please login first!",Toast.LENGTH_SHORT);
                }
            }
        };
        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=EmailId.getText().toString();
                String password = login.this.password.getText().toString();
                if(email.isEmpty()){
                    EmailId.setError("plz check email id");
                    EmailId.requestFocus();
                }
                else if(password.isEmpty()){
                    login.this.password.setError("plz enter your password");
                    login.this.password.requestFocus();
                }
                else if(email.isEmpty() && password.isEmpty()){
                    Toast.makeText(login.this,"Please enter the information !",Toast.LENGTH_SHORT);
                }
                else if(!(email.isEmpty() && password.isEmpty())){
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(login.this,"login Error",Toast.LENGTH_SHORT);
                            }
                            else {
                                Intent intToHome = new Intent(login.this,Main5Activity.class);
                                startActivity(intToHome);
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(login.this,"Error occurred!",Toast.LENGTH_SHORT);
                }
            }
        });
        SignUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSignUp = new Intent(login.this,MainActivity.class);
                startActivity(intSignUp);
            }
        });

    }

    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }
    }

