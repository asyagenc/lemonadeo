package com.lemonade.lemonadeo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class game extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button forwardButton = findViewById(R.id.forwardButton);
        Button backwardButton = findViewById(R.id.backwardButton);
        Button gameMenuButton = findViewById(R.id.gameMenuButton);

        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startdifficulty("Forward");
            }
        });

        backwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startdifficulty("Backward");
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

    private void startdifficulty(String gameMode) {
        Intent intent = new Intent(game.this, difficulty.class);
        intent.putExtra("gameMode", gameMode);
        startActivity(intent);
    }
}