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
        ballImageView = findViewById(R.id.ballImageView);


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

        // Rastgele hedef konumları belirle, ekran sınırları içinde kalacak şekilde ayarla
        int randomX = random.nextInt(Math.max(1, backgroundImageWidth - ballWidth));
        int randomY = random.nextInt(Math.max(1, backgroundImageHeight - ballHeight));

        // Ekran sınırlarını kontrol et
        randomX = Math.max(0, randomX); // Sol sınıra çarpmasını önler
        randomX = Math.min(backgroundImageWidth - ballWidth, randomX); // Sağ sınıra çarpmasını önler
        randomY = Math.max(0, randomY); // Üst sınıra çarpmasını önler
        randomY = Math.min(backgroundImageHeight - ballHeight, randomY); // Alt sınıra çarpmasını önler

        // Animasyon oluştur
        TranslateAnimation animation = new TranslateAnimation(0, randomX - ballImageView.getX(), 0, randomY - ballImageView.getY());

        // Hareketin biraz daha yavaş olması için animasyon süresini arttır
        animation.setDuration(2000); // Animasyon süresi, isteğinize göre ayarlayabilirsiniz

        animation.setFillAfter(true);

        // Animasyonu başlat
        ballImageView.startAnimation(animation);

        // Yeni konuma topu yerleştir
        ballImageView.setX(randomX);
        ballImageView.setY(randomY);

        // Tekrar animasyonu başlatmak için belirli bir süre sonra moveBall() fonksiyonunu çağır
        Handler handler = new Handler();
        handler.postDelayed(this::moveBall, 3000); // Millisaniye cinsinden gecikme süresi (animasyon süresi + bir tampon süre)
    }




}