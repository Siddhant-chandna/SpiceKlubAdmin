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
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class SignUpActivity extends AppCompatActivity {

    Button signUp;
    EditText name,email,password;
    TextView signIn;
    FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUp = findViewById(R.id.reg_btn);
        name = findViewById(R.id.name_reg);
        email = findViewById(R.id.email_reg);
        password = findViewById(R.id.password_reg);
        signIn = findViewById(R.id.sign_in);
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        progressBar=findViewById(R.id.regProgress);
        progressBar.setVisibility(View.GONE);

        signIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LogInActivity.class));
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createUser();
                progressBar.setVisibility(View.VISIBLE);
            }

        });
    }

    private void createUser(){
        String adminName=name.getText().toString();
        String adminEmail=email.getText().toString();
        String adminPassword=password.getText().toString();
        String openStatus="Open";

        if(TextUtils.isEmpty(adminName)){
            Toast.makeText(this,"Name is Empty!",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(adminEmail)){
            Toast.makeText(this,"Name is Empty!",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(adminPassword)){
            Toast.makeText(this,"Password is Empty!",Toast.LENGTH_SHORT).show();
        }
        if(adminPassword.length()<6){
            Toast.makeText(this,"Password is too Short,It must be greater than 6 Characters!",Toast.LENGTH_SHORT).show();
            return;
        }

//             Create User
        auth.createUserWithEmailAndPassword(adminEmail,adminPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            AdminModel adminModel=new AdminModel(adminName,adminEmail,adminPassword,openStatus);
                            String id=auth.getCurrentUser().getUid();
                            progressBar.setVisibility(View.GONE);
                            database.getReference().child("Admin").child(id).setValue(adminModel);
                            startActivity(new Intent(SignUpActivity.this, LogInActivity.class));

                            Toast.makeText(SignUpActivity.this,"Sign Up Successful",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(SignUpActivity.this,"Error in Sign Up!"+task.getException(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
