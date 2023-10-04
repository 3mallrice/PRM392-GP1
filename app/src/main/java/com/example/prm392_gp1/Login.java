package com.example.prm392_gp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    ImageView ivLogin;
    EditText etUsername, etPassword;
    Button btnLogin;
    private final String nameUser = "8xbet";
    private final String passUser = "8x";
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
                if (etUsername.getText().toString().equalsIgnoreCase(nameUser) && etPassword.getText().toString().equalsIgnoreCase(passUser)){
                    Intent intent = new Intent(Login.this, Lobby.class);
                    MusicPlayer.playAudioFromResource(Login.this, R.raw.successful);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast toast=Toast.makeText(Login.this,"Invalid username or password",Toast.LENGTH_SHORT);
                    toast.show();
                }
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
        String REQUIRE = "Require";
        if(TextUtils.isEmpty(etUsername.getText().toString())) {
            etUsername.setError(REQUIRE);
            return false;
        }
        if(TextUtils.isEmpty(etPassword.getText().toString())) {
            etPassword.setError(REQUIRE);
            return false;
        }
        return true;
    }
}