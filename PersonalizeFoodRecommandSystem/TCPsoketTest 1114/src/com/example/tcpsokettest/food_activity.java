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

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import bean.Food;
import bean.Material;
import bean.User;

import com.facebook.android.Facebook;

public class food_activity extends Activity {

	private String t1;
	private String str;

	private Map<String, Object> foodmap;
	private Map<String, Object> innerMap;
	private int selectedItem;

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private boolean threadBtn;
	private User user;
	private Food food;
	private ArrayList<Food> recommand_food;
	private ArrayList<Food> menu_food;

	private int position;
	private Food selectedFood;
	private Material material;
	private Food food_o;
	private String facebookToken;
	private Facebook facebook;

	private SendMassgeHandler mMainHandler = null;
	private static final String serverIP = "192.168.0.17";
	 private static final int SEND_THREAD_INFOMATION = 0;
	     

	private static final int serverPort = 9286;

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		threadBtn = false;
	}

	@SuppressWarnings("unchecked")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.food);

		// setUpNetworking();
		Thread readerThread = new Thread(new Reader());
		readerThread.start();
		threadBtn = true;
		mMainHandler = new SendMassgeHandler();
		// XML
		final ImageView main1 = (ImageView) findViewById(R.id.temp_1);
		final ImageView main2 = (ImageView) findViewById(R.id.main_2);
		final ImageView main3 = (ImageView) findViewById(R.id.main_3);

		final TextView selected_view = (TextView) findViewById(R.id.selected);
		final TextView main1_name_view = (TextView) findViewById(R.id.main1_name);
		final TextView main2_name_view = (TextView) findViewById(R.id.main2_name);
		final TextView main3_name_view = (TextView) findViewById(R.id.main3_name);

		final TextView main1_price_view = (TextView) findViewById(R.id.main1_price);
		final TextView main2_price_view = (TextView) findViewById(R.id.main2_price);
		final TextView main3_price_view = (TextView) findViewById(R.id.main3_price);

		final TextView explain = (TextView) findViewById(R.id.menu_explain);
		final ImageView menu_bt = (ImageView) findViewById(R.id.menu);

		final ImageView right = (ImageView) findViewById(R.id.right);

		// INTENT DATA
		user = (User) this.getIntent().getExtras().get("user");
		selectedItem = Integer.parseInt(this.getIntent().getExtras()
				.get("selectedItem").toString());
		menu_food = (ArrayList<Food>) this.getIntent().getExtras()
				.get("menu_food");
		recommand_food = (ArrayList<Food>) this.getIntent().getExtras()
				.get("recommand_food");
		selectedFood = (Food) this.getIntent().getExtras()
				.get("selectedFood");

		
		// check selected data
		if (selectedItem > 0 & selectedItem < menu_food.size() + 1) {
			if(selectedFood==null)
				selectedFood = menu_food.get(selectedItem - 1);
			ImageView what = (ImageView) findViewById(R.id.main_what);
			int whatnum = getBaseContext().getResources().getIdentifier(
					"food_" + selectedItem, "drawable",
					getBaseContext().getPackageName());
			what.setImageResource(whatnum);
			selected_view.setText(selectedFood.getName());
			selected_view.setTextColor(Color.RED);
			explain.setText(selectedFood.getExplain());
		}


		int i_num1 = getBaseContext().getResources().getIdentifier(
				"food_" + recommand_food.get(0).getId(), "drawable",
				getBaseContext().getPackageName());
		int i_num2 = getBaseContext().getResources().getIdentifier(
				"food_" + recommand_food.get(1).getId(), "drawable",
				getBaseContext().getPackageName());
		int i_num3 = getBaseContext().getResources().getIdentifier(
				"food_" + recommand_food.get(2).getId(), "drawable",
				getBaseContext().getPackageName());

		main1.setImageResource(i_num1);
		main2.setImageResource(i_num2);
		main3.setImageResource(i_num3);

		main1_name_view.setText(recommand_food.get(0).getName());
		main2_name_view.setText(recommand_food.get(1).getName());
		main3_name_view.setText(recommand_food.get(2).getName());

		main1_price_view.setText(recommand_food.get(0).getPrice());
		main2_price_view.setText(recommand_food.get(1).getPrice());
		main3_price_view.setText(recommand_food.get(2).getPrice());

		// 1menu click
		main1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				selectedFood = recommand_food.get(0);
				selectedItem = 1;
				SendSelectedFood(user, selectedFood, "no");
			}
		});

		main2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				selectedFood = recommand_food.get(1);
				selectedItem = 2;
				SendSelectedFood(user, selectedFood, "no");
			}
		});

		main3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				selectedFood = recommand_food.get(2);
				selectedItem = 3;
				SendSelectedFood(user, selectedFood, "no");
			}
		});

		right.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				if (selectedFood == null) {
					Toast.makeText(getBaseContext(), "please select food",
							Toast.LENGTH_LONG).show();
					return;
				} else {

					SendNextBtnToMaterial(user, selectedFood, selectedFood);
				}
			}
		});

		menu_bt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				SendMenuBtn(user);
			}
		});

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

						switch (keyName) {

						case "user":
							// user = (User)map.get("user");
							// System.out.println(user.getId() + " / " +
							// user.getNation());
							break;
						case "food":

							innerMap = (HashMap<String, Object>) map
									.get("food");
							recommand_food = (ArrayList<Food>) innerMap
									.get("recommand_food");
							menu_food = (ArrayList<Food>) innerMap
									.get("menu_food");
							break;
						case "selected_food":
							innerMap = (Map<String, Object>) map
									.get("selected_food");
							food = (Food) innerMap.get("FOOD");
							selectedFood = food;
			               
							Message msg = mMainHandler.obtainMessage();
			                msg.what = SEND_THREAD_INFOMATION;
			                msg.obj = food; 
			                mMainHandler.sendMessage(msg);
							break;
						case "menuBtn":

							innerMap = (Map<String, Object>) map.get("menuBtn");
							user = (User) innerMap.get("USER");
							ProcessingMenuBtn();

							break;

						case "nextBtn_to_material":

							innerMap = (Map<String, Object>) map
									.get("nextBtn_to_material");
							user = (User) innerMap.get("USER");
							food = (Food) innerMap.get("FOOD");
							food_o = (Food) innerMap.get("FOOD_O");
							ProcessingNextBtnToMaterial(user, food, food_o);

							break;

						case "prevBtn_to_food":

							break;
						}

					}

				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}
	
	 // Handler 클래스
    class SendMassgeHandler extends Handler {
         
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
             
            switch (msg.what) {
            case SEND_THREAD_INFOMATION:
            	Food tempfood=(Food)msg.obj;
               
               ImageView what = (ImageView) findViewById(R.id.main_what);
				int whatnum = getBaseContext().getResources().getIdentifier(
						"food_" + tempfood.getId(), "drawable",
						getBaseContext().getPackageName());
				what.setImageResource(whatnum);
				TextView explain = (TextView) findViewById(R.id.menu_explain);
				explain.setText(tempfood.getExplain());

				final TextView selected_view = (TextView) findViewById(R.id.selected);
				selected_view.setText(tempfood.getName());
				selected_view.setTextColor(Color.RED);
               break;
                 
 
            default:
                break;
            }
        }
         
    };


	public User getUser() {
		if (this.user == null) {
			User tuser = new User();
			tuser.setId("abcd");
			tuser.setNation("kr");
			tuser.setTableNum("1-1");
			tuser.setAge(1992);
			tuser.setSex("female");
			this.user = tuser;
		}
		return user;
	}

	// SendUser ~
	// 사용자가 선택한 음식 보냄(추천된3개중에서 / 메뉴판 10개중에서0
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

	// food화면에서 이전버튼을 누르면 메인으로 이동
	public void SendPrevBtnToMain(User user) {
		try {
			Map<String, Object> prevBtn_to_main_list = new HashMap<String, Object>();
			prevBtn_to_main_list.put("USER", user);
			Map<String, Object> map_prevBtn_to_main = new HashMap<String, Object>();
			map_prevBtn_to_main.put("prevBtn_to_main", prevBtn_to_main_list);
			out.writeObject(map_prevBtn_to_main);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 메뉴판 버튼 눌림
	public void SendMenuBtn(User user) {
		try {
			Map<String, Object> menuBtn_list = new HashMap<String, Object>();
			menuBtn_list.put("USER", user);
			menuBtn_list.put("menu", "menu");
			Map<String, Object> map_menuBtn = new HashMap<String, Object>();
			map_menuBtn.put("menuBtn", menuBtn_list);
			out.writeObject(map_menuBtn);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// food에서 음식 선택하고 다음버튼 누르면 재료 선택화면으로 이동
	public void SendNextBtnToMaterial(User user, Food food, Food food_o) {
		try {

			Map<String, Object> nextBtn_to_material_list = new HashMap<String, Object>();
			nextBtn_to_material_list.put("USER", user);
			nextBtn_to_material_list.put("FOOD", food);
			nextBtn_to_material_list.put("FOOD_O", food_o);

			Map<String, Object> map_nextBtn_to_material = new HashMap<String, Object>();
			map_nextBtn_to_material.put("nextBtn_to_material",
					nextBtn_to_material_list);

			out.writeObject(map_nextBtn_to_material);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 재료선택에서 음식 옆에 나오는 6개 중에 어떤 자리를 선택했는지 보내줌
	public void SendSelectePosition(User user, int position) {
		try {
			Map<String, Object> selected_position_list = new HashMap<String, Object>();
			selected_position_list.put("USER", user);
			selected_position_list.put("position", position);
			Map<String, Object> map_selected_position = new HashMap<String, Object>();
			map_selected_position.put("selected_position",
					selected_position_list);
			out.writeObject(map_selected_position);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 어떤 재료를 선택햇는지를 보내줌
	public void SendSelecteMaterial(User user, int position, Material material) {
		try {
			Map<String, Object> selected_material_list = new HashMap<String, Object>();
			selected_material_list.put("USER", user);
			selected_material_list.put("position", position);
			selected_material_list.put("MATERIAL", material);
			Map<String, Object> map_selected_material = new HashMap<String, Object>();
			map_selected_material.put("selected_material",
					selected_material_list);
			out.writeObject(map_selected_material);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 재료화면에서 이전버튼을 누르면 food나오는 화면으로 이동. food로 가면 다시 FOOD받아서 선택했었던 값이 나옴
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

	// 재료선택 완료하고 다음버튼 누르면 완료
	public void SendNextBtnToLast(User user, Food food) {
		try {
			Map<String, Object> nextBtn_to_last_list = new HashMap<String, Object>();
			nextBtn_to_last_list.put("USER", user);
			nextBtn_to_last_list.put("FOOD", food);
			Map<String, Object> map_nextBtn_to_last = new HashMap<String, Object>();
			map_nextBtn_to_last.put("nextBtn_to_last", nextBtn_to_last_list);
			out.writeObject(map_nextBtn_to_last);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 처리부분
	// ProcessigUser ~
	public void ProcessingUser(User tuser) {
		user.setTableNum(tuser.getTableNum());
		user.setId(tuser.getId());
	}

	public void ProcessingFood(User user, ArrayList<Food> recommand_food,
			ArrayList<Food> menu_food) {

		this.user = user;
		this.recommand_food = recommand_food;
		this.menu_food = menu_food;
	}


	public void ProcessingPrevBtnToMain() {
		Intent main = new Intent(food_activity.this, TCPsoketTest.class);
		main.addFlags(main.FLAG_ACTIVITY_NO_HISTORY);
		startActivity(main);
		finish();
	}

	public void ProcessingMenuBtn() {

		Intent s_food = new Intent(food_activity.this, menuboard_activity.class);
		s_food.putExtra("menu_food", menu_food);
		s_food.putExtra("recommand_food", recommand_food);
		s_food.putExtra("user", user);
		s_food.putExtra("selectedFood", selectedFood);
		
		s_food.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
				| Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(s_food);
		finish();
	}

	public void ProcessingNextBtnToMaterial(User user, Food food, Food food_o) {

		Intent foodInfo = new Intent(food_activity.this,
				material_activity.class);
		foodInfo.putExtra("user", user);
		foodInfo.putExtra("recommand_food", recommand_food);
		foodInfo.putExtra("menu_food", menu_food);
		foodInfo.putExtra("food", food);
		foodInfo.putExtra("food_o", menu_food.get(food.getId()-1));
		foodInfo.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
				| Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(foodInfo);
		finish();
	}


	
}
