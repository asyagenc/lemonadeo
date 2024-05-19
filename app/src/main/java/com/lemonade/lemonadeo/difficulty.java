package com.lemonade.lemonadeo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class difficulty extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

        String gameMode = getIntent().getStringExtra("gameMode");
        Button easyButton = findViewById(R.id.easyButton);
        Button mediumButton = findViewById(R.id.mediumButton);
        Button hardButton = findViewById(R.id.hardButton);

        easyButton.setOnClickListener(v -> startGame(gameMode,  "Easy"));
        mediumButton.setOnClickListener(v -> startGame(gameMode, "Medium"));
        hardButton.setOnClickListener(v -> startGame(gameMode, "Hard"));



    }

    private void startGame(String gameMode, String difficulty) {
        Intent intent;
        if ("Forward".equals(gameMode)) {
            intent = new Intent(this, forwardmanner.class);
            finish();
        } else {
            intent = new Intent(this, backwardmanner.class);
            finish();
        }
        intent.putExtra("difficulty", difficulty);
        startActivity(intent);
        finish();

    }
}