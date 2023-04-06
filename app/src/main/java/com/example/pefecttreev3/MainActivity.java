package com.example.pefecttreev3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }//end onCreate

    public void goButton(View v)
    {
        //This takes the text from the heightInput text area and stores it in the int variable height
        TextInputEditText heightInput = findViewById(R.id.heightInput);
        String tempHeight = String.valueOf(heightInput.getEditableText());
        int height = Integer.parseInt(tempHeight);

        Log.i("MyLog", "height of the tree: " + height);
        //This creates the intent and sets an extra with the value of the height
        Intent intent = new Intent(MainActivity.this, PerfectTree.class);
        intent.putExtra("height", height);
        startActivity(intent);
    }//end goButton
}//end MainActivity