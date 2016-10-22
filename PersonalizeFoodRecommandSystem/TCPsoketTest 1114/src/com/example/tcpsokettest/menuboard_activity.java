package com.example.tcpsokettest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


//import controller.GreenLightManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import bean.Food;
import bean.User;

public class menuboard_activity extends Activity {
	private Map<String, Object> innerMap;
	private int selectedItem;
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private boolean threadBtn;
	private User user;
	private ArrayList<Food> recommand_food;
	private ArrayList<Food> foodinfo;
	private Food selected_food;
	private String str;
	private static final String serverIP = "192.168.0.17";
	private static final int serverPort = 9286;

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		// Thread end
		threadBtn = false;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menuboard);

		// Thread start
		Thread readerThread = new Thread(new Reader());
		readerThread.start();
		threadBtn = true;

		// INTENT DATA
		user = (User) this.getIntent().getExtras().get("user");
		recommand_food = (ArrayList<Food>) this.getIntent().getExtras()
				.get("recommand_food");
		foodinfo = (ArrayList<Food>) this.getIntent().getExtras()
				.get("menu_food");
		if(	this.getIntent().getExtras().get("selectedFood")==null)
			selectedItem=0;
		else{
			selected_food = (Food) this.getIntent().getExtras().get("selectedFood");
			selectedItem = selected_food.getId();
		}
		// XML
		final ImageView menu_1 = (ImageView) findViewById(R.id.s_1);
		final ImageView menu_2 = (ImageView) findViewById(R.id.s_2);
		final ImageView menu_3 = (ImageView) findViewById(R.id.s_3);
		final ImageView menu_4 = (ImageView) findViewById(R.id.s_4);
		final ImageView menu_5 = (ImageView) findViewById(R.id.s_5);
		final ImageView menu_6 = (ImageView) findViewById(R.id.s_6);
		final ImageView menu_7 = (ImageView) findViewById(R.id.s_7);
		final ImageView menu_8 = (ImageView) findViewById(R.id.s_8);
		final ImageView menu_9 = (ImageView) findViewById(R.id.s_9);
		final ImageView menu_10 = (ImageView) findViewById(R.id.s_10);

		final TextView price_1 = (TextView) findViewById(R.id.t_1);
		final TextView price_2 = (TextView) findViewById(R.id.t_2);
		final TextView price_3 = (TextView) findViewById(R.id.t_3);
		final TextView price_4 = (TextView) findViewById(R.id.t_4);
		final TextView price_5 = (TextView) findViewById(R.id.t_5);
		final TextView price_6 = (TextView) findViewById(R.id.t_6);
		final TextView price_7 = (TextView) findViewById(R.id.t_7);
		final TextView price_8 = (TextView) findViewById(R.id.t_8);
		final TextView price_9 = (TextView) findViewById(R.id.t_9);
		final TextView price_10 = (TextView) findViewById(R.id.t_10);

		price_1.setText(foodinfo.get(0).getPrice());
		price_2.setText(foodinfo.get(1).getPrice());
		price_3.setText(foodinfo.get(2).getPrice());
		price_4.setText(foodinfo.get(3).getPrice());
		price_5.setText(foodinfo.get(4).getPrice());
		price_6.setText(foodinfo.get(5).getPrice());
		price_7.setText(foodinfo.get(6).getPrice());
		price_8.setText(foodinfo.get(7).getPrice());
		price_9.setText(foodinfo.get(8).getPrice());
		price_10.setText(foodinfo.get(9).getPrice());

		final TextView name_1 = (TextView) findViewById(R.id.name_1);
		final TextView name_2 = (TextView) findViewById(R.id.name_2);
		final TextView name_3 = (TextView) findViewById(R.id.name_3);
		final TextView name_4 = (TextView) findViewById(R.id.name_4);
		final TextView name_5 = (TextView) findViewById(R.id.name_5);
		final TextView name_6 = (TextView) findViewById(R.id.name_6);
		final TextView name_7 = (TextView) findViewById(R.id.name_7);
		final TextView name_8 = (TextView) findViewById(R.id.name_8);
		final TextView name_9 = (TextView) findViewById(R.id.name_9);
		final TextView name_10 = (TextView) findViewById(R.id.name_10);

		name_1.setText(foodinfo.get(0).getName());
		name_2.setText(foodinfo.get(1).getName());
		name_3.setText(foodinfo.get(2).getName());
		name_4.setText(foodinfo.get(3).getName());
		name_5.setText(foodinfo.get(4).getName());
		name_6.setText(foodinfo.get(5).getName());
		name_7.setText(foodinfo.get(6).getName());
		name_8.setText(foodinfo.get(7).getName());
		name_9.setText(foodinfo.get(8).getName());
		name_10.setText(foodinfo.get(9).getName());

		final ImageView exit_bt = (ImageView) findViewById(R.id.exit);
		menu_1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				go(1);
			}
		});

		menu_2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				go(2);
			}
		});
		menu_3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				go(3);
			}
		});
		menu_4.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				go(4);
			}
		});
		menu_5.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				go(5);
			}
		});
		menu_6.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				go(6);
			}
		});
		menu_7.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				go(7);
			}
		});
		menu_8.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				go(8);
			}
		});
		menu_9.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				go(9);
			}
		});
		menu_10.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				go(10);
			}
		});
		exit_bt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				SendPrevBtnToFood();
			}
		});
	}

	public void exit(){
		Intent food = new Intent(menuboard_activity.this,
				food_activity.class);
		food.putExtra("menu_food", foodinfo);
		food.putExtra("recommand_food", recommand_food);
		food.putExtra("user", user);
		food.putExtra("selectedItem", selectedItem);
		food.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
				| Intent.FLAG_ACTIVITY_CLEAR_TOP);

		startActivity(food);
		finish();
	}
	public void selected(){
		Intent food = new Intent(menuboard_activity.this, food_activity.class);
		food.putExtra("selectedItem",selectedItem);
		food.putExtra("menu_food", foodinfo);
		food.putExtra("recommand_food", recommand_food);
		food.putExtra("user", user);
		food.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
				| Intent.FLAG_ACTIVITY_CLEAR_TOP);

		startActivity(food);
		finish();
	}
	public void go(int num) {
		selectedItem = num;
		selected_food=foodinfo.get(selectedItem - 1);
		SendSelectedFood(user,selected_food , "menu");
	}

	public void SendPrevBtnToFood() {
		try {
			Map<String, Object> prevBtn_to_food_list = new HashMap<String, Object>();
			prevBtn_to_food_list.put("USER", user);
			if(selectedItem==0)
				prevBtn_to_food_list.put("FOOD",null);
			else
				prevBtn_to_food_list.put("FOOD", foodinfo.get(selectedItem - 1));
			Map<String, Object> map_prevBtn_to_food = new HashMap<String, Object>();
			map_prevBtn_to_food.put("prevBtn_to_food", prevBtn_to_food_list);
			out.writeObject(map_prevBtn_to_food);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void SendSelectedFood(User user, Food food, String isMenu) {
		try {
			Map<String, Object> selected_food_list = new HashMap<String, Object>();
			selected_food_list.put("USER", user);
			selected_food_list.put("FOOD", food);
			selected_food_list.put("isMenu", isMenu);
			Map<String, Object> map_selected_food = new HashMap<String, Object>();
			map_selected_food.put("selected_food", selected_food_list);

			out.writeObject(map_selected_food);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void SendPrevBtnToFood(User user, Food food) {
		try {
			Map<String, Object> prevBtn_to_food_list = new HashMap<String, Object>();
			prevBtn_to_food_list.put("USER", user);
			prevBtn_to_food_list.put("FOOD", food);
			Map<String, Object> map_prevBtn_to_food = new HashMap<String, Object>();
			map_prevBtn_to_food.put("prevBtn_to_food", prevBtn_to_food_list);
			out.writeObject(map_prevBtn_to_food);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
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
					Map<String, Object> map = (Map<String, Object>) in
							.readObject();
					innerMap = new HashMap<String, Object>();
					Set key = map.keySet();
					for (Iterator iterator = key.iterator(); iterator.hasNext();) {
						String keyName = (String) iterator.next();
						User tempUser =new User();
						
						switch (keyName) {

						case "selected_food":
							innerMap = (Map<String, Object>) map.get("selected_food");
							User tuser1 = (User) innerMap.get("USER");
							if( (Food) innerMap.get("FOOD")==null)
								selectedItem=0;
							else{
							selected_food = (Food) innerMap.get("FOOD");
							selectedItem = selected_food.getId();
							}
							selected();
							break;
						case "prevBtn_to_food":
							exit();
							break;
						}

					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
}
