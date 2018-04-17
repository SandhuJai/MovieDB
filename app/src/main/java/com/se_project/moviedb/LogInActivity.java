package com.se_project.moviedb;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity {
    //TODO : Complete Body of update UI
    // TODO : Complete Facebook and Google Login

    private FirebaseAuth mAuth;
    private Button logInBtn;
    private Button signUpBtn;
    private Button logInGoogleBtn;
    private Button logInFacebookBtn;
    private EditText emailText;
    private EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mAuth = FirebaseAuth.getInstance();
        logInBtn = findViewById(R.id.logIn_LogIn_button);
        signUpBtn = findViewById(R.id.signUp_LogIn_button);
        logInFacebookBtn = findViewById(R.id.logInFacebook_LogIn_Button);
        logInGoogleBtn = findViewById(R.id.logInGoogle_LogIn_Button);
        emailText = findViewById(R.id.email_LogIn_EditText);
        passwordText = findViewById(R.id.password_LogIn_EditText);

        FirebaseUser currentUser = mAuth.getCurrentUser();

        // Checking if user is already signed in
        updateUI(currentUser);

        // adding event handlers to buttons
        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogInActivity.this, SignUpActivity.class));
            }
        });
        logInGoogleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logInWithGoogle();
            }
        });
        logInFacebookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logInWithFacebook();
            }
        });
    }

    // Updates UI of app according to the currentUser
    private void updateUI(FirebaseUser user) {
        //TODO : Complete Body
    }

    // logs in user with email and password
    private void logIn() {
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        }else {
                            Toast.makeText(LogInActivity.this, "Authentication Failed",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    // logs in user with Facebook account
    private void logInWithFacebook() {
        //TODO: Complete Body
    }

    // logs in user with Google account
    private void logInWithGoogle() {
        //TODO: Complete Body
    }
}
