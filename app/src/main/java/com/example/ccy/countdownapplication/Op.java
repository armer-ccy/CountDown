package com.example.ccy.countdownapplication;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;

public class Op extends Activity {
	private MediaPlayer mediaPlayer;//MediaPlayer对象
	private boolean isPause = false;//是否暂停
	private File file;//要播放的文件
	private String strflice;

	public String update2(String data) {
		if(Integer.parseInt(data) + 1 > 5) data = "0";
		else data = (Integer.parseInt(data) + 1) + "";
		return data;
	}

	public String update(String data) {
		if(Integer.parseInt(data) + 1 > 9) data = "0";
		else data = (Integer.parseInt(data) + 1) + "";
		return data;
	}

	@Override
	protected void onDestroy() {
		if(mediaPlayer.isPlaying()){
			mediaPlayer.stop();
		}
		mediaPlayer.release();
		super.onDestroy();
	}

	//判断文件是否存在
	private boolean isFileExist() {
		file = new File(Environment.getExternalStorageDirectory() + File.separator + strflice);
		if(file.exists()){
			/*  mediaPlayer=new MediaPlayer();
			mediaPlayer = MediaPlayer.create(this, R.raw.my);
			try {
                //mediaPlayer.setDataSource(file.getAbsolutePath());
                //mediaPlayer.prepare();//预加载音频
                //mediaPlayer.start();//播放音乐
			} catch(Exception e) {
				e.printStackTrace();
			}
			Toast.makeText(this, "file exist", Toast.LENGTH_LONG).show();*/
			return true;
		}
		return true;
	}

	//播放音乐的方法
	private void play() throws IOException {
		try {
			AssetManager am = getAssets();
			AssetFileDescriptor afd = am.openFd(strflice);
			mediaPlayer.reset();//从新设置要播放的音乐
			//mediaPlayer.setDataSource(file.getAbsolutePath())
			//mediaPlayer.prepare();//预加载音频
			mediaPlayer.setDataSource(afd.getFileDescriptor());
			mediaPlayer.prepare();
			mediaPlayer.start();//播放音乐

		} catch(Exception e) {
			e.printStackTrace();
			Log.e("err", e.getMessage());
		}
	}

	public void song(String strflice){
		this.strflice=strflice;
		if(isFileExist()){
			//对MediaPlayer对象添加事件监听，当播放完成时重新开始音乐播放
			mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					try {
						play();
					} catch(IOException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	public void songStop(){
		mediaPlayer.stop();
	}
}