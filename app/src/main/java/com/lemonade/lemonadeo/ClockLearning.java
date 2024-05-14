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

    Button backClockLearning;
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



        backClockLearning = findViewById(R.id.backBttnClockLearning);
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
        backClockLearning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LearningMainMenu.class);
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

    protected void onResume() {

        super.onResume();
        // Saat ve dakika ibrelerini çizme
        drawClockHands();

    }

    private void drawClockHands() {


        // Drawable'dan Bitmap oluşturun
        @SuppressLint("UseCompatLoadingForDrawables") Drawable drawable = getResources().getDrawable(R.drawable.clocklearning);
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


        float hourAngle = (hour % 12 + minute / 60f) * 360 / 12;
        float minuteAngle = minute * 360 / 60;

        // Saat ve dakika ibrelerini çizme
        canvas.drawLine(centerX, centerY, centerX + (float) java.lang.Math.cos(java.lang.Math.toRadians(hourAngle - 90)) * hourHandLength, centerY + (float) java.lang.Math.sin(java.lang.Math.toRadians(hourAngle - 90)) * hourHandLength, hourHandPaint);
        canvas.drawLine(centerX, centerY, centerX + (float) java.lang.Math.cos(java.lang.Math.toRadians(minuteAngle - 90)) * minuteHandLength, centerY + (float) java.lang.Math.sin(java.lang.Math.toRadians(minuteAngle - 90)) * minuteHandLength, minuteHandPaint);

        // ImageView içine çizimi gösterme
        emptyLearnCLock.setImageBitmap(newBitmap);
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