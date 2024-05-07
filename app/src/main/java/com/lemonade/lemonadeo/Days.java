package com.lemonade.lemonadeo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;

public class Days extends AppCompatActivity {

    Button mainButton;
    Button[] otherButtons = new Button[7];
    Button backDays;

    TextView message;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_days);

        mainButton = findViewById(R.id.shuffleBttn);
        otherButtons = new Button[7]; // Array to hold references to all other buttons

        Toolbar toolbar = findViewById(R.id.toolbarDays);
        setSupportActionBar(toolbar);

        otherButtons[0] = findViewById(R.id.dayBttn1);
        otherButtons[1] = findViewById(R.id.dayBttn2);
        otherButtons[2] = findViewById(R.id.dayBttn3);
        otherButtons[3] = findViewById(R.id.dayBttn4);
        otherButtons[4] = findViewById(R.id.dayBttn5);
        otherButtons[5] = findViewById(R.id.dayBttn6);
        otherButtons[6] = findViewById(R.id.dayBttn7);
        message = findViewById(R.id.txtVieww);
        backDays=findViewById(R.id.backBttnDays);

        backDays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),GameMainMenu.class);
                startActivity(intent);
                finish();
            }
        });




        int[] currentDay = {1};

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentDay[0] = 1; // Reset current day to 1
                String temp;
                String temp2;

                for (int i = 0; i <7; i++) {
                    otherButtons[i].setEnabled(true);
                }


                for (int j = 0; j < 7; j++) {
                    Random random = new Random();
                    int randomIndex = random.nextInt(7);
                    int randomIndex2 = random.nextInt(7);
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
                    if (currentDay[0] == 1 && otherButtons[buttonIndex].getText().equals("Monday")) {
                        message.setText("Press for next day");
                        otherButtons[buttonIndex].setBackgroundColor(Color.GREEN);
                        otherButtons[buttonIndex].setEnabled(false);
                        currentDay[0] = 2;
                    } else if (currentDay[0] == 2 && otherButtons[buttonIndex].getText().equals("Tuesday")) {
                        message.setText("Press for next day");
                        otherButtons[buttonIndex].setBackgroundColor(Color.GREEN);
                        otherButtons[buttonIndex].setEnabled(false);
                        currentDay[0] = 3;
                    } else if (currentDay[0] == 3 && otherButtons[buttonIndex].getText().equals("Wednesday")) {
                        message.setText("Press for next day");
                        otherButtons[buttonIndex].setBackgroundColor(Color.GREEN);
                        otherButtons[buttonIndex].setEnabled(false);
                        currentDay[0] = 4;
                    } else if (currentDay[0] == 4 && otherButtons[buttonIndex].getText().equals("Thursday")) {
                        message.setText("Press for next day");
                        otherButtons[buttonIndex].setBackgroundColor(Color.GREEN);
                        otherButtons[buttonIndex].setEnabled(false);
                        currentDay[0] = 5;
                    }

                    else if (currentDay[0] == 5 && otherButtons[buttonIndex].getText().equals("Friday")) {
                        message.setText("Press for next day");
                        otherButtons[buttonIndex].setBackgroundColor(Color.GREEN);
                        otherButtons[buttonIndex].setEnabled(false);
                        currentDay[0] = 6;
                    }

                    else if (currentDay[0] == 6 && otherButtons[buttonIndex].getText().equals("Saturday")) {
                        message.setText("Press for next day");
                        otherButtons[buttonIndex].setBackgroundColor(Color.GREEN);
                        otherButtons[buttonIndex].setEnabled(false);
                        currentDay[0] = 7;
                    }

                    else if (currentDay[0] == 7 && otherButtons[buttonIndex].getText().equals("Sunday")) {
                        message.setText("Press for next day");
                        otherButtons[buttonIndex].setBackgroundColor(Color.GREEN);
                        otherButtons[buttonIndex].setEnabled(false);
                        currentDay[0] = 0;
                    }

                }
            });

        }









        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
    }
}