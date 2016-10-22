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
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mmouse.mm.connect.osc.OSCMessage;
import com.mmouse.mm.connect.osc.OSCPort;
import com.mmouse.mm.connect.osc.OSCPortOut;
import com.mmouse.mm.RMate.ControlPen.PanelConfig.PanelItem;


/**
 *
 * @author jsera
 *
 *         <pre>
 *         TODO:
 *         trackbutton + mouse click toggles the mouse button to enable click and drag
 *         add scroll wheel
 *         add port selection text box on front page
 *         add back button. Make it go back to the IP connect page
 * </pre>
 */

public class ControlPen extends Activity implements View.OnClickListener{
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
    private static final String TAG = "RemoteDroid";
    //
    private OSCPortOut sender;
    // thread and graphics stuff

    private FrameLayout flAdvancedPanel;
    private int advancedPanelHeight = 72;
    private String advancedPanelConfig = "Play{icon:bitblt_halpha16('" +
            "0000000000FF00000000000000000000" +
            "0000000000FFFF000000000000000000" +
            "0000000000FFFFFF0000000000000000" +
            "0000000000FFFFFFFF00000000000000" +
            "0000000000FFFFFFFFFF000000000000" +
            "0000000000FFFFFFFFFFFF0000000000" +
            "0000000000FFFFFFFFFFFFFF00000000" +
            "0000000000FFFFFFFFFFFFFFFF000000" +
            "0000000000FFFFFFFFFFFFFF77000000" +
            "0000000000FFFFFFFFFFFF7733000000" +
            "0000000000FFFFFFFFFF773300000000" +
            "0000000000FFFFFFFF77330000000000" +
            "0000000000FFFFFF7733000000000000" +
            "0000000000FFFF773300000000000000" +
            "0000000000FF77330000000000000000" +
            "00000000007733000000000000000000');" +
            "command:key_code(162,162,162);" +
            "}\r\n" +
            "Next{icon:bitblt_halpha16('" +
            "0000BBFF0000FF000000000000000000" +
            "0000BBFF5500FFFF0000000000000000" +
            "0000BBFF5500FFFFFF00000000000000" +
            "0000BBFF5500FFFFFFFF000000000000" +
            "0000BBFF5500FFFFFFFFFF0000000000" +
            "0000BBFF5500FFFFFFFFFFFF00000000" +
            "0000BBFF5500FFFFFFFFFFFFFF000000" +
            "0000BBFF5500FFFFFFFFFFFFFFFF0000" +
            "0000BBFF5500FFFFFFFFFFFFFF770000" +
            "0000BBFF5500FFFFFFFFFFFF77330000" +
            "0000BBFF5500FFFFFFFFFF7733000000" +
            "0000BBFF5500FFFFFFFF773300000000" +
            "0000BBFF5500FFFFFF77330000000000" +
            "0000BBFF5500FFFF7733000000000000" +
            "0000BBFF5500FF773300000000000000" +
            "00000033550077330000000000000000');" +
            "command:key_code(162,162,162);" +
            "}" +
            "Ctrl{icon:bitblt_halpha16('" +
            "0000BBFF0000FF000000000000000000" +
            "0000BBFF5500FFFF0000000000000000" +
            "0000BBFF5500FFFFFF00000000000000" +
            "0000BBFF5500FFFFFFFF000000000000" +
            "0000BBFF5500FFFFFFFFFF0000000000" +
            "0000BBFF5500FFFFFFFFFFFF00000000" +
            "0000BBFF5500FFFFFFFFFFFFFF000000" +
            "0000BBFF5500FFFFFFFFFFFFFFFF0000" +
            "0000BBFF5500FFFFFFFFFFFFFF770000" +
            "0000BBFF5500FFFFFFFFFFFF77330000" +
            "0000BBFF5500FFFFFFFFFF7733000000" +
            "0000BBFF5500FFFFFFFF773300000000" +
            "0000BBFF5500FFFFFF77330000000000" +
            "0000BBFF5500FFFF7733000000000000" +
            "0000BBFF5500FF773300000000000000" +
            "00000033550077330000000000000000');" +
            "command:key_code(162,162,162);" +
            "}";



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

    /* slide menu */
    private DisplayMetrics metrics;
    private LinearLayout ll_mainLayout;
    private LinearLayout ll_menuLayout;
    private FrameLayout.LayoutParams leftMenuLayoutPrams;
    private int leftMenuWidth;
    private static boolean isLeftExpanded;
    private ImageButton bt_left, currentslide, slideMenu, penMenu, laserMenu, playMenu, alarmMenu, settingMenu, exitMenu;
    private ImageButton mouseBtn, penBtn, eraserBtn ;


