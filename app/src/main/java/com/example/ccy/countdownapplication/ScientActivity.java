package com.example.ccy.countdownapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ScientActivity extends AppCompatActivity implements View.OnClickListener {

	public static final int UPDATE_TEXT = 1;
	public static final int SHAKE_TEXT = 2;

	Vibrator vibrator;

	Button pause;
	Button end;

	EditText time;

	String time_real;
	String time_display;
	String strfile;

	Boolean BTimeThread=true;

	Op op=new Op();

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch(msg.what) {
				case UPDATE_TEXT:
					//在这里可以进行UI操作
					time.setText(time_display);
					break;
				case SHAKE_TEXT:
					time.setText(time_display);
					vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
					long[] pattern = {800, 200}; // 停止 开启
					//第二个参数表示使用pattern第几个参数作为震动时间重复震动，如果是-1就震动一次
					vibrator.vibrate(pattern, -1);

					//op.song(strfile);

				default:
					break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

		time = (EditText) findViewById(R.id.time);
		time.clearFocus();//失去焦点，不弹出键盘
		//time.setCursorVisible(false);

		pause = (Button) findViewById(R.id.pause);
		end = (Button) findViewById(R.id.end);

		pause.setOnClickListener(this);
		end.setOnClickListener(this);

		Intent intent = getIntent();
		time_real = intent.getStringExtra("time");
		strfile = intent.getStringExtra("strfile");

		int hour = (time_real.charAt(0) - '0') * 10 + (time_real.charAt(1) - '0');
		int minute = (time_real.charAt(3) - '0') * 10 + (time_real.charAt(4) - '0');
		int second = (time_real.charAt(6) - '0') * 10 + (time_real.charAt(7) - '0');
		time_display = (hour / 10) + " " + (hour % 10) + " " + ":" + " "
			               + (minute / 10) + " " + (minute % 10) + " " + ":" + " "
			               + (second / 10) + " " + (second % 10);
		time.setText(time_display + "");

		new TimeThread().start();
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(ScientActivity.this, MainActivity.class);
		Intent intent2 = new Intent(ScientActivity.this, ThirdActivity.class);
		switch(v.getId()) {
			case R.id.pause:
				BTimeThread=false;
				if(!"00:00:00".equals(time_real))
				{
					intent2.putExtra("time", time_real);
					startActivity(intent2);
					finish();
				}
				else{
					startActivity(intent);
					finish();
				}
				break;
			case R.id.end:
				BTimeThread=false;
				startActivity(intent);
				finish();
				break;
		}
	}

	//开一个线程继承Thread
	public class TimeThread extends Thread {
		//重写run方法
		@Override
		public void run() {
			super.run();
			while(BTimeThread&&!"00:00:00".equals(time_real)) {
				int hour = (time_real.charAt(0) - '0') * 10 + (time_real.charAt(1) - '0');
				int minute = (time_real.charAt(3) - '0') * 10 + (time_real.charAt(4) - '0');
				int second = (time_real.charAt(6) - '0') * 10 + (time_real.charAt(7) - '0');
				try {
					//每隔一秒
					Thread.sleep(1000);
					if(second - 1 >= 0) second--;
					else {
						if(minute > 0 || hour > 0) second = 59;
						if(minute - 1 >= 0) minute--;
						else if(hour > 0){
							minute = 59;
							hour--;
						}
					}
					time_real = "" + (hour / 10) + (hour % 10) + ":"
						            + (minute / 10) + (minute % 10) + ":"
						            + (second / 10) + (second % 10);
					time_display = (hour / 10) + " " + (hour % 10) + " " + ":" + " "
						               + (minute / 10) + " " + (minute % 10) + " " + ":" + " "
						               + (second / 10) + " " + (second % 10);
					Message message = new Message();
					message.what = UPDATE_TEXT;
					handler.sendMessage(message);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			while(BTimeThread&&"00:00:00".equals(time_real)) {
				try {
					Message message = new Message();
					message.what = SHAKE_TEXT;
					handler.sendMessage(message);
					Thread.sleep(1000);
				} catch(InterruptedException e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			moveTaskToBack(true);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
