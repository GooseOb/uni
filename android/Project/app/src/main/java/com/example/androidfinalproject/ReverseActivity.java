package com.example.androidfinalproject;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ReverseActivity extends AppCompatActivity {
    private TextView reversedText1, reversedText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reverse);

        reversedText1 = findViewById(R.id.text1);
        reversedText2 = findViewById(R.id.text2);

        String word1 = getIntent().getStringExtra("word1");
        String word2 = getIntent().getStringExtra("word2");

        if (word1 != null && word2 != null) {
            reversedText1.setText(new StringBuilder(word1).reverse().toString());
            reversedText2.setText(new StringBuilder(word2).reverse().toString());
        }
    }
}