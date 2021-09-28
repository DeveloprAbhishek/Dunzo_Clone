package com.example.dunzoclone.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import com.example.dunzoclone.R;

public class PaymentSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);

        VideoView VideoViewPs = findViewById(R.id.VideoViewPs);


        String path = "android.resource://com.example.dunzoclone/" + R.raw.pss;

        Uri uri = Uri.parse(path);
        VideoViewPs.setVideoURI(uri);

        VideoViewPs.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

        VideoViewPs.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Intent intent = new  Intent(PaymentSuccess.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}