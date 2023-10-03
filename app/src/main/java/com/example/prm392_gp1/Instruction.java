package com.example.prm392_gp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Instruction extends AppCompatActivity {

    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

        btnStart = (Button) findViewById(R.id.btnstartGameButton);
        btnStart.setOnClickListener(view -> {
            Intent intent = new Intent(Instruction.this, Lobby.class);
            startActivity(intent);
        });
        MusicPlayer.playAudioFromResource(this, R.raw.waiting);
    }


}