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

import java.util.Locale;
import java.util.Random;

public class Clock extends AppCompatActivity {

    ImageView clockImageView;

    Button playButton;
    Button newButton;
    EditText input;
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
        input = findViewById(R.id.inputHour);
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
                if(count == 10 && !isAppLanguageTurkish()) {
                    score.setText("Game Over! Your score is: " + total_score + "/10");
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
                else if (count == 10 && isAppLanguageTurkish()) {
                    score.setText("Oyun Bitti! Puanınız: " + total_score + "/10");
                    if (total_score < 3 && total_score >= 0) {
                        result.setText("Uzman olmak için daha çok çalışmalısınız!");
                        result.setVisibility(View.VISIBLE);
                    } else if (total_score >= 3 && total_score <= 7) {
                        result.setText("Uzman olmaya yaklaşıyorsunuz!");
                        result.setVisibility(View.VISIBLE);
                    } else if (total_score == 8 || total_score == 9) {
                        result.setText("Uzman olmaya çok az kaldı!");
                        result.setVisibility(View.VISIBLE);
                    } else if (total_score == count) {
                        result.setText("Uzmansınız!");
                        result.setVisibility(View.VISIBLE);
                    }
                    return;
                }
                count++;
                // Rastgele saat ve dakika seçimi
                Random random = new Random();
                hour = random.nextInt(12) + 1; // 1 ile 12 arasında rastgele bir saat seç
                minute = random.nextInt(12)*5; // 0 ile 59 arasında rastgele bir dakika seç
                score.setText("Score: " + total_score);

                // Saat ve dakika ibrelerini çizme
                drawClockHands();
                playButton.setEnabled(true);
            }
        });


    }

   public  String turkishi(int saat) {
       if (saat == 1 || saat == 5 || saat == 8 || saat == 11) {
           return "'i";
       } else if (saat == 2 || saat == 7 || saat == 12) {
           return "'yi";
       } else if (saat == 3 || saat == 4) {
           return "'ü";
       } else if (saat == 6) {
           return "'yı";
       } else if (saat == 9 || saat == 10) {
           return "'u";
       }
       return "i";

   }

    public  String turkisha(int saat) {
        if (saat == 1 || saat == 5 || saat == 8 || saat == 11 || saat == 3 || saat == 4) {
            return "'e";
        } else if (saat == 2 || saat == 7 || saat == 12) {
            return "'ye";
        } else if (saat == 6) {
            return "'ya";
        } else if (saat == 9 || saat == 10) {
            return "'a";
        }
     return "e";
    }


    private boolean isAppLanguageTurkish() {
        Locale current = getResources().getConfiguration().locale;
        return current.getLanguage().equals(new Locale("tr").getLanguage());
    }

    protected void onResume() {
            super.onResume();
            // Saat ve dakika ibrelerini çizme
            if (clockImageView.getWidth() > 0 && clockImageView.getHeight() > 0) {
                drawClockHands();
            } else {
                // Bekleme ya da başka bir işlem yapma
            }
        }




    private void drawClockHands() {


        // ImageView'ın genişlik ve yüksekliğini al
        int imageViewWidth = clockImageView.getWidth();
        int imageViewHeight = clockImageView.getHeight();

        // Saat ve dakika ibrelerinin başlangıç noktalarını hesapla
        int centerX = imageViewWidth / 2;
        int centerY = (imageViewHeight / 2)-20;

        float hourHandLength = imageViewWidth * 0.13f; // Yelkovanın uzunluğu
        float minuteHandLength = imageViewWidth * 0.20f; // Akrebin uzunluğu
        hourHandPaint.setStrokeWidth(13f);
        minuteHandPaint.setStrokeWidth(13f);
        hourHandPaint.setColor(Color.parseColor("#20b2aa"));
        minuteHandPaint.setColor(Color.parseColor("#ee1289"));

        // Saat ve dakika ibrelerini çizme
        float hourAngle = (hour % 12 + minute / 60f) * 360 / 12;
        float minuteAngle = minute * 360 / 60;

        // Saat ve dakika ibrelerini çizmek için başlangıç ve bitiş noktalarını belirle
        float hourX = centerX + (float) Math.cos(Math.toRadians(hourAngle - 90)) * hourHandLength;
        float hourY = centerY + (float) Math.sin(Math.toRadians(hourAngle - 90)) * hourHandLength;
        float minuteX = centerX + (float) Math.cos(Math.toRadians(minuteAngle - 90)) * minuteHandLength;
        float minuteY = centerY + (float) Math.sin(Math.toRadians(minuteAngle - 90)) * minuteHandLength;

        // ImageView üzerine çizim yapmak için bir Canvas oluştur
        Bitmap bitmap = Bitmap.createBitmap(imageViewWidth, imageViewHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        // Saat ve dakika ibrelerini çizin
        canvas.drawLine(centerX, centerY, hourX, hourY, hourHandPaint);
        canvas.drawLine(centerX, centerY, minuteX, minuteY, minuteHandPaint);

        // ImageView'a yeni bitmap'i ayarlayın
        clockImageView.setImageBitmap(bitmap);
    }






    final String[] answer = new String[1];

    public void playGame() {

        answer[0] = input.getText().toString().trim();

        if (!isAppLanguageTurkish()){
            if (minute == 0) {
                answer[0] = Integer.toString(hour) + " o'clock";
            } else if (minute < 15) {
                answer[0] = Integer.toString(minute) + " past " + Integer.toString(hour);
            } else if (minute == 15) {
                answer[0] = "quarter past " + Integer.toString(hour);
            } else if (minute < 30) {
                answer[0] = Integer.toString(minute) + " past " + Integer.toString(hour);
            } else if (minute == 30) {
                answer[0] = "half past " + Integer.toString(hour);
            } else if (minute < 45) {
                answer[0] = Integer.toString(60 - minute) + " to " + Integer.toString(hour + 1);
            } else if (minute == 45) {
                answer[0] = "quarter to " + Integer.toString(hour + 1);
            } else if (minute < 60) {
                answer[0] = Integer.toString(60 - minute) + " to " + Integer.toString(hour + 1);
            }

        if (!input.getText().toString().isEmpty()) {
            if (input.getText().toString().equals(answer[0])) {
                Toast.makeText(getApplicationContext(), "Congrulations! Correct Answer.", Toast.LENGTH_SHORT).show();
                total_score++;
            } else {
                Toast.makeText(getApplicationContext(), "Wrong Answer! Correct Answer was:  " + answer[0], Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Please enter valid values.", Toast.LENGTH_SHORT).show();
        }

    }

        else{

            if (minute == 0) {
                answer[0] = Integer.toString(hour);
            } else if (minute < 15) {
                answer[0] = Integer.toString(hour) +turkishi(hour)+ " " + Integer.toString(minute) + " gece";
            } else if (minute == 15) {
                answer[0] =   Integer.toString(hour) +turkishi(hour)+" ceyrek gece";
            } else if (minute < 30) {
                answer[0] = Integer.toString(hour) +turkishi(hour)+ " " + Integer.toString(minute) + " gece";
            } else if (minute == 30) {
                answer[0] =  Integer.toString(hour) + " bucuk";
            } else if (minute < 45) {
                answer[0] = Integer.toString(hour+1) +turkisha(hour)+ " " + Integer.toString(60 - minute) + " var";
            } else if (minute == 45) {
                answer[0] = Integer.toString(hour + 1) + "ceyrek var" ;
            } else if (minute < 60) {
                answer[0] =  Integer.toString(hour+1) +turkisha(hour)+ " " + Integer.toString(60 - minute) + " var";
            }

            if (!input.getText().toString().isEmpty()) {
                if (input.getText().toString().equals(answer[0])) {
                    // Doğru cevap
                    Toast.makeText(getApplicationContext(), "Tebrikler! Doğru Cevap.", Toast.LENGTH_SHORT).show();
                    total_score++;
                } else {
                    Toast.makeText(getApplicationContext(), "Yanlış Cevap! Doğru Cevap: " + answer[0], Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Lütfen geçerli değerler girin.", Toast.LENGTH_SHORT).show();
            }





        }

    }

}
