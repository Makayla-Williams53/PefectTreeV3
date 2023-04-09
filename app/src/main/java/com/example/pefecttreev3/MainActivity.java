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

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(MainActivity.this, "Item: " + item, Toast.LENGTH_SHORT).show();
            }//end onItemClick
        });//end setOnItemClickListener
    }//end onCreate

    public void goButton(View v)
    {
        //This takes the text from the heightInput text area and stores it in the int variable height
        TextInputEditText heightInput = findViewById(R.id.heightInput);
        String tempHeight = String.valueOf(heightInput.getEditableText());
        int height = Integer.parseInt(tempHeight);

        //This takes the text from the rootInput text area and stores it in the int variable root
        TextInputEditText rootInput = findViewById(R.id.rootInput);
        String tempRoot = String.valueOf(rootInput.getEditableText());
        int root = Integer.parseInt(tempRoot);

        //This takes the text from the patternInput dropdown menu and stores it in the string pattern
        AutoCompleteTextView patternInput = findViewById(R.id.patternInput);
        String pattern = String.valueOf(patternInput.getEditableText());

        //This takes the text from the patternNumInput text area and stores it in the int variable num
        TextInputEditText patternNumInput = findViewById(R.id.patternNumInput);
        String tempNum = String.valueOf(patternNumInput.getEditableText());
        int num = Integer.parseInt(tempNum);

        //This creates the intent and sets an extra with the value of the height
        Intent intent = new Intent(MainActivity.this, PerfectTree.class);
        intent.putExtra("height", height);
        intent.putExtra("root", root);
        intent.putExtra("pattern", pattern);
        intent.putExtra("num", num);
        startActivity(intent);
    }//end goButton
}//end MainActivity