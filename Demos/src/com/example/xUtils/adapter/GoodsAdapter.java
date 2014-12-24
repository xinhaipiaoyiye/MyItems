package com.example.xUtils.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import android.widget.TextView;

import com.example.demos.R;
import com.example.xUtils.entity.GoodsEntity;
import com.lidroid.xutils.BitmapUtils;

public class GoodsAdapter extends BaseAdapter {
	public final String HOST = "http://192.172.10.32:8080/HttpUrlServer/";
	List<GoodsEntity> mList;
	LayoutInflater mLayoutInflater;
	BitmapUtils mBitmapUtils;
	private Context mContext;
	public GoodsAdapter(List<GoodsEntity> list, Context mContext) {
		this.mList = list;
		mLayoutInflater = LayoutInflater.from(mContext);
		mBitmapUtils = new BitmapUtils(mContext);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
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
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = mLayoutInflater.inflate(R.layout.xutils_list_item, null);
			viewHolder = new ViewHolder();
			viewHolder.name = (TextView) convertView
					.findViewById(R.id.XUtils_Item_Tv);
			viewHolder.image = (ImageView) convertView
					.findViewById(R.id.XUtils_Item_Im);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		GoodsEntity goodsEntity = mList.get(position);
		viewHolder.name.setText(goodsEntity.getCatetitle());
		String imageUrl =goodsEntity.getCateurl();
		//加载图片  自动使用了图片异步加载
		mBitmapUtils.display(viewHolder.image, HOST + imageUrl);
		return convertView;
	}

	class ViewHolder {
		TextView name;
		ImageView image;
	}

}
