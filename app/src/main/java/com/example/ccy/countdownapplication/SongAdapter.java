package com.example.ccy.countdownapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

//自定义适配器，继承自ArrayAdapter
public class SongAdapter extends ArrayAdapter<String> {
	//resourceID指定ListView的布局方式
	private int resourceID;
	//重写SongAdapter的构造器
	public SongAdapter(Context context, int textViewResourceID , List<String> objects){
		super(context,textViewResourceID,objects);
		resourceID = textViewResourceID;
	}
	//自定义item资源的解析方式
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//获取当前NoteContent实例
		String songName = getItem(position);
		View view;
		ViewHolder viewHolder;
		/*if(convertView == null){*/
		view = LayoutInflater.from(getContext()).inflate(resourceID,null);
		viewHolder = new ViewHolder();
		viewHolder.songName = (TextView)view.findViewById(R.id.song_Name);
		//将ViewHolder存储在View中
		view.setTag(viewHolder);
		/*}else {
			view = convertView;
			viewHolder = (ViewHolder)view.getTag();
		}*/
		//引入NoteContent对象的属性值
		viewHolder.songName.setText(songName);
		return view;
	}

	class ViewHolder{
		TextView songName;
	}
}