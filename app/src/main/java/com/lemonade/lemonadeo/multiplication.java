package com.lemonade.lemonadeo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class multiplication extends AppCompatActivity {

    private TextView numberTextView,questionTextView,scoreTextView,resultTextView;
    private EditText answerEditText;
    private Button submitAnswerButton;

    private ImageView imageView;
    private int total_score = 0;
    private int num1,num2;
    private int count = 0;

    private int min,max;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication);

        String difficulty = getIntent().getStringExtra("difficulty");
        if("Easy".equals(difficulty)){
            min = 1;
            max = 10;
        }
        else if("Medium".equals(difficulty)){
            min = 10;
            max = 50;
        }
        else if("Hard".equals(difficulty)){
            min = 50;
            max = 100;
        }

        numberTextView = findViewById(R.id.numberTextView);
        questionTextView = findViewById(R.id.questionTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        answerEditText = findViewById(R.id.answerEditText);
        submitAnswerButton = findViewById(R.id.submitAnswerButton);
        resultTextView = findViewById(R.id.resultTextView);
        imageView = findViewById(R.id.imageView);

        submitAnswerButton.setOnClickListener(v -> {
            int answer = 0;
            try {
                answer = Integer.parseInt(answerEditText.getText().toString());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            if (answer == (num1 * num2)) {
                total_score++;
                showAlertDialog("Congrulations! Correct Answer.");
            } else {
                showAlertDialog("Wrong Answer! Correct Answer was " + (num1 * num2));
            }
            answerEditText.setText("");
        });
        next_question();
    }
    private void next_question() {
        if(count == 10) {
            scoreTextView.setText("Game Over! Your score is: " + total_score + "/10");
            answerEditText.setText("");
            answerEditText.setVisibility(View.GONE);
            imageView.setVisibility(View.GONE);
            submitAnswerButton.setVisibility(View.GONE);
            numberTextView.setVisibility(View.GONE);
            if(total_score < 3 && total_score >= 0){
                resultTextView.setText("You should work harder to be an expert!");
                resultTextView.setVisibility(View.VISIBLE);
            }
            else if(total_score >= 3 && total_score <= 7){
                resultTextView.setText("You are getting closer to be an expert!");
                resultTextView.setVisibility(View.VISIBLE);
            }
            else if(total_score == 8 || total_score == 9){
                resultTextView.setText("You are an inch away to be an expert!");
                resultTextView.setVisibility(View.VISIBLE);
            }
            else if(total_score == count){
                resultTextView.setText("You are an expert!");
                resultTextView.setVisibility(View.VISIBLE);
            }
            return;
        }
        count++;
        Random random = new Random();
        num1 = random.nextInt((10 - 1) + 1) + 1;
        num2 = random.nextInt((max - min) + 1) + min;
        numberTextView.setText("What is the result of " + num1 + " x " + num2 + "?");
        questionTextView.setText("Question " + count + " / 10");
        scoreTextView.setText("Current Score: " + total_score);
    }
    private void showAlertDialog(String message) {
        new AlertDialog.Builder(multiplication.this)
                .setMessage(message)
                .setPositiveButton("Next Question", (dialog, which) -> next_question())
                .show();
    }
}