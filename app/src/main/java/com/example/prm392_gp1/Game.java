package com.example.prm392_gp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Game extends AppCompatActivity {
    Button btnInstruction, btnStartRace, btnReset, btnBack;
    CheckBox cb1, cb2, cb3;
    EditText betHorse1, betHorse2, betHorse3;
    TextView txtMoney;
    SeekBar track1, track2, track3;
    int bet1, bet2, bet3, index1, index2, index3 , total;
    boolean checkValue = false;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Binding();
        btnInstruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Game.this, Instruction.class);
                startActivity(intent);
            }
        });

        //bet
        CountDownTimer countDownTimer = new CountDownTimer(60000,700) {
            @Override
            public void onTick(long l) {
                Random random = new Random();
                int one = random.nextInt(5);
                int two = random.nextInt(5);
                int three = random.nextInt(5);
                if (track1.getProgress() >= track1.getMax()){
                    this.cancel();
                    mediaPlayer.stop();
                    if (cb1.isChecked()){
                        total += (bet1 * 1.75);
                    }
                    txtMoney.setText(String.valueOf(total));
                    MusicPlayer.playAudioFromResource(Game.this, R.raw.successful);
                    Toast.makeText(Game.this,"ONE WIN",Toast.LENGTH_SHORT).show();
                }
                if (track2.getProgress() >= track2.getMax()){
                    this.cancel();
                    mediaPlayer.stop();
                    if (cb2.isChecked()){
                        total += (bet2 * 1.75);
                    }
                    txtMoney.setText(String.valueOf(total));
                    MusicPlayer.playAudioFromResource(Game.this, R.raw.successful);
                    Toast.makeText(Game.this,"TWO WIN",Toast.LENGTH_SHORT).show();
                }
                if (track3.getProgress() >= track3.getMax()){
                    this.cancel();
                    mediaPlayer.stop();
                    if (cb3.isChecked()){
                        total += (bet3 * 1.75);
                    }
                    txtMoney.setText(String.valueOf(total));
                    MusicPlayer.playAudioFromResource(Game.this, R.raw.successful);
                    Toast.makeText(Game.this,"THREE WIN",Toast.LENGTH_SHORT).show();
                }
                track1.setProgress(track1.getProgress() + one);
                track2.setProgress(track2.getProgress() + two);
                track3.setProgress(track3.getProgress() + three);
            }
            @Override
            public void onFinish() {
            }
        };
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Game.this, Lobby.class);
                startActivity(intent);
            }
        });
        btnStartRace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cb1.isChecked() || cb2.isChecked() || cb3.isChecked()){
                    if (cb1.isChecked()) {
                        if (!betHorse1.getText().toString().isEmpty()) {
                            bet1 = Integer.parseInt(betHorse1.getText().toString());
                            checkValue = true;
                        } else {
                            Toast.makeText(Game.this, "Hay chọn tiền cược ô 1", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if (cb2.isChecked()){
                        if (!betHorse2.getText().toString().isEmpty()){
                            bet2 = Integer.parseInt(betHorse2.getText().toString());
                            checkValue = true;
                        }else {
                            Toast.makeText(Game.this,"Hay chọn tiền cược ô 2",Toast.LENGTH_SHORT).show();
                        }
                    }
                    if (cb3.isChecked()){
                        if (!betHorse3.getText().toString().isEmpty()){
                            bet3 = Integer.parseInt(betHorse3.getText().toString());
                            checkValue = true;
                        }else {
                            Toast.makeText(Game.this,"Hay chọn tiền cược ô 3",Toast.LENGTH_SHORT).show();
                        }
                    }
                    if (checkValue){
                        if ( total > 0){
                            if ((bet1 + bet2 + bet3) <= total ){
                                total -= (bet1 + bet2 + bet3);
                                txtMoney.setText(String.valueOf(total));
                                mediaPlayer = MediaPlayer.create(Game.this,R.raw.game);
                                countDownTimer.start();
                                mediaPlayer.start();
                            }else {
                                Toast.makeText(Game.this,"Số Tiền Cược Quá Lớn!",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(Game.this,"Bạn Đã Hết Tiền!",Toast.LENGTH_SHORT).show();
                        }
                    }
                }else {
                    Toast.makeText(Game.this,"Chọn con ngựa đặt cược",Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                track1.setProgress(0);
                track2.setProgress(0);
                track3.setProgress(0);
                betHorse1.setText(null);
                betHorse2.setText(null);
                betHorse3.setText(null);
                cb1.setChecked(false);
                cb2.setChecked(false);
                cb3.setChecked(false);
                bet1 = bet2 = bet3 = 0;
            }
        });
    }

    private void Binding(){
        btnStartRace = (Button) findViewById(R.id.btnStartRace);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnReset = (Button) findViewById(R.id.btnReset);
        btnInstruction = (Button) findViewById(R.id.btnInstruction);
        txtMoney = (TextView) findViewById(R.id.txtMoney);
        cb1 = (CheckBox) findViewById(R.id.cb1);
        cb2 = (CheckBox) findViewById(R.id.cb2);
        cb3 = (CheckBox) findViewById(R.id.cb3);
        track1 = (SeekBar) findViewById(R.id.horse1Track);
        track1.setEnabled(false);
        track2 = (SeekBar) findViewById(R.id.horse2Track);
        track2.setEnabled(false);
        track3 = (SeekBar) findViewById(R.id.horse3Track);
        track3.setEnabled(false);
        betHorse1 = (EditText) findViewById(R.id.betHorse1);
        betHorse2 = (EditText) findViewById(R.id.betHorse2);
        betHorse3 = (EditText) findViewById(R.id.betHorse3);
        total = Integer.parseInt(txtMoney.getText().toString());
    }
}