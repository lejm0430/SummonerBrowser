package com.example.summonerbrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText searchName = null;
    private Button btnSearch = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchName = findViewById(R.id.edit_search_name);
        btnSearch = findViewById(R.id.btn_search);

    }
}