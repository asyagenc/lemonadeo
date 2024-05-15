package com.lemonade.lemonadeo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GameMainMenu extends AppCompatActivity {


    CardView cardView,cardView2,cardView3,cardView4,cardView5,cardView6,cardView7,cardView8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game_main_menu);



        cardView=findViewById(R.id.daysCardx);
        cardView2=findViewById(R.id.ballCard);
        cardView3=findViewById(R.id.monthCard);
        cardView4=findViewById(R.id.daysCard);
        cardView5=findViewById(R.id.spellingCard);
        cardView6=findViewById(R.id.multiplicationCard);
        cardView7=findViewById(R.id.digitCard);
        cardView8=findViewById(R.id.similarCard);





        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameMainMenu.this, Clock.class);
                startActivity(intent);
            }
        });



        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameMainMenu.this, Months.class);
                startActivity(intent);

            }
        });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameMainMenu.this, Days.class);
                startActivity(intent);
            }
        });




        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameMainMenu.this, mathMenu.class);
                startActivity(intent);
            }
        });

        cardView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameMainMenu.this, similarPics.class);
                startActivity(intent);
            }
        });

        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameMainMenu.this, spelling.class);
                startActivity(intent);
            }
        });



        cardView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameMainMenu.this, game.class);
                startActivity(intent);
            }
        });



        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Ball.class);
                startActivity(intent);
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}