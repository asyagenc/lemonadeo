package com.lemonade.lemonadeo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.view.View;
import androidx.cardview.widget.CardView;

public class learnDays extends AppCompatActivity {

    MediaPlayer playMonday, playTuesday, playWednesday, playThursday, playFriday, playSaturday, playSunday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_days);

        playMonday = MediaPlayer.create(this, R.raw.mooonday);
        playTuesday = MediaPlayer.create(this, R.raw.tuesdayy);
        playWednesday = MediaPlayer.create(this, R.raw.wwednesday);
        playThursday = MediaPlayer.create(this, R.raw.thursday);
        playFriday = MediaPlayer.create(this, R.raw.fridayy);
        playSaturday = MediaPlayer.create(this, R.raw.saaaturday);
        playSunday = MediaPlayer.create(this, R.raw.ssundayy);

        CardView cardView1 = findViewById(R.id.monCard);
        CardView cardView2 = findViewById(R.id.tuesdayCard);
        CardView cardView3 = findViewById(R.id.wedCard);
        CardView cardView4 = findViewById(R.id.thursdayCard);
        CardView cardView5 = findViewById(R.id.fridayCard);
        CardView cardView6 = findViewById(R.id.saturdayCard);
        CardView cardView7 = findViewById(R.id.sundayCard);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio(playMonday);
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio(playTuesday);
            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio(playWednesday);
            }
        });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio(playThursday);
            }
        });

        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio(playFriday);
            }
        });

        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio(playSaturday);
            }
        });

        cardView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio(playSunday);
            }
        });
    }

    private void playAudio(MediaPlayer mediaPlayer) {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (playMonday != null) {
            playMonday.release();
        }
        if (playTuesday != null) {
            playTuesday.release();
        }
        if (playWednesday != null) {
            playWednesday.release();
        }
        if (playThursday != null) {
            playThursday.release();
        }
        if (playFriday != null) {
            playFriday.release();
        }
        if (playSaturday != null) {
            playSaturday.release();
        }
        if (playSunday != null) {
            playSunday.release();
        }
    }
}