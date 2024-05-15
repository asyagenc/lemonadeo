package com.lemonade.lemonadeo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.Math;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Random;

public class Clock extends AppCompatActivity {

    ImageView clockImageView;

    Button playButton;
    Button newButton;
    EditText inputHour;
    EditText inputMinute;
    TextView score;
    TextView result;

    int hour = 12;
    int minute = 0;
    int total_score = 0;
    int count = 0;

    Paint hourHandPaint;
    Paint minuteHandPaint;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        clockImageView = findViewById(R.id.clockImageView);
        playButton = findViewById(R.id.playButton);
        inputHour = findViewById(R.id.inputHour);
        inputMinute = findViewById(R.id.inputMinute);
        newButton=findViewById(R.id.newButton);
        score=findViewById(R.id.scoreClock);
        result=findViewById(R.id.resultClock);


        Resources resources = getResources();
        hourHandPaint = new Paint();
        hourHandPaint.setColor(Color.BLUE);
        hourHandPaint.setStrokeWidth(5f);
        hourHandPaint.setAntiAlias(true);

        minuteHandPaint = new Paint();
        minuteHandPaint.setColor(Color.RED);
        minuteHandPaint.setStrokeWidth(3f);
        minuteHandPaint.setAntiAlias(true);




        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Oyun fonksiyonunu çağır
                playGame();
                playButton.setEnabled(false);
            }
        });

        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count == 10) {
                    score.setText("Game Over! Your score is: " + score + "/10");
                    if(total_score < 3 && total_score >= 0){
                        result.setText("You should work harder to be an expert!");
                        result.setVisibility(View.VISIBLE);
                    }
                    else if(total_score >= 3 && total_score <= 7){
                        result.setText("You are getting closer to be an expert!");
                        result.setVisibility(View.VISIBLE);
                    }
                    else if(total_score == 8 || total_score == 9){
                        result.setText("You are an inch away to be an expert!");
                        result.setVisibility(View.VISIBLE);
                    }
                    else if(total_score == count){
                        result.setText("You are an expert!");
                        result.setVisibility(View.VISIBLE);
                    }
                    return;
                }
                count++;
                // Rastgele saat ve dakika seçimi
                Random random = new Random();
                hour = random.nextInt(12) + 1; 
                minute = random.nextInt(12)*5; 
                score.setText("Score: " + total_score);

                // Saat ve dakika ibrelerini çizme
                drawClockHands();
                playButton.setEnabled(true);
            }
        });


    }

    protected void onResume() {
            super.onResume();
            // Saat ve dakika ibrelerini çizme
            if (clockImageView.getWidth() > 0 && clockImageView.getHeight() > 0) {
                drawClockHands();
            } else {
               
    
            }
        }




    private void drawClockHands() {
        int imageViewWidth = clockImageView.getWidth();
        int imageViewHeight = clockImageView.getHeight();
        
        int centerX = imageViewWidth / 2;
        int centerY = (imageViewHeight / 2)-20;

        float hourHandLength = imageViewWidth * 0.13f; 
        float minuteHandLength = imageViewWidth * 0.20f; 
        hourHandPaint.setStrokeWidth(13f);
        minuteHandPaint.setStrokeWidth(13f);
        hourHandPaint.setColor(Color.parseColor("#20b2aa"));
        minuteHandPaint.setColor(Color.parseColor("#ee1289"));

        float hourAngle = (hour % 12 + minute / 60f) * 360 / 12;
        float minuteAngle = minute * 360 / 60;


        float hourX = centerX + (float) Math.cos(Math.toRadians(hourAngle - 90)) * hourHandLength;
        float hourY = centerY + (float) Math.sin(Math.toRadians(hourAngle - 90)) * hourHandLength;
        float minuteX = centerX + (float) Math.cos(Math.toRadians(minuteAngle - 90)) * minuteHandLength;
        float minuteY = centerY + (float) Math.sin(Math.toRadians(minuteAngle - 90)) * minuteHandLength;


        Bitmap bitmap = Bitmap.createBitmap(imageViewWidth, imageViewHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

  
        canvas.drawLine(centerX, centerY, hourX, hourY, hourHandPaint);
        canvas.drawLine(centerX, centerY, minuteX, minuteY, minuteHandPaint);

    
        clockImageView.setImageBitmap(bitmap);
    }





 
    public void playGame() {
       

        String strHour = inputHour.getText().toString().trim();
        String strMinute = inputMinute.getText().toString().trim();

        if (!strHour.isEmpty() && !strMinute.isEmpty()) {
            int userHour = Integer.parseInt(strHour);
            int userMinute = Integer.parseInt(strMinute);

           
            if (userHour == hour && userMinute == minute) {
               
             Toast.makeText(getApplicationContext(), "Congrulations! Correct Answer.", Toast.LENGTH_SHORT).show();
             total_score++;
            } else {
                Toast.makeText(getApplicationContext(), "Wrong Answer! Correct Answer was " + hour + ":" + minute, Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Please enter valid values.", Toast.LENGTH_SHORT).show();
        }
    }
}
