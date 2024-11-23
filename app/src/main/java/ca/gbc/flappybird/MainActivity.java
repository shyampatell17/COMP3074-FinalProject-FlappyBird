package ca.gbc.flappybird;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        AppHolder.assign(this.getApplicationContext());
    }

    // Method to start the game when clicked on button "startGame"
    public void startGame(View view){
        Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
            finish();
        }

}