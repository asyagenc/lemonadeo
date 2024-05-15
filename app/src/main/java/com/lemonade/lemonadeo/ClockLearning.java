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

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ClockLearning extends AppCompatActivity {


    Button submit;
    EditText hourInput;
    EditText minuteInput;
    ImageView emptyLearnCLock;
    Paint hourHandPaint;
    TextView analog;
    Paint minuteHandPaint;
    int hour = 12;
    int minute = 0;
    final String[] answer = new String[1];


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_clock_learning);




        hourInput = findViewById(R.id.hourInput);
        minuteInput = findViewById(R.id.minuteInput);
        emptyLearnCLock = findViewById(R.id.emptyClock);
        submit = findViewById(R.id.submitInput);
        analog = findViewById(R.id.AnalogOutput);


        Resources resources = getResources();
        hourHandPaint = new Paint();
        hourHandPaint.setColor(Color.BLUE);
        hourHandPaint.setStrokeWidth(5f);
        hourHandPaint.setAntiAlias(true);

        minuteHandPaint = new Paint();
        minuteHandPaint.setColor(Color.RED);
        minuteHandPaint.setStrokeWidth(3f);
        minuteHandPaint.setAntiAlias(true);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String hourS = hourInput.getText().toString().trim(); // trim() metodu ile baştaki ve sondaki boşlukları kaldırır
                if (!hourS.isEmpty()) {
                    hour = Integer.parseInt(hourS);
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter an valid hour", Toast.LENGTH_SHORT).show();
                }

                String minuteS = minuteInput.getText().toString().trim(); // trim() metodu ile baştaki ve sondaki boşlukları kaldırır
                if (!minuteS.isEmpty()) {
                    minute = Integer.parseInt(minuteS);
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter an valid minute", Toast.LENGTH_SHORT).show();
                }
                drawClockHands();
                updateClockText();


            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    protected void onResume() {
        super.onResume();
        if (emptyLearnCLock.getWidth() > 0 && emptyLearnCLock.getHeight() > 0) {
            drawClockHands();
        } else {
         
        }

    }

    private void drawClockHands() {

// ImageView'ın genişlik ve yüksekliğini al
        int imageViewWidth = emptyLearnCLock.getWidth();
        int imageViewHeight = emptyLearnCLock.getHeight();

        // Saat ve dakika ibrelerinin başlangıç noktalarını hesapla
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

        emptyLearnCLock.setImageBitmap(bitmap);
    }

    private void updateClockText() {
        if (minute == 0) {
            analog.setText(Integer.toString(hour) + " o'clock");
        } else if (minute < 15) {
            analog.setText(Integer.toString(minute) + " past " + Integer.toString(hour));
        } else if (minute == 15) {
            analog.setText(" quarter past " + Integer.toString(hour));
        } else if (minute < 30) {
            analog.setText(Integer.toString(minute) + " past " + Integer.toString(hour));
        } else if (minute == 30) {
            analog.setText(" half past " + Integer.toString(hour));
        } else if (minute < 45) {
            analog.setText(Integer.toString(60 - minute) + " to " + Integer.toString(hour + 1));
        } else if (minute == 45) {
            analog.setText(" quarter to " + Integer.toString(hour + 1));
        } else if (minute < 60) {
            analog.setText(Integer.toString(60 - minute) + " to " + Integer.toString(hour + 1));
        }
    }
}
