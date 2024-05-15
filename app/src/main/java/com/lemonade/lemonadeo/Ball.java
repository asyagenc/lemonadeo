package com.lemonade.lemonadeo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class Ball extends AppCompatActivity {

    ImageView ballImageView;
    Random random;
    TextView countdown;
    TextView result;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball);

        ballImageView = findViewById(R.id.ballImageView);
        countdown = findViewById(R.id.countdownBall);
        result=findViewById(R.id.resultBall);

        random = new Random();
        Toast.makeText(getApplicationContext(), "Follow the hot air balloon with your eyes", Toast.LENGTH_SHORT).show();

        moveBall();
        startCountdown();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    void moveBall() {
        // ConstraintLayout türünde backgroundImage olarak değiştirildi
        androidx.constraintlayout.widget.ConstraintLayout backgroundImage = findViewById(R.id.backgroundImage);
        int backgroundImageWidth = backgroundImage.getWidth();
        int backgroundImageHeight = backgroundImage.getHeight();

        int ballWidth = ballImageView.getWidth();
        int ballHeight = ballImageView.getHeight();

        int randomX = random.nextInt(Math.max(1, backgroundImageWidth - ballWidth));
        int randomY = random.nextInt(Math.max(1, backgroundImageHeight - ballHeight));

        randomX = Math.max(0, randomX);
        randomX = Math.min(backgroundImageWidth - ballWidth, randomX);
        randomY = Math.max(0, randomY);
        randomY = Math.min(backgroundImageHeight - ballHeight, randomY);

        TranslateAnimation animation = new TranslateAnimation(0, randomX - ballImageView.getX(), 0, randomY - ballImageView.getY());
        animation.setDuration(3000);
        animation.setFillAfter(true);

        ballImageView.startAnimation(animation);

        ballImageView.setX(randomX);
        ballImageView.setY(randomY);

        Handler handler = new Handler();
        handler.postDelayed(this::moveBall, 3000);
    }

    private void startCountdown() {
        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                countdown.setText(String.valueOf(millisUntilFinished / 1000));
                countdown.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            }

            public void onFinish() {
                result.setVisibility(View.VISIBLE);
                Intent intent = new Intent(Ball.this, GameMainMenu.class);
                startActivity(intent);
            }
        }.start();
    }
}
