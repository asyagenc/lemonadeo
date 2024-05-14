package com.lemonade.lemonadeo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.net.Uri;
import android.widget.MediaController;

public class DaysLearning extends AppCompatActivity {

    Button backDaysLearning;
    VideoView daysLearningVideo;
    MediaController mediaController;
    boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_days_learning);


        backDaysLearning=findViewById(R.id.backBttnDaysLearning);
        daysLearningVideo=findViewById(R.id.daysLearningVideo);
        mediaController = new MediaController(this);


        backDaysLearning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LearningMainMenu.class);
                startActivity(intent);
                finish();
            }
        });

        String daysPath = "android.resource://" + getPackageName() + "/" + R.raw.days;

        daysLearningVideo.setVideoURI(Uri.parse(daysPath));

        daysLearningVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                // Video hazır olduğunda, oynatmaya başla
                daysLearningVideo.start();
            }
        });

        // VideoView'a tıklanıldığında gerçekleşecek olayları tanımlama
        daysLearningVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    // Eğer video oynatılıyorsa, duraklat
                    daysLearningVideo.pause();
                    isPlaying = false;
                } else {
                    // Eğer video duraklatılmışsa, başlat
                    daysLearningVideo.start();
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