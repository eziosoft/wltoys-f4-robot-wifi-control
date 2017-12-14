package com.eziosoft.wltoysrobot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    SeekBar s1, s2;
    Button RedB, PinkB, GreenB, BlueB;
    UDPClient udpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        udpClient = new UDPClient();

        s1 = findViewById(R.id.seekBar1);
        s2 = findViewById(R.id.seekBar2);
        RedB = findViewById(R.id.button1);
        PinkB = findViewById(R.id.button2);
        GreenB = findViewById(R.id.button3);
        BlueB = findViewById(R.id.button4);


        s1.setOnSeekBarChangeListener(onSeekBarChangeListener);
        s2.setOnSeekBarChangeListener(onSeekBarChangeListener);

        RedB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                udpClient.setMode(1);
            }
        });

        BlueB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                udpClient.setMode(8);
            }
        });

    }


    @Override
    protected void onPause() {
        super.onPause();
        udpClient.Stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        udpClient.Start();

    }

    SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            Log.d("aaa", "s onProgressChanged " + String.valueOf(i));
            udpClient.setFR(s1.getProgress());
            udpClient.setLR(s2.getProgress());

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}
