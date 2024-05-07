package com.lemonade.lemonadeo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;




public class Directions extends AppCompatActivity {
    TextView textView;
    TextView textView2;
    TextView textView3;

    ImageView imageView;
    ImageView imageView2;
    Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_directions);
        textView=findViewById(R.id.myCats);
        textView2=findViewById(R.id.molly);
        textView3=findViewById(R.id.daisy);
        imageView=findViewById(R.id.Molly);
        imageView2=findViewById(R.id.Daisy);
        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),LeftNRight.class);
                startActivity(intent);
                finish();
            }
        });

    }
}