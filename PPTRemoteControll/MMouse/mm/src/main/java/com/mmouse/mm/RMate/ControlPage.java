package com.mmouse.mm.RMate;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Objects;
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
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.speech.RecognizerIntent;
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
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mmouse.mm.connect.osc.OSCMessage;
import com.mmouse.mm.connect.osc.OSCPort;
import com.mmouse.mm.connect.osc.OSCPortOut;

public class ControlPage extends Activity implements View.OnClickListener{
    //
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

    /* slide menu */
    private DisplayMetrics metrics;
    private LinearLayout ll_mainLayout;
    private LinearLayout ll_menuLayout;
    private FrameLayout.LayoutParams leftMenuLayoutPrams;
    private int leftMenuWidth;
    private static boolean isLeftExpanded;
    private ImageButton bt_left, currentslide, slideMenu, penMenu, laserMenu, playMenu, alarmMenu, settingMenu, exitMenu ;
    private ImageButton prevBtn, nextBtn;
    private Drawable prevBtnAlpha, nextBtnAlpha;

    public boolean volume = false;


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
    private CoordinateSpace lastSpace;
    private CoordinateSpace currSpace;
    // toggles
    private boolean toggleButton = false;
    // multitouch scroll
    // private float scrollX = 0f;
    private float scrollY = 0f;



    public ControlPage() {
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
//            case R.id.playMenu:
////                Intent intentPlay = new Intent(this, ControlPlay.class);
////                this.startActivity(intentPlay);
////                this.finish();
//                Object[] args3 = new Object[1];
//                args3[0]=1;
//                OSCMessage msg3 = new OSCMessage("/playbutton",args3);
//                try {
//                    this.sender.send(msg3);
//                } catch (Exception ex) {
//                    //Toast.makeText(getApplicationContext(), "play5", Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, ex.toString());
//                }
//                break;

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
            //

            try {
                //
                setContentView(R.layout.activity_control_page);
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

            /** 버튼 id 받아오기 & 이벤트 연결**/
            //상단메뉴
            bt_left = (ImageButton) findViewById(R.id.bt_left);
            bt_left.setOnClickListener(this);
            nextBtn = (ImageButton) findViewById(R.id.nextBtn);
            prevBtn = (ImageButton) findViewById(R.id.prevBtn);

            View.OnClickListener gotoListener = new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder gotoBuilder = new AlertDialog.Builder(ControlPage.this);
                    gotoBuilder.setTitle("Go to select page");
                    gotoBuilder.setMessage("insert select page number");

                    final EditText input = new EditText(ControlPage.this);
                    gotoBuilder.setView(input);

                    gotoBuilder.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    String inputPage = input.getEditableText().toString();
                                    onGotoBtn(inputPage);
                                }
                            }).setNegativeButton("Close",
                            new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int which){
                                    dialog.cancel();
                                }
                            });
                    gotoBuilder.show();
                }
            };
            ImageButton gotoBtn = (ImageButton) findViewById(R.id.go_toBtn);
            gotoBtn.setOnClickListener(gotoListener);


            View.OnClickListener volumeListener = new View.OnClickListener(){
                @Override
                public void onClick(View view) {

                    if(volume==false){
                        Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();

                        volume = true;

                        prevBtn.setEnabled(false);
                        nextBtn.setEnabled(false);
                        prevBtnAlpha.setAlpha(70);
                        nextBtnAlpha.setAlpha(70);

                    }
                    else if(volume==true){
                        Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_SHORT).show();
                        volume = false;

                        prevBtn.setEnabled(true);
                        nextBtn.setEnabled(true);
                        prevBtnAlpha.setAlpha(255);
                        nextBtnAlpha.setAlpha(255);

                    }

                }
            };
            ImageButton volumeBtn = (ImageButton) findViewById(R.id.volumBtn);
            volumeBtn.setOnClickListener(volumeListener);

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

            //이전, 다음
            View.OnClickListener prevListener = new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    onPrevBtn();
                }
            };

            prevBtn.setOnClickListener(prevListener);
            prevBtnAlpha = prevBtn.getBackground();

            View.OnClickListener nextListener = new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    onNextBtn();
                }
            };

            nextBtn.setOnClickListener(nextListener);
            nextBtnAlpha = nextBtn.getBackground();

        }
    }


    private void onPrefs() {
        Intent i = new Intent(ControlPage.this, PrefsActivity.class);
        this.startActivity(i);
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

    public boolean onKeyDown(int keycode, KeyEvent ev) {
        switch (keycode){
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if(volume == true){
                    onNextBtn();
                }
                else
                    Toast.makeText(getApplicationContext(), "Click the Volume Button", Toast.LENGTH_SHORT).show();
                break;

            case KeyEvent.KEYCODE_VOLUME_UP:
                if(volume == true){
                    onPrevBtn();
                }
                else
                    Toast.makeText(getApplicationContext(), "Click the Volume Button", Toast.LENGTH_SHORT).show();
                break;

            case KeyEvent.KEYCODE_BACK:
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
                break;
        }
        return true;
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

    private void onGotoBtn(String inputPage){
        Object[] args = new Object[1];
        args[0] = inputPage;
        //입력받은 선택페이지 값 넣기
        OSCMessage msg = new OSCMessage("/gotobutton", args);
        Toast.makeText(getApplicationContext(), inputPage, Toast.LENGTH_SHORT).show();
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
                            Intent slideIntent = new Intent(ControlPage.this, ControlPage.class);
                            startActivity(slideIntent);
                            this.finish();
                        }else if(s.equals("laser")){
                            Intent laserIntent = new Intent(ControlPage.this, ControlLaser.class);
                            startActivity(laserIntent);
                            this.finish();
                        }else if(s.equals("pen")){
                            Intent penIntent = new Intent(ControlPage.this, ControlPen.class);
                            startActivity(penIntent);
                            this.finish();
//                        }else if(s.equals("alarm")){
//                            Intent alarmIntent = new Intent(ControlPage.this, ControlAlarm.class);
//                            startActivity(alarmIntent);
//                            this.finish();
//                        }else if(s.equals("play")){
//                            Intent playIntent = new Intent(ControlPage.this, ControlPage.class);
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
                                    Intent slideIntent = new Intent(ControlPage.this, ControlPage.class);
                                    startActivity(slideIntent);
                                    this.finish();
                                }else if(s.equals("laser")){
                                    Intent laserIntent = new Intent(ControlPage.this, ControlLaser.class);
                                    startActivity(laserIntent);
                                    this.finish();
                                }else if(s.equals("pen")){
                                    Intent penIntent = new Intent(ControlPage.this, ControlPen.class);
                                    startActivity(penIntent);
                                    this.finish();
//                                }else if(s.equals("Timer")){
//                                    Intent alarmIntent = new Intent(ControlPage.this, ControlAlarm.class);
//                                    startActivity(alarmIntent);
                                    this.finish();
//                                }else if(s.equals("Video")){
//                                    Intent playIntent = new Intent(ControlPage.this, ControlPage.class);
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
}


