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

public class SignUp extends AppCompatActivity {

    TextView LoginTextView;
    EditText EmailEditText, passwordEditText;
    Button SignUpButton;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        EmailEditText = findViewById(R.id.enterEmail);
        passwordEditText = findViewById(R.id.enterPassword);

        mAuth = FirebaseAuth.getInstance();
        LoginTextView = findViewById(R.id.loginTextView);
        SignUpButton = findViewById(R.id.signUpButton);
        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String EmailId = EmailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                if (EmailId.isEmpty()) {
                    EmailEditText.setError("Email id is required");
                    EmailEditText.requestFocus();
                } else if (password.isEmpty()) {
                    passwordEditText.setError("Password is required");
                    passwordEditText.requestFocus();

                } else if (EmailId.isEmpty() && password.isEmpty()) {

                    Toast.makeText(SignUp.this, "Both fields are required!", Toast.LENGTH_SHORT);
                } else if (!(EmailId.isEmpty() && password.isEmpty())) {
                    mAuth.createUserWithEmailAndPassword(EmailId, password).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(SignUp.this, "signUp Unsuccessful, please Try Again", Toast.LENGTH_SHORT);
                            } else {
                                startActivity(new Intent(SignUp.this, Main5Activity.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(SignUp.this, "Error occurred!", Toast.LENGTH_SHORT);
                }
            }
        });
        LoginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iu = new Intent(SignUp.this, login.class);
                startActivity(iu);
            }
        });}

}
