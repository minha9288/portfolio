package com.mmouse.mm.RMate;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Bitmap.Config;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
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
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mmouse.mm.connect.osc.OSCMessage;
import com.mmouse.mm.connect.osc.OSCPort;
import com.mmouse.mm.connect.osc.OSCPortOut;

public class ControlLaser extends Activity implements View.OnClickListener{
    private  static  final String VOICESLIDE = "slide";
    private  static  final String VOICELASER = "laser";
    private  static  final String VOICEPEN = "pen";
    private  static  final String VOICEALARM = "Timer";
    private  static  final String VOICEPLAY = "Video";

    public int timer;

    private  static  final String VOICESLIDE_KR = "슬라이드";
    private  static  final String VOICELASER_KR = "레이저";
    private  static  final String VOICEPEN_KR = "펜";
    private  static  final String VOICEALARM_KR = "타이머";
    private  static  final String VOICEPLAY_KR = "동영상";

    private  static  final int RC_EN = 1111;
    private  static  final int RC_KR = 2222;

    private  static  final String[] VOICEINPUTS = {VOICESLIDE,VOICEPEN,VOICELASER,VOICEPLAY,VOICEALARM};
    private  static  final String[] VOICEINPUTS_KR = {VOICESLIDE_KR,VOICEPEN_KR,VOICELASER_KR,VOICEPLAY_KR,VOICEALARM_KR};

    private  static  boolean found = false;


    //
    private static final int TAP_NONE = 0;
    private static final int TAP_FIRST = 1;
    private static final int TAP_SECOND = 2;
    private static final int TAP_DOUBLE = 3;
    private static final int TAP_DOUBLE_FINISH = 4;
    private static final int TAP_RIGHT = 5;
    private static final String TAG = "MultiMouse";

    //
    private OSCPortOut sender;
    // thread and graphics stuff
    private Handler handler = new Handler();
    //
    private FrameLayout laser;
    private boolean laserToggle = false;
    private Runnable LaserDown;
    private Runnable LaserUp;
    //
    private DisplayMetrics metrics;
    private LinearLayout ll_mainLayout;
    private LinearLayout ll_menuLayout;
    private FrameLayout.LayoutParams leftMenuLayoutPrams;
    private int leftMenuWidth;
    private static boolean isLeftExpanded;
    private ImageButton bt_left, currentslide, slideMenu, penMenu, laserMenu, playMenu, alarmMenu, settingMenu, exitMenu ;

    private boolean laserOnOFF = false;
    private boolean laserLeftbtn = false;

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
    private Sensor mSensorGyroscope;

    // sensor tolerance
    private boolean useOrientation = true;
    //
    private Point3D accel;
    private boolean accelSet = false;
    private Point3D mag;
    private boolean magSet = false;
    private Point3D gyro;
    private boolean gyroSet = false;
    //
    float x,y;
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

    /**
     * Cached multitouch information
     */
    private boolean mIsMultitouchEnabled;

    public ControlLaser() {
        super();
    }

    private void enableSensors() {
        if (mSensorManager == null) {
            mSensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        }

        if (mSensorAccelerometer == null) {
            mSensorAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            Log.d(TAG, "Accelerometer Sensor: " + mSensorAccelerometer);
        }

//        if (mSensorGyroscope == null) {
//            mSensorGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
//            Log.d(TAG, "Gyro Sensor: " + mSensorGyroscope);
//        }

        if (mSensorMagnetic == null) {
            mSensorMagnetic = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            Log.d(TAG, "Magnetic Sensor: " + mSensorMagnetic);
        }

        this.mSensorManager.registerListener(this.mSensorListener, mSensorAccelerometer,
                SensorManager.SENSOR_DELAY_GAME);
//        this.mSensorManager.registerListener(this.mSensorListener, mSensorGyroscope,
//                SensorManager.SENSOR_DELAY_GAME);
        this.mSensorManager.registerListener(this.mSensorListener, mSensorMagnetic,
                SensorManager.SENSOR_DELAY_GAME);
    }

    private void disableSensors() {
        if (mSensorManager != null) {
            this.mSensorManager.unregisterListener(this.mSensorListener);
            this.mSensorManager = null;
        }
    }

