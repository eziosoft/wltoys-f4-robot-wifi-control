package com.eziosoft.wltoysrobot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    SeekBar s1, s2;
    Button RedB, PinkB, GreenB, BlueB;
    RobotControl robotControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        robotControl = new RobotControl();

        s1 = findViewById(R.id.seekBar1);
        s2 = findViewById(R.id.seekBar2);
        RedB = findViewById(R.id.button1);
        PinkB = findViewById(R.id.button2);
        GreenB = findViewById(R.id.button3);
        BlueB = findViewById(R.id.button4);


        s1.setOnSeekBarChangeListener(onSeekBarChangeListener);
        s2.setOnSeekBarChangeListener(onSeekBarChangeListener);

        RedB.setOnClickListener(buttonsListener);
        PinkB.setOnClickListener(buttonsListener);
        GreenB.setOnClickListener(buttonsListener);
        BlueB.setOnClickListener(buttonsListener);
    }


    @Override
    protected void onPause() {
        super.onPause();
        robotControl.Stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        robotControl.Start();

    }


    View.OnClickListener buttonsListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.equals(RedB)) {
                robotControl.setMode(Protocol.MODE_RED);
            }

            if (view.equals(PinkB)) {
                robotControl.setMode(Protocol.MODE_PINK);
            }

            if (view.equals(GreenB)) {
                robotControl.setMode(Protocol.MODE_GREEN);
            }

            if (view.equals(BlueB)) {
                robotControl.setMode(Protocol.MODE_BLUE);
            }
        }
    };


    SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            robotControl.setFR(s1.getProgress());
            robotControl.setLR(s2.getProgress());
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}
