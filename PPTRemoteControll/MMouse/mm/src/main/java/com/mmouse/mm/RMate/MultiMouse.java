package com.mmouse.mm.RMate;

        import java.net.InetAddress;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.LinkedList;
        import java.util.List;
        import java.util.Map;

        import android.app.Activity;
        import android.content.Intent;
        import android.graphics.drawable.Drawable;
        import android.os.Build;
        import android.os.Bundle;
        import android.os.StrictMode;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.Window;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.ListView;
        import android.widget.SimpleAdapter;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.mmouse.mm.connect.osc.OSCMessage;
        import com.mmouse.mm.connect.osc.OSCPort;
        import com.mmouse.mm.connect.osc.OSCPortOut;

/*
 * To-do
 *
 * DNS lookup
 * arrow keys, esc, win key
 */

public class MultiMouse extends Activity {
    private static final String TAG = "MultiMouse";
    // menu item(s)
    public static final int MENU_PREFS = 0;
    public static final int MENU_HELP = 1;


    //
    private EditText tbIp;
    //
    private ListView mHostlist;

    //연결, 시작 버튼
    ImageButton but;
    ImageButton startshow;
    Drawable startshowAlpha;
    private OSCPortOut sender;

    public MultiMouse() {
        super();
    }

    /** Called when the activity is first created. */
    @Override
    //시작합니당 두둥두둥
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Settings.init(this.getApplicationContext());

        requestWindowFeature(Window.FEATURE_NO_TITLE); // added to save screen space, the Title was shown twice, in Standard Android bar, then below in Bolder larger text, this gets rid of the standard android bar


        // set some listeners

        startActivity(new Intent(this,SplashActivity.class));

        String pak = this.getPackageName();
        System.out.println(pak);

