package com.example.prm392_gp1;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;

public class MusicPlayer {

    private static MediaPlayer mediaPlayer;

    public static void playAudioFromResource(Context context, int resourceId) {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioAttributes(
                    new AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
            );
            mediaPlayer.setOnCompletionListener(mp -> {
                // Khi hoàn thành phát âm thanh, phát lại từ đầu
                mediaPlayer.start();
            });
        } else {
            mediaPlayer.reset(); // Đặt lại MediaPlayer nếu nó đã được sử dụng trước đó
        }

        try {
            mediaPlayer = MediaPlayer.create(context, resourceId);
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
            releaseMediaPlayer();
        }
    }

    public static void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            mediaPlayer = null;
        }
    }
}