package com.example.prm392_gp1;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import java.util.Random;

public class Game extends AppCompatActivity {
    Button btnInstruction, btnStartRace, btnReset, btnBack;
    CheckBox cb1, cb2, cb3;
    EditText betHorse1, betHorse2, betHorse3;
    TextView txtMoney;
    SeekBar track1, track2, track3;
    int bet1, bet2, bet3, total;
    boolean checkValue = false;
    MediaPlayer mediaPlayer;
    double winningsHorse1 = 0, winningsHorse2 = 0, winningsHorse3 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Binding();

        String thumb = getIntent().getStringExtra("thumb");
        if (thumb != null) {
            switch (thumb) {
                case "dog":
                    track1.setThumb(ResourcesCompat.getDrawable(getResources(), R.drawable.dog1, null));
                    track2.setThumb(ResourcesCompat.getDrawable(getResources(), R.drawable.dog2, null));
                    track3.setThumb(ResourcesCompat.getDrawable(getResources(), R.drawable.dog3, null));
                    break;
                case "duck":
                    track1.setThumb(ResourcesCompat.getDrawable(getResources(), R.drawable.duck1, null));
                    track2.setThumb(ResourcesCompat.getDrawable(getResources(), R.drawable.duck2, null));
                    track3.setThumb(ResourcesCompat.getDrawable(getResources(), R.drawable.duck3, null));
                    break;
                default:
                    track1.setThumb(ResourcesCompat.getDrawable(getResources(), R.drawable.horse1small, null));
                    track2.setThumb(ResourcesCompat.getDrawable(getResources(), R.drawable.horse2small, null));
                    track3.setThumb(ResourcesCompat.getDrawable(getResources(), R.drawable.horse3small, null));
            }
        } else {
            track1.setThumb(ResourcesCompat.getDrawable(getResources(), R.drawable.horse1small, null));
            track2.setThumb(ResourcesCompat.getDrawable(getResources(), R.drawable.horse2small, null));
            track3.setThumb(ResourcesCompat.getDrawable(getResources(), R.drawable.horse3small, null));
        }

        btnInstruction.setOnClickListener(view -> {
            Intent intent = new Intent(Game.this, Instruction.class);
            startActivity(intent);
        });

        // Bet
        CountDownTimer countDownTimer = new CountDownTimer(60000, 700) {
            @Override
            public void onTick(long l) {
                Random random = new Random();
                int one = random.nextInt(10);
                int two = random.nextInt(10);
                int three = random.nextInt(10);
                if (track1.getProgress() >= track1.getMax()) {
                    this.cancel();
                    mediaPlayer.stop();
                    if (cb1.isChecked()) {
                        winningsHorse1 = bet1 * 1.75;
                        total += winningsHorse1;
                    } else {
                        total -= bet1;
                    }
                    txtMoney.setText(String.valueOf(total));
                    MusicPlayer.playAudioFromResource(Game.this, R.raw.successful);
                    showResultDialog(winningsHorse1, winningsHorse2, winningsHorse3);
                    Toast.makeText(Game.this, "1st WINS", Toast.LENGTH_SHORT).show();
                }
                if (track2.getProgress() >= track2.getMax()) {
                    this.cancel();
                    mediaPlayer.stop();
                    if (cb2.isChecked()) {
                        winningsHorse2 = bet2 * 1.75;
                        total += winningsHorse2;
                    } else {
                        total -= bet2;
                    }
                    txtMoney.setText(String.valueOf(total));
                    MusicPlayer.playAudioFromResource(Game.this, R.raw.successful);
                    showResultDialog(winningsHorse1, winningsHorse2, winningsHorse3);
                    Toast.makeText(Game.this, "2nd WINS", Toast.LENGTH_SHORT).show();
                }
                if (track3.getProgress() >= track3.getMax()) {
                    this.cancel();
                    mediaPlayer.stop();
                    if (cb3.isChecked()) {
                        winningsHorse3 = bet3 * 1.75;
                        total += winningsHorse3;
                    } else {
                        total -= bet3;
                    }
                    txtMoney.setText(String.valueOf(total));
                    MusicPlayer.playAudioFromResource(Game.this, R.raw.successful);
                    showResultDialog(winningsHorse1, winningsHorse2, winningsHorse3);
                    Toast.makeText(Game.this, "3rd WINS", Toast.LENGTH_SHORT).show();
                }
                track1.setProgress(track1.getProgress() + one);
                track2.setProgress(track2.getProgress() + two);
                track3.setProgress(track3.getProgress() + three);
            }

            @Override
            public void onFinish() {
            }
        };
        btnBack.setOnClickListener(view -> {
            Intent intent = new Intent(Game.this, Lobby.class);
            startActivity(intent);
        });