        try{
            // 메인 화면 두둥
            setContentView(R.layout.activity_multimouse);
            this.sender = new OSCPortOut(InetAddress.getByName(Settings.ip), OSCPort.defaultSCOSCPort());
        }catch(Exception ex){
            Log.d(TAG, ex.toString());
        }
        // StrictMode는 진저브래드 부터 지원이 가능하니 버전을 체크해주고
        // 메인액티비티에서 네트워크부분을 건드리려고 하면 예외가 발생하는 경우가 있으므로
        // 안드로이드의 Policy를 잠시 풀어준다.
        if(Build.VERSION.SDK_INT>=9){
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
            .detectDiskReads()
            .detectNetwork()
            .detectDiskWrites()
            .penaltyLog()
            .build());
        }
        // activity_main에 있는 id가 btnConnect인 버튼 객체를 가져옴
        but = (ImageButton)this.findViewById(R.id.btnConnect);
        // but 버튼이 눌렸을 때 onConnectButton 이벤트가 호출.
        but.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onConnectButton();
            }
        });

        startshow = (ImageButton)this.findViewById(R.id.startshow);
        startshow.setEnabled(false);
        startshow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onStartShowButton();
            }
        });

        startshowAlpha = startshow.getBackground();
        startshowAlpha.setAlpha(70);

        // check SharedPreferences for IP
        Settings.init(this.getApplicationContext());

        // 최근 접속한 호스트들을 보여줌
        mHostlist = (ListView) findViewById(R.id.lvHosts);
        populateHostList();

        this.tbIp = (EditText)this.findViewById(R.id.etIp);
        if (Settings.ip != null) {
            this.tbIp.setText(Settings.ip);
        }

        // goes to onStart
    }

    private void populateHostList() {
        // populates the host list with saved hosts from the settings
        LinkedList<String> ips = Settings.savedHosts;
        String[] from = new String[]{"hostip"};
        int[] to = new int[]{R.id.hostEntry};
        List<Map<String,String>> data = new ArrayList<Map<String, String>>();
        for (String s:ips){
            Map<String, String> map = new HashMap<String, String>();
            map.put("hostip", s);
            data.add(map);
        }

        /**
         * in order to be able to hook up the onclick listeners for
         * the children of the simple adapter, we have to override the
         * getView() method to set the listeners we want, so callbacks
         * can function correctly
         */

        SimpleAdapter adapter = new SimpleAdapter(this,data,R.layout.savedhost,from,to){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View v = super.getView(position, convertView, parent);
                TextView text = (TextView) v.findViewById(R.id.hostEntry);
                final CharSequence str = text.getText();
                ImageButton b = (ImageButton) v.findViewById(R.id.hostbutton);

                // set the listener for clicking on the text
                text.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        onSavedHost(str);
                    }
                });

                // set the listener for clicking on the delete button
                b.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        onRemoveSavedHost(str);
                    }
                });

                return v;
            }
        };

        mHostlist.setAdapter(adapter);

    }


    /** OS kills process */
    public void onDestroy() {
        super.onDestroy();
        this.sender.close();
    }

    /** App starts anything it needs to start */
    public void onStart() {
        super.onStart();
    }

    /** App kills anything it started */
    public void onStop() {
        super.onStop();
    }

    /** App starts displaying things */
    public void onResume() {
        super.onResume();
    }


    /** App goes into background */
    public void onPause() {
        super.onPause();
    }

    // menu

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        //
        menu.add(0, MENU_PREFS, 0, R.string.txt_preferences).setShortcut('0', 'p').setIcon(R.drawable.setting);

        //
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //
        switch (item.getItemId()) {
            case MENU_PREFS:
                //
                this.onPrefs();
                break;
        }
        //
        return super.onOptionsItemSelected(item);
    }

    // 버튼이 눌렸다 야호 접속하자 우왕우왕
    private void onConnectButton() {
        // text박스에 입력한 내용을 String변수 ip에 저장
        String ip = this.tbIp.getText().toString();
        // ip 포맷 확인하기, 0부터 9까지이고 1~4글자
        if (ip.matches("^[0-9]{1,4}\\.[0-9]{1,4}\\.[0-9]{1,4}\\.[0-9]{1,4}$")) {
            // ip 포맷이 맞을 경우
            try {
                // Settings.java의 setIp에 매개변수 ip값을 넘겨줌
                Settings.setIp(ip);

                startshow.setEnabled(true);
                startshowAlpha.setAlpha(255);


                /*
                // PadActivity로 이동
                Intent i = new Intent(this, ControlPage.class);
                this.startActivity(i);
                this.finish();
                */
            }
            // ip 포맷이 아닐 경우
            catch (Exception ex) {
                //this.tvError.setText("Invalid IP address");
                //this.tvError.setVisibility(View.VISIBLE);
                Toast.makeText(this, this.getResources().getText(R.string.toast_invalidIP), Toast.LENGTH_LONG).show();
                Log.d(TAG, ex.toString());
            }
        } else {
            //this.tvError.setText("Invalid IP address");
            //this.tvError.setVisibility(View.VISIBLE);
            Toast.makeText(this, this.getResources().getText(R.string.toast_invalidIP), Toast.LENGTH_LONG).show();
        }
    }

    private void onStartShowButton(){
        this.StartShow();
        Intent i = new Intent(this, ControlPage.class);
        this.startActivity(i);
        this.finish();
    }


    private void onRemoveSavedHost(CharSequence str) {
        try {
            Settings.removeSavedHost(str);
            populateHostList();
            // TODO: we should be able to just call _below_ to update the view
            // however, the DataSetObserver is locked onto a copy of the data,
            // not a reference (from Settings). This needs to be changed...
            //((SimpleAdapter)mHostlist.getAdapter()).notifyDataSetChanged();

        } catch (Exception e) {
            Log.d(TAG,"couldnt remove "+str.toString()+" from list: "+e.toString());
        }

    }

    private void onSavedHost(CharSequence s) {
        try {
            tbIp.setText(s);
        } catch (Exception e) {
            Log.d(TAG,e.toString());
        }

    }

    private void onPrefs() {
        Intent i = new Intent(MultiMouse.this, PrefsActivity.class);
        this.startActivity(i);
    }


    private void StartShow(){
        Toast.makeText(getApplicationContext(), "startshow들어옴", Toast.LENGTH_SHORT).show();
        Object[] args = new Object[1];
        args[0] = 0;
        OSCMessage msg = new OSCMessage("/startshow", args);
        try {
            this.sender.send(msg);
        } catch (Exception ex) {
            Log.d(TAG, ex.toString());
        }
    }


}