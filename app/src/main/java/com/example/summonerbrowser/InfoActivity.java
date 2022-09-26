package com.example.summonerbrowser;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class InfoActivity extends AppCompatActivity {

    private TextView tvName = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();
        String summonerName = intent.getStringExtra("SummonerName");
        int summonerLevel = intent.getIntExtra("SummonerLevel",0);
        int summonerIcon = intent.getIntExtra("SummonerIcon",0);
        String summonerTier = intent.getStringExtra("SummonerTier");
        String summonerRank = intent.getStringExtra("SummonerRank");

        tvName = findViewById(R.id.tv_name);

        tvName.setText(summonerName);

    }


}
