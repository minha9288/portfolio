package com.mmouse.mm.RMate;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Bitmap.Config;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.PowerManager;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnKeyListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.mmouse.mm.connect.osc.OSCMessage;
import com.mmouse.mm.connect.osc.OSCPort;
import com.mmouse.mm.connect.osc.OSCPortOut;
import com.mmouse.mm.RMate.ControlPage.PanelConfig.PanelItem;

public class ControlAlarm extends Activity implements View.OnClickListener{
    //


    private static final int TAP_NONE = 0;
    private static final int TAP_FIRST = 1;
    private static final int TAP_SECOND = 2;
    private static final int TAP_DOUBLE = 3;
    private static final int TAP_DOUBLE_FINISH = 4;
    private static final int TAP_RIGHT = 5;
    private static final String TAG = "RemoteDroid";
    //
    private OSCPortOut sender;
    // thread and graphics stuff
    private Handler handler = new Handler();
    //
    private long startedTime;
    private long countTime;
    private FrameLayout flLeftButton;
    private boolean leftToggle = false;
    private Runnable rLeftDown;
    private Runnable rLeftUp;
    //
    private FrameLayout flRightButton;
    private boolean rightToggle = false;
    private Runnable rRightDown;
    private Runnable rRightUp;
    //
    private FrameLayout flMidButton;
    private boolean softShown = false;
    private Runnable rMidDown;
    private Runnable rMidUp;

    private Vibrator vibrator;
    private CountDownTimer cdt;
    //
    private EditText etAdvancedText;

    /* slide menu */
    private DisplayMetrics metrics;
    private LinearLayout ll_mainLayout;
    private LinearLayout ll_menuLayout;
    private FrameLayout.LayoutParams leftMenuLayoutPrams;
    private int leftMenuWidth;
    private static boolean isLeftExpanded;
    private ImageButton bt_left, slideMenu, penMenu, lazerMenu, voiceMenu, playMenu, alarmMenu, settingMenu, exitMenu ;


    public class PanelConfig
    {
        ArrayList<PanelItem> PanelItems = new ArrayList<PanelItem>();
        public PanelConfig(String config)
        {
            int level = 0;
            String current = "";
            for (int i = 0; i < config.length(); i++)
            {
                String chr = config.substring(i, i+1);
                current += chr;
                if (chr.equals("{"))
                {
                    if (level == 0) current = current.trim();
                    level++;
                }
                if (chr.equals("}"))
                {
                    level--;
                    if (level == 0)
                    {
                        PanelItems.add(new PanelItem(current));
                        current = "";
                    }
                }
            }
        }

        public class PanelItem
        {
            public String Name = "";
            Bitmap icon = null;
            PanelCommand command = null;
            public PanelItem(String config)
            {
                boolean isBefore = true;
                String current = "";
                String name = "";
                String value = "";
                for (int i = 0; i < config.length(); i++)
                {
                    String chr = config.substring(i, i + 1);
                    if (isBefore)
                    {
                        if (chr.equals("{"))
                        {
                            current = current.trim();
                            Name = current;
                            current = "";
                            isBefore = false;
                        }
                        else
                        {
                            current += chr;
                        }
                    }
                    else
                    {
                        if (chr.equals(":"))
                        {
                            name = current.trim().toLowerCase();
                            current = "";
                        }
                        else if (chr.equals("}") || chr.equals(";"))
                        {
                            if (!name.equals(""))
                            {
                                value = current.trim();
                                current = "";
                                if(name.equals("icon"))
                                {
                                    icon = parseBitmap(value);
                                }
                                if(name.equals("command"))
                                {
                                    command = parseCommand(value);
                                }
                                if(name.equals("name"))
                                {
                                    Name = parseString(value);
                                }
                                name = "";
                            }
                        }
                        else
                        {
                            current += chr;
                        }
                    }
                }
            }

