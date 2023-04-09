package com.example.pefecttreev3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class PerfectTree extends AppCompatActivity {

    Tree myTree;
    int height;
    int root;
    String pattern;
    int num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfect_tree);

        //Takes out the extra integers send from the previous activity
        Bundle extras = getIntent().getExtras();
        height = extras.getInt("height");
        root = extras.getInt("root");
        pattern = extras.getString("pattern");
        num = extras.getInt("num");
        myTree = new Tree();

        myTree.addRoot(root);
        for(int i = 0; i < height; i++)
        {
            myTree.addRow(num, pattern);
        }
        updateView();
    }//end onCreate

    public void addRow(View v)
    {
        myTree.addRow(num, pattern);
        updateView();
    }

    public void deleteRow(View v)
    {
        myTree.deleteRow(myTree.getRoot());
        updateView();
    }

    public void updateView()
    {
        TextView treePrint = findViewById(R.id.treeView);
        treePrint.setMovementMethod(new ScrollingMovementMethod());
        treePrint.setText(myTree.TreePrinter(myTree));
    }
}//end PerfectTree