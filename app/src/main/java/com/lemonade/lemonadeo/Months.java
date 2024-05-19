package com.lemonade.lemonadeo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

public class Months extends AppCompatActivity {

    Button mainButton;

    Button[] otherButtons = new Button[12];

    MediaPlayer correctMonths;
    MediaPlayer incorrectMonths;
    ImageView lemonadegirl;
    GifImageView gifImageView;
    TextView message,boyBubble,text;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout); // Bu satırı ekleyin

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_months);

        correctMonths = MediaPlayer.create(this, R.raw.correct);
        incorrectMonths = MediaPlayer.create(this, R.raw.incorrect);

        mainButton = findViewById(R.id.shuffleBttn);
        otherButtons = new Button[12]; // Array to hold references to all other buttons
        lemonadegirl=findViewById(R.id.imageView11);
        gifImageView=findViewById(R.id.gif);
        otherButtons[0] = findViewById(R.id.monthBttn1);
        otherButtons[1] = findViewById(R.id.monthBttn2);
        otherButtons[2] = findViewById(R.id.monthBttn3);
        otherButtons[3] = findViewById(R.id.monthBttn4);
        otherButtons[4] = findViewById(R.id.monthBttn5);
        otherButtons[5] = findViewById(R.id.monthBttn6);
        otherButtons[6] = findViewById(R.id.monthBttn7);
        otherButtons[7] = findViewById(R.id.monthBttn8);
        otherButtons[8] = findViewById(R.id.monthBttn9);
        otherButtons[9] = findViewById(R.id.monthBttn10);
        otherButtons[10] = findViewById(R.id.monthBttn11);
        otherButtons[11] = findViewById(R.id.monthBttn12);
        message = findViewById(R.id.txtVieww);
        boyBubble=findViewById(R.id.bubble);
        text=findViewById(R.id.textView17);
        gifImageView.setImageResource(View.GONE);
        message.setVisibility(View.GONE);
        text.setVisibility(View.GONE);




        int[] currentMonth= {1};

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lemonadegirl.setImageResource(R.drawable.lemonadegirl);

                currentMonth[0] = 1; // Reset current day to 1
                String temp;
                String temp2;
                lemonadegirl.setImageResource(R.drawable.lemlemboy);
                boyBubble.setVisibility(View.VISIBLE);
                lemonadegirl.setVisibility(View.VISIBLE);
                text.setVisibility(View.GONE);
                message.setVisibility(View.GONE);
                gifImageView.setVisibility(View.GONE);

                for (int i = 0; i <12; i++) {
                    otherButtons[i].setEnabled(true);
                    otherButtons[i].setBackgroundColor(Color.parseColor("#FF9800"));
                }
                for (Button button : otherButtons) {
                    button.setVisibility(View.VISIBLE);
                }


                for (int j = 0; j < 12; j++) {
                    Random random = new Random();
                    int randomIndex = random.nextInt(12);
                    int randomIndex2 = random.nextInt(12);
                    temp = otherButtons[randomIndex].getText().toString();
                    temp2=otherButtons[randomIndex2].getText().toString();
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
                    if (currentMonth[0] == 1 && otherButtons[buttonIndex].getText().equals("January")) {
                        text.setText("Press for next month");
                        text.setVisibility(View.VISIBLE);
                        correctMonths.start();
                        otherButtons[buttonIndex].setBackgroundColor(Color.rgb(139, 195, 74));
                        otherButtons[buttonIndex].setEnabled(false);
                        currentMonth[0] = 2;
                    } else if (currentMonth[0] == 2 && otherButtons[buttonIndex].getText().equals("February")) {
                        text.setText("Press for next month");
                        text.setVisibility(View.VISIBLE);
                        correctMonths.start();
                        otherButtons[buttonIndex].setBackgroundColor(Color.rgb(139, 195, 74));
                        otherButtons[buttonIndex].setEnabled(false);
                        currentMonth[0] = 3;
                    } else if (currentMonth[0] == 3 && otherButtons[buttonIndex].getText().equals("March")) {
                        text.setText("Press for next month");
                        text.setVisibility(View.VISIBLE);
                        correctMonths.start();
                        otherButtons[buttonIndex].setBackgroundColor(Color.rgb(139, 195, 74));
                        otherButtons[buttonIndex].setEnabled(false);
                        currentMonth[0] = 4;
                    } else if (currentMonth[0] == 4 && otherButtons[buttonIndex].getText().equals("April")) {
                        text.setText("Press for next month");
                        text.setVisibility(View.VISIBLE);
                        correctMonths.start();
                        otherButtons[buttonIndex].setBackgroundColor(Color.rgb(139, 195, 74));
                        otherButtons[buttonIndex].setEnabled(false);
                        currentMonth[0] = 5;
                    }

                    else if (currentMonth[0] == 5 && otherButtons[buttonIndex].getText().equals("May")) {
                        text.setText("Press for next month");
                        text.setVisibility(View.VISIBLE);
                        correctMonths.start();
                        otherButtons[buttonIndex].setBackgroundColor(Color.rgb(139, 195, 74));
                        otherButtons[buttonIndex].setEnabled(false);
                        currentMonth[0] = 6;
                    }

                    else if (currentMonth[0] == 6 && otherButtons[buttonIndex].getText().equals("June")) {
                        text.setText("Press for next month");
                        text.setVisibility(View.VISIBLE);
                        correctMonths.start();
                        otherButtons[buttonIndex].setBackgroundColor(Color.rgb(139, 195, 74));
                        otherButtons[buttonIndex].setEnabled(false);
                        currentMonth[0] = 7;
                    }

                    else if (currentMonth[0] == 7 && otherButtons[buttonIndex].getText().equals("July")) {
                        text.setText("Press for next month");
                        text.setVisibility(View.VISIBLE);
                        correctMonths.start();
                        otherButtons[buttonIndex].setBackgroundColor(Color.rgb(139, 195, 74));
                        otherButtons[buttonIndex].setEnabled(false);
                        currentMonth[0] = 8;
                    }
                    else if (currentMonth[0] == 8 && otherButtons[buttonIndex].getText().equals("August")) {
                        text.setText("Press for next month");
                        text.setVisibility(View.VISIBLE);
                        correctMonths.start();
                        otherButtons[buttonIndex].setBackgroundColor(Color.rgb(139, 195, 74));
                        otherButtons[buttonIndex].setEnabled(false);
                        currentMonth[0] = 9;

                    }
                    else if (currentMonth[0] == 9 && otherButtons[buttonIndex].getText().equals("September")) {
                        text.setText("Press for next month");
                        text.setVisibility(View.VISIBLE);
                        correctMonths.start();
                        otherButtons[buttonIndex].setBackgroundColor(Color.rgb(139, 195, 74));
                        otherButtons[buttonIndex].setEnabled(false);
                        currentMonth[0] = 10;

                    }

                    else if (currentMonth[0] == 10 && otherButtons[buttonIndex].getText().equals("October")) {
                        text.setText("Press for next month");
                        text.setVisibility(View.VISIBLE);
                        correctMonths.start();
                        otherButtons[buttonIndex].setBackgroundColor(Color.rgb(139, 195, 74));
                        otherButtons[buttonIndex].setEnabled(false);
                        currentMonth[0] = 11;

                    }

                    else if (currentMonth[0] == 11 && otherButtons[buttonIndex].getText().equals("November")) {
                        text.setText("Press for next month");
                        text.setVisibility(View.VISIBLE);
                        correctMonths.start();
                        otherButtons[buttonIndex].setBackgroundColor(Color.rgb(139, 195, 74));
                        otherButtons[buttonIndex].setEnabled(false);
                        currentMonth[0] = 12;

                    }

                    else if (currentMonth[0] == 12 && otherButtons[buttonIndex].getText().equals("December")) {
                        message.setText("CONGRATULATIONS ENJOY YOUR LEMONADE!");
                        correctMonths.start();
                        otherButtons[buttonIndex].setBackgroundColor(Color.rgb(139, 195, 74));
                        otherButtons[buttonIndex].setEnabled(false);
                        lemonadegirl.setImageResource(R.drawable.limonataa);
                        currentMonth[0] = 0;
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
                        currentMonth[0] = 0;


                    }

                }
            });

        }




    }




}