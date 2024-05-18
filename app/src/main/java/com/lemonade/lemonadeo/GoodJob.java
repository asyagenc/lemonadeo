package com.lemonade.lemonadeo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GoodJob extends AppCompatActivity {

    TextView textView;
    TextView textView2;
    ImageView imageView,exit;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_good_job);


        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.tada);
        mediaPlayer.start();


        imageView=findViewById(R.id.imageView6);
        textView=findViewById(R.id.textView10);
        textView2=findViewById(R.id.textView11);
        exit=findViewById(R.id.imageView8);


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GoodJob.this, LearningMainMenu.class);
                startActivity(intent);
                finish();
            }
        });








        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
