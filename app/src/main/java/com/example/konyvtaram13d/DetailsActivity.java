package com.example.konyvtaram13d;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class DetailsActivity extends AppCompatActivity {

    private TextView textViewTitle;
    private TextView textViewAuthor;
    private TextView textViewPages;
    private TextView textViewYear;
    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        textViewTitle = findViewById(R.id.textViewTitle);
        textViewAuthor = findViewById(R.id.textViewAuthor);
        textViewPages = findViewById(R.id.textViewPages);
        textViewYear = findViewById(R.id.textViewYear);
        buttonBack = findViewById(R.id.buttonBack);

        String title = getIntent().getStringExtra("title");
        String author = getIntent().getStringExtra("author");
        int pages = getIntent().getIntExtra("pages", 0);
        int year = new Random().nextInt(2023 - 1500 + 1) + 1500;

        textViewTitle.setText(title);
        textViewAuthor.setText(author);
        textViewPages.setText(String.valueOf(pages));
        textViewYear.setText(String.valueOf(year));

        buttonBack.setOnClickListener(v -> finish());
    }
}