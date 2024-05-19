package com.lemonade.lemonadeo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Digital extends AppCompatActivity {


    Button neww;
    Button checkk;
    EditText hourInput;
    TextView hourr;
    TextView minutee;

    TextView score;
    TextView result;

    int hourRandom = 12;
    int minuteRandom = 0;
    int total_score = 0;
    int count = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_digital);


        neww=findViewById(R.id.newButtonD);
        checkk=findViewById(R.id.playButtonD);
        hourInput=findViewById(R.id.inputClock);
        hourr=findViewById(R.id.hour);
        minutee=findViewById(R.id.minute);
        score=findViewById(R.id.scoreDigitalClock);
        result=findViewById(R.id.resultDigitalClock);



        neww.setOnClickListener(new View.OnClickListener() {
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
                score.setText("Score: " + total_score);
                Random random = new Random();
                hourRandom = random.nextInt(12) + 1;
                minuteRandom = random.nextInt(60);
                hourr.setText(String.valueOf(hourRandom));
                minutee.setText(String.valueOf(minuteRandom));
                neww.setEnabled(true);
            }
        });

        final String[] answer = new String[3];

        checkk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                answer[0]=numberToText(hourRandom);
                answer[1]=numberToText(minuteRandom);
                answer[2]=answer[0]+" " + answer[1];

                if(!hourInput.getText().toString().isEmpty()) {
                    if (answer[2].equals(hourInput.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Congrulations! Correct Answer.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Wrong Answer!", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Please enter valid values.", Toast.LENGTH_SHORT).show();
                }





            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public static String numberToText(int number) {
        if (number < 20) {
            if(number<10){
                return singleDigitToText(number);}
            else { return tenToNineteenToText(number);}
        } else if (number < 30) {
            return "twenty-" + singleDigitToText(number % 10);
        } else if (number < 40) {
            return "thirty-" + singleDigitToText(number % 10);
        } else if (number < 50) {
            return "forty-" + singleDigitToText(number % 10);
        } else if (number < 60) {
            return "fifty-" + singleDigitToText(number % 10);
        } else if (number == 60) {
            return "sixty";
        } else {
            return "";
        }
    }


    public static String singleDigitToText(int digit) {
        switch (digit) {
            case 0: return "zero";
            case 1: return "one";
            case 2: return "two";
            case 3: return "three";
            case 4: return "four";
            case 5: return "five";
            case 6: return "six";
            case 7: return "seven";
            case 8: return "eight";
            case 9: return "nine";
            default: return "";
        }
    }

    public static String tenToNineteenToText(int number) {
        switch (number) {
            case 10: return "ten";
            case 11: return "eleven";
            case 12: return "twelve";
            case 13: return "thirteen";
            case 14: return "fourteen";
            case 15: return "fifteen";
            case 16: return "sixteen";
            case 17: return "seventeen";
            case 18: return "eighteen";
            case 19: return "nineteen";
            default: return "";
        }
    }
}
