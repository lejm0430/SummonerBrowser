package com.example.summonerbrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText searchName = null;
    private Button btnSearch = null;
    public Name_API_THREAD apiThread = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchName = findViewById(R.id.edit_search_name);
        btnSearch = findViewById(R.id.btn_search);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(searchName.getText().toString().length() != 0){
                    apiThread = new Name_API_THREAD(searchName.getText().toString());
                    try {
                        apiThread.start();
                        apiThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                    intent.putExtra("SummonerName",searchName.getText().toString());
                    intent.putExtra("SummonerLevel",apiThread.getSummonersLevel());
                    intent.putExtra("SummonerIcon",apiThread.getSummonersIcon());
                    intent.putExtra("SummonerTier",apiThread.getSummonersTier());
                    intent.putExtra("SummonerRank",apiThread.getSummonersRank());
                    startActivity(intent);
                }

            }
        });

    }

    public void test(String string){

        //apiThread.getSummoners_level();
    }
}