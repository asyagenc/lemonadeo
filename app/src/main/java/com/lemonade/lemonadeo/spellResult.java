package com.lemonade.lemonadeo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class spellResult extends AppCompatActivity {

    Button button;
    Button button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_spell_result);


        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.cartoon_music);
        mediaPlayer.start();
        button=findViewById(R.id.Homepage);
        button2=findViewById(R.id.buttonTA);

        button.setOnClickListener(v -> {
            Intent intent = new Intent(spellResult.this, GameMainMenu.class);
            startActivity(intent);
        });



        button2.setOnClickListener(v -> {
            Intent intent = new Intent(spellResult.this, spelling.class);
            startActivity(intent);
        });


        int score = getIntent().getIntExtra("SCORE", 0);


        String scoreMessage = getString(R.string.score_message, score);



        TextView scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText(scoreMessage);


        // Display feedback message based on the score range
        TextView feedbackTextView = findViewById(R.id.feedbackTextView);
        String feedbackMessage;
        if (score >= 80 && score <= 100) {
            feedbackMessage = getString(R.string.feedback_fantastic);
        } else if (score >= 50 && score < 80) {
            feedbackMessage = getString(R.string.feedback_good_job);
        } else {
            feedbackMessage = getString(R.string.feedback_better_next_time);
        }
        feedbackTextView.setText(feedbackMessage);
    }





}