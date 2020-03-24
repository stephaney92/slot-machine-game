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
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private int slotpiclocation;
    private Random rand;
    public boolean on;
    public Handler handler;
    public updateCount update;
    private int cherryLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pointCounter = findViewById(R.id.pointCounter);
        cherryImage = getDrawable(R.drawable.cherry);
        grapeImage = getDrawable(R.drawable.grape);
        strawberryImage = getDrawable(R.drawable.strawberry);
        linearLayout = findViewById(R.id.linearLayout);

        imageView2 =  findViewById(R.id.imageView2);
        imageView3 =  findViewById(R.id.imageView3);
        imageView4 =  findViewById(R.id.imageView4);


        update = new updateCount();

        on = false;

        rand = new Random();
        cherryLocation = rand.nextInt(3);
        handler = new Handler();

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
    public class updateCount implements Runnable{

        public void run(){

            if (imageView2.getDrawable() == strawberryImage) {
                imageView2.setImageDrawable(grapeImage);
            } else if (imageView3.getDrawable() == grapeImage) {
                imageView3.setImageDrawable(cherryImage);
            } else if (imageView4.getDrawable() == cherryImage) {
                imageView4.setImageDrawable(strawberryImage);
            }

            handler.postDelayed(update, 1000);


        }
    }
}
