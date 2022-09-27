package com.example.summonerbrowser;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
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

    private ImageView imgIcon = null;
    private TextView tvLevel = null;
    private TextView tvName = null;

    private TextView tvTier = null;
    private TextView tvWin = null;
    private TextView tvLose = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();
        Bitmap summonerIcon = intent.getParcelableExtra("SummonerIcon");
        int summonerLevel = intent.getIntExtra("SummonerLevel",0);
        String summonerName = intent.getStringExtra("SummonerName");

        String summonerTier = intent.getStringExtra("SummonerTier");
        int summonerPoint = intent.getIntExtra("SummonerPoint",0);
        int summonerWin = intent.getIntExtra("SummonerWin",0);
        int summonerLose = intent.getIntExtra("SummonerLose",0);

        //String summonerRank = intent.getStringExtra("SummonerRank");

        imgIcon = findViewById(R.id.img_icon);
        tvLevel = findViewById(R.id.tv_level);
        tvName = findViewById(R.id.tv_name);

        tvTier = findViewById(R.id.tv_tier);
        tvWin = findViewById(R.id.tv_win);
        tvLose = findViewById(R.id.tv_lose);

        imgIcon.setImageBitmap(summonerIcon);
        tvLevel.setText(String.valueOf(summonerLevel));
        tvName.setText(summonerName);

        tvTier.setText("Tier : " + summonerTier + "(" + summonerPoint + ")");
        tvWin.setText("Win : " + summonerWin);
        tvLose.setText("Lose : " + summonerLose);
    }


}