            public String parseString(String value)
            {
                return value.trim().replace("'", "");
            }
            public Bitmap parseBitmap(String value)
            {
                value = value.trim();
                if(value.startsWith("bitblt_halpha16("))
                {
                    String bb = "";
                    for (int i = 16; i < value.length(); i++)
                    {
                        String chr = value.substring(i, i + 1);
                        if ("0123456789ABCDEFabcdef".contains(chr)) bb += chr;
                    }
                    if(bb.length()<16*16*2) return Bitmap.createBitmap(16, 16, Config.ARGB_8888);
                    Bitmap bmp = Bitmap.createBitmap(16, 16, Config.ARGB_8888);
                    int ix=0;
                    for (int y = 0; y < 16; y++)
                    {
                        for (int x = 0; x < 16; x++)
                        {
                            String hex_pair = bb.substring(ix,ix+2); ix+=2;
                            bmp.setPixel(x, y, Color.argb(Integer.valueOf(hex_pair, 16).intValue(), 0, 0, 0));
                        }
                    }
                    return bmp;
                }if(value.startsWith("bitblt_halpha32("))
            {
                String bb = "";
                for (int i = 16; i < value.length(); i++)
                {
                    String chr = value.substring(i, i + 1);
                    if ("0123456789ABCDEFabcdef".contains(chr)) bb += chr;
                }
                if(bb.length()<32*32*2) return Bitmap.createBitmap(16, 16, Config.ARGB_8888);
                Bitmap bmp = Bitmap.createBitmap(32, 32, Config.ARGB_8888);
                int ix=0;
                for (int y = 0; y < 32; y++)
                {
                    for (int x = 0; x < 32; x++)
                    {
                        String hex_pair = bb.substring(ix,ix+2); ix+=2;
                        bmp.setPixel(x, y, Color.argb(Integer.valueOf(hex_pair, 16).intValue(), 0, 0, 0));
                    }
                }
                return bmp;
            }
                if(value.startsWith("bitblt_halpha48("))
                {
                    String bb = "";
                    for (int i = 16; i < value.length(); i++)
                    {
                        String chr = value.substring(i, i + 1);
                        if ("0123456789ABCDEFabcdef".contains(chr)) bb += chr;
                    }
                    if(bb.length()<48*48*2) return Bitmap.createBitmap(16, 16, Config.ARGB_8888);
                    Bitmap bmp = Bitmap.createBitmap(48, 48, Config.ARGB_8888);
                    int ix=0;
                    for (int y = 0; y < 48; y++)
                    {
                        for (int x = 0; x < 48; x++)
                        {
                            String hex_pair = bb.substring(ix,ix+2); ix+=2;
                            bmp.setPixel(x, y, Color.argb(Integer.valueOf(hex_pair, 16).intValue(), 0, 0, 0));
                        }
                    }
                    return bmp;
                }
                if(value.startsWith("bitblt_halpha64("))
                {
                    String bb = "";
                    for (int i = 16; i < value.length(); i++)
                    {
                        String chr = value.substring(i, i + 1);
                        if ("0123456789ABCDEFabcdef".contains(chr)) bb += chr;
                    }
                    if(bb.length()<48*48*2) return Bitmap.createBitmap(16, 16, Config.ARGB_8888);
                    Bitmap bmp = Bitmap.createBitmap(64, 64, Config.ARGB_8888);
                    int ix=0;
                    for (int y = 0; y < 64; y++)
                    {
                        for (int x = 0; x < 64; x++)
                        {
                            String hex_pair = bb.substring(ix,ix+2); ix+=2;
                            bmp.setPixel(x, y, Color.argb(Integer.valueOf(hex_pair, 16).intValue(), 0, 0, 0));
                        }
                    }
                    return bmp;
                }
                return Bitmap.createBitmap(16, 16, Config.ARGB_8888);
            }
            public class PanelCommand
            {
                public String Windows = "";
                public String Linux = "";
                public String OSX = "";
            }
            public PanelCommand parseCommand(String value)
            {
                return null;
            }
        }
    }

    //
    private float xHistory;
    private float yHistory;
    //
    private int lastPointerCount = 0;
    // power lock
    private PowerManager.WakeLock lock;
    // sensors
    private SensorManager mSensorManager;
    private SensorEventListener mSensorListener;
    private Sensor mSensorAccelerometer;
    private Sensor mSensorMagnetic;
    // sensor tolerance
    private boolean useOrientation = false;

    //
    private Point3D accel;
    private boolean accelSet = false;
    private Point3D mag;
    private boolean magSet = false;

    private SharedPreferences settings;
    //
    private CoordinateSpace lastSpace;
    private CoordinateSpace currSpace;
    // toggles
    private boolean toggleButton = false;
    // tap to click
    private long lastTap = 0;
    private int tapState = TAP_NONE;
    private Timer tapTimer;
    // multitouch scroll
    // private float scrollX = 0f;
    private float scrollY = 0f;
    /**
     * Mouse sensitivity power
     */
    private double mMouseSensitivityPower;
    private static final float sScrollStepMax = 6f;
    private static final float sScrollStepMin = 45f;
    private static final float sScrollMaxSettingsValue = 100f;
    private float mScrollStep;// = 12f;
    private static final float sTrackMultiplier = 6f;

    public static final String OK = "ok";
    public static final String CANCEL = "cancel";

