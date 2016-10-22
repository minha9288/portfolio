package com.example.tcpsokettest;

import getuserinformation.BasicInfo;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.NfcF;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import bean.Food;
import bean.Material;
import bean.User;

import com.example.simplenfc.NFC.NdefMessageParser;
import com.example.simplenfc.NFC.ParsedRecord;
import com.example.simplenfc.NFC.TextRecord;
import com.example.simplenfc.NFC.UriRecord;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.android.Facebook;
import com.facebook.model.GraphUser;

//NFC

public class TCPsoketTest extends Activity {
	// �냼耳�
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	private boolean threadBtn;

	//사용자 정보 얻기
	Facebook facebook;
	private String facebookToken;
	
	private String tableNum;
	private String name;
	private String birthday;
	private int age;
	private String gender;
	
	private User user;
	private Food food;
	private ArrayList<Food> recommand_food;
	private ArrayList<Food> menu_food;
	private int position;
	private Material material;
	private Food food_o;
	// nfc
	private NfcAdapter mAdapter;
	private PendingIntent mPendingIntent;
	private IntentFilter[] mFilters;
	private String[][] mTechLists;

	public static final int TYPE_TEXT = 1;
	public static final int TYPE_URI = 2;

	
	Map<String, Object> innerMap;
	private static final String serverIP = "192.168.0.17"; //"192.168.43.75";
	private static final int serverPort = 9286;

	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		threadBtn = false;
	}

	@SuppressLint("NewApi")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// NFC
		mAdapter = NfcAdapter.getDefaultAdapter(this);
		Intent targetIntent = new Intent(this, TCPsoketTest.class);
		targetIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		mPendingIntent = PendingIntent.getActivity(this, 0, targetIntent, 0);

		IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
		try {
			ndef.addDataType("*/*");
		} catch (MalformedMimeTypeException e) {
			throw new RuntimeException("fail", e);
		}

		mFilters = new IntentFilter[] { ndef, };
		mTechLists = new String[][] { new String[] { NfcF.class.getName() } };

		Intent passedIntent = getIntent();

		if (passedIntent != null) {
			String action = passedIntent.getAction();

			if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
				processTag(passedIntent);
			}
		}
		
		//sendUser();
		
		ImageView right = (ImageView) findViewById(R.id.right);

		final TextView text = (TextView) findViewById(R.id.text);

		Thread readerThread = new Thread(new Reader());
		readerThread.start();
		threadBtn = true;

		right.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				sendUser();