        btnStartRace.setOnClickListener(view -> {
            MusicPlayer.releaseMediaPlayer();
            if (cb1.isChecked() || cb2.isChecked() || cb3.isChecked()) {
                if (cb1.isChecked()) {
                    String bet1Text = betHorse1.getText().toString();
                    if (!bet1Text.isEmpty() && bet1Text != null) {
                        bet1 = Integer.parseInt(bet1Text);
                        if (bet1 > 0) {
                            checkValue = true;
                        } else {
                            Toast.makeText(Game.this, "Bet amount for 1st must be greater than 0", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Game.this, "Please select a bet amount for 1st pet", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    betHorse1.setText("");
                }
                if (cb2.isChecked()) {
                    String bet2Text = betHorse2.getText().toString();
                    if (!bet2Text.isEmpty() && bet2Text != null) {
                        bet2 = Integer.parseInt(bet2Text);
                        if (bet2 > 0) {
                            checkValue = true;
                        } else {
                            Toast.makeText(Game.this, "Bet amount for 2nd must be greater than 0", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Game.this, "Please select a bet amount for 2nd pet", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    betHorse2.setText("");
                }
                if (cb3.isChecked()) {
                    String bet3Text = betHorse3.getText().toString();
                    if (!bet3Text.isEmpty() && bet3Text != null) {
                        bet1 = Integer.parseInt(bet3Text);
                        if (bet1 > 0) {
                            checkValue = true;
                        } else {
                            Toast.makeText(Game.this, "Bet amount for 3rd must be greater than 0", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Game.this, "Please select a bet amount for 3rd pet", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    betHorse3.setText("");
                }
                if (checkValue) {
                    if (total > 0) {
                        if ((bet1 + bet2 + bet3) <= total) {
                            total -= (bet1 + bet2 + bet3);
                            txtMoney.setText(String.valueOf(total));
                            mediaPlayer = MediaPlayer.create(Game.this, R.raw.game);
                            countDownTimer.start();
                            mediaPlayer.start();
                        } else {
                            Toast.makeText(Game.this, "Bet amount exceeds your total balance!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Game.this, "You are out of money!", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(Game.this, "Select at least 1 to bet", Toast.LENGTH_SHORT).show();
            }
        });

        btnReset.setOnClickListener(view -> {
            countDownTimer.cancel();
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
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
        });
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void showResultDialog(double winningsHorse1, double winningsHorse2, double winningsHorse3) {
        String title = "";
        String message = "";

        if (winningsHorse1 > 0) {
            title = "Congratulations!";
            message = "1st horse has won " + winningsHorse1 + " coins!";
        } else if (winningsHorse2 > 0) {
            title = "Congratulations!";
            message = "2nd horse has won " + winningsHorse2 + " coins!";
        } else if (winningsHorse3 > 0) {
            title = "Congratulations!";
            message = "3rd horse has won " + winningsHorse3 + " coins!";
        } else {
            title = "Sorry!";
            message = "You lost the race!";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", null);
        builder.setNegativeButton("Lobby", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Intent intent = new Intent(Game.this, Lobby.class);
                startActivity(intent);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void Binding() {
        btnStartRace = findViewById(R.id.btnStartRace);
        btnBack = findViewById(R.id.btnBack);
        btnReset = findViewById(R.id.btnReset);
        btnInstruction = findViewById(R.id.btnInstruction);
        txtMoney = findViewById(R.id.txtMoney);
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);

        track1 = findViewById(R.id.horse1Track);
        track1.setEnabled(false);
        track2 = findViewById(R.id.horse2Track);
        track2.setEnabled(false);
        track3 = findViewById(R.id.horse3Track);
        track3.setEnabled(false);

        betHorse1 = findViewById(R.id.betHorse1);
        betHorse2 = findViewById(R.id.betHorse2);
        betHorse3 = findViewById(R.id.betHorse3);
        total = Integer.parseInt(txtMoney.getText().toString());
    }
}
