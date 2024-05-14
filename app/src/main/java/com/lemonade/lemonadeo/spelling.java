package com.lemonade.lemonadeo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.lang.Math;



import java.util.ArrayList;
import java.util.Arrays;


import java.util.List;




public class spelling extends AppCompatActivity {


    TextView wordTextView,correctSpellingTextView,correct;
    Button playButton,submitButton,nextButton,exitButton;
    LinearLayoutCompat letterLayout;
    List<String> words;
    List<Integer> imageResources;
    List<Integer> pronunciationAudios;
    int currentWordIndex=0;
    int score = 0;
    ImageView imageView;
    TextView wordTxt;

    MediaPlayer mediaPlayer;
    MediaPlayer incorrectPlayer;
    MediaPlayer correctPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_spelling);

        correctPlayer = MediaPlayer.create(this, R.raw.correct);
        incorrectPlayer = MediaPlayer.create(this, R.raw.incorrect);

        wordTextView=findViewById(R.id.spellingEditText);
        playButton=findViewById(R.id.playButton);
        submitButton=findViewById(R.id.submitButton);
        nextButton=findViewById(R.id.nextButton);
        letterLayout=findViewById(R.id.letterLayout);
        imageView=findViewById(R.id.imageView12);
        correctSpellingTextView=findViewById(R.id.textView13);
        wordTxt=findViewById(R.id.textView12);
        exitButton=findViewById(R.id.button14);
        correct=findViewById(R.id.textView14);


        TextView correctSpellingTextView = findViewById(R.id.textView13);
        correctSpellingTextView.setVisibility(View.GONE);
        wordTxt.setVisibility(View.GONE);
        correct.setVisibility(View.GONE);
        submitButton.setVisibility(View.VISIBLE);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        initializeWords();
        displayWord();


        wordTextView.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                // Call the method to check spelling or perform any action
                checkSpelling();

                // Close the keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                return true;
            }
            return false;
        });

        nextButton.setEnabled(false);

        nextButton.setOnClickListener(v -> goToNextWord());

        submitButton.setOnClickListener(v -> checkSpelling());
        playButton.setOnClickListener(v -> playPronunciation());

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(spelling.this, MainActivity.class);
                startActivity(intent);

            }
        });


        displayWord();
    }

    private void initializeWords() {

        words = Arrays.asList(getResources().getStringArray(R.array.spelling_words));

        imageResources = Arrays.asList(
                R.drawable.autumn_jpg,
                R.drawable.wed_jpg,
                R.drawable.orange,
                R.drawable.sat_jpg,
                R.drawable.jan,
                R.drawable.green_jpg,
                R.drawable.tongue,
                R.drawable.wrist_jpg,
                R.drawable.finger,
                R.drawable.firef_jpg

        );

        pronunciationAudios = Arrays.asList(
                R.raw.autumn,
                R.raw.wednesday,
                R.raw.orange,
                R.raw.saturday,
                R.raw.january,
                R.raw.green,
                R.raw.tongue,
                R.raw.wrist,
                R.raw.finger,
                R.raw.firefighter

        );
    }




    private void displayWord() {
        submitButton.setVisibility(View.VISIBLE);
        List<String> mutableWords = new ArrayList<>(words); // Create a mutable copy of the words list

        if (!mutableWords.isEmpty()) {
            int randomIndex = (int) (Math.random() * mutableWords.size());
            String word = mutableWords.get(randomIndex);
            mutableWords.remove(randomIndex); // Remove the word from the mutable list
            int imageResource = imageResources.get(currentWordIndex);
            int pronunciationAudio = pronunciationAudios.get(currentWordIndex);

            // Set the image resource
            imageView.setImageResource(imageResource);

            // Clear the letter layout
            letterLayout.removeAllViews();

            // Add placeholders for each letter in the word
            for (int i = 0; i < word.length(); i++) {
                TextView placeholderTextView = getTextView();
                letterLayout.addView(placeholderTextView);
            }

            // Initialize media player for pronunciation
            mediaPlayer = MediaPlayer.create(this, pronunciationAudio);
        } else {
            // Display final score or any other relevant information
            Intent intent = new Intent(spelling.this, spellResult.class);
            intent.putExtra("score", score);
            startActivity(intent);
            finish(); // Finish MainActivity to prevent going back to it from ResultActivity
        }
    }

    @NonNull
    private TextView getTextView() {
        TextView placeholderTextView = new TextView(this);
        placeholderTextView.setLayoutParams(new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        ));
        placeholderTextView.setBackgroundResource(R.drawable.border);
        placeholderTextView.setGravity(Gravity.CENTER);
        placeholderTextView.setOnClickListener(v -> {
            // Handle placeholder click (e.g., to place a letter)
            // You can implement this functionality as needed
        });
        return placeholderTextView;
    }


    private void playPronunciation() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    @SuppressLint("SetTextI18n")
    private void checkSpelling() {
        String word = words.get(currentWordIndex);
        String userSpelling = wordTextView.getText().toString().trim();

        if (userSpelling.equalsIgnoreCase(word)) {
            playCorrectSound();
            score += 5;
            correctSpellingTextView.setVisibility(View.GONE);
            wordTxt.setVisibility(View.GONE);
            nextButton.setEnabled(true);
            correct.setText("Correct!");
            correct.setVisibility(View.VISIBLE);
            submitButton.setVisibility(View.GONE);

        } else {
            playIncorrectSound();
            correctSpellingTextView.setText("Correct Spelling:");
            wordTxt.setText(word);
            correct.setVisibility(View.GONE);
            correctSpellingTextView.setVisibility(View.VISIBLE);
            wordTxt.setVisibility(View.VISIBLE);
            submitButton.setVisibility(View.GONE);
        }
        nextButton.setEnabled(true);
    }

    private void goToNextWord() {
        // Clear the previous answer
        wordTextView.setText("");

        currentWordIndex++;
        if (currentWordIndex < words.size()) {
            displayWord();
            correctSpellingTextView.setVisibility(View.GONE);
            wordTxt.setVisibility(View.GONE);
            correct.setVisibility(View.GONE);
            nextButton.setEnabled(false);
        } else {
            Intent intent = new Intent(spelling.this, spellResult.class);
            intent.putExtra("SCORE", score);
            startActivity(intent);
            finish();
        }
    }

    private void playIncorrectSound() {
        if (incorrectPlayer != null) {
            incorrectPlayer.start();
        }
    }

    private void playCorrectSound() {
        if (correctPlayer != null) {
            correctPlayer.start();
        }
    }

}