    /**
     * Cached multitouch information
     */
    private boolean mIsMultitouchEnabled;
    public ControlAlarm() {
        super();
    }

    private void enableSensors() {
        if (mSensorManager == null) {
            mSensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        }
        if (mSensorAccelerometer == null) {
            mSensorAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            Log.d(TAG, "Accelerometer Sensor: " + mSensorAccelerometer);
        }
        if (mSensorMagnetic == null) {
            mSensorMagnetic = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            Log.d(TAG, "Magnetic Sensor: " + mSensorMagnetic);
        }
        this.mSensorManager.registerListener(this.mSensorListener, mSensorAccelerometer,
                SensorManager.SENSOR_DELAY_GAME);
        this.mSensorManager.registerListener(this.mSensorListener, mSensorMagnetic,
                SensorManager.SENSOR_DELAY_GAME);
    }

    private void disableSensors() {
        if (mSensorManager != null) {
            this.mSensorManager.unregisterListener(this.mSensorListener);
            this.mSensorManager = null;

        }

    }



    /*

     * (non-Javadoc)

     *

     * @see android.app.Activity#onCreate(android.os.Bundle)

     */
    private void initSildeMenu() {

        // init left menu width
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        leftMenuWidth = (int) ((metrics.widthPixels) * 0.30);

        // init main view
        ll_mainLayout = (LinearLayout) findViewById(R.id.control_alarm_ll_mainlayout);

        // init left menu
        ll_menuLayout = (LinearLayout) findViewById(R.id.control_alarm_ll_menuLayout);
        leftMenuLayoutPrams = (FrameLayout.LayoutParams) ll_menuLayout
                .getLayoutParams();
        leftMenuLayoutPrams.width = leftMenuWidth;
        ll_menuLayout.setLayoutParams(leftMenuLayoutPrams);

        // init ui
        bt_left = (ImageButton) findViewById(R.id.control_alarm_bt_left);
        bt_left.setOnClickListener(this);

        slideMenu = (ImageButton) findViewById(R.id.slideMenu);
        penMenu = (ImageButton) findViewById(R.id.penMenu);
        lazerMenu = (ImageButton) findViewById(R.id.laserMenu);
        //voiceMenu = (ImageButton) findViewById(R.id.voiceMenu);
        //playMenu = (ImageButton) findViewById(R.id.playMenu);
        alarmMenu = (ImageButton) findViewById(R.id.alarmMenu);
        slideMenu.setOnClickListener(this);
        penMenu.setOnClickListener(this);
        lazerMenu.setOnClickListener(this);
        voiceMenu.setOnClickListener(this);
        playMenu.setOnClickListener(this);
        alarmMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.control_alarm_bt_left:
                menuLeftSlideAnimationToggle();
                break;
            // 슬라이드
            case R.id.slideMenu:
                Intent intentPage = new Intent(this, ControlPage.class);
                this.startActivity(intentPage);

                break;

            // 펜
            case R.id.penMenu:
                Intent intentPen = new Intent(this, ControlPen.class);
                this.startActivity(intentPen);
                this.finish();
                break;

            // 레이저
            case R.id.laserMenu:
                Intent intentLaser = new Intent(this, ControlLaser.class);
                this.startActivity(intentLaser);
                this.finish();
                break;

            // 음성

            // 동영상 재생

            // 알람
            case R.id.alarmMenu:
                break;

            // 설정


            // 어플 종료
            case R.id.exitMenu:
                Object[] args1 = new Object[1];
                args1[0] = 1;
                //입력받은 선택페이지 값 넣기
                OSCMessage msg1 = new OSCMessage("/endshow", args1);
                Toast.makeText(getApplicationContext(), "end", Toast.LENGTH_SHORT).show();
                try {
                    this.sender.send(msg1);
                } catch (Exception ex) {
                    Log.d(TAG, ex.toString());
                }
                break;
        }
    }
    /**
     * left menu toggle
     */
    private void menuLeftSlideAnimationToggle() {

        if (!isLeftExpanded) {

            isLeftExpanded = true;

            // Expand
            new OpenAnimation(ll_mainLayout, leftMenuWidth,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.30f, 0, 0.0f, 0, 0.0f);

            // disable all of main view
            FrameLayout viewGroup = (FrameLayout) findViewById(R.id.control_alarm_ll_fragment)
                    .getParent();
            enableDisableViewGroup(viewGroup, false);

            // enable empty view
            ((LinearLayout) findViewById(R.id.control_alarm_ll_empty))
                    .setVisibility(View.VISIBLE);

            findViewById(R.id.control_alarm_ll_empty).setEnabled(true);
            findViewById(R.id.control_alarm_ll_empty).setOnTouchListener(
                    new View.OnTouchListener() {

                        @Override
                        public boolean onTouch(View arg0, MotionEvent arg1) {
                            menuLeftSlideAnimationToggle();
                            return true;
                        }
                    });

        } else {
            isLeftExpanded = false;

            // close
            new CloseAnimation(ll_mainLayout, leftMenuWidth,
                    TranslateAnimation.RELATIVE_TO_SELF, 0.30f,
                    TranslateAnimation.RELATIVE_TO_SELF, 0.0f, 0, 0.0f, 0, 0.0f);

            // enable all of main view
            FrameLayout viewGroup = (FrameLayout) findViewById(R.id.control_alarm_ll_fragment)
                    .getParent();
            enableDisableViewGroup(viewGroup, true);

            // disable empty view
            ((LinearLayout) findViewById(R.id.control_alarm_ll_empty))
                    .setVisibility(View.GONE);
            findViewById(R.id.control_alarm_ll_empty).setEnabled(false);

        }
    }

    /**
     * 뷰의 동작을 제어한다. 하위 모든 뷰들이 enable 값으로 설정된다.
     *
     * @param viewGroup
     * @param enabled
     */
    public static void enableDisableViewGroup(ViewGroup viewGroup,
                                              boolean enabled) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = viewGroup.getChildAt(i);
            if (view.getId() != R.id.bt_left) {
                view.setEnabled(enabled);
                if (view instanceof ViewGroup) {
                    enableDisableViewGroup((ViewGroup) view, enabled);
                }
            }
        }
    }


    TextView tv;
    private interface LL extends View.OnClickListener
    {
        public PendingIntent p();
        public PendingIntent p1();
    }
    private Button okBtn;
    private Button cButton;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Settings.init(this.getApplicationContext());


        settings = getSharedPreferences("hi",Context.MODE_PRIVATE);


        // Hide the title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (this.lock == null) {
            Context appContext = this.getApplicationContext();
            // get wake lock
            PowerManager manager = (PowerManager) appContext
                    .getSystemService(Context.POWER_SERVICE);
            this.lock = manager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, this

                    .getString(R.string.app_name));
            // prepare sensor Listener


            //

            try {

                //

                setContentView(R.layout.activity_control_alarm);
                this.initSildeMenu();

                DisplayMetrics dm = new DisplayMetrics();

                this.getWindowManager().getDefaultDisplay().getMetrics(dm);

                //

                this.sender = new OSCPortOut(InetAddress.getByName(Settings.ip), OSCPort.defaultSCOSCPort());

                //



            } catch (Exception ex) {

                Log.d(TAG, ex.toString());

            }


            this.changeMouse();

            okBtn = (Button) findViewById(R.id.control_alarm_button);
            final EditText editText = (EditText) findViewById(R.id.control_alarm_editText);
            tv = (TextView) findViewById(R.id.control_alarm_tv);
            cButton = (Button) findViewById(R.id.control_alarm_c_button);



            // 버튼 id 받아오기 & 이벤트 연결
            final LL alarmListener = new LL() {

                PendingIntent pendingIntent;
                PendingIntent pendingIntent1;
                @Override
                public void onClick(View view) {
                    final String time = editText.getText().toString();
                    if(time.equals(""))
                    {
                        Toast.makeText(getApplicationContext(), "Enter Time Limit", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(!(time.equals("")))
                        Toast.makeText(getApplicationContext(), "You have " + time + " minutes", Toast.LENGTH_SHORT).show();

                    Long mtime = new GregorianCalendar().getTimeInMillis()+ Integer.parseInt(time.toString())*60*1000;
                    startedTime = System.currentTimeMillis();
                    countTime = Integer.parseInt(time.toString())*60*1000;
                    Intent intent = new Intent(ControlAlarm.this, AlarmReceiver.class);
                    pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),12345,intent,0);

                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, mtime, pendingIntent);

                    Intent intent1 = new Intent(ControlAlarm.this, AlarmReceiver.class);
                    intent1.putExtra("reminder", true);
                    pendingIntent1 = PendingIntent.getBroadcast(getApplicationContext(),123456,intent1,0);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, mtime-60000, pendingIntent1);

                    okBtn.setEnabled(false);
                    cButton.setEnabled(true);

                    if(!(cdt == null))
                        System.gc();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                        cdt = new CountDownTimer(Integer.parseInt(time.toString()) * 60 * 1000, 1000) {

                            public void onTick(long millisUntilFinished) {
                                tv.setText("seconds remaining: " + millisUntilFinished / 1000);
                            }

                            public void onFinish() {
                                tv.setText("done!");
                            }
                        }.start();

                }

                @Override
                public PendingIntent p() {
                    return pendingIntent;
                }

                @Override
                public PendingIntent p1() {
                    return pendingIntent1;
                }
            };

            okBtn.setOnClickListener(alarmListener);


            View.OnClickListener cancelListener = new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    okBtn.setEnabled(true);
                    cButton.setEnabled(false);
                    ((AlarmManager)getSystemService(ALARM_SERVICE)).cancel(alarmListener.p());
                    ((AlarmManager)getSystemService(ALARM_SERVICE)).cancel(alarmListener.p1());
                    if(cdt!=null)
                        cdt.cancel();
                    countTime = 0;
                    tv.setText("");
                }
            };
            cButton.setOnClickListener(cancelListener);



