package com.example.tcpsokettest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import bean.Food;
import bean.Material;
import bean.Translator;
import bean.User;

public class material_activity extends Activity {

   private int position;
   private Material material;
   private Food food_o;
   
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
   private static final int SEND_POSITION_INFOMATION = 0;
   private static final int SEND_MATERIAL_INFOMATION = 1;
   private Translator trans;
   private int click_alter;
   private SendMassgeHandler mMainHandler = null;
   private int click_check;
   private int m_selected1, m_selected2, m_selected3, m_selected4,
         m_selected5, m_selected6;

   private int m_alter1, m_alter2, m_alter3, m_alter4, m_alter5, m_alter6;

   private Food selected_food;

   private static final String serverIP = "192.168.0.17";

   private static final int serverPort = 9286;

   private TextView explain;
   private ImageView alter1, alter2, alter3, alter4, alter5, alter6;
   private ImageView material1, material2, material3, material4, material5,
         material6;

   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.material);

      Thread readerThread = new Thread(new Reader());
      readerThread.start();
      threadBtn = true;
      mMainHandler = new SendMassgeHandler();
      trans=new Translator();
      final ImageView right = (ImageView) findViewById(R.id.right);
      final ImageView left = (ImageView) findViewById(R.id.left);
      final ImageView main = (ImageView) findViewById(R.id.main);
  
      material1 = (ImageView) findViewById(R.id.s_1);
      material2 = (ImageView) findViewById(R.id.s_2);
      material3 = (ImageView) findViewById(R.id.s_3);
      material4 = (ImageView) findViewById(R.id.s_4);
      material5 = (ImageView) findViewById(R.id.s_5);
      material6 = (ImageView) findViewById(R.id.s_6);

      alter1 = (ImageView) findViewById(R.id.a_1);
      alter2 = (ImageView) findViewById(R.id.a_2);
      alter3 = (ImageView) findViewById(R.id.a_3);
      alter4 = (ImageView) findViewById(R.id.a_4);
      alter5 = (ImageView) findViewById(R.id.a_5);
      alter6 = (ImageView) findViewById(R.id.a_6);

      alter1.setVisibility(View.GONE);
      alter2.setVisibility(View.GONE);
      alter3.setVisibility(View.GONE);
      alter4.setVisibility(View.GONE);
      alter5.setVisibility(View.GONE);
      alter6.setVisibility(View.GONE);
      
      explain = (TextView) findViewById(R.id.text);

   
      recommand_food = (ArrayList<Food>) this.getIntent().getExtras()
            .get("recommand_food");
      menu_food = (ArrayList<Food>) this.getIntent().getExtras()
            .get("menu_food");
      user = (User) this.getIntent().getExtras().get("user");
      selected_food = (Food) (this.getIntent().getExtras().get("food"));
      food_o = (Food) (this.getIntent().getExtras().get("food_o"));

      int s_food_num = selected_food.getId();
      selectedItem = s_food_num;

      // //
      int selected_food_num = getBaseContext().getResources().getIdentifier(
            "food_" + s_food_num, "drawable",
            getBaseContext().getPackageName());
      main.setImageResource(selected_food_num);

      m_selected1 = getBaseContext().getResources().getIdentifier(
            "material_" + selected_food.getMaterialList().get(0).getId(),
            "drawable", getBaseContext().getPackageName());
      m_selected2 = getBaseContext().getResources().getIdentifier(
            "material_" + selected_food.getMaterialList().get(1).getId(),
            "drawable", getBaseContext().getPackageName());
      m_selected3 = getBaseContext().getResources().getIdentifier(
            "material_" + selected_food.getMaterialList().get(2).getId(),
            "drawable", getBaseContext().getPackageName());
      m_selected4 = getBaseContext().getResources().getIdentifier(
            "material_" + selected_food.getMaterialList().get(3).getId(),
            "drawable", getBaseContext().getPackageName());
      m_selected5 = getBaseContext().getResources().getIdentifier(
            "material_" + selected_food.getMaterialList().get(4).getId(),
            "drawable", getBaseContext().getPackageName());
      m_selected6 = getBaseContext().getResources().getIdentifier(
            "material_" + selected_food.getMaterialList().get(5).getId(),
            "drawable", getBaseContext().getPackageName());

      m_alter1 = getBaseContext().getResources().getIdentifier(
            "material_" + selected_food.getMaterialList().get(0).getId(),
            "drawable", getBaseContext().getPackageName());
      material1.setImageResource(m_selected1);
      material2.setImageResource(m_selected2);
      material3.setImageResource(m_selected3);
      material4.setImageResource(m_selected4);
      material5.setImageResource(m_selected5);
      material6.setImageResource(m_selected6);

      right.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
            SendNextBtnToLast(user, selected_food);
            AlarmStart();
         }
      });
      left.setOnClickListener(new OnClickListener() {
         @Override
         public void onClick(View v) {

            SendPrevBtnToFood(user, food_o);
         }
      });
      material1.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            click_check = 1;
            SendSelectePosition(user, 1);
         }
      });
      material2.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            click_check = 2;
            SendSelectePosition(user, 2);
         }
      });
      material3.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            click_check = 3;
            SendSelectePosition(user, 3);
         }
      });
      material4.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            click_check = 4;
            SendSelectePosition(user, 4);
         }
      });
      material5.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            click_check = 5;
            SendSelectePosition(user, 5);
         }
      });
      material6.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            click_check = 6;
            SendSelectePosition(user, 6);
         }
      });
      alter1.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            click_alter = 6;
            SendSelecteMaterial(user, click_alter,
                  food_o.getMaterialList().get(click_alter - 1));
         }
      });
      alter2.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            click_alter = 5;
            SendSelecteMaterial(user, click_alter,
                  food_o.getMaterialList().get(click_alter - 1));
         }
      });
      alter3.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            click_alter = 4;
            SendSelecteMaterial(user, click_alter,
                  food_o.getMaterialList().get(click_alter - 1));
         }
      });
      alter4.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            click_alter = 3;
            SendSelecteMaterial(user, click_alter,
                  food_o.getMaterialList().get(click_alter - 1));
         }
      });
      alter5.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            click_alter = 2;
            SendSelecteMaterial(user, click_alter,
                  food_o.getMaterialList().get(click_alter - 1));
         }
      });
      alter6.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            click_alter = 1;
            SendSelecteMaterial(user, click_alter,
                  food_o.getMaterialList().get(click_alter - 1));
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

               Map<String, Object> map = (Map<String, Object>) in
                     .readObject();
               innerMap = new HashMap<String, Object>();
               Set key = map.keySet();
               for (Iterator iterator = key.iterator(); iterator.hasNext();) {
                  String keyName = (String) iterator.next();

                  switch (keyName) {
                  case "prevBtn_to_food":
                     innerMap = (Map<String, Object>) map
                           .get("prevBtn_to_food");
                     user = (User) innerMap.get("USER");
                     food = (Food) innerMap.get("FOOD");
                     ProcessingPrevBtnToFood();
                     break;

                  case "selected_position":
                     innerMap = (Map<String, Object>) map
                           .get("selected_position");
                     user = (User) innerMap.get("USER");
                     position = (int) innerMap.get("position");
                     Message msg = mMainHandler.obtainMessage();
                     msg.what = SEND_POSITION_INFOMATION;
                     msg.arg1 = position+1;
                     mMainHandler.sendMessage(msg);
                     break;
                  case "selected_material":
                     innerMap = (Map<String, Object>) map
                           .get("selected_material");
                     user = (User) innerMap.get("USER");
                     position = (int) innerMap.get("position");
                     material = (Material) innerMap.get("MATERIAL");
                     Message msg1 = mMainHandler.obtainMessage();
                     msg1.what = SEND_MATERIAL_INFOMATION;
                     msg1.arg2 = position+1;
                     msg1.obj = material;
                     mMainHandler.sendMessage(msg1);

                     break;
                  case "nextBtn_to_last":
                     innerMap = (Map<String, Object>) map
                           .get("nextBtn_to_last");
                     user = (User) innerMap.get("USER");
                     food = (Food) innerMap.get("FOOD");
                     ProcessingNextBtnToLast();
                     break;

                  }
               }
            }
         } catch (Exception ex) {
            ex.printStackTrace();
         }
      }
   }

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

   public void SendSelectePosition(User user, int position) {
      try {
         Map<String, Object> selected_position_list = new HashMap<String, Object>();
         selected_position_list.put("USER", user);
         selected_position_list.put("position", position-1);
         Map<String, Object> map_selected_position = new HashMap<String, Object>();
         map_selected_position.put("selected_position",
               selected_position_list);
         out.writeObject(map_selected_position);
         out.flush();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void SendSelecteMaterial(User user, int position, Material material) {
      try {
         Map<String, Object> selected_material_list = new HashMap<String, Object>();
         selected_material_list.put("USER", user);
         selected_material_list.put("position", position-1);
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

   public void ProcessingPrevBtnToFood() {
      Intent foodInfo = new Intent(material_activity.this,
            food_activity.class);
      foodInfo.putExtra("user", user);
      foodInfo.putExtra("recommand_food", recommand_food);
      foodInfo.putExtra("menu_food", menu_food);
      foodInfo.putExtra("selectedItem", food_o.getId());
      foodInfo.putExtra("selectedFood",selected_food);
      foodInfo.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
            | Intent.FLAG_ACTIVITY_CLEAR_TOP);
      
      startActivity(foodInfo);
      finish();
   }

   public void ProcessingNextBtnToLast() {
      Intent check = new Intent(material_activity.this, check_activity.class);
      check.addFlags(check.FLAG_ACTIVITY_CLEAR_TOP);
      startActivity(check);
       finish();
   }

   // Handler �겢�옒�뒪
   class SendMassgeHandler extends Handler {

      @Override
      public void handleMessage(Message msg) {

         ImageView alter1 = (ImageView) findViewById(R.id.a_1);
         ImageView alter2 = (ImageView) findViewById(R.id.a_2);
         ImageView alter3 = (ImageView) findViewById(R.id.a_3);
         ImageView alter4 = (ImageView) findViewById(R.id.a_4);
         ImageView alter5 = (ImageView) findViewById(R.id.a_5);
         ImageView alter6 = (ImageView) findViewById(R.id.a_6);
         
         super.handleMessage(msg);

         switch (msg.what) {
         case SEND_POSITION_INFOMATION:
            click_check = msg.arg1;
            
            alter1.setVisibility(View.VISIBLE);
            alter2.setVisibility(View.VISIBLE);
            alter3.setVisibility(View.VISIBLE);
            alter4.setVisibility(View.VISIBLE);
            alter5.setVisibility(View.VISIBLE);
            alter6.setVisibility(View.VISIBLE);
            
            int a1 = food_o.getMaterialList().get(0).getId();
            int a2 = food_o.getMaterialList().get(1).getId();
            int a3 = food_o.getMaterialList().get(2).getId();
            int a4 = food_o.getMaterialList().get(3).getId();
            int a5 = food_o.getMaterialList().get(4).getId();
            int a6 = food_o.getMaterialList().get(5).getId();
            
            int a1num = getBaseContext().getResources().getIdentifier(
                  "material_" + a1, "drawable",
                  getBaseContext().getPackageName());
            int a2num = getBaseContext().getResources().getIdentifier(
                  "material_" + a2, "drawable",
                  getBaseContext().getPackageName());
            int a3num = getBaseContext().getResources().getIdentifier(
                  "material_" + a3, "drawable",
                  getBaseContext().getPackageName());
            int a4num = getBaseContext().getResources().getIdentifier(
                  "material_" + a4, "drawable",
                  getBaseContext().getPackageName());
            int a5num = getBaseContext().getResources().getIdentifier(
                  "material_" + a5, "drawable",
                  getBaseContext().getPackageName());
            int a6num = getBaseContext().getResources().getIdentifier(
                  "material_" + a6, "drawable",
                  getBaseContext().getPackageName());

            alter1.setImageResource(a6num);
            alter2.setImageResource(a5num);
            alter3.setImageResource(a4num);
            alter4.setImageResource(a3num);
            alter5.setImageResource(a2num);
            alter6.setImageResource(a1num);
            
            String tempName=trans.translating(selected_food.getMaterialList().get(click_check-1).getName(), user.getNation());
            String tempExplain=trans.translating(selected_food.getMaterialList().get(click_check-1).getExplain(), user.getNation());
            explain.setText(tempName+"\n"+tempExplain);
            
            break;

         case SEND_MATERIAL_INFOMATION:

             click_alter = msg.arg2;
             Material material = food_o.getMaterialList().get(click_alter-1);

             selected_food.getMaterialList().set(click_check-1, material);

            alter1.setVisibility(View.GONE);
            alter2.setVisibility(View.GONE);
            alter3.setVisibility(View.GONE);
            alter4.setVisibility(View.GONE);
            alter5.setVisibility(View.GONE);
            alter6.setVisibility(View.GONE);
           
            int a11 = selected_food.getMaterialList().get(0).getId();
            int a12 = selected_food.getMaterialList().get(1).getId();
            int a13 = selected_food.getMaterialList().get(2).getId();
            int a14 = selected_food.getMaterialList().get(3).getId();
            int a15 = selected_food.getMaterialList().get(4).getId();
            int a16 = selected_food.getMaterialList().get(5).getId();

            int a11num = getBaseContext().getResources().getIdentifier(
                  "material_" + a11, "drawable",
                  getBaseContext().getPackageName());
            int a12num = getBaseContext().getResources().getIdentifier(
                  "material_" + a12, "drawable",
                  getBaseContext().getPackageName());
            int a13num = getBaseContext().getResources().getIdentifier(
                  "material_" + a13, "drawable",
                  getBaseContext().getPackageName());
            int a14num = getBaseContext().getResources().getIdentifier(
                  "material_" + a14, "drawable",
                  getBaseContext().getPackageName());
            int a15num = getBaseContext().getResources().getIdentifier(
                  "material_" + a15, "drawable",
                  getBaseContext().getPackageName());
            int a16num = getBaseContext().getResources().getIdentifier(
                  "material_" + a16, "drawable",
                  getBaseContext().getPackageName());

          
            ImageView material1 = (ImageView) findViewById(R.id.s_1);
            ImageView material2 = (ImageView) findViewById(R.id.s_2);
            ImageView material3 = (ImageView) findViewById(R.id.s_3);
            ImageView material4 = (ImageView) findViewById(R.id.s_4);
            ImageView material5 = (ImageView) findViewById(R.id.s_5);
            ImageView material6 = (ImageView) findViewById(R.id.s_6);
            
            material1.setImageResource(a11num);
            material2.setImageResource(a12num);
            material3.setImageResource(a13num);
            material4.setImageResource(a14num);
            material5.setImageResource(a15num);
            material6.setImageResource(a16num);

             tempName=trans.translating(material.getName(), user.getNation());
             tempExplain=trans.translating(material.getExplain(), user.getNation());
            explain.setText(tempName+"\n"+tempExplain);
            
            break;
         }
      }

   };
   
   public void AlarmStart(){       
        Intent intent = new Intent(this, AlarmReceiver.class);
//        intent.putExtra("user", user);
//        intent.putExtra("selectedFood",selected_food);
//        intent.addFlags(Intent.FLAG_RECEIVER_REGISTERED_ONLY);
//        sendBroadcast(intent);
        
        SharedPreferences preferences = getSharedPreferences("shared",0);

        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("selected",selected_food.getName());
        PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 2);
        
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);

   }
}