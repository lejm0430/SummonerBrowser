package com.example.summonerbrowser;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Name_API_THREAD extends Thread implements Runnable{

    public boolean is_success = false;
    public String summonerName = null;
    public String Summoners_id = null;
    public int Summoners_level = 0;
    public int Summoners_icon = 0;
    public Bitmap Summoners_bitmap = null;

    public String Summoners_name = null;
    public String Summoners_league = null;
    public String Summoners_tier = null;
    public int Summoners_point = 0;
    public String Summoners_rank = null;
    public int Summoners_win = 0;
    public int Summoners_losses = 0;

    public JSONObject jsonObj = null;

    public String TOKEN = BuildConfig.RIOT_API_KEY;

    public Name_API_THREAD(String summonerName){
        this.summonerName = summonerName;
    }

    public int getSummonersLevel(){
        return Summoners_level;
    }
    public Bitmap getSummonersIcon(){
        return Summoners_bitmap;
    }
    public String getSummonersLeague(){return Summoners_league;}
    public String getSummonersTier(){
        return Summoners_tier;
    }
    public int getSummonersPoint(){
        return Summoners_point;
    }
    public String getSummonersRank(){
        return Summoners_rank;
    }
    public String getSummonerName(){
        return Summoners_name;
    }
    public int getSummonersWin(){
        return Summoners_win;
    }
    public int getSummonersLose(){
        return Summoners_losses;
    }

    @Override
    public void run() {
        String SummonerName = this.summonerName.replaceAll(" ", "%20");
        String requestURL = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + SummonerName + "?api_key=" + TOKEN;
        JSONObject jsonObj = get(requestURL);
        try {
            if(jsonObj == null){ is_success = false; return; }
            else{ is_success = true;}
            Summoners_id = (String) jsonObj.get("id");
            Summoners_level = (int) jsonObj.get("summonerLevel");
            Summoners_icon = (int) jsonObj.get("profileIconId");

            Summoners_bitmap = getImageFromUrl("https://ddragon.leagueoflegends.com/cdn/12.18.1/img/profileicon/"+Summoners_icon+".png");
            getInfo();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getInfo(){
        String requestURL = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/"+Summoners_id + "?api_key=" + TOKEN;
        JSONObject jsonObj = getArray(requestURL);
        try {
            Summoners_league = (String) jsonObj.get("queueType");
            Summoners_tier = (String) jsonObj.get("tier");
            Summoners_rank = (String) jsonObj.get("rank");
            Summoners_point = (int) jsonObj.get("leaguePoints");
            Summoners_name = (String) jsonObj.get("summonerName");
            Summoners_win = (int) jsonObj.get("wins");
            Summoners_losses = (int) jsonObj.get("losses");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject get(String strUrl){
        try{
            URL url = new URL(strUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setRequestMethod("GET");
            con.setDoOutput(false);

            StringBuilder sb = new StringBuilder();

            if(con.getResponseCode() == HttpURLConnection.HTTP_OK){
                //url에서 버퍼 형태로 데이터를 받음
                BufferedReader br = new BufferedReader(new InputStreamReader
                        (con.getInputStream(), "utf-8"));
                String line = br.readLine();
                while((line != null)){
                    sb.append(line).append("\n");
                    line = br.readLine();
                }
                br.close();
                String result =  sb.toString();
                //JSON 형태로 변환 후 리턴
                JSONObject jsonObj = new JSONObject(result);

                return jsonObj;
            }else{
                Log.d("wrong response: ", con.getResponseMessage());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject getArray(String strUrl){
        try{
            URL url = new URL(strUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setRequestMethod("GET");
            con.setDoOutput(false);

            StringBuilder sb = new StringBuilder();

            if(con.getResponseCode() == HttpURLConnection.HTTP_OK){
                //url에서 버퍼 형태로 데이터를 받음
                BufferedReader br = new BufferedReader(new InputStreamReader
                        (con.getInputStream(), "utf-8"));
                String line = br.readLine();
                while((line != null)){
                    sb.append(line).append("\n");
                    line = br.readLine();
                }
                br.close();
                String result =  sb.toString();

                JSONArray jsonArray = new JSONArray(result);
                for(int i = 0; i<jsonArray.length(); i++){
                    jsonObj = jsonArray.getJSONObject(i);
                }

                return jsonObj;
            }else{
                Log.d("wrong response: ", con.getResponseMessage());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Bitmap getImageFromUrl(String urlStr){
        Bitmap imgBitmap = null;
        try{
            URL url = new URL(urlStr);
            URLConnection con = url.openConnection();
            con.connect();
            BufferedInputStream bf = new BufferedInputStream(con.getInputStream());
            imgBitmap = BitmapFactory.decodeStream(bf);
            bf.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imgBitmap;
    }
}
