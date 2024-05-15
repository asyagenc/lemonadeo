package com.lemonade.lemonadeo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import android.net.Uri;





public class MonthsLearning extends AppCompatActivity {

    MediaPlayer playFeb, playJan, playMarch, playApril, playMay, playJune, playJuly , playAug, playSep, playOct, playNov ,playDec;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_months_learning);
        playJan = MediaPlayer.create(this, R.raw.tuesdayy);
        playFeb = MediaPlayer.create(this, R.raw.mooonday);
        playMarch = MediaPlayer.create(this, R.raw.wwednesday);
        playApril= MediaPlayer.create(this, R.raw.thursday);
        playMay = MediaPlayer.create(this, R.raw.fridayy);
        playJune = MediaPlayer.create(this, R.raw.saaaturday);
        playJuly = MediaPlayer.create(this, R.raw.ssundayy);
        playAug = MediaPlayer.create(this, R.raw.wwednesday);
        playSep= MediaPlayer.create(this, R.raw.thursday);
        playOct = MediaPlayer.create(this, R.raw.fridayy);
        playNov = MediaPlayer.create(this, R.raw.saaaturday);
        playDec = MediaPlayer.create(this, R.raw.ssundayy);

        CardView cardView1 = findViewById(R.id.janCard);
        CardView cardView2 = findViewById(R.id.febCard);
        CardView cardView3 = findViewById(R.id.marchCard);
        CardView cardView4 = findViewById(R.id.aprilCard);
        CardView cardView5 = findViewById(R.id.mayCard);
        CardView cardView6 = findViewById(R.id.juneCard);
        CardView cardView7 = findViewById(R.id.julyCard);
        CardView cardView8 = findViewById(R.id.augustCard);
        CardView cardView9 = findViewById(R.id.septemberCard);
        CardView cardView10 = findViewById(R.id.octoberCard);
        CardView cardView11 = findViewById(R.id.novemberCard);
        CardView cardView12 = findViewById(R.id.decemberCard);


        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio(playJan);
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio(playFeb);
            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio(playMarch);
            }
        });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio(playApril);
            }
        });

        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio(playMay);
            }
        });

        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio(playJune);
            }
        });

        cardView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio(playJuly);
            }
        });
        cardView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio(playAug);
            }
        });
        cardView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio(playSep);
            }
        });
        cardView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio(playOct);
            }
        });
        cardView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio(playNov);
            }
        });
        cardView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio(playDec);
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
        if (playJan != null) {
            playJan.release();
        }
        if (playFeb != null) {
            playFeb.release();
        }
        if (playMarch != null) {
            playMarch.release();
        }
        if (playApril != null) {
            playApril.release();
        }
        if (playMay != null) {
            playMay.release();
        }
        if (playJune != null) {
            playJune.release();
        }
        if (playJuly != null) {
            playJuly.release();
        }
        if (playAug != null) {
            playAug.release();
        }
        if (playSep != null) {
            playSep.release();
        }
        if (playOct != null) {
            playOct.release();
        }
        if (playNov != null) {
            playNov.release();
        }
    }
}