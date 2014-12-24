package com.example.notepad.adapter;
import java.util.List;

import com.example.demos.R;
import com.example.notepad.entity.NotepadEntity;

import android.content.Context;
import android.text.NoCopySpan.Concrete;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
public class NotepadAdapter extends BaseAdapter {
	private Context mContecxt;
	private List<NotepadEntity> mList;
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}
	public NotepadAdapter(Context mContecxt, List<NotepadEntity> mList) {
		super();
		this.mContecxt = mContecxt;
		this.mList = mList;
	}
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder=null;
		if(convertView==null){
			viewHolder=new ViewHolder();
			convertView=LayoutInflater.from(mContecxt).inflate(R.layout.notepad_list_item, null);
			viewHolder.mTime=(TextView) convertView.findViewById(R.id.textdate);
			viewHolder.mMood=(TextView) convertView.findViewById(R.id.textmood);
			viewHolder.mTitle=(TextView) convertView.findViewById(R.id.texttitle);
			viewHolder.mContent=(TextView) convertView.findViewById(R.id.textcontent);
			convertView.setTag(viewHolder);

		}else{
			viewHolder=(ViewHolder) convertView.getTag();
		}
		viewHolder.mTime.setText(mList.get(position).getNote_time());
		viewHolder.mMood.setText(mList.get(position).getNote_mood());
		viewHolder.mTitle.setText(mList.get(position).getNote_title());
		viewHolder.mContent.setText(mList.get(position).getNote_content());
		// TODO Auto-generated method stub
		return convertView;
	}
	class ViewHolder{
		TextView mTime;
		TextView mMood;
		TextView mTitle;
		TextView mContent;
	}
}
