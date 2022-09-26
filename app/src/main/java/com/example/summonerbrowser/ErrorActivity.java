package com.example.summonerbrowser;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ErrorActivity extends AppCompatActivity {

    private Button btnResurch = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);

        btnResurch = findViewById(R.id.btn_resurch);

        btnResurch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("BtnResurch OnClick : ","on");
                Intent intent = new Intent(ErrorActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
