package com.lemonade.lemonadeo;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class learnSeasons extends AppCompatActivity {

    MediaPlayer playWinter, playSpring, playSummer, playFall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_learn_seasons);

        playWinter = MediaPlayer.create(this, R.raw.spring);
        playSpring = MediaPlayer.create(this, R.raw.summer);
        playSummer = MediaPlayer.create(this, R.raw.aut);
        playFall= MediaPlayer.create(this, R.raw.winter);

        CardView cardView1 = findViewById(R.id.winterCard);
        CardView cardView2 = findViewById(R.id.springCard);
        CardView cardView3 = findViewById(R.id.summerCard);
        CardView cardView4 = findViewById(R.id.fallCard);


        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio(playWinter);
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio(playSpring);
            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio(playSummer);
            }
        });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio(playFall);
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    private void playAudio(MediaPlayer mediaPlayer) {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }


    protected void onDestroy() {
        super.onDestroy();
        if (playWinter != null) {
            playWinter.release();
        }
        if (playSpring != null) {
            playSpring.release();
        }
        if (playFall != null) {
            playFall.release();
        }
        if (playSummer != null) {
            playSummer.release();
        }

    }


}