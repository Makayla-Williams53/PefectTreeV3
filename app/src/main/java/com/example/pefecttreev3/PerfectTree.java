package com.example.pefecttreev3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class PerfectTree extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfect_tree);

        //Takes out the extra integers send from the previous activity
        Bundle extras = getIntent().getExtras();
        int height = extras.getInt("height");
        int root = extras.getInt("root");
        String pattern = extras.getString("pattern");
        Log.i("MyLog", "sent pattern of the tree: " + pattern);

    }//end onCreate
}//end PerfectTree