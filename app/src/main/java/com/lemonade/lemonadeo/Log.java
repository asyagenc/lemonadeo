package com.lemonade.lemonadeo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.checkerframework.checker.nullness.qual.NonNull;

public class Log extends AppCompatActivity {

    TextInputEditText editTextEmail,editTextPassword;
    Button logBttn;
    FirebaseAuth mAuth;
    Button RegPageBttn;


    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();}
    }


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log);


        mAuth=FirebaseAuth.getInstance();
        AppCompatEditText editTextEmail=findViewById(R.id.LogEmail);
        AppCompatEditText editTextPassword=findViewById(R.id.LogPasswordd);
        logBttn=findViewById(R.id.LogPageBttn);
        RegPageBttn=findViewById(R.id.LogPageRegBttn);


        RegPageBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
                finish();

            }
        });

        logBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email,password;
                email=editTextEmail.getText().toString();
                password=editTextPassword.getText().toString();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Log.this,"Enter a valid email.",Toast.LENGTH_SHORT).show();
                    return;}

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Log.this,"You have to enter your password.",Toast.LENGTH_SHORT).show();
                    return;}

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {



                                if (task.isSuccessful()) {
                                    Toast.makeText(Log.this, "Login successful.", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);
                                    finish();


                                } else {
                                Toast.makeText(Log.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });



            }
        });



    }
}