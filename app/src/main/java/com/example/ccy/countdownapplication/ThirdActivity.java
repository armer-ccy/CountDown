package com.example.ccy.countdownapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {

	Button go;
	Button end;

	EditText time;

	String time_real;
	String time_display;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pause);
		time = (EditText) findViewById(R.id.time);
		time.clearFocus();//失去焦点，不弹出键盘
		//time.setCursorVisible(false);

		go = (Button) findViewById(R.id.go);
		end = (Button) findViewById(R.id.end);

		go.setOnClickListener(this);
		end.setOnClickListener(this);

		Intent intent = getIntent();
		time_real = intent.getStringExtra("time");

		int hour = (time_real.charAt(0) - '0') * 10 + (time_real.charAt(1) - '0');
		int minute = (time_real.charAt(3) - '0') * 10 + (time_real.charAt(4) - '0');
		int second = (time_real.charAt(6) - '0') * 10 + (time_real.charAt(7) - '0');
		time_display = (hour / 10) + " " + (hour % 10) + " " + ":" + " "
			               + (minute / 10) + " " + (minute % 10) + " " + ":" + " "
			               + (second / 10) + " " + (second % 10);
		time.setText(time_display + "");
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.go:
				Intent intent = new Intent(ThirdActivity.this, ScientActivity.class);
				intent.putExtra("time", time_real);
				startActivity(intent);
				finish();
				break;
			case R.id.end:
				Intent intent2 = new Intent(ThirdActivity.this, MainActivity.class);
				startActivity(intent2);
				finish();
				break;
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
