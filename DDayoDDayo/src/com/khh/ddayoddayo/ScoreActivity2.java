package com.khh.ddayoddayo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;


//�Ѥѱ�� ��� ���Ұ�� ��Ƽ��Ƽ�Ѥ�
public class ScoreActivity2 extends Activity {
	



	TextView scoreview;
	int totalscore;
	public SoundPool soundpool;
	int score_out;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_score2);
	    


	    
	    soundpool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0); 
	    score_out = soundpool.load(this, R.raw.efs_rankshow, 1);
	    soundpool.play(score_out, 1, 1, 0, 0, 1); 
	    
	    if(MainActivity.efsonoff==true){  
			soundpool.play(score_out, 1, 1, 0, 0, 1);  
			}
	    Intent intent = getIntent();
	    totalscore = intent.getExtras().getInt("totalscore");
	    
	    scoreview = (TextView) findViewById(R.id.scoreview);
	    scoreview.setText(String.valueOf(totalscore));
    
	    findViewById(R.id.rank_okbt).setOnClickListener(new OnClickListener(){
	    	public void onClick(View view){
	    		//#Ȯ�ι�ư�� ������ �������� ���ư���
	    		finish();
	    		Stage2Activity.bgm_stage.stop();
	    		Intent intent = new Intent(ScoreActivity2.this, MainActivity.class);
	    		MainActivity.splash = false;

	    		startActivity(intent);
	    	}
	    });
   
	}
	
	
	/**
	 * Back��ư ������ �� ���̾�α� Ȯ��
	 * Ȯ�ι�ư : ���� ��Ƽ��Ƽ�� �̵�
	 * ��ҹ�ư : ���� ��Ƽ��Ƽ ����
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event){
		switch(keyCode){
		case KeyEvent.KEYCODE_BACK:
			AlertDialog.Builder alert = new AlertDialog.Builder(ScoreActivity2.this);
			alert.setMessage("�������� ���ư��ðڽ��ϱ�?").setPositiveButton("Ȯ��", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int which){
					finish();
					MainActivity.splash = false;
					Stage2Activity.bgm_stage.stop();
					Intent intent = new Intent(ScoreActivity2.this, MainActivity.class);
					startActivity(intent);
				}
			}).setNegativeButton("���", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.cancel();
				}
			});
			AlertDialog backdialog = alert.create();
			backdialog.show();
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

}
