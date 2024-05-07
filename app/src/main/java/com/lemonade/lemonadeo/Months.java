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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class Months extends AppCompatActivity {

    Button mainButton;
    Button backMonth;
    Button[] otherButtons = new Button[12];

    TextView message;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout); // Bu satırı ekleyin

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_months);

        mainButton = findViewById(R.id.shuffleBttn);
        backMonth=findViewById(R.id.backBttnMonths);
        otherButtons = new Button[12]; // Array to hold references to all other buttons

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


        backMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),GameMainMenu.class);
                startActivity(intent);
                finish();
            }
        });

        int[] currentMonth= {1};

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                currentMonth[0] = 1; // Reset current day to 1
                String temp;
                String temp2;

                for (int i = 0; i <12; i++) {
                    otherButtons[i].setEnabled(true);
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
                        message.setText("Press for next month");
                        otherButtons[buttonIndex].setBackgroundColor(Color.GREEN);
                        otherButtons[buttonIndex].setEnabled(false);
                        currentMonth[0] = 2;
                    } else if (currentMonth[0] == 2 && otherButtons[buttonIndex].getText().equals("February")) {
                        message.setText("Press for next month");
                        otherButtons[buttonIndex].setBackgroundColor(Color.GREEN);
                        otherButtons[buttonIndex].setEnabled(false);
                        currentMonth[0] = 3;
                    } else if (currentMonth[0] == 3 && otherButtons[buttonIndex].getText().equals("March")) {
                        message.setText("Press for next month");
                        otherButtons[buttonIndex].setBackgroundColor(Color.GREEN);
                        otherButtons[buttonIndex].setEnabled(false);
                        currentMonth[0] = 4;
                    } else if (currentMonth[0] == 4 && otherButtons[buttonIndex].getText().equals("April")) {
                        message.setText("Press for next month");
                        otherButtons[buttonIndex].setBackgroundColor(Color.GREEN);
                        otherButtons[buttonIndex].setEnabled(false);
                        currentMonth[0] = 5;
                    }

                    else if (currentMonth[0] == 5 && otherButtons[buttonIndex].getText().equals("May")) {
                        message.setText("Press for next month");
                        otherButtons[buttonIndex].setBackgroundColor(Color.GREEN);
                        otherButtons[buttonIndex].setEnabled(false);
                        currentMonth[0] = 6;
                    }

                    else if (currentMonth[0] == 6 && otherButtons[buttonIndex].getText().equals("June")) {
                        message.setText("Press for next month");
                        otherButtons[buttonIndex].setBackgroundColor(Color.GREEN);
                        otherButtons[buttonIndex].setEnabled(false);
                        currentMonth[0] = 7;
                    }

                    else if (currentMonth[0] == 7 && otherButtons[buttonIndex].getText().equals("July")) {
                        message.setText("Press for next month");
                        otherButtons[buttonIndex].setBackgroundColor(Color.GREEN);
                        otherButtons[buttonIndex].setEnabled(false);
                    }
                    else if (currentMonth[0] == 8 && otherButtons[buttonIndex].getText().equals("August")) {
                        message.setText("Press for next month");
                        otherButtons[buttonIndex].setBackgroundColor(Color.GREEN);
                        otherButtons[buttonIndex].setEnabled(false);
                       
                    }
                    else if (currentMonth[0] == 9 && otherButtons[buttonIndex].getText().equals("September")) {
                        message.setText("Press for next month");
                        otherButtons[buttonIndex].setBackgroundColor(Color.GREEN);
                        otherButtons[buttonIndex].setEnabled(false);
                        
                    }

                    else if (currentMonth[0] == 10 && otherButtons[buttonIndex].getText().equals("October")) {
                        message.setText("Press for next month");
                        otherButtons[buttonIndex].setBackgroundColor(Color.GREEN);
                        otherButtons[buttonIndex].setEnabled(false);

                    }

                    else if (currentMonth[0] == 11 && otherButtons[buttonIndex].getText().equals("November")) {
                        message.setText("Press for next month");
                        otherButtons[buttonIndex].setBackgroundColor(Color.GREEN);
                        otherButtons[buttonIndex].setEnabled(false);

                    }

                    else if (currentMonth[0] == 12 && otherButtons[buttonIndex].getText().equals("December")) {
                        message.setText("Press for next month");
                        otherButtons[buttonIndex].setBackgroundColor(Color.GREEN);
                        otherButtons[buttonIndex].setEnabled(false);
                        currentMonth[0] = 0;

                    }

                }
            });

        }




    }
}