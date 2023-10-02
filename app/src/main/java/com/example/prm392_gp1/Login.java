package com.example.prm392_gp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Login extends AppCompatActivity {

    ImageView ivLogin;
    EditText etUsername, etPassword;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Binding();
        ivLogin.setImageResource(R.drawable.cute);

        MusicPlayer.playAudioFromResource(this, R.raw.waiting);

        btnLogin.setOnClickListener(view -> {
            if (!checkInput())
                return;
            else {
                Intent intent = new Intent(Login.this, Instruction.class);
                MusicPlayer.playAudioFromResource(Login.this, R.raw.successful);
                startActivity(intent);
                finish();
            }
        });
    }
    private void Binding() {
        ivLogin = (ImageView) findViewById(R.id.ivLogin);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
    }

    private boolean checkInput(){
        // user, password
        if(TextUtils.isEmpty(etUsername.getText().toString())
                || TextUtils.isEmpty(etPassword.getText().toString())){
            String REQUIRE = "Require";
            etUsername.setError(REQUIRE);
            return false;
        }
        return true;
    }
}