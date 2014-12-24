package com.example.cache;

import com.example.BaiduPush.BaiduPushActivity;
import com.example.demos.R;
import com.example.duome_locus.locus.LoginLocusActivity;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.annotation.view.ViewInject;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

public class AfinalImageCacheActivity extends FinalActivity {
	@ViewInject(id=R.id.gridview) GridView gridview;
	private FinalBitmap fb;
	@ViewInject(id=R.id.go) Button mGoBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.afinal_imagecache_gridview);
		gridview.setAdapter(new ImageAdapter());
		
		fb = FinalBitmap.create(this);
		
		fb.configLoadingImage(R.drawable.ic_launcher);
		mGoBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent(AfinalImageCacheActivity.this,LoginLocusActivity.class);
				startActivity(it);
			}
		});
	}
	
	String[] imageUrls = {"http://f.hiphotos.baidu.com/image/w%3D1366%3Bcrop%3D0%2C0%2C1366%2C768/sign=b632d85e004f78f0800b9ef04f073136/3ac79f3df8dcd100f77ebe2b718b4710b8122fdd.jpg",
			"http://img1.touxiang.cn/uploads/20120509/09-014358_953.jpg",
			"http://img1.touxiang.cn/uploads/20120509/09-014235_881.jpg"};
	
	class ImageAdapter extends BaseAdapter{
		@Override
		public int getCount() {
			return imageUrls.length;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}
		
		ImageView imageView;
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView == null){
				convertView = LayoutInflater.from(AfinalImageCacheActivity.this).inflate(R.layout.afinal_list_item, null);
				imageView = (ImageView) convertView.findViewById(R.id.imageview);
				convertView.setTag(imageView);
			} else {
				imageView = (ImageView) convertView.getTag();
			}
			//主要写这行代码
			fb.display(imageView, imageUrls[position]);
			return convertView;
		}
		
	}

}