/*
        Button gotoBtn = (Button) findViewById(R.id.go_toBtn);
        gotoBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.d("ControlPage", "버튼눌림");
                Toast.makeText(ControlPage.this, "버튼눌림", Toast.LENGTH_SHORT).show();

                AlertDialog.Builder input_pageNum = new AlertDialog.Builder(ControlPage.this);
                input_pageNum.setMessage("Enter the page number").setCancelable(false).setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                onGotoBtn();
                            }
                        }).setNegativeButton("Close",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which){
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = input_pageNum.create();
                alert.show();

            }
        });
  */


        }





    }


    @SuppressWarnings("unused")

    private void onPrefs() {

        //Intent i = new Intent(ControlPage.this, PrefsActivity.class);

        //this.startActivity(i);

    }





















    public void onStart() {

        super.onStart();

    }



    public void onResume() {

        super.onResume();

        startedTime =  settings.getLong("started", 0);
        countTime =  settings.getLong("count",0);


        // acquire screen lock

        this.lock.acquire();

        // set sensor

        if (this.useOrientation) {

            enableSensors();

        }


        if(settings.contains(OK)) {
            okBtn.setEnabled(settings.getBoolean(OK,true));
        }
        if(settings.contains(CANCEL)){
            okBtn.setEnabled(settings.getBoolean(CANCEL, true));
        }
        if(settings.contains("started")){

            long yawsan = System.currentTimeMillis()-settings.getLong("started",0);

//            if(!(cdt == null))
//                System.gc();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            if(countTime != 0) {
                cdt = new CountDownTimer((settings.getLong("count", 0) - yawsan), 1000) {

                    public void onTick(long millisUntilFinished) {
                        tv.setText("seconds remaining: " + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                        tv.setText("done!");
                    }
                }.start();
            }
        }

    }


    public void onPause() {

        super.onPause();

        // this'd be a great time to disconnect from the server, and clean

        // up anything that needs to be cleaned up.

        // release screen lock

        this.lock.release();



        // release sensor

        disableSensors();

        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("ok",okBtn.isEnabled());
        editor.putBoolean("cancel", cButton.isEnabled());
        editor.putLong("started",startedTime);
        editor.putLong("count",countTime);
        editor.commit();
    }



    public void onStop() {

        super.onStop();

    }



    public void onDestroy() {

        super.onDestroy();

        this.sender.close();
    }



    // keyboard



    public boolean onKeyDown(int keycode, KeyEvent ev) {
        switch (keycode){
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                this.onNextBtn();
                break;

            case KeyEvent.KEYCODE_VOLUME_UP:
                this.onPrevBtn();
                break;


            case KeyEvent.KEYCODE_BACK:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("close");
                builder.setMessage("do you want to close?");
                builder.setNegativeButton("NO", null);
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        changeMouse();
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                });
                builder.show();
                break;
        }
        return true;
    }



    public void changeMouse(){
        Object[] args = new Object[1];
        args[0] = 0;
        OSCMessage msg = new OSCMessage("/changeMouse", args);
        try {
            this.sender.send(msg);
        } catch (Exception ex) {
            Log.d(TAG, ex.toString());
        }
    }

    private void onPrevBtn(){
        Object[] args = new Object[1];
        args[0] = 1;
        OSCMessage msg = new OSCMessage("/prevbutton", args);
        try {
            this.sender.send(msg);
        } catch (Exception ex) {
            Log.d(TAG, ex.toString());
        }
    }

    private void onNextBtn(){
        Object[] args = new Object[1];
        args[0] = 1;
        OSCMessage msg = new OSCMessage("/nextbutton", args);
        try {
            this.sender.send(msg);
        } catch (Exception ex) {
            Log.d(TAG, ex.toString());
        }
    }

}
