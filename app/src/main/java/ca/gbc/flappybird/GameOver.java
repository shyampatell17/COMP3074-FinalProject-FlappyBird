package ca.gbc.flappybird;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game_over);

        Button mRestartButton;

        // Rating the game feature, getting executed
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener((ratingBar1, rating, fromUser) -> {
            // Save the rating locally
            SharedPreferences preferences = getSharedPreferences("game_prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putFloat("user_rating", rating);
            editor.apply();
        });

        // Restart the game button
        mRestartButton = findViewById(R.id.btnRestart);

        mRestartButton.setOnClickListener(v -> {
            Intent myIntent = new Intent(GameOver.this, MainActivity.class);
            startActivity(myIntent);
            finish();
        });

        // Sharing your progress on Social Media
        findViewById(R.id.btnShare).setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            String shareMessage = "I scored 7 points in Flappy Bird! Can you beat me?";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "Share via"));
        });


    }
}