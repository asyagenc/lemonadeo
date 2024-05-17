package com.lemonade.lemonadeo;
//test
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LeftNRight extends AppCompatActivity {


    TextView textView;
    TextView textView2;
    TextView textView3;
    ImageView imageView;
    ImageView imageView2;
    Button button;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_left_nright);

        textView=findViewById(R.id.Molly_txt);
        textView2=findViewById(R.id.Daisy_txt);
        textView3=findViewById(R.id.textView);
        imageView=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);
        button=findViewById(R.id.button2);//next
        button2=findViewById(R.id.button3);//back


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),InFrontOf.class);
                startActivity(intent);
                finish();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),Directions.class);
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