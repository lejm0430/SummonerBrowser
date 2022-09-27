package com.example.summonerbrowser;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    private ImageView imgTier = null;
    private TextView tvPoint = null;
    private TextView tvTotal = null;
    private TextView tvWin = null;
    private TextView tvLose = null;

    private Button btnResurch = null;

    private Bitmap summonerIcon = null;
    private int summonerLevel = 0;
    private String summonerName = null;
    private String summonerTier = null;
    private int summonerPoint = 0;
    private int summonerWin = 0;
    private int summonerLose = 0;
    private String summonerRank = null;

    public Name_API_THREAD apiThread = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();
        summonerIcon = intent.getParcelableExtra("SummonerIcon");
        summonerLevel = intent.getIntExtra("SummonerLevel",0);
        summonerName = intent.getStringExtra("SummonerName");

        summonerTier = intent.getStringExtra("SummonerTier");
        summonerPoint = intent.getIntExtra("SummonerPoint",0);
        summonerWin = intent.getIntExtra("SummonerWin",0);
        summonerLose = intent.getIntExtra("SummonerLose",0);
        summonerRank = intent.getStringExtra("SummonerRank");

        btnResurch = findViewById(R.id.btn_resurch);

        imgIcon = findViewById(R.id.img_icon);
        tvLevel = findViewById(R.id.tv_level);
        tvName = findViewById(R.id.tv_name);

        tvTier = findViewById(R.id.tv_tier);
        imgTier = findViewById(R.id.img_tier);
        tvPoint = findViewById(R.id.tv_point);
        tvTotal = findViewById(R.id.tv_total);
        tvWin = findViewById(R.id.tv_win);
        tvLose = findViewById(R.id.tv_lose);

        btnResurch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reSurch();
            }
        });

        imgIcon.setImageBitmap(summonerIcon);
        tvLevel.setText("레벨 : "+summonerLevel);
        tvName.setText(summonerName);

        String packName = getApplicationContext().getPackageName();
        int tierIconId = getApplicationContext().getResources().getIdentifier("emblem_"+summonerTier.toLowerCase(),"drawable",packName);

        imgTier.setImageResource(tierIconId);
        tvTier.setText("등급 : " + summonerTier + " " + summonerRank);
        tvPoint.setText("점수 : " + summonerPoint);
        tvTotal.setText((summonerWin+summonerLose)+"전");
        tvWin.setText(summonerWin+"승");
        tvLose.setText(summonerLose+"패");
    }

    public void reSurch(){
        if(summonerName != null){
            apiThread = new Name_API_THREAD(summonerName);
            try {
                apiThread.start();
                apiThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            summonerIcon = apiThread.getSummonersIcon();
            summonerLevel = apiThread.getSummonersLevel();
            summonerName = apiThread.getSummonerName();

            summonerTier = apiThread.getSummonersTier();
            summonerPoint = apiThread.getSummonersPoint();
            summonerWin = apiThread.getSummonersWin();
            summonerLose = apiThread.getSummonersLose();
            summonerRank = apiThread.getSummonersRank();

            imgIcon.setImageBitmap(summonerIcon);
            tvLevel.setText("레벨 : "+summonerLevel);
            tvName.setText(summonerName);

            String packName = getApplicationContext().getPackageName();
            int tierIconId = getApplicationContext().getResources().getIdentifier("emblem_"+summonerTier.toLowerCase(),"drawable",packName);

            imgTier.setImageResource(tierIconId);
            tvTier.setText("등급 : " + summonerTier + " " + summonerRank);
            tvPoint.setText("점수 : " + summonerPoint);
            tvTotal.setText((summonerWin+summonerLose)+"전");
            tvWin.setText(summonerWin+"승");
            tvLose.setText(summonerLose+"패");
        }
    }

}