    /**
     * Cached multitouch information
     */
    private boolean mIsMultitouchEnabled;
    public ControlPen() {
        super();
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

        currentslide.setOnClickListener(this);
        slideMenu.setOnClickListener(this);
        penMenu.setOnClickListener(this);
        laserMenu.setOnClickListener(this);
        //playMenu.setOnClickListener(this);
        alarmMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_left:
                Toast.makeText(getApplicationContext(), "tdfad", Toast.LENGTH_SHORT).show();
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
                changeMouse();
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
                changeMouse();
                break;
            // 동영상 재생
//            case R.id.playMenu:
//                changeMouse();
//                break;
            // 알람
            case R.id.alarmMenu:
                changeMouse();
                Intent intentAlarm = new Intent(this, ControlAlarm.class);
                this.startActivity(intentAlarm);
                this.finish();

                break;

            // 슬라이드 종료
            case R.id.exitMenu:
                changeMouse();
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

            //마우스모드
            case R.id.mouseBtn:
                changeMouse();
                break;
            //펜모드
            case R.id.penBtn:
                changePen();
                break;
            //지우개
            case R.id.eraserBtn:
                eraserPen();
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
            //FrameLayout viewGroup = (FrameLayout) findViewById(R.id.ll_fragment).getParent();
            //enableDisableViewGroup(viewGroup, false);

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

            // disable empty view
            ((LinearLayout) findViewById(R.id.ll_empty))
                    .setVisibility(View.GONE);
            findViewById(R.id.ll_empty).setEnabled(false);

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
            this.mag = new Point3D();
            this.lastSpace = new CoordinateSpace();
            this.currSpace = new CoordinateSpace();

            // UI runnables

            this.getWindow().setFlags(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN,
                    WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        }
        //

        try {

            //

            setContentView(R.layout.activity_control_pen);

            DisplayMetrics dm = new DisplayMetrics();

            this.getWindowManager().getDefaultDisplay().getMetrics(dm);

            //

            this.sender = new OSCPortOut(InetAddress.getByName(Settings.ip), OSCPort.defaultSCOSCPort());

            //

            this.initTouchpad();
            this.changePen();




        } catch (Exception ex) {

            Log.d(TAG, ex.toString());

        }

        mouseBtn = (ImageButton) findViewById(R.id.mouseBtn);
        penBtn = (ImageButton) findViewById(R.id.penBtn);
        eraserBtn = (ImageButton) findViewById(R.id.eraserBtn);
        mouseBtn.setOnClickListener(this);
        penBtn.setOnClickListener(this);
        eraserBtn.setOnClickListener(this);

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



    }

    private void onAdvancedToggle() {



        if(flAdvancedPanel.getHeight()<10){

            android.view.ViewGroup.LayoutParams lp = flAdvancedPanel

                    .getLayoutParams();

            lp.height = advancedPanelHeight;

            flAdvancedPanel.setLayoutParams(lp);





            PanelConfig pc = new PanelConfig(advancedPanelConfig);

            for (PanelItem item : pc.PanelItems)

            {

                Bitmap bd = Bitmap.createScaledBitmap(item.icon, 48, 48, true);

                ImageButton ib = new ImageButton(getApplicationContext());

                ib.setImageBitmap(bd);

            }



        }else{

            android.view.ViewGroup.LayoutParams lp = flAdvancedPanel.getLayoutParams();

            lp.height=0;

            flAdvancedPanel.setLayoutParams(lp);

        }

    }



    @SuppressWarnings("unused")

    private void onPrefs() {

        Intent i = new Intent(ControlPen.this, PrefsActivity.class);

        this.startActivity(i);

    }






