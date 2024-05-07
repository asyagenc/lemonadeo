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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.net.Uri;

public class SeasonsLearning extends AppCompatActivity {

    VideoView seasonsLearningVideo;
    Button backSeasonsLearning;
    MediaController mediaController;
    boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_seasons_learning);
        mediaController = new MediaController(this);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Toolbar toolbar = findViewById(R.id.toolbarSeasonsLearning);
        setSupportActionBar(toolbar);



        seasonsLearningVideo=findViewById(R.id.seasonsLearningVideo);
        backSeasonsLearning=findViewById(R.id.backBttnSeasonsLearning);

        backSeasonsLearning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LearningMainMenu.class);
                startActivity(intent);
                finish();
            }
        });


        String seasonsPath = "android.resource://" + getPackageName() + "/" + R.raw.seasons;

        seasonsLearningVideo.setVideoURI(Uri.parse(seasonsPath));

        seasonsLearningVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                // Video hazır olduğunda, oynatmaya başla
                seasonsLearningVideo.start();
            }
        });

        // VideoView'a tıklanıldığında gerçekleşecek olayları tanımlama
        seasonsLearningVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    // Eğer video oynatılıyorsa, duraklat
                    seasonsLearningVideo.pause();
                    isPlaying = false;
                } else {
                    // Eğer video duraklatılmışsa, başlat
                    seasonsLearningVideo.start();
                    isPlaying = true;
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}