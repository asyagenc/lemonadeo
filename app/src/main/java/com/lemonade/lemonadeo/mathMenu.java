package com.lemonade.lemonadeo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class mathMenu extends AppCompatActivity {

    private Button easyButton;
    private Button mediumButton;
    private Button hardButton;
    private Button gameMenuButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_menu);

        easyButton = findViewById(R.id.easyButton);
        mediumButton = findViewById(R.id.mediumButton);
        hardButton = findViewById(R.id.hardButton);

        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame("Easy");
            }
        });

        mediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame("Medium");
            }
        });

        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame("Hard");
            }
        });
        gameMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),GameMainMenu.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void startGame(String difficulty) {
        Intent intent = new Intent(mathMenu.this, multiplication.class);
        intent.putExtra("difficulty", difficulty);
        startActivity(intent);
    }
}