package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    TextView mydataTitle;
    TextView mydataCompleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //findView
        mydataTitle = findViewById(R.id.tv_title);
        mydataTitle = findViewById(R.id.tv_completed);

        //get intent
        Intent intent = getIntent();

        String title = intent.getStringExtra("title");
        String completed = intent.getStringExtra("completed");

        //set data
        mydataTitle.setText(title);
    }
}