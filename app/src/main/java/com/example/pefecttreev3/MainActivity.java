package com.example.pefecttreev3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.android.material.textfield.TextInputEditText;

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

        //This takes the text from the rootInput text area and stores it in the int variable root
        TextInputEditText rootInput = findViewById(R.id.rootInput);
        String tempRoot = String.valueOf(rootInput.getEditableText());

        //This takes the text from the patternInput dropdown menu and stores it in the string pattern
        AutoCompleteTextView patternInput = findViewById(R.id.patternInput);
        String pattern = String.valueOf(patternInput.getEditableText());

        //This takes the text from the patternNumInput text area and stores it in the int variable num
        TextInputEditText patternNumInput = findViewById(R.id.patternNumInput);
        String tempNum = String.valueOf(patternNumInput.getEditableText());

        //This creates the intent and sets an extra with the value of the height
        Intent intent = new Intent(MainActivity.this, PerfectTree.class);

        Pattern testCase = Pattern.compile("[0-9]");
        Matcher heightMatcher = testCase.matcher(tempHeight + "");
        boolean hMatchFound = heightMatcher.find();

        Matcher rootMatcher = testCase.matcher(tempRoot + "");
        boolean rMatchFound = rootMatcher.find();

        Matcher numMatcher = testCase.matcher(tempNum + "");
        boolean nMatchFound = numMatcher.find();

        boolean pMatch = false;
        if(pattern.equals("+") || pattern.equals("-") || pattern.equals("/") || pattern.equals("*"))
        {
            pMatch = true;
        }//end if

        if(hMatchFound && rMatchFound && nMatchFound && pMatch)
        {
            int height = Integer.parseInt(tempHeight);
            int root = Integer.parseInt(tempRoot);
            int num = Integer.parseInt(tempNum);

            intent.putExtra("height", height);
            intent.putExtra("root", root);
            intent.putExtra("pattern", pattern);
            intent.putExtra("num", num);
            startActivity(intent);
        }//end if
        else
        {
            Toast.makeText(this,"Please insert integers only for the text area", Toast.LENGTH_SHORT).show();
        }//end else
    }//end goButton
}//end MainActivity