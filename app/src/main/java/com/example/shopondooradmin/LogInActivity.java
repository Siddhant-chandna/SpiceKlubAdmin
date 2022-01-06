package com.example.shopondooradmin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class LogInActivity extends AppCompatActivity {
    Button signIn;
    TextView signUp;
    EditText email,password;
    FirebaseAuth auth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        signUp=findViewById(R.id.sign_up);
        signIn=findViewById(R.id.login_btn);
        email=findViewById(R.id.email_login);
        password=findViewById(R.id.password_login);
        auth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.loginProgress);
        progressBar.setVisibility(View.GONE);

        signUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(LogInActivity.this, SignUpActivity.class));
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
                progressBar.setVisibility(View.VISIBLE);
            }
        });
        if(auth.getCurrentUser() != null){
            progressBar.setVisibility(View.VISIBLE);
            startActivity(new Intent(LogInActivity.this, MainActivity.class));
            Toast.makeText(this, "Logging you In!", Toast.LENGTH_SHORT).show();
            finish();
        }

    }



    private void loginUser(){
        String adminEmail=email.getText().toString();
        String adminPassword=password.getText().toString();

        if(TextUtils.isEmpty(adminEmail)){
            Toast.makeText(this,"Email is Empty!",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(adminPassword)){
            Toast.makeText(this,"Password is Empty!",Toast.LENGTH_SHORT).show();
        }
        else if(adminPassword.length()<6) {
            Toast.makeText(this, "Password is too Short,It must be greater than 6 Characters!", Toast.LENGTH_SHORT).show();
            return;
        }

        // LOGIN Admin
        auth.signInWithEmailAndPassword(adminEmail,adminPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LogInActivity.this,"LogIn Successful",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(LogInActivity.this,MainActivity.class);
                            startActivity(intent);
                            progressBar.setVisibility(View.GONE);
                            finish();
                        }
                        else{
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(LogInActivity.this,"LogIn Error:"+task.getException(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}