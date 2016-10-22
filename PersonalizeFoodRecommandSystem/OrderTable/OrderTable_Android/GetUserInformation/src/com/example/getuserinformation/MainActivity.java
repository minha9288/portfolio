package com.example.getuserinformation;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;

import com.facebook.*;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;
import com.facebook.android.Util;
import com.facebook.model.*;

import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;


 public class MainActivity extends Activity {
	 
	 String facebookToken;
	 Facebook facebook;
	 String name;
	 String gender;
	 String birthday;
	 Session session;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		name = null;
		gender = null;
		birthday = null;
		
		facebook = new Facebook(BasicInfo.FACEBOOK_APP_ID);
		
		//session = Session.getActiveSession();
		//start Facebook Login
		Session.openActiveSession(this, true, new Session.StatusCallback() {
			
			@Override
			public void call(Session session, SessionState state, Exception exception) {
				// TODO Auto-generated method stub
				
				//이름 + 성별 + 생일 받아오기
				if(name==null){
					if(session.isOpened()){
						facebookToken = session.getAccessToken();
					
						if(!session.getPermissions().contains("user_birthday")){
							String[] PERMISSION_ARRAY_READ = {"user_birthday"}; 
							List<String> PERMISSION_LIST = Arrays.asList(PERMISSION_ARRAY_READ);
							session.requestNewReadPermissions(new Session.NewPermissionsRequest(MainActivity.this, PERMISSION_LIST));
						}
					
						Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {
							@Override
							public void onCompleted(GraphUser user, Response response) {
								// TODO Auto-generated method stub
								name = user.getName();
								gender = user.asMap().get("gender").toString();
								birthday = user.getBirthday();
							
								TextView welcome = (TextView) findViewById(R.id.welcome);
								welcome.setText(name + " / " + gender + " / " + birthday + "");
							}
						});
					}				
				}
			}
		});
	}	
	//세선관리
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	}

 }



