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

                     "�Ľ��� �ּ�");

             urlConnection = (HttpURLConnection) url.openConnection();



             buf = new BufferedInputStream(urlConnection

                     .getInputStream());

             BufferedReader bufreader = new BufferedReader(

                     new InputStreamReader(buf, "UTF-8"));



              // �ּҳ� ��������� �о��

             while ((line = bufreader.readLine()) != null) {

                 Log.d("line:", line);

                 page += line;

             }

             JSONObject json = new JSONObject(page);

             JSONArray jArr = json.getJSONArray("ūƲ");

             for (int i = 0; i < jArr.length(); i++) {

            	 

                 json = jArr.getJSONObject(i);



                 String a = json.getString("ù��°");

                 String b = json.getString("�ι�°");

                 String c = json.getString("����°");



                 //listitem.add(new ListItem(a, b, c));



             }

             //arrayAdpt.notifyDataSetChanged();



         } catch (Exception e) {

             Log.d("Error:", e.getMessage());

         } finally {

             // URL ���� ����

             urlConnection.disconnect();

         }

         //favoriteList.setAdapter(arrayAdpt);

     }
	
}
