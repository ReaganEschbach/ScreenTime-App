package com.tryingagain;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.tryingagain.databinding.ActivityTimerBinding;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Timer extends AppCompatActivity {

    private int duration;
    private boolean timerRunning = false;
    ActivityTimerBinding binding;
    TextView hour, minute, second;
    Button startTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTimerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        duration = Chore.getSelectedChore().getTime();

        hour = binding.hour;
        minute = binding.minute;
        second = binding.second;

        startTimer = binding.timerStart;

        String beforeTimer[] = Chore.getTimeStringLayout(Chore.getSelectedChore()).split(":");
        hour.setText(beforeTimer[0]);
        minute.setText(beforeTimer[1]);
        second.setText(beforeTimer[2]);

        startTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!timerRunning){
                    timerRunning = true;

                    new CountDownTimer(duration * 1000, 1000){
                        @Override
                        public void onTick(long millisUntilFinished){

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    String time = String.format(Locale.getDefault(), "%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) -
                                                    TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));

                                    final String[] hourMinSec = time.split(":");

                                    hour.setText(hourMinSec[0]);
                                    minute.setText(hourMinSec[1]);
                                    second.setText(hourMinSec[2]);
                                }
                            });
                        }

                        @Override
                        public void onFinish(){

                            duration = Chore.getSelectedChore().getTime();
                            timerRunning = false;
                        }
                    }.start();
                }
                else{
                    Toast.makeText(Timer.this, "Timer is already running.", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}