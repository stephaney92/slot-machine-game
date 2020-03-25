package com.example.slot_machine;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
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
    private SeekBar speedBar;
    public boolean on;
    public Handler handler;
    public updateCount update;
    private int count;
    private Button startButton;



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

        startButton = findViewById(R.id.startButton);

        speedBar = findViewById(R.id.speedBar);

        //creating an new update method
        update = new updateCount();


        //checks to see if save instance state is empty
        //meaning if the app hasn't started yet then the instance is null
        if (savedInstanceState == null){
            count = 0;
            on = false;
        }
        else{
            //else continue with count of the points in the next orientation
            //extracting count from from the bundle
            count = savedInstanceState.getInt("COUNT");
            pointCounter.setText("" + count );
            //passes value of on to the next orientation
            on = savedInstanceState.getBoolean("ON");
            if (on){
                startButton.setText("Stop");
            }


        }


        //creating a new handler
        handler = new Handler();

        //adding speed to the progress bar
        speedBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBar.setProgress(progress);
                handler.postDelayed(update, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    public void onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);
        //putting information into the bundle
        bundle.putInt("COUNT", count);
        bundle.putBoolean("ON", on);
    }
    public void invitePressed (View v){
        //navigates to contacts (view = action_view, this content ("")
        //uniform resource to find content by a string
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"));
        //starts the intent
        startActivity(i);
    }

    public void rulesPressed(View v){
        //launches second screen rules_Activity screen
        //intent-activity pages talk to each other
        Intent i = new Intent(this,rules_Activity.class);
        startActivityForResult(i,1);
    }

    public void startPressed(View v){
        //if the button is pressed already clicking it again will stop it
        if (on) {
            on = false;
            //removes handler and stops counter
            handler.removeCallbacks(update);
            startButton.setText("Start");
            //point counter update
            if (imageView2.getDrawable() == imageView3.getDrawable()) {
                count += 50;
                pointCounter.setText(count + "");
            }
            else if (imageView3.getDrawable() == imageView4.getDrawable()) {
                count += 50;
                pointCounter.setText(count + "");
            }
            else if (imageView4.getDrawable() == imageView2.getDrawable()) {
                count += 50;
                pointCounter.setText(count + "");
            }
            else if (imageView2.getDrawable().equals(imageView3.getDrawable()) && imageView3.getDrawable().equals(imageView4.getDrawable()) &&imageView4.getDrawable().equals(imageView2.getDrawable())){
                count += 100;
                pointCounter.setText(count + "");
            }


        } else {
            //else if its on keep and has not been clicked a second time counter keeps going
            on = true;
            //creates handler starts counter
            handler.postDelayed(update, 1000);
            startButton.setText("Stop");

        }

    }

    public class updateCount implements Runnable{

        public void run(){

            if (imageView2.getDrawable() == grapeImage) {
                imageView2.setImageDrawable(strawberryImage);
                } else if (imageView2.getDrawable() == strawberryImage) {
                    imageView2.setImageDrawable(cherryImage);
                } else {
                    imageView2.setImageDrawable(grapeImage);
                }
            if (imageView3.getDrawable() == cherryImage) {
                    imageView3.setImageDrawable(grapeImage);
                } else if (imageView3.getDrawable() == grapeImage) {
                    imageView3.setImageDrawable(strawberryImage);
                } else {
                    imageView3.setImageDrawable(cherryImage);
                }
            if (imageView4.getDrawable() == strawberryImage) {
                    imageView4.setImageDrawable(grapeImage);
                } else if (imageView4.getDrawable() == grapeImage) {
                    imageView4.setImageDrawable(cherryImage);
                } else {
                    imageView4.setImageDrawable(strawberryImage);
                }

            handler.postDelayed(update, 1000);

        }

    }

}
