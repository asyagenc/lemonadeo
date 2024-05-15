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

        final String[] answer = new String[1];

        checkk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(minuteRandom==0){
                    answer[0] =Integer.toString( hourRandom) +" o'clock";}
                else  if(minuteRandom<15){
                    answer[0] =Integer.toString( minuteRandom)+" past " +Integer.toString( hourRandom);}
                else if(minuteRandom==15){
                    answer[0] =" quarter past "+Integer.toString( hourRandom);}
                else if(minuteRandom<30){
                    answer[0] =Integer.toString( minuteRandom)+" past " +Integer.toString( hourRandom);}
                else if(minuteRandom==30){
                    answer[0] =" half past "+Integer.toString( hourRandom);}
                else if(minuteRandom<45){
                    answer[0] =Integer.toString( 60-minuteRandom)+" to " +Integer.toString( hourRandom +1);}
                else if(minuteRandom==45){
                    answer[0] =" quarter to "+Integer.toString( hourRandom +1);}
                else if(minuteRandom<60){
                    answer[0] =Integer.toString( 60-minuteRandom)+" to " +Integer.toString( hourRandom +1);}

                if(!hourInput.getText().toString().isEmpty()) {
                    if (answer[0].equals(hourInput.getText().toString())) {
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
}