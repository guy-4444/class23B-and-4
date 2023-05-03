package com.guy.class23b_and_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaActionSound;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.textview.MaterialTextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private MaterialTextView main_LBL_info;

    private StepDetector stepDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_LBL_info = findViewById(R.id.main_LBL_info);

        stepDetector = new StepDetector(this, new StepDetector.CallBack_StepDetector() {
            @Override
            public void stepDetect() {
                playBeepSound();
            }

            @Override
            public void yemanStepDetect() {
                playMusic();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        stepDetector.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stepDetector.stop();
    }

    private void playBeepSound(){
        MediaActionSound sound = new MediaActionSound();
        sound.play(MediaActionSound.START_VIDEO_RECORDING);
    }

    private void playMusic() {
        MediaPlayer mp;
        mp = MediaPlayer.create(this, R.raw.snd_claps);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
                mp=null;
            }
        });
        mp.start();
    }
}