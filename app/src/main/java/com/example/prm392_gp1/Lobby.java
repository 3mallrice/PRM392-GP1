package com.example.prm392_gp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

        btnHorse = (Button) findViewById(R.id.btnHorse);
        btnDog = (Button) findViewById(R.id.btnDog);
        btnDuck = (Button) findViewById(R.id.btnDuck);
        btnHD = (Button) findViewById(R.id.btnHD);
        noti = (TextView) findViewById(R.id.Noti);
        btnHorse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Lobby.this, Game.class);
                startActivity(intent);
            }
        });

        btnHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Lobby.this, Instruction.class);
                startActivity(intent);
            }
        });

        btnDuck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noti.setVisibility(View.VISIBLE);
                noti.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        noti.setVisibility(View.INVISIBLE);
                    }
                }, 3000);
            }
        });

        btnDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noti.setVisibility(View.VISIBLE);
                noti.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        noti.setVisibility(View.INVISIBLE);
                    }
                }, 3000);
            }
        });
    }
}