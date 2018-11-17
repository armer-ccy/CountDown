package com.example.ccy.countdownapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	Button hour2;
	Button hour1;
	Button hour;
	Button minute2;
	Button minute1;
	Button minute;
	Button second2;
	Button second1;
	Button start;
	Button end;
	Button Alarm;

	Op op = new Op();

	String strfile = "beach.mp3";

/*	public static final int CODE_ALARM = 1;

	/**
	 * 闹钟铃声文件夹
	 * /system/media/audio/alarms   		系统闹钟铃声
	 * /sdcard/music/alarms        		用户闹钟铃声
	 * /
	private String strAlarmFolder = "/system/media/audio/alarms";
//	private String strAlarmFolder = "/sdcard/music/alarms ";*/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		hour2 = (Button) findViewById(R.id.hour2);
		hour1 = (Button) findViewById(R.id.hour1);
		hour = (Button) findViewById(R.id.hour);
		minute2 = (Button) findViewById(R.id.minute2);
		minute1 = (Button) findViewById(R.id.minute1);
		minute = (Button) findViewById(R.id.minute);
		second2 = (Button) findViewById(R.id.second2);
		second1 = (Button) findViewById(R.id.second1);
		start = (Button) findViewById(R.id.start);
		end = (Button) findViewById(R.id.end);
		Alarm = (Button) findViewById(R.id.Alarm);

		hour2.setOnClickListener(this);
		hour1.setOnClickListener(this);
		hour.setOnClickListener(this);
		minute2.setOnClickListener(this);
		minute1.setOnClickListener(this);
		minute.setOnClickListener(this);
		second2.setOnClickListener(this);
		second1.setOnClickListener(this);
		start.setOnClickListener(this);
		end.setOnClickListener(this);
		Alarm.setOnClickListener(this);

		init();

		Alarm.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				List<String> songList = new ArrayList<>();
				songList.clear();

				String str;

				str="beach.mp3";
				songList.add(str);
				str="beach1.mp3";
				songList.add(str);

				//songList.add("beach.mp3");

				final Context dialogContext = new ContextThemeWrapper(MainActivity.this,
					android.R.style.Theme_Light);

				final LayoutInflater dialogInflater = (LayoutInflater) dialogContext
					                                                       .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

				AlertDialog.Builder builder = new AlertDialog.Builder(dialogContext);
				AlertDialog alertDialog;
				builder.setTitle("设置铃声：");
				// 通过LayoutInflater来加载一个xml的布局文件作为一个View对象
				//View view1 = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_song, null);
				View view1 = dialogInflater.inflate(R.layout.activity_song, (ViewGroup) findViewById(R.id.song));
				// 设置我们自己定义的布局文件作为弹出框的Content
				builder.setView(view1);
				ListView listView = (ListView) findViewById(R.id.song_view);

				SongAdapter adapter = new SongAdapter(dialogContext, R.layout.song_item, songList);
				listView.setAdapter(adapter);
				/*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						//Toast.makeText(MainActivity.this, noteContent.getNo()+"  "+noteContent.getMome(), Toast
						// .LENGTH_SHORT).show();

					}
				});*/
				builder = new AlertDialog.Builder(dialogContext);
				builder.setView(view1);
				alertDialog = builder.create();
				alertDialog.show();
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.hour2:
				hour2.setText(op.update(hour2.getText().toString())); break;
			case R.id.hour1:
				hour1.setText(op.update(hour1.getText().toString())); break;
			case R.id.minute2:
				minute2.setText(op.update2(minute2.getText().toString())); break;
			case R.id.minute1:
				minute1.setText(op.update(minute1.getText().toString())); break;
			case R.id.second2:
				second2.setText(op.update2(second2.getText().toString())); break;
			case R.id.second1:
				second1.setText(op.update(second1.getText().toString())); break;
			case R.id.start:
				Intent intent = new Intent(MainActivity.this, ScientActivity.class);
				intent.putExtra("time", getString());
				intent.putExtra("strfile", strfile);
				startActivity(intent);
				finish();
				break;
			case R.id.end:
				init(); break;
			case R.id.Alarm:
				/*// 打开系统铃声设置
				Intent intentAlarm = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
				// 设置铃声类型和title
				intentAlarm.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE,
					RingtoneManager.TYPE_ALARM);
				intentAlarm.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE,
					"设置闹钟铃声");
				// 当设置完成之后返回到当前的Activity
				startActivityForResult(intentAlarm, CODE_ALARM);*/
				/*// TODO Auto-generated method stub
				// 打开系统铃声设置
				Intent intentAlarm = new Intent(
					RingtoneManager.ACTION_RINGTONE_PICKER);

				// 设置类型为来电
				// intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE,
				// RingtoneManager.TYPE_RINGTONE);

				// 列表中不显示"默认铃声"选项，默认是显示的
				intentAlarm.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT,
					false);

				// 列表中不显示"静音"选项，默认是显示该选项，如果默认"静音"项被用户选择，
				// 则EXTRA_RINGTONE_PICKED_URI 为null
				// intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT,false);
				intentAlarm.putExtra(RingtoneManager.EXTRA_RINGTONE_INCLUDE_DRM,
					true);

				//切换铃声时，响铃
				intentAlarm.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE,
					RingtoneManager.TYPE_ALARM);

				// 设置列表对话框的标题，不设置，默认显示"铃声"
				intentAlarm.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "设置来电铃声");
				startActivityForResult(intentAlarm, CODE_ALARM);*/
				break;
			default:
				break;
		}
	}

	/*
	/**
	 * 当设置铃声之后的回调函数
	 * /
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != RESULT_OK) {
			return;
		} else {
			// 得到我们选择的铃声,如果选择的是"静音"，那么将会返回null
			Uri uri = data
				          .getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
			Log.e("onActivityResult====", "" + uri);
			Toast.makeText(this, uri + "", Toast.LENGTH_SHORT).show();
			if (uri != null) {
				switch (requestCode) {
					case CODE_ALARM:
						RingtoneManager.setActualDefaultRingtoneUri(this,
							RingtoneManager.TYPE_RINGTONE, uri);
						break;
				}
			}
		}
	}*/

	public void init() {
		hour2.setText("0");
		hour1.setText("0");
		hour.setText(":");
		minute2.setText("0");
		minute1.setText("0");
		minute.setText(":");
		second2.setText("0");
		second1.setText("0");
	}

	public String getString() {
		return hour2.getText().toString() + hour1.getText().toString() + ":"
			       + minute2.getText().toString() + minute1.getText().toString() + ":"
			       + second2.getText().toString() + second1.getText().toString();
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
