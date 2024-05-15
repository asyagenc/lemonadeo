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

public class LearningMainMenu extends AppCompatActivity {


   CardView cardView,cardView2,cardView3,cardView4,cardView5,cardView6;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_learning_main_menu);


        cardView=findViewById(R.id.daysCardx);
        cardView2=findViewById(R.id.seasonCard);
        cardView3=findViewById(R.id.monthCard);
        cardView4=findViewById(R.id.daysCard);
        cardView5=findViewById(R.id.multiplicationCard);
        cardView6=findViewById(R.id.directionCard)  ;



        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ClockLearning.class);
                startActivity(intent);
                finish();}
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),learnSeasons.class);
                startActivity(intent);
                finish();

            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MonthsLearning.class);
                startActivity(intent);
                finish();

            }
        });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),learnDays.class);
                startActivity(intent);
                finish();

            }
        });



        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MathLearning.class);
                startActivity(intent);
                finish();
            }
        });




        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Directions.class);
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