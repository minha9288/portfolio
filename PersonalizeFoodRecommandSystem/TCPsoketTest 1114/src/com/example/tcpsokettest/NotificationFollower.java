package com.example.tcpsokettest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import bean.Food;
import bean.Rate;
import bean.User;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.StreamCorruptedException;

public class NotificationFollower extends Activity implements OnClickListener{

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private boolean threadBtn;
	
	private static final String serverIP = "175.204.205.175";
	private static final int serverPort = 9286;
	private Food selected_food;
	Rate rate;
	User user;

	int num;
	int count_check;

	Connection conn;
	Statement stmt = null;
	
	protected void onStop() {
		super.onStop();
		threadBtn = false;
	}
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.rating);
	    selected_food = (Food) (this.getIntent().getExtras().get("selectedFood"));
		 Log.e("selected_food", selected_food+"");
		 
	    Thread readerThread = new Thread(new Reader());
		readerThread.start();
		threadBtn = true;
		
		final RatingBar ratingbar = (RatingBar)findViewById(R.id.ratingbar);
		final TextView t1_rating = (TextView)findViewById(R.id.t1_rating);		
		final TextView t2_thanku = (TextView)findViewById(R.id.t2_thanku);	
		final Button save_bt = (Button)findViewById(R.id.save_bt);
		
		ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				t1_rating.setText(rating+"");
				num = (int)rating;
			}
		});
		
		save_bt.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				Log.e("save_bt", num+"눌림");
				count_check++;
				if(count_check==1){
					oneckeck();
					Log.e("1", "들어옴");
					t2_thanku.setVisibility(View.VISIBLE);
				}
			}
		});	
	}
	
	public class Reader implements Runnable {

		public void run() {
			try {
				Log.e("2", "들어옴");
				InetAddress serverAddr = InetAddress.getByName(serverIP);
				socket = new Socket(serverAddr, serverPort);
				out = new ObjectOutputStream(socket.getOutputStream());
				in = new ObjectInputStream(socket.getInputStream());
		
				while (threadBtn) {				
					Map<String, Object> map = (Map<String, Object>) in.readObject(); 
					Log.e("맵생성", map.toString());				
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	public void oneckeck(){
		Log.e("3", "들어옴");
		sendRate();
	}
	public void sendRate() {
		try {
			Log.e("4", "들어옴");
			rate = new Rate();
			rate.setUser_id(getUserId());
			rate.setFood_id(1);
			rate.setOrdered_rate(num);
			Log.e("rate의 user_id", rate.getUser_id()+"");
			Map<String, Object> send_rate = new HashMap<String, Object>();
			send_rate.put("rate", rate);
			out.writeObject(send_rate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getUserId() {
		String user_id;
		TelephonyManager mgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		user_id = mgr.getDeviceId();

		Log.e("장치아이디", user_id);
		return user_id;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