//				Intent food = new Intent(TCPsoketTest.this, food_activity.class);
//				food.setFlags(food.FLAG_ACTIVITY_CLEAR_TOP);
//				startActivity(food);
			}
		});
		
		
	} // oncreate

	public class Reader implements Runnable {
		public void run() {
			try {

				InetAddress serverAddr = InetAddress.getByName(serverIP);

				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
						.detectAll().penaltyLog().build();
				StrictMode.setThreadPolicy(policy);

				socket = new Socket(serverAddr, serverPort);
				out = new ObjectOutputStream(socket.getOutputStream());
				in = new ObjectInputStream(socket.getInputStream());
				while (threadBtn) {
					
					Map<String, Object> map = (Map<String, Object>) in.readObject();
					innerMap = new HashMap<String, Object>();					
					Set key = map.keySet();
					for (Iterator iterator = key.iterator(); iterator.hasNext();) {
						String keyName = (String) iterator.next();
						
						switch (keyName) {
						
						case "food":
							innerMap = (HashMap<String, Object>) map
									.get("food");
							user = (User) innerMap.get("USER");
							recommand_food = (ArrayList<Food>) innerMap
									.get("recommand_food");
							menu_food = (ArrayList<Food>) innerMap
									.get("menu_food");
							ProcessingFood(user, recommand_food, menu_food);
							break;

						}

					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public String getUserId() {
		String user_id;
		TelephonyManager mgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		user_id = mgr.getDeviceId();
		return user_id;
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	}

	// ///////// // NFC function
	@SuppressLint("NewApi")
	public void onResume() {

		super.onResume();
		if (mAdapter != null)
			mAdapter.enableForegroundDispatch(this, mPendingIntent, mFilters,
					mTechLists);
		if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction()))
			onNewIntent(getIntent());
	}

	@SuppressLint("NewApi")
	public void onPause() {
		super.onPause();
		if (mAdapter != null)
			mAdapter.disableForegroundDispatch(this);
	}

	@SuppressLint("NewApi")
	public void onNewIntent(Intent passedIntent) {
		// NFC
		Tag tag = passedIntent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
		if (tag != null) {
			byte[] tagId = tag.getId();
		}

		if (passedIntent != null) {
			processTag(passedIntent);
		}
	}

	public static final String CHARS = "0123456789ABCDEF";

	public static String toHexString(byte[] data) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < data.length; ++i) {
			sb.append(CHARS.charAt((data[i] >> 4) & 0x0F)).append(
					CHARS.charAt(data[i] & 0x0F));
		}
		return sb.toString();
	}

	private void processTag(Intent passedIntent) {

		Parcelable[] rawMsgs = passedIntent
				.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
		if (rawMsgs == null) {
			return;
		}

		NdefMessage[] msgs;
		if (rawMsgs != null) {
			msgs = new NdefMessage[rawMsgs.length];
			for (int i = 0; i < rawMsgs.length; i++) {
				msgs[i] = (NdefMessage) rawMsgs[i];
				showTag(msgs[i]); // showTag �޼ҵ� ȣ��
			}
		}
	}

	private int showTag(NdefMessage mMessage) {

		List<ParsedRecord> records = NdefMessageParser.parse(mMessage);
		final int size = records.size();
		for (int i = 0; i < size; i++) {
			ParsedRecord record = records.get(i);

			int recordType = record.getType();
			String recordStr = ""; // NFC
			if (recordType == ParsedRecord.TYPE_TEXT) {
				recordStr = ((TextRecord) record).getText();
			} else if (recordType == ParsedRecord.TYPE_URI) {
				recordStr = "URI : " + ((UriRecord) record).getUri().toString();
			}

			tableNum=recordStr;
			
		}

		return size;
	}
	
public void getUserInfo(){
		
		name = null;
		gender = null;
		birthday = null;
		facebook = new Facebook(BasicInfo.FACEBOOK_APP_ID);
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
	                     session.requestNewReadPermissions(new Session.NewPermissionsRequest(TCPsoketTest.this, PERMISSION_LIST));
	                  }
	               
	                  Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {
	                     @Override
	                     public void onCompleted(GraphUser user, Response response) {
	                        // TODO Auto-generated method stub
	                        name = user.getName();
	                        gender = user.asMap().get("gender").toString();
	                        birthday = user.getBirthday();
	                     
	                     }
	                  });
	               }            
	            }
	         }
	      });
	}

	public void sendUser() {
		try {
			//facebook 접속
			//Intent infoIntent = new Intent(TCPsoketTest.this, GetUserInfo.class);
			user = new User();
			user.setId(getUserId());

			user.setNation(getCountry());
			user.setAge(1992);
			user.setSex(gender);
			user.setTableNum(tableNum);
			Log.e("user정보set", user.getId() + " / " + user.getNation() + " / " + user.getAge() + " / " + user.getSex() + " / " + user.getTableNum());
			Map<String, Object> map_user = new HashMap<String, Object>();
			map_user.put("user", user);
			out.writeObject(map_user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getCountry(){
		 Locale locale    = getResources().getConfiguration().locale;
		  String country    =  locale.getCountry();
		  return country;
	}
	public void SendSelectedFood(User user, Food food) {
		try {
			Map<String, Object> selected_food_list = new HashMap<String, Object>();
			selected_food_list.put("USER", user);
			selected_food_list.put("FOOD", food);
			Map<String, Object> map_selected_food = new HashMap<String, Object>();
			map_selected_food.put("selected_food", selected_food_list);
			out.writeObject(map_selected_food);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 처리부분
	// ProcessigUser ~
	public void ProcessingUser(User user) {
		String tableNum;
		String user_id;
		tableNum = user.getTableNum();
		user_id = user.getId();
	}

	public void ProcessingFood(User user, ArrayList<Food> recommand_food,
			ArrayList<Food> menu_food) {

		Intent food = new Intent(TCPsoketTest.this, food_activity.class);

		food.putExtra("user",user);
		food.putExtra("recommand_food", recommand_food); 
		food.putExtra("menu_food", menu_food); 
		food.putExtra("selectedItem", 0); 
		food.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
				| Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(food);
		finish();
	}
}