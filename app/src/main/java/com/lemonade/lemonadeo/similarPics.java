package com.lemonade.lemonadeo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;


import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class similarPics extends AppCompatActivity {

    private ImageView mainImageView;
    private ImageView similarImageView1;
    private ImageView similarImageView2;
    private ImageView correctImageView;
    private int score = 0;
    private int roundsPlayed = 0;
    private boolean firstAttempt = true;
    private boolean inGame = true;

    private final List<ImageSet> imageSets = new ArrayList<>();
    private MediaPlayer mediaPlayer;
    private MediaPlayer mediaPlayerIncorrect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similar_pics);



        mediaPlayer = MediaPlayer.create(this, R.raw.correct);
        mediaPlayerIncorrect = MediaPlayer.create(this, R.raw.incorrect);


        mainImageView = findViewById(R.id.mainImageView);
        similarImageView1 = findViewById(R.id.similarImageView1);
        similarImageView2 = findViewById(R.id.similarImageView2);


        similarImageView1.setOnClickListener(v -> {
            if (inGame) {
                checkAnswer(similarImageView1);
            } else {
                Intent intent = new Intent(similarPics.this, ResultActivity.class);
                startActivity(intent);

            }
        });

        similarImageView2.setOnClickListener(v -> {
            if (inGame) {
                checkAnswer(similarImageView2);
            } else {
                // Navigate to ResultActivity when the game is not in progress
                Intent intent = new Intent(similarPics.this, ResultActivity.class);
                startActivity(intent);
            }
        });

        initializeImageSets();

        // Start the first round
        startNewRound();
    }

    private void initializeImageSets() {
        // Manually assign main images with their corresponding similar and wrong images
        imageSets.add(new ImageSet(R.drawable.angry2, R.drawable.angry, R.drawable.happy));
        imageSets.add(new ImageSet(R.drawable.cat, R.drawable.kitty, R.drawable.skank));
        imageSets.add(new ImageSet(R.drawable.dog, R.drawable.puppy, R.drawable.raccoon));
        imageSets.add(new ImageSet(R.drawable.butterfly, R.drawable.butterfly2, R.drawable.ladybug));
        imageSets.add(new ImageSet(R.drawable.ship, R.drawable.ship2, R.drawable.duck));
        imageSets.add(new ImageSet(R.drawable.pizza_jpg, R.drawable.pizza, R.drawable.pie));
        imageSets.add(new ImageSet(R.drawable.krmzelma, R.drawable.yeilelma, R.drawable.top));
        imageSets.add(new ImageSet(R.drawable.lemon, R.drawable.lemons, R.drawable.orange));
        imageSets.add(new ImageSet(R.drawable.plane, R.drawable.plane2, R.drawable.seagull));
        imageSets.add(new ImageSet(R.drawable.hat, R.drawable.cap, R.drawable.headband));
        Collections.shuffle(imageSets);



    }


    private void startNewRound() {
        if (imageSets.isEmpty()) {
            inGame = false;
            Intent intent = new Intent(similarPics.this, ResultActivity.class);
            intent.putExtra("SCORE", score);
            startActivity(intent);
            return;

        }
        Log.d("startNewRound", "imageSets size: " + imageSets.size());

        Random random = new Random();
        int currentImageSetIndex = random.nextInt(imageSets.size());
        ImageSet currentImageSet = imageSets.remove(currentImageSetIndex);

        mainImageView.setImageResource(currentImageSet.getMainImageId());

        correctImageView = random.nextBoolean() ? similarImageView1 : similarImageView2;
        correctImageView.setImageResource(currentImageSet.getSimilarImageId());

        ImageView wrongImageView = correctImageView == similarImageView1 ? similarImageView2 : similarImageView1;
        wrongImageView.setImageResource(currentImageSet.getWrongImageId());

        firstAttempt = true;
    }


    private void playIncorrectSound() {
        if (mediaPlayerIncorrect != null) {
            mediaPlayerIncorrect.start();
        }
    }

    private void playCorrectSound() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if (mediaPlayerIncorrect != null) {
            mediaPlayerIncorrect.release();
            mediaPlayerIncorrect = null;
        }
    }



    private void checkAnswer(ImageView selectedImageView) {
        boolean correctAnswer = (selectedImageView == correctImageView);

        // Update score based on user's performance
        if (correctAnswer && firstAttempt) {
            score += 10; // Correct on the first try
        } else if (correctAnswer) {
            score += 5; // Correct but not on the first try
        } else {
            score -= 5; // Incorrect
        }

        if (correctAnswer) {
            roundsPlayed++; // Increment rounds played
            startNewRound();
        }

        if (correctAnswer) {
            playCorrectSound();
        } else {
            playIncorrectSound();
        }


        // Check if the answer is correct and start a new round if so
        if (roundsPlayed == imageSets.size()) {
            inGame = false;
        }
    }



    private static class ImageSet {
        private final int mainImageId;
        private final int similarImageId;
        private final int wrongImageId;

        public ImageSet(int mainImageId, int similarImageId, int wrongImageId) {
            this.mainImageId = mainImageId;
            this.similarImageId = similarImageId;
            this.wrongImageId = wrongImageId;
        }

        public int getMainImageId() {
            return mainImageId;
        }

        public int getSimilarImageId() {
            return similarImageId;
        }

        public int getWrongImageId() {
            return wrongImageId;
        }
    }





}
