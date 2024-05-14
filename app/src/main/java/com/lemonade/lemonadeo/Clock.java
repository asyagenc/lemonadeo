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
    TextView hourTextView;
    TextView minuteTextView;
    Button playButton;
    Button backClock;
    Button newButton;
    EditText inputHour;
    EditText inputMinute;

    int hour = 12;
    int minute = 0;

    Paint hourHandPaint;
    Paint minuteHandPaint;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        clockImageView = findViewById(R.id.clockImageView);
        hourTextView = findViewById(R.id.hourTextView);
        minuteTextView = findViewById(R.id.minuteTextView);
        playButton = findViewById(R.id.playButton);
        inputHour = findViewById(R.id.inputHour);
        inputMinute = findViewById(R.id.inputMinute);
        newButton=findViewById(R.id.newButton);
        backClock=findViewById(R.id.backBttnClock);

        Resources resources = getResources();
        hourHandPaint = new Paint();
        hourHandPaint.setColor(Color.BLUE);
        hourHandPaint.setStrokeWidth(5f);
        hourHandPaint.setAntiAlias(true);

        minuteHandPaint = new Paint();
        minuteHandPaint.setColor(Color.RED);
        minuteHandPaint.setStrokeWidth(3f);
        minuteHandPaint.setAntiAlias(true);


        backClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),GameMainMenu.class);
                startActivity(intent);
                finish();
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Oyun fonksiyonunu çağır
                playGame();
                minuteTextView.setVisibility(View.VISIBLE);
                hourTextView.setVisibility(View.VISIBLE);
                playButton.setEnabled(false);
            }
        });

        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Rastgele saat ve dakika seçimi
                Random random = new Random();
                hour = random.nextInt(12) + 1; // 1 ile 12 arasında rastgele bir saat seç
                minute = random.nextInt(60); // 0 ile 59 arasında rastgele bir dakika seç

                // Saat ve dakika ibrelerini çizme
                drawClockHands();
                minuteTextView.setVisibility(View.GONE);
                hourTextView.setVisibility(View.GONE);
                playButton.setEnabled(true);
            }
        });


    }

    protected void onResume() {

        super.onResume();
        // Saat ve dakika ibrelerini çizme
        drawClockHands();

    }

    private void drawClockHands() {
        // Saat ve dakika değerlerini metin olarak güncelleme
        updateClockText();

        // Drawable'dan Bitmap oluşturun
        @SuppressLint("UseCompatLoadingForDrawables") Drawable drawable = getResources().getDrawable(R.drawable.emptykadran);
        Bitmap clockBitmap = ((BitmapDrawable) drawable).getBitmap();

        // Saat ve dakika ibrelerinin boyutlarını hesaplama
        int centerX = clockBitmap.getWidth() / 2;
        int centerY = clockBitmap.getHeight() / 2;

        float hourHandLength = centerX * 0.5f;
        float minuteHandLength = centerX * 0.7f;

        // Yeni bir Bitmap oluşturun ve ona canvas bağlayın
        Bitmap newBitmap = Bitmap.createBitmap(clockBitmap.getWidth(), clockBitmap.getHeight(), clockBitmap.getConfig());
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawBitmap(clockBitmap, 0, 0, null);

        // Saat ve dakika ibrelerinin açılarını hesaplama
        float hourAngle = (hour % 12 + minute / 60f) * 360 / 12;
        float minuteAngle = minute * 360 / 60;

        // Saat ve dakika ibrelerini çizme
        canvas.drawLine(centerX, centerY, centerX + (float) Math.cos(Math.toRadians(hourAngle - 90)) * hourHandLength, centerY + (float) Math.sin(Math.toRadians(hourAngle - 90)) * hourHandLength, hourHandPaint);
        canvas.drawLine(centerX, centerY, centerX + (float) Math.cos(Math.toRadians(minuteAngle - 90)) * minuteHandLength, centerY + (float) Math.sin(Math.toRadians(minuteAngle - 90)) * minuteHandLength, minuteHandPaint);

        // ImageView içine çizimi gösterme
        clockImageView.setImageBitmap(newBitmap);
    }

    private void updateClockText() {
        hourTextView.setText("Saat " + hour);
        minuteTextView.setText("Dakika " + minute);
    }

    // Oyun fonksiyonu (uygulamaya göre geliştirilecek)
    public void playGame() {
        // Kullanıcının girişini alın

        String strHour = inputHour.getText().toString().trim();
        String strMinute = inputMinute.getText().toString().trim();

        if (!strHour.isEmpty() && !strMinute.isEmpty()) {
            int userHour = Integer.parseInt(strHour);
            int userMinute = Integer.parseInt(strMinute);

            // Kullanıcının girdiği değerlerin kontrolü
            if (userHour == hour && userMinute == minute) {
                // Doğru cevap
                Toast.makeText(getApplicationContext(), "Tebrikler, doğru cevap!", Toast.LENGTH_SHORT).show();
            } else {
                // Yanlış cevap
                Toast.makeText(getApplicationContext(), "Maalesef yanlış cevap! Doğru cevap: " + hour + ":" + minute, Toast.LENGTH_LONG).show();
            }
        } else {
            // Boş giriş
            Toast.makeText(getApplicationContext(), "Lütfen saat ve dakika değerlerini giriniz.", Toast.LENGTH_SHORT).show();
        }
    }
}
