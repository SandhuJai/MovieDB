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
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUpActivity extends AppCompatActivity {
    // TODO : Body for UpdateUI method
    // TODO : Use Name to be added to User

    private FirebaseAuth mAuth;
    private EditText nameText;
    private EditText emailText;
    private EditText passwordText;
    private EditText confirmPasswordText;
    private Button logInBtn;
    private Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        nameText = findViewById(R.id.name_SignUp_EditText);
        emailText = findViewById(R.id.email_SignUp_EditText);
        passwordText = findViewById(R.id.password_SignUp_EditText);
        confirmPasswordText = findViewById(R.id.confirmPassword_SignUp_EditText);
        logInBtn = findViewById(R.id.logIn_SignUp_Button);
        signUpBtn = findViewById(R.id.signUp_SignUp_Button);

        FirebaseUser currentUser = mAuth.getCurrentUser();

        // Checking if user is already signed in
        updateUI(currentUser);

        // adding event handlers to buttons
        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // starts logInActivity
                startActivity(new Intent(SignUpActivity.this, LogInActivity.class));
            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    // Updates UI of app according to the currentUser
    private void updateUI(FirebaseUser user) {
        //TODO : Complete Body
    }

    // signs up a new user using email ID and password
    private void signUp() {
        final String email = emailText.getText().toString();
        final String password = passwordText.getText().toString();
        final String confirmPassword = confirmPasswordText.getText().toString();
        final String name = nameText.getText().toString();

        if(email.contains("@") && email.contains(".com")) {
            if(password.equals(confirmPassword)) {
                if(password.length() > 4) {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        if(user != null) {
                                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                                    .setDisplayName(name)
                                                    .build();

                                            user.updateProfile(profileUpdates)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            Toast.makeText(SignUpActivity.this, "Profile Created", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                        }
                                        updateUI(user);
                                    }else {
                                        Toast.makeText(SignUpActivity.this, "Could" +
                                                "n't create account", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else {
                    Toast.makeText(SignUpActivity.this, "Passwords too short", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(SignUpActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(SignUpActivity.this, "Email is not valid", Toast.LENGTH_SHORT).show();
        }
    }
}
