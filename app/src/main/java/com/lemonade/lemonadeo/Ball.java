package com.lemonade.lemonadeo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.Math;
import java.util.Random;

public class Ball extends AppCompatActivity {

    ImageView ballImageView;
    Random random;
    Button backBall;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ball);
        backBall = findViewById(R.id.backBttnBall);
        ballImageView = findViewById(R.id.ballImageView);
        Toolbar toolbar = findViewById(R.id.toolbarBall);
        setSupportActionBar(toolbar);

        random = new Random();

        // Set up back button functionality
        backBall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GameMainMenu.class);
                startActivity(intent);
                finish();
            }
        });

        moveBall();

        // Handle system bar insets for layout adjustments
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    void moveBall() {
        // Masa alanının koordinatlarını al
        ImageView backgroundImage = findViewById(R.id.backgroundImage);
        int backgroundImageWidth = backgroundImage.getWidth();
        int backgroundImageHeight = backgroundImage.getHeight();

        // Topun boyutlarını al
        int ballWidth = ballImageView.getWidth();
        int ballHeight = ballImageView.getHeight();

        // Masa alanının içinde rastgele bir konum belirle
        int randomX = random.nextInt(Math.max(1, backgroundImageWidth - ballWidth));
        int randomY = random.nextInt(Math.max(1, backgroundImageHeight - ballHeight));

        // Belirlenen konuma topu yerleştir
        ballImageView.setX(randomX);
        ballImageView.setY(randomY);

        // Tekrar animasyonu başlatmak için belirli bir süre sonra moveBall() fonksiyonunu çağır
        Handler handler = new Handler();
        handler.postDelayed(this::moveBall, 1200); // Millisaniye cinsinden gecikme süresi (animasyon süresi + bir tampon süre)
    }


}
