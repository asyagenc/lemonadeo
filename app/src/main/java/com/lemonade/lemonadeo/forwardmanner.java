package com.lemonade.lemonadeo;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.Gravity;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class forwardmanner extends AppCompatActivity {

    private TextView numberTextView,timerTextView,questionTextView,scoreTextView;
    private EditText answerEditText;
    private Button submitAnswerButton;
    private int total_score = 0;
    private int num;
    private int count = 0;
    private int min,max;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forwardmanner);

        String difficulty = getIntent().getStringExtra("difficulty");
        if("Easy".equals(difficulty)){
            min = 1000;
            max = 10000;
        }
        else if("Medium".equals(difficulty)){
            min = 10000;
            max = 100000;
        }
        else if("Hard".equals(difficulty)){
            min = 100000;
            max = 1000000;
        }

        numberTextView = findViewById(R.id.numberTextView);
        timerTextView = findViewById(R.id.timerTextView);
        questionTextView = findViewById(R.id.questionTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        answerEditText = findViewById(R.id.answerEditText);
        submitAnswerButton = findViewById(R.id.submitAnswerButton);
        imageView= findViewById(R.id.imageView10);



        submitAnswerButton.setOnClickListener(v -> {
            int answer = 0;
            try {
                answer = Integer.parseInt(answerEditText.getText().toString());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            if (answer == num) {
                total_score++;
                showAlertDialog("Congrulations! Correct Answer.");
            } else {
                showAlertDialog("Wrong Answer! Correct Answer was " + num);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),game.class);
                startActivity(intent);
                finish();
            }
        });

        next_question();
    }

    private void next_question() {
        if(count == 10) {
            scoreTextView.setText("Game Over! Your score is: " + total_score + " / 10");
            return;
        }
        count++;
        Random random = new Random();
        num = random.nextInt((max - min) + 1) + min;
        numberTextView.setText("Try to remember the number below\n\n" + num);
        numberTextView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        answerEditText.setText("");
        answerEditText.setVisibility(View.GONE);
        submitAnswerButton.setVisibility(View.GONE);
        numberTextView.setVisibility(View.VISIBLE);

        new CountDownTimer(6000, 1000) {
            public void onTick(long millisUntilFinished) {
                questionTextView.setText("Question: " + count + " / 10");
                timerTextView.setText("\nTime remaining: " + millisUntilFinished / 1000 + " sec");
            }

            public void onFinish() {
                numberTextView.setVisibility(View.GONE);
                answerEditText.setVisibility(View.VISIBLE);
                submitAnswerButton.setVisibility(View.VISIBLE);
                timerTextView.setText("Enter your answer in correct order:");
            }
        }.start();
    }
    private void showAlertDialog(String message) {
        new AlertDialog.Builder(forwardmanner.this)
                .setMessage(message)
                .setPositiveButton("Next Question", (dialog, which) -> next_question())
                .show();
    }
}