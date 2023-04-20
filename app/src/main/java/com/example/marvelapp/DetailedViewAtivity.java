package com.example.marvelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailedViewAtivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView nameTextView;
    private TextView realNameTextView;
    private TextView teamTextView;
    private TextView firstAppearanceTextView;
    private TextView publisherTextView;
    private TextView bioTextView;
    private TextView createdByTextView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_view_ativity);

        imageView = findViewById(R.id.image_view);
        nameTextView = findViewById(R.id.name_view);
        realNameTextView = findViewById(R.id.real_name_view);
        teamTextView = findViewById(R.id.team_view);
        firstAppearanceTextView = findViewById(R.id.first_appearance_view);
        publisherTextView = findViewById(R.id.publisher_view);
        bioTextView = findViewById(R.id.bio_view);
        createdByTextView = (TextView) findViewById(R.id.created_by_view);

        // Retrieve the intent that started this activity
        Intent intent = getIntent();

        // Retrieve the data from the intent using the keys
        String name = intent.getStringExtra("name");
        String realName = intent.getStringExtra("realname");
        String team = intent.getStringExtra("team");
        String firstAppearance = intent.getStringExtra("firstappearance");
        String createdBy = intent.getStringExtra("createdby");
        String publisher = intent.getStringExtra("publisher");
        String imageUrl = intent.getStringExtra("imageurl");
        String bio = intent.getStringExtra("bio");

        Glide.with(this)
                .load(imageUrl)
                .into(imageView);

        nameTextView.setText(name);
        realNameTextView.setText(realName);
        teamTextView.setText(team);
        firstAppearanceTextView.setText(firstAppearance);
        publisherTextView.setText(publisher);
        bioTextView.setText(bio);
        createdByTextView.setText(createdBy);


    }
}