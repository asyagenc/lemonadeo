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





public class MonthsLearning extends AppCompatActivity {

    VideoView monthsLearningVideo;
    Button backMonthsLearning;
    MediaController mediaController;
    boolean isPlaying = false;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_months_learning);
        mediaController = new MediaController(this);

        backMonthsLearning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LearningMainMenu.class);
                startActivity(intent);
                finish();
            }
        });


        String monthsPath = "android.resource://" + getPackageName() + "/" + R.raw.months;

        monthsLearningVideo.setVideoURI(Uri.parse(monthsPath));

        monthsLearningVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                // Video hazır olduğunda, oynatmaya başla
                monthsLearningVideo.start();
            }
        });

        // VideoView'a tıklanıldığında gerçekleşecek olayları tanımlama
        monthsLearningVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    // Eğer video oynatılıyorsa, duraklat
                    monthsLearningVideo.pause();
                    isPlaying = false;
                } else {
                    // Eğer video duraklatılmışsa, başlat
                    monthsLearningVideo.start();
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