package com.lemonade.lemonadeo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

public class Days extends AppCompatActivity {

    Button mainButton;
    Button[] otherButtons = new Button[7];


    TextView message,boyBubble,text;
    MediaPlayer correctDays;
    MediaPlayer incorrectDays;
    ImageView lemonadegirl;
    GifImageView gifImageView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_days);

        mainButton = findViewById(R.id.shuffleBttn);
        otherButtons[0] = findViewById(R.id.dayBttn1);
        otherButtons[1] = findViewById(R.id.dayBttn2);
        otherButtons[2] = findViewById(R.id.dayBttn3);
        otherButtons[3] = findViewById(R.id.dayBttn4);
        otherButtons[4] = findViewById(R.id.dayBttn5);
        otherButtons[5] = findViewById(R.id.dayBttn6);
        otherButtons[6] = findViewById(R.id.dayBttn7);
        message = findViewById(R.id.textView15);
        lemonadegirl = findViewById(R.id.imageView11);
        gifImageView=findViewById(R.id.gif);
        boyBubble=findViewById(R.id.textView16);
        text=findViewById(R.id.textView17);

        correctDays = MediaPlayer.create(this, R.raw.correct);
        incorrectDays = MediaPlayer.create(this, R.raw.incorrect);
        gifImageView.setImageResource(View.GONE);
        message.setVisibility(View.GONE);
        text.setVisibility(View.GONE);







        int[] currentDay = {1};

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentDay[0] = 1; // Reset current day to 1
                String temp;
                String temp2;
                lemonadegirl.setImageResource(R.drawable.lemlemboy);
                boyBubble.setVisibility(View.VISIBLE);
                lemonadegirl.setVisibility(View.VISIBLE);
                text.setVisibility(View.GONE);
                message.setVisibility(View.GONE);
                gifImageView.setVisibility(View.GONE);
                for (Button button : otherButtons) {
                    button.setVisibility(View.VISIBLE);
                }


                for (Button button : otherButtons) {
                    button.setEnabled(true);
                }

                for (int j = 0; j < 7; j++) {
                    Random random = new Random();
                    int randomIndex = random.nextInt(7);
                    int randomIndex2 = random.nextInt(7);
                    temp = otherButtons[randomIndex].getText().toString();
                    temp2 = otherButtons[randomIndex2].getText().toString();
                    otherButtons[randomIndex].setText(temp2);
                    otherButtons[randomIndex2].setText(temp);
                }
            }
        });

        for (int j = 0; j < otherButtons.length; j++) {
            final int buttonIndex = j; // Capture button index for each listener

            otherButtons[j].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (currentDay[0] == 1 && otherButtons[buttonIndex].getText().equals("Monday")) {

                        text.setText("Press for next day");
                        text.setVisibility(View.VISIBLE);
                        correctDays.start();
                        otherButtons[buttonIndex].setBackgroundColor(Color.rgb(139, 195, 74));
                        otherButtons[buttonIndex].setEnabled(false);
                        currentDay[0] = 2;
                    } else if (currentDay[0] == 2 && otherButtons[buttonIndex].getText().equals("Tuesday")) {
                        text.setText("Press for next day");
                        text.setVisibility(View.VISIBLE);
                        correctDays.start();
                        otherButtons[buttonIndex].setBackgroundColor(Color.rgb(139, 195, 74));
                        otherButtons[buttonIndex].setEnabled(false);
                        currentDay[0] = 3;
                    } else if (currentDay[0] == 3 && otherButtons[buttonIndex].getText().equals("Wednesday")) {
                        text.setText("Press for next day");
                        text.setVisibility(View.VISIBLE);
                        correctDays.start();
                        otherButtons[buttonIndex].setBackgroundColor(Color.rgb(139, 195, 74));
                        otherButtons[buttonIndex].setEnabled(false);
                        currentDay[0] = 4;
                    } else if (currentDay[0] == 4 && otherButtons[buttonIndex].getText().equals("Thursday")) {
                        text.setText("Press for next day");
                        text.setVisibility(View.VISIBLE);
                        correctDays.start();
                        otherButtons[buttonIndex].setBackgroundColor(Color.rgb(139, 195, 74));
                        otherButtons[buttonIndex].setEnabled(false);
                        currentDay[0] = 5;
                    } else if (currentDay[0] == 5 && otherButtons[buttonIndex].getText().equals("Friday")) {
                        text.setText("Press for next day");
                        text.setVisibility(View.VISIBLE);
                        correctDays.start();
                        otherButtons[buttonIndex].setBackgroundColor(Color.rgb(139, 195, 74));
                        otherButtons[buttonIndex].setEnabled(false);
                        currentDay[0] = 6;
                    } else if (currentDay[0] == 6 && otherButtons[buttonIndex].getText().equals("Saturday")) {
                        text.setText("Press for next day");
                        text.setVisibility(View.VISIBLE);
                        correctDays.start();
                        otherButtons[buttonIndex].setBackgroundColor(Color.rgb(139, 195, 74));
                        otherButtons[buttonIndex].setEnabled(false);
                        currentDay[0] = 7;
                    } else if (currentDay[0] == 7 && otherButtons[buttonIndex].getText().equals("Sunday")) {
                        message.setText("CONGRATULATIONS ENJOY YOUR LEMONADE!");
                        correctDays.start();
                        gifImageView.setImageResource(R.drawable.limonata);
                        gifImageView.setVisibility(View.VISIBLE);
                        message.setVisibility(View.VISIBLE);
                        lemonadegirl.setVisibility(View.GONE);
                        text.setVisibility(View.GONE);
                        boyBubble.setVisibility(View.GONE);
                        for (Button button : otherButtons) {
                            button.setVisibility(View.GONE);
                        }

                        otherButtons[buttonIndex].setBackgroundColor(Color.rgb(139, 195, 74));
                        otherButtons[buttonIndex].setEnabled(false);
                        currentDay[0] = 0;
                    }
                }
            });
        }
    }
}
