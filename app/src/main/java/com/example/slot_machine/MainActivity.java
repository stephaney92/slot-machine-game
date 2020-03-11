package com.example.slot_machine;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    private TextView pointCounter;
    private Drawable cherryImage;
    private Drawable grapeImage;
    private Drawable strawberryImage;
    private LinearLayout linearLayout;
    private ImageView[] imageViews;
    private int slotpiclocation;
    private Random rand;
    public boolean on;
    public Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pointCounter = findViewById(R.id.pointCounter);
        cherryImage =  getDrawable(R.drawable.cherry);
        grapeImage = getDrawable(R.drawable.grape);
        strawberryImage = getDrawable(R.drawable.strawberry);
        linearLayout = findViewById(R.id.linearLayout);
        imageViews = new ImageView[3];


        on = false;

        rand = new Random();
        slotpiclocation = rand.nextInt(4);




    }
    public void startPressed(View v){
        //if the button is pressed already clicking it again will stop it
        if (on) {
            on = false;
            //removes handler and stops counter
            handler.removeCallbacks(update);

        } else {
            //else if its on keep and has not been clicked a second time counter keeps going
            on = true;
            //creates handler starts counter
            handler.postDelayed(update, 1000);
        }

    }
}
