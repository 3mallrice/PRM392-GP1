package com.example.prm392_gp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Lobby extends AppCompatActivity {
    Button btnHorse;
    Button btnDog;
    Button btnDuck;
    Button btnHD;
    TextView noti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        MusicPlayer.playAudioFromResource(Lobby.this, R.raw.waiting);
        btnHorse = (Button) findViewById(R.id.btnHorse);
        btnDog = (Button) findViewById(R.id.btnDog);
        btnDuck = (Button) findViewById(R.id.btnDuck);
        btnHD = (Button) findViewById(R.id.btnHD);
        noti = (TextView) findViewById(R.id.Noti);
        btnHorse.setOnClickListener(view -> {
            Intent intent = new Intent(Lobby.this, Game.class);
            intent.putExtra("thumb", "horse");
            startActivity(intent);
        });

        btnHD.setOnClickListener(view -> {
            Intent intent = new Intent(Lobby.this, Instruction.class);
            startActivity(intent);
        });

        btnDuck.setOnClickListener(view -> {
//                noti.setVisibility(View.VISIBLE);
//                noti.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        noti.setVisibility(View.INVISIBLE);
//                    }
//                }, 3000);
            Intent intent = new Intent(Lobby.this, Game.class);
            intent.putExtra("thumb", "duck");
            startActivity(intent);
        });

        btnDog.setOnClickListener(view -> {
//                noti.setVisibility(View.VISIBLE);
//                noti.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        noti.setVisibility(View.INVISIBLE);
//                    }
//                }, 3000);
            Intent intent = new Intent(Lobby.this, Game.class);
            intent.putExtra("thumb", "dog");
            startActivity(intent);
        });
    }
}