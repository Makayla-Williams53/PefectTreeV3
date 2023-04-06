package com.example.pefecttreev3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class PerfectTree extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfect_tree);

        Bundle extras = getIntent().getExtras();
        int height = extras.getInt("height");
        Toast.makeText(this, "the sent height is: " + height, Toast.LENGTH_SHORT).show();
    }
}