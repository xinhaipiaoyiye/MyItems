package com.example.notepad1;

import java.util.List;

import com.example.demos.R;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
public class myBaseAdapter extends BaseAdapter {	
	Context context;
	List<content> list;
	//设置构造方法，用来传递数据
	public myBaseAdapter(Context context,List<content> list) {
		super();
		this.context=context;
		this.list=list;
	}
	@Override
	public int getCount() {
		return list.size();
	}
	@Override
	public Object getItem(int position) {
		return list.get(position);
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder V=new ViewHolder();
		//  加载布局文件，用来作为listview的显示
		LayoutInflater lInflater=LayoutInflater.from(context);
		convertView=lInflater.inflate(R.layout.notepad_list_item, null);	
	     V.t1=(TextView)convertView.findViewById(R.id.texttitle);
	     V.t2=(TextView)convertView.findViewById(R.id.textmood);
	     V.t3=(TextView)convertView.findViewById(R.id.textcontent);
	     V.t4=(TextView)convertView.findViewById(R.id.textdate);
	     V.t1.setText(list.get(position).getMtitle());
	     V.t2.setText(list.get(position).getMhood());
	     V.t3.setText(list.get(position).getMcontent());
	     V.t4.setText(list.get(position).getMpost_time());
	     Log.e("shich",position+""); 
		 return convertView;
	}
	//内部类：用来定义布局文件中所使用到的控件
	public class ViewHolder{
	private TextView t1;
	private TextView t2;
	private TextView t3;
	private TextView t4;	
	}
	}