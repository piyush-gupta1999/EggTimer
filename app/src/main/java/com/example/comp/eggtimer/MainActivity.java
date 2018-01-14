package com.example.comp.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar timerSeekBar;
    TextView timerTextView;
    Button controlButton;
    Boolean counterIsActive=false;
    CountDownTimer countDownTimer;
    public void resetTimer()
    {
        timerTextView.setText("0:30");
        timerSeekBar.setProgress(30);
        countDownTimer.cancel();
        timerSeekBar.setEnabled(true);
        controlButton.setText("Go!");
        counterIsActive=false;
    }

    public void updateTimer(int secondsLeft){
        int minutes=(int)secondsLeft/60;
        int seconds=secondsLeft-minutes*60;
        String secondString=Integer.toString(seconds);
        if(seconds<=9)
        {
            secondString="0" + secondString;
        }

        timerTextView.setText(Integer.toString(minutes) + ":" + secondString);

    }
    public void controlTimer(View view)
    {
        if(counterIsActive==false)
        {
            counterIsActive=true;
            timerSeekBar.setEnabled(false);
            controlButton.setText("Stop");
            countDownTimer=new CountDownTimer(timerSeekBar.getProgress()*1000 + 100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                        updateTimer((int) millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                resetTimer();
                MediaPlayer mplayer =MediaPlayer.create(getApplicationContext(),R.raw.hornsound);
                mplayer.start();
                }
            }.start();
        }
        else
        {
           resetTimer();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         timerSeekBar=(SeekBar)findViewById(R.id.timerSeekBar);
         timerTextView=(TextView)findViewById(R.id.timerTextView);
        controlButton=(Button)findViewById(R.id.controlButton);
        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
