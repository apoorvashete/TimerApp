package com.example.appy;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView textView;
    Boolean counterIsActive= false;
    Button button;
    CountDownTimer timer;

    public void Finished(){
        textView.setText("0:30");
        seekBar.setProgress(30);
        timer.cancel();
        button.setText("Go!");
        seekBar.setEnabled(true);
        counterIsActive=false;


    }

    public void updateTimer(int secsLeft){
        int mins= secsLeft/ 60;
        int secs= secsLeft- mins*60;

        String secondString= Integer.toString(secs);

         if (secs<=9){

            secondString= "0"+ secondString;
        }

        textView.setText(Integer.toString(mins)+ ":" + secondString );

    }


    public  void controlTimer (View view) {


        if (counterIsActive == false) {
            counterIsActive = true;
            seekBar.setEnabled(false);
            button.setText("Stop");



            timer = new CountDownTimer(seekBar.getProgress() * 1000, 1000) {


                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }


                @Override
                public void onFinish() {

                    textView.setText("0:00");
                    Log.i("Finished.", "Timer done!");
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
                    mediaPlayer.start();
                    Finished();


                }
            }.start();


        }else{
            Finished();


        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         seekBar= (SeekBar) findViewById(R.id.seekBar);
         textView= (TextView) findViewById(R.id.textView);
         button= (Button) findViewById(R.id.button);


        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }
}
