package com.lemonade.lemonadeo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class Ball extends AppCompatActivity {

    ImageView ballImageView, backgroundImage;
    TextView countdownTextView, resultTextView;
    Random random;
    int backgroundImageWidth, backgroundImageHeight;
    int screenWidth, screenHeight;

    // Define the boundaries for the ball's movement
    private int LEFT_BOUNDARY;
    private int TOP_BOUNDARY;
    private int RIGHT_BOUNDARY;
    private int BOTTOM_BOUNDARY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ball);

        ballImageView = findViewById(R.id.ballImageView);
        countdownTextView = findViewById(R.id.countdownBall);
        resultTextView = findViewById(R.id.resultBall);
        random = new Random();

        // Get screen dimensions
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;

        // Set up back button functionality

        // Handle system bar insets for layout adjustments
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Wait until the layout is inflated and measured to retrieve background image dimensions
        backgroundImage = findViewById(R.id.backgroundImage);
        backgroundImage.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // Remove the listener to avoid multiple calls
                backgroundImage.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                // Get the dimensions of the background image
                backgroundImageWidth = backgroundImage.getWidth();
                backgroundImageHeight = backgroundImage.getHeight();

                // Calculate boundaries
                LEFT_BOUNDARY = 0;
                TOP_BOUNDARY = 0;
                RIGHT_BOUNDARY = screenWidth - ballImageView.getWidth(); // Adjusted to exclude the width of the ball
                BOTTOM_BOUNDARY = screenHeight - 100 - ballImageView.getHeight(); // Adjusted to exclude 100 dp from bottom and the height of the ball

                // Start moving the ball
                moveBall();

                // Start countdown
                startCountdown();
            }
        });
    }

    // Define class variables to store the last position of the ball
    private float lastX = 0;
    private float lastY = 0;

    void moveBall() {
        // Get the ball's dimensions
        int ballWidth = ballImageView.getWidth();
        int ballHeight = ballImageView.getHeight();

        // Determine new position within the visible boundaries
        int randomX = LEFT_BOUNDARY + random.nextInt(RIGHT_BOUNDARY - LEFT_BOUNDARY - ballWidth);
        int randomY = TOP_BOUNDARY + random.nextInt(BOTTOM_BOUNDARY - TOP_BOUNDARY - ballHeight);

        // Create animation to the new position, starting from the last position
        TranslateAnimation animation = new TranslateAnimation(
                lastX, randomX,
                lastY, randomY
        );
        animation.setDuration(1000); // Set a shorter duration for smoother movement
        animation.setFillAfter(true);

        // Start the animation
        ballImageView.startAnimation(animation);

        // Update the last position to the current position
        lastX = randomX;
        lastY = randomY;

        // Schedule the next movement after a short delay
        ballImageView.postDelayed(this::moveBall, 1000); // Adjust delay as needed
    }


    // Countdown timer for 60 seconds
    private void startCountdown() {
        new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                countdownTextView.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                countdownTextView.setText("0");
                countdownTextView.setVisibility(View.GONE);
                resultTextView.setVisibility(View.VISIBLE); // Show the resultTextView
                ballImageView.setVisibility(View.GONE); // Hide the ball immediately when countdown finishes
            }
        }.start();
    }
}
