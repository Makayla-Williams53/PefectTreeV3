package com.example.pefecttreev3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    String[] item = {"+", "-", "/", "*"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autoCompleteTextView = findViewById(R.id.patternInput);
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, item);
        autoCompleteTextView.setAdapter(adapterItems);
    }//end onCreate

    public void goButton(View v)
    {
        //This takes the text from the heightInput text area and stores it in the int variable height
        TextInputEditText heightInput = findViewById(R.id.heightInput);
        String tempHeight = String.valueOf(heightInput.getEditableText());
        int height = Integer.parseInt(tempHeight);

        TextInputEditText rootInput = findViewById(R.id.rootInput);
        String tempRoot = String.valueOf(rootInput.getEditableText());
        int root = Integer.parseInt(tempRoot);
        Log.i("MyLog", "root of the tree: " + root);

        //This creates the intent and sets an extra with the value of the height
        Intent intent = new Intent(MainActivity.this, PerfectTree.class);
        intent.putExtra("height", height);
        intent.putExtra("root", root);
        startActivity(intent);
    }//end goButton
}//end MainActivity