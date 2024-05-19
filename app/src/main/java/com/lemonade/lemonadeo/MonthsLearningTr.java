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





public class MonthsLearningTr extends AppCompatActivity {

    MediaPlayer playFeb, playJan, playMarch, playApril, playMay, playJune, playJuly , playAug, playSep, playOct, playNov ,playDec;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_months_learning_tr);
        playJan = MediaPlayer.create(this, R.raw.boysaysjan);
        playFeb = MediaPlayer.create(this, R.raw.boysayfeb);
        playMarch = MediaPlayer.create(this, R.raw.boysaymarch);
        playApril= MediaPlayer.create(this, R.raw.boysayapril);
        playMay = MediaPlayer.create(this, R.raw.boysaysmay);
        playJune = MediaPlayer.create(this, R.raw.boysayjune);
        playJuly = MediaPlayer.create(this, R.raw.boysaysjuly);
        playAug = MediaPlayer.create(this, R.raw.boysayaug);
        playSep= MediaPlayer.create(this, R.raw.boysaysept);
        playOct = MediaPlayer.create(this, R.raw.boysayoct);
        playNov = MediaPlayer.create(this, R.raw.boysaynov);
        playDec = MediaPlayer.create(this, R.raw.boysaydec);

        CardView cardView1 = findViewById(R.id.janCardTr);
        CardView cardView2 = findViewById(R.id.febCardTr);
        CardView cardView3 = findViewById(R.id.marchCardTr);
        CardView cardView4 = findViewById(R.id.aprilCardTr);
        CardView cardView5 = findViewById(R.id.mayCardTr);
        CardView cardView6 = findViewById(R.id.juneCardTr);
        CardView cardView7 = findViewById(R.id.julyCardTr);
        CardView cardView8 = findViewById(R.id.augustCardTr);
        CardView cardView9 = findViewById(R.id.septemberCardTr);
        CardView cardView10 = findViewById(R.id.octoberCardTr);
        CardView cardView11 = findViewById(R.id.novemberCardTr);
        CardView cardView12 = findViewById(R.id.decemberCardTr);


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