    private void initTouchpad() {

        FrameLayout fl = (FrameLayout) this.findViewById(R.id.TouchPad);



        // let's set up a touch listener

        fl.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent ev) {

                return onMouseMove(ev);

            }

        });

    }








    public void onStart() {

        super.onStart();

    }



    public void onResume() {
        super.onResume();
        // acquire screen lock
        this.lock.acquire();
    }



    public void onPause() {
        super.onPause();
        // this'd be a great time to disconnect from the server, and clean
        // up anything that needs to be cleaned up.
        // release screen lock
        this.lock.release();
    }



    public void onStop() {
        super.onStop();
    }

    public void onDestroy() {
        super.onDestroy();
        this.sender.close();
    }


    // mouse events

    boolean scrollTag = false;

    int scrollCount = 0;

    int rightClickAllowance = 1; //scroll iterations before skipping Right Click and doing scroll instead in two touch right click mode

    private boolean onMouseMove(MotionEvent ev) {

        int type = 0;
        float xMove = 0f;
        float yMove = 0f;

        int pointerCount = 1;

        if (mIsMultitouchEnabled) {

            pointerCount = WrappedMotionEvent.getPointerCount(ev);

        }

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                //
                //
                type = 0;
                xMove = ev.getX();
                yMove = ev.getY();
                //
                //this.xHistory = ev.getX();
                //this.yHistory = ev.getY();
                this.sendMouseEvent(type, xMove, yMove);

                //
                break;
            case MotionEvent.ACTION_UP:
                type = 1;
                xMove = ev.getX();
                yMove = ev.getY();
                //scrollX= 0;
                scrollY = 0;
                scrollTag = false; //clear multi-touch event
                this.sendMouseEvent(type, xMove, yMove);

                break;
            case MotionEvent.ACTION_MOVE:
                if (pointerCount == 1) {
                    // move
                    type = 2;
                    if (lastPointerCount == 1) {
                        xMove = ev.getX() - this.xHistory;
                        yMove = ev.getY() - this.yHistory;
                    }
                    this.xHistory = ev.getX();
                    this.yHistory = ev.getY();
                } else if (pointerCount == 2) {
                    // multi-touch scroll
                    type = -1;
                    int pointer0 = WrappedMotionEvent.getPointerId(ev, 0);
                    int pointer1 = WrappedMotionEvent.getPointerId(ev, 1);
                    // float posX = WrappedMotionEvent.getX(ev, pointer0);
                    float posY = WrappedMotionEvent.getY(ev, pointer0);
                    // only consider the second pointer if I had a previous history
                    if (lastPointerCount == 2) {
                        // posX += WrappedMotionEvent.getX(ev, pointer1);
                        // posX /= 2;
                        posY += WrappedMotionEvent.getY(ev, pointer1);
                        posY /= 2;
                        // xMove = posX - this.xHistory;
                        yMove = posY - this.yHistory;
                    } else {
                        // xMove = posX - this.xHistory;
                        yMove = posY - this.yHistory;
                        // posX += WrappedMotionEvent.getX(ev, pointer1);
                        // posX /= 2;
                        posY += WrappedMotionEvent.getY(ev, pointer1);
                        posY /= 2;
                    }
                    // this.xHistory = posX;
                    this.yHistory = posY;
                }
                break;
        }
        if (type == -1) {
            // scrollX += xMove;
            scrollY += yMove;
            int dir = 0;
            // if (Math.abs(scrollX) > SCROLL_STEP) {
            // // can't deal with X scrolling yet
            // scrollX = 0f;
            // }
            if (Math.abs(scrollY) > mScrollStep) {
                if (scrollY > 0f) {
                    dir = 1;
                } else {
                    dir = -1;
                }
                if (Settings.scrollInverted) {
                    dir = -dir;
                }
                scrollY = 0f;
            }
            if(scrollTag==true) scrollCount++;
            else scrollCount = 0;
            scrollTag = true; //flag multi touch state for next up event
            if(Settings.twoTouchRightClick == true){ //if two finger right click is enabled we need to delay scrolling (1 iterations)
                if(dir!=0 && scrollCount > rightClickAllowance) { this.sendScrollEvent(dir); }//lets only send scroll events if there is distance to scroll
            } else {
                if(dir!=0) this.sendScrollEvent(dir); //lets only send scroll events if there is distance to scroll
            }
        } else if (type == 2) {
            // if type is 0 or 1, the server will not do anything with it, so we
            // only send type 2 events
            this.sendMouseEvent(type, xMove, yMove);
        }
        lastPointerCount = pointerCount;
        return true;
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

        Object[] args2 = new Object[1];


        if(type == 0){
            OSCMessage msg = new OSCMessage("/mouse", args);

            try {
                this.sender.send(msg);
            } catch (Exception ex) {
                Log.d(TAG, ex.toString());
            }
        }else if(type == 1){
            args2[0] = 1;
            OSCMessage msg2 = new OSCMessage("/leftbutton", args2);
            OSCMessage msg = new OSCMessage("/mouse", args);

            try {
                this.sender.send(msg);
                this.sender.send(msg2);
            } catch (Exception ex) {
                Log.d(TAG, ex.toString());
            }
        }else {
            args2[0] = 0;
            OSCMessage msg2 = new OSCMessage("/leftbutton", args2);
            OSCMessage msg = new OSCMessage("/mouse", args);

            try {
                this.sender.send(msg);
                this.sender.send(msg2);
            } catch (Exception ex) {
                Log.d(TAG, ex.toString());
            }
        }
    }
    private void sendScrollEvent(int dir) {
        Object[] args = new Object[1];
        args[0] = dir;
        //
        OSCMessage msg = new OSCMessage("/wheel", args);
        try {
            this.sender.send(msg);
        } catch (Exception ex) {
            Log.d(TAG, ex.toString());
        }
    }
    private boolean onLeftTouch(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                moveMouseWithSecondFinger(ev);
                break;
        }
        //
        return true;
    }

    /**
     * Used to move the mouse with the second finger when one of the mouse
     * buttons are pressed on the UI.
     *
     * @param ev
     */

    private void moveMouseWithSecondFinger(MotionEvent ev) {
        if (!mIsMultitouchEnabled) {
            return;
        }
        int pointerCount = WrappedMotionEvent.getPointerCount(ev);
        // if it is a multitouch move event
        if (pointerCount == 2) {
            // int pointer0 = ev.getPointerId(0);
            int pointer1 = WrappedMotionEvent.getPointerId(ev, 1);
            float x = WrappedMotionEvent.getX(ev, pointer1);
            float y = WrappedMotionEvent.getY(ev, pointer1);
            if (lastPointerCount == 2) {
                float xMove = x - this.xHistory;
                float yMove = y - this.yHistory;
                this.sendMouseEvent(2, xMove, yMove);
            }
            this.xHistory = x;
            this.yHistory = y;
        }
        lastPointerCount = pointerCount;
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

    public void changePen(){
        Object[] args = new Object[1];
        args[0] = 1;
        OSCMessage msg = new OSCMessage("/changePen", args);
        try {
            this.sender.send(msg);
        } catch (Exception ex) {
            Log.d(TAG, ex.toString());
        }
    }

    public void cancellatePen() {
        Object[] args = new Object[1];
        args[0] = 1;
        OSCMessage msg = new OSCMessage("/leftbutton", args);
        OSCMessage msg1 = new OSCMessage("/leftbutton", args);
        try {
            this.sender.send(msg);
        } catch (Exception ex) {
            Log.d(TAG, ex.toString());
        }
    }

    public void eraserPen(){
        Object[] args = new Object[1];
        args[0] = 0;
        OSCMessage msg = new OSCMessage("/erasePen", args);
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
                            Intent slideIntent = new Intent(ControlPen.this, ControlPage.class);
                            startActivity(slideIntent);
                            this.finish();
                        }else if(s.equals("laser")){
                            Intent laserIntent = new Intent(ControlPen.this, ControlLaser.class);
                            startActivity(laserIntent);
                            this.finish();
                        }else if(s.equals("pen")){
                            Intent penIntent = new Intent(ControlPen.this, ControlPen.class);
                            startActivity(penIntent);
                            this.finish();
//                        }else if(s.equals("alarm")){
//                            Intent alarmIntent = new Intent(ControlPage.this, ControlAlarm.class);
//                            startActivity(alarmIntent);
//                            this.finish();
//                        }else if(s.equals("play")){
//                            Intent playIntent = new Intent(ControlPen.this, ControlPage.class);
//                            startActivity(playIntent);
//                            this.finish();
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
                                    Intent slideIntent = new Intent(ControlPen.this, ControlPage.class);
                                    startActivity(slideIntent);
                                    this.finish();
                                }else if(s.equals("laser")){
                                    Intent laserIntent = new Intent(ControlPen.this, ControlLaser.class);
                                    startActivity(laserIntent);
                                    this.finish();
                                }else if(s.equals("pen")){
                                    Intent penIntent = new Intent(ControlPen.this, ControlPen.class);
                                    startActivity(penIntent);
                                    this.finish();
//                                }else if(s.equals("Timer")){
//                                    Intent alarmIntent = new Intent(ControlPage.this, ControlAlarm.class);
//                                    startActivity(alarmIntent);
                                    this.finish();
//                                }else if(s.equals("Video")){
//                                    Intent playIntent = new Intent(ControlPen.this, ControlPage.class);
//                                    startActivity(playIntent);
//                                    this.finish();
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

}