    private void initSildeMenu() {

        // init left menu width
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        leftMenuWidth = (int) ((metrics.widthPixels) * 0.30);

        // init main view
        ll_mainLayout = (LinearLayout) findViewById(R.id.ll_mainlayout);

        // init left menu
        ll_menuLayout = (LinearLayout) findViewById(R.id.ll_menuLayout);
        leftMenuLayoutPrams = (FrameLayout.LayoutParams) ll_menuLayout
                .getLayoutParams();
        leftMenuLayoutPrams.width = leftMenuWidth;
        ll_menuLayout.setLayoutParams(leftMenuLayoutPrams);

        // init ui
        bt_left = (ImageButton) findViewById(R.id.bt_left);
        bt_left.setOnClickListener(this);



        currentslide = (ImageButton) findViewById(R.id.currentslide);
        slideMenu = (ImageButton) findViewById(R.id.slideMenu);
        penMenu = (ImageButton) findViewById(R.id.penMenu);
        laserMenu = (ImageButton) findViewById(R.id.laserMenu);
        //playMenu = (ImageButton) findViewById(R.id.playMenu);
        alarmMenu = (ImageButton) findViewById(R.id.alarmMenu);
        exitMenu = (ImageButton) findViewById(R.id.exitMenu);

        currentslide.setOnClickListener(this);
        slideMenu.setOnClickListener(this);
        penMenu.setOnClickListener(this);
        laserMenu.setOnClickListener(this);
        //playMenu.setOnClickListener(this);
        alarmMenu.setOnClickListener(this);
        exitMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bt_left:
                menuLeftSlideAnimationToggle();
                break;

            case R.id.currentslide:
                Object[] args = new Object[1];
                args[0] = 1;
                //입력받은 선택페이지 값 넣기
                OSCMessage msg = new OSCMessage("/startshow", args);
                Toast.makeText(getApplicationContext(), "currentslide", Toast.LENGTH_SHORT).show();
                try {
                    this.sender.send(msg);
                } catch (Exception ex) {
                    Log.d(TAG, ex.toString());
                }
                break;


            // 슬라이드
            case R.id.slideMenu:
                Intent intentPage = new Intent(this, ControlPage.class);
                this.startActivity(intentPage);
                this.finish();
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

            // 동영상 재생


            // 알람
            case R.id.alarmMenu:
                Intent intentAlarm = new Intent(this, ControlAlarm.class);
                this.startActivity(intentAlarm);
                this.finish();
                break;


            // 쇼 종료
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

            // 슬라이딩 메뉴 열렸을 때,
            isLeftExpanded = true;

            // Expand
            new OpenAnimation(ll_mainLayout, leftMenuWidth,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.30f, 0, 0.0f, 0, 0.0f);

            // disable all of main view
            FrameLayout viewGroup = (FrameLayout) findViewById(R.id.ll_fragment)
                    .getParent();
            enableDisableViewGroup(viewGroup, false);

            // 희정이 추가중
            View back = (View) findViewById(R.id.ll_fragment);

            AlphaAnimation alpha = new AlphaAnimation(0.1f,0.1f);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            back.setAnimation(alpha);


            // enable empty view
            ((LinearLayout) findViewById(R.id.ll_empty))
                    .setVisibility(View.VISIBLE);

            findViewById(R.id.ll_empty).setEnabled(true);
            findViewById(R.id.ll_empty).setOnTouchListener(
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
            FrameLayout viewGroup = (FrameLayout) findViewById(R.id.ll_fragment)
                    .getParent();
            enableDisableViewGroup(viewGroup, true);

            // 희정이 추가함
            View back = (View) findViewById(R.id.ll_fragment);

            AlphaAnimation alpha = new AlphaAnimation(1.0f,1.0f);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            back.setAnimation(alpha);




            // disable empty view
            ((LinearLayout) findViewById(R.id.ll_empty))
                    .setVisibility(View.GONE);
            findViewById(R.id.ll_empty).setEnabled(false);

        }
    }

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

    /*
     * (non-Javadoc)
     *
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Settings.init(this.getApplicationContext());
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
            this.mSensorListener = new SensorEventListener() {
                // @Override
                public void onSensorChanged(SensorEvent event) {
                    Sensor sensor = event.sensor;
                    int type = sensor.getType();
                    switch (type) {
                        case Sensor.TYPE_GYROSCOPE:
                            onAccelerometer(event.values);
                            break;
//                        case Sensor.TYPE_GYROSCOPE:
//                            onGyroscope(event.values);
//                            break;
                        case Sensor.TYPE_MAGNETIC_FIELD:
                            onMagnetic(event.values);
                            break;
                        // case Sensor.TYPE_ORIENTATION:
                        // break;
                    }
                }

                // @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {
                    // no use for this
                }
            };

            if (useOrientation) {
                // enable Sensors
                enableSensors();
            }

            /**
             * Caches information and forces WrappedMotionEvent class to load at
             * Activity startup (avoid initial lag on touchpad).
             */
            this.mIsMultitouchEnabled = WrappedMotionEvent.isMutitouchCapable();

            // Setup accelerations
            mMouseSensitivityPower = 1 + ((double) Settings.sensitivity) / 100d;
            mScrollStep = (sScrollStepMin - sScrollStepMax)
                    * (sScrollMaxSettingsValue - Settings.scrollSensitivity) / sScrollMaxSettingsValue
                    + sScrollStepMax;

            Log.d(TAG, "mScrollStep=" + mScrollStep);
            Log.d(TAG, "Settings.sensitivity=" + Settings.scrollSensitivity);
            //
            this.accel = new Point3D();
            //this.gyro = new Point3D();
            this.mag = new Point3D();
            this.lastSpace = new CoordinateSpace();
            this.currSpace = new CoordinateSpace();

            // window manager stuff
            this.getWindow().setFlags(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN,
                    WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        }
        //
        try {
            //
            setContentView(R.layout.activity_control_laser);
            this.initSildeMenu();
            DisplayMetrics dm = new DisplayMetrics();
            this.getWindowManager().getDefaultDisplay().getMetrics(dm);
            //
            this.sender = new OSCPortOut(InetAddress.getByName(Settings.ip), OSCPort
                    .defaultSCOSCPort());
            //

            this.initLaserButton();

        } catch (Exception ex) {
            Log.d(TAG, ex.toString());
        }

        this.changeMouse();

        View.OnClickListener speechListener = new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "SPEAK");

                startActivityForResult(intent, RC_EN);
            }
        };
        ImageButton speechBtn = (ImageButton) findViewById(R.id.speechBtn);
        speechBtn.setOnClickListener(speechListener);

        View.OnClickListener playListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object[] args3 = new Object[1];
                args3[0]=1;
                OSCMessage msg3 = new OSCMessage("/playbutton",args3);
                try {
                    sender.send(msg3);
                } catch (Exception ex) {
                    //Toast.makeText(getApplicationContext(), "f5", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, ex.toString());
                }
            }
        };
        ImageButton playBtn = (ImageButton) findViewById(R.id.playBtn);
        playBtn.setOnClickListener(playListener);

    }

    @SuppressWarnings("unused")
    private void onPrefs() {
        Intent i = new Intent(ControlLaser.this, PrefsActivity.class);
        this.startActivity(i);
    }

    private void initLaserButton() {
        //FrameLayout lv= (FrameLayout) this.findViewById(R.id.laserBtn);
        ImageButton ib= (ImageButton) this.findViewById(R.id.laserBtn);
        LayoutParams lp = ib.getLayoutParams();
        if(!Settings.hideMouseButtons) lp.height=0;
        ib.setLayoutParams(lp);
        // listener
        ib.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent ev) {
                return onLaserTouch(ev);
            }
        });
        //this.laser = ib;
    }

    public void onStart() {
        super.onStart();
    }

    public void onResume() {
        super.onResume();
        // acquire screen lock
        this.lock.acquire();
        // set sensor
        if (this.useOrientation) {
            enableSensors();
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
    }

    public void onStop() {
        super.onStop();
    }

    public void onDestroy() {
        super.onDestroy();
        this.sender.close();
    }

    // orientation event

    private void onAccelerometer(float[] values) {
        x = values[2];
        y = values[0];
        Point3D.copy(values, this.accel);
        this.accelSet = true;
        if (this.accelSet && this.magSet) {
            this.moveMouseFromSensors();
        }
    }

    private void onMagnetic(float[] values) {
        Point3D.copy(values, this.mag);
        this.magSet = true;
        if (this.gyroSet && this.magSet) {
            this.moveMouseFromSensors();
        }
    }

    private void moveMouseFromSensors() {
        this.accelSet = false;
        this.magSet = false;

        //
        this.currSpace.setSpace(this.accel, this.mag);
        this.sendMouseEvent(2, (float) (x*-10), (float) (y*-10));
        this.lastSpace.copy(this.currSpace);
    }

    // abstract mouse event

    private void sendMouseEvent(int type, float x, float y) {
        //
        float xDir = x == 0 ? 1 : x / Math.abs(x);
        float yDir = y == 0 ? 1 : y / Math.abs(y);
        //
        Object[] args = new Object[3];
        args[0] = type;
        args[1] = (float) (Math.pow(Math.abs(x), mMouseSensitivityPower)) * xDir;
        args[2] = (float) (Math.pow(Math.abs(y), mMouseSensitivityPower)) * yDir;
        // Log.d(TAG, String.valueOf(Settings.getSensitivity()));
        //
        Object[] args1 = new Object[1];  //그리기모드일 때 코드 추가
        args1[0] = 0;  //그리기모드일 때 코드 추가
        OSCMessage msg = new OSCMessage("/mouse", args);  //패드 움직임
        //OSCMessage msg1 = new OSCMessage("/leftbutton", args1);  //그리기모드일 때 코드 추가
        //OSCMessage msg2 = new OSCMessage("/laser", args1);  //레이저모드일 때 코드 추가
        try {
            //this.sender.send(msg1);   //그리기모드일 때 코드 추가
            this.sender.send(msg);

        } catch (Exception ex) {
            Log.d(TAG, ex.toString());
        }
    }

    private boolean onLaserTouch(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //
                //useOrientation = true;

                if (this.laserOnOFF == false){
                    if(this.laserLeftbtn == false){
                        this.laserDown();
                        this.laserLeftbtn = true;
                        this.laserOnOFF = true;
                    }
                    if(this.laserLeftbtn == true){
                        this.laserOnOFF = true;
                        this.laserDown();
                    }

                }
                /*
                if (this.toggleButton == false) {
                    if (this.laserToggle) {
                        this.laserDown();
                        this.laserToggle = false;
                    }
                    this.laserToggle = false;
                    this.laserDown();
                }
                */
                break;

            case MotionEvent.ACTION_UP:
                //useOrientation = false;
                if(laserOnOFF == true){
                    this.laserOnOFF = false;
                    this.laserUp();
                }
                /*
                if (this.toggleButton == false) {
                    this.laserUp();
                } else {
                    // toggle magic!
                    if (this.laserToggle) {
                        this.laserUp();
                    } else {
                        this.laserDown();
                    }
                    this.laserToggle = !this.laserToggle;

                }
                */
                break;
        }
        //
        return true;
    }

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



    private void laserDown() {
        Object[] args = new Object[1];
        args[0] = 0;
        OSCMessage msg = new OSCMessage("/leftbutton", args);
        OSCMessage msg1 = new OSCMessage("/laserdown", args);

        try {
            this.sender.send(msg1);
            this.sender.send(msg);

        } catch (Exception ex) {
            Log.d(TAG, ex.toString());
        }
        // graphical feedback
        this.handler.post(this.LaserDown);
    }

    private void laserUp() {

        Object[] args = new Object[1];
        args[0] = 0;
        Object[] args1 = new Object[1];
        args1[0] = 1;
        OSCMessage msg = new OSCMessage("/leftbutton", args1);
        OSCMessage msg1 = new OSCMessage("/laserup", args);
        try {
            this.sender.send(msg);
            this.sender.send(msg1);
        } catch (Exception ex) {
            Log.d(TAG, ex.toString());
        }
        // graphical feedback
        this.handler.post(this.LaserUp);
    }


    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("close");
        builder.setMessage("do you want to close?");
        builder.setNegativeButton("NO", null);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
        builder.show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_EN && resultCode == RESULT_OK)
        {

            ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            ArrayList<String> array = firstWord(results);
            for(String s : array)
            {

                for(String s1 : VOICEINPUTS)
                {
                    // @@@@@ 여기 이동시키는 페이지 다시 작성하기.
                    if(s1.equals(s))
                    {
                        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                        if(s.equals("slide"))
                        {
                            Intent slideIntent = new Intent(ControlLaser.this, ControlPage.class);
                            startActivity(slideIntent);
                            this.finish();
                        }else if(s.equals("laser")){
                            Intent laserIntent = new Intent(ControlLaser.this, ControlLaser.class);
                            startActivity(laserIntent);
                            this.finish();
                        }else if(s.equals("pen")){
                            Intent penIntent = new Intent(ControlLaser.this, ControlPen.class);
                            startActivity(penIntent);
                            this.finish();
//                        }else if(s.equals("alarm")){
//                            Intent alarmIntent = new Intent(ControlPage.this, ControlAlarm.class);
//                            startActivity(alarmIntent);
//                            this.finish();
//                        }else if(s.equals("play")){
//                            Intent playIntent = new Intent(ControlLaser.this, ControlPage.class);
//                            startActivity(playIntent);
//                            this.finish();
//                        }
                        }
                        found = true;
                        break;
                    }
                }
                if(found)
                    break;
            }
            if(!found)
            {
                for(String s : VOICEINPUTS)
                {
                    boolean f = false;
                    String str = s.substring(0,2);
                    for(String ss : array)
                    {
                        try {
                            if (ss.substring(0, 2).equals(str)) {
                                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                                if(s.equals("slide"))
                                {
                                    Intent slideIntent = new Intent(ControlLaser.this, ControlPage.class);
                                    startActivity(slideIntent);
                                    this.finish();
                                }else if(s.equals("laser")){
                                    Intent laserIntent = new Intent(ControlLaser.this, ControlLaser.class);
                                    startActivity(laserIntent);
                                    this.finish();
                                }else if(s.equals("pen")){
                                    Intent penIntent = new Intent(ControlLaser.this, ControlPen.class);
                                    startActivity(penIntent);
                                    this.finish();
//                                }else if(s.equals("Timer")){
//                                    Intent alarmIntent = new Intent(ControlPage.this, ControlAlarm.class);
//                                    startActivity(alarmIntent);
                                    this.finish();
//                                }else if(s.equals("Video")){
//                                    Intent playIntent = new Intent(ControlLaser.this, ControlPage.class);
//                                    startActivity(playIntent);
//                                    this.finish();
//                                }
                                }
                                found = true;
                                f = true;
                                break;
                            }
                        }catch (IndexOutOfBoundsException e)
                        {
                            //Toast.makeText(getApplicationContext(), "Please tell these words: slide, pen, laser, play, alarm", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if(f)
                        break;
                }
                if(!found)
                {
                    //Toast.makeText(getApplicationContext(), "Please tell these words: slide, pen, laser, play, alarm", Toast.LENGTH_SHORT).show();
                }
            }
            found = false;
        }


    }
    private ArrayList<String> firstWord(ArrayList<String> array)
    {
        ArrayList<String> arrayToReturn = new ArrayList<String>();
        ArrayList<String> arrayToReturn1 = new ArrayList<String>();
        for(int i = 0; i<array.size(); i++)
        {
            arrayToReturn.add(array.get(i).split(" ")[0]);
        }
        for(String str : arrayToReturn)
        {
            if(str.length() > 2)
                arrayToReturn1.add(str);
        }
        return arrayToReturn1;
    }
    private ArrayList<String> firstWord_kr(ArrayList<String> array)
    {
        ArrayList<String> arrayToReturn = new ArrayList<String>();
        for(int i = 0; i<array.size(); i++)
        {
            arrayToReturn.add(array.get(i).split(" ")[0]);
        }

        return arrayToReturn;
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
