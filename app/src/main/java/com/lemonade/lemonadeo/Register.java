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

public class Register extends AppCompatActivity {

    TextInputEditText editTextEmail,editTextPassword;
    Button regBttn;
    Button LogPageBttn;
    FirebaseAuth mAuth;
    ProgressBar bar;



    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(Register.this, MainActivity.class);
            startActivity(intent);
            finish();}
    }

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        mAuth=FirebaseAuth.getInstance();
        AppCompatEditText  editTextEmail=findViewById(R.id.RegEmail);
        AppCompatEditText  editTextPassword=findViewById(R.id.RegPasswordd);
        regBttn=findViewById(R.id.RegPageBttn);
        bar=findViewById(R.id.progressBar);
        LogPageBttn=findViewById(R.id.RegPageLogBttn);

        LogPageBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Log.class);
                startActivity(intent);

            }
        });



        regBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar.setVisibility(View.VISIBLE);
                String email,password;
                email=editTextEmail.getText().toString();
                password=editTextPassword.getText().toString();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Register.this,"enter an valid email.",Toast.LENGTH_SHORT).show();
                    return;}

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Register.this,"you have to enter your password.",Toast.LENGTH_SHORT).show();
                    return;}

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                bar.setVisibility(View.GONE);

                                if (task.isSuccessful()) {

                                    Toast.makeText(Register.this, "Account created.",Toast.LENGTH_SHORT).show();}
                                else {
                        Toast.makeText(Register.this, "Authentication failed.",Toast.LENGTH_SHORT).show(); }
                            }
                        });


            }
        });


        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ConstraintLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
    }
}