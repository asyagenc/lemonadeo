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

    Button backDigital ;
    Button neww;
    Button checkk;
    EditText hourInput;
    TextView hourr;
    TextView minutee;

    int hourRandom = 12;
    int minuteRandom = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_digital);

        backDigital=findViewById(R.id.backBttnDigital);
        neww=findViewById(R.id.newButtonD);
        checkk=findViewById(R.id.playButtonD);
        hourInput=findViewById(R.id.inputClock);
        hourr=findViewById(R.id.hour);
        minutee=findViewById(R.id.minute);
        Toolbar toolbar = findViewById(R.id.toolbarDigital);
        setSupportActionBar(toolbar);


        neww.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                        Toast.makeText(getApplicationContext(), "Tebrikler, doğru cevap!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Maalesef yanlış cevap!", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Lütfen saat ve dakika değerlerini giriniz.", Toast.LENGTH_SHORT).show();
                }





            }
        });


        backDigital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),GameMainMenu.class);
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