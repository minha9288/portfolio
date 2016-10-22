package com.example.getuserinformation;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

public class JsonParser {
	
	String[] list = {};
			
	JsonParser(String urlAddress, String id){
		 URL url = null;

         HttpURLConnection urlConnection = null;

         BufferedInputStream buf = null;

         String line = null;

         String page = "";

        // listitem.clear();
         
         try {

             url = new URL(

                     "파싱할 주소");

             urlConnection = (HttpURLConnection) url.openConnection();



             buf = new BufferedInputStream(urlConnection

                     .getInputStream());

             BufferedReader bufreader = new BufferedReader(

                     new InputStreamReader(buf, "UTF-8"));



              // 주소내 모든정보를 읽어옴

             while ((line = bufreader.readLine()) != null) {

                 Log.d("line:", line);

                 page += line;

             }

             JSONObject json = new JSONObject(page);

             JSONArray jArr = json.getJSONArray("큰틀");

             for (int i = 0; i < jArr.length(); i++) {

            	 

                 json = jArr.getJSONObject(i);



                 String a = json.getString("첫번째");

                 String b = json.getString("두번째");

                 String c = json.getString("세번째");



                 //listitem.add(new ListItem(a, b, c));



             }

             //arrayAdpt.notifyDataSetChanged();



         } catch (Exception e) {

             Log.d("Error:", e.getMessage());

         } finally {

             // URL 연결 해제

             urlConnection.disconnect();

         }

         //favoriteList.setAdapter(arrayAdpt);

     }
	
}
