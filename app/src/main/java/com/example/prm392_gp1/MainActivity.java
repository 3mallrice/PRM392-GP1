package com.example.prm392_gp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView ivWelcome;
    Button btnToLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Binding();
        ivWelcome.setImageResource(R.drawable.welcome_image);

        MusicPlayer.playAudioFromResource(this, R.raw.waiting);

        btnToLogin.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
            finish();
        });
    }

    private void Binding() {
        ivWelcome = (ImageView) findViewById(R.id.ivWelcome);
        btnToLogin = (Button) findViewById(R.id.btnToLogin);
    }
}
