package com.example.splash;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.example.cache.AfinalImageCacheActivity;
import com.example.demos.R;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class SplashActivity extends Activity {
	private ViewPager mViewPager;
	private List<ImageView> mImageList;
	private Button mGoBtn;
	private int [] url={R.drawable.vp,R.drawable.vp1,R.drawable.vp3};
	private ImageView[] imageviews=new ImageView[3];
	private Handler handler=new Handler(){
		int p=0;
		public void handleMessage(android.os.Message msg) {
			p++;
			if(p==3){
				p=0;
			}
			mViewPager.setCurrentItem(p);
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		mViewPager=(ViewPager) findViewById(R.id.viewpager);
		mGoBtn=(Button) findViewById(R.id.go);
		mGoBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent(SplashActivity.this,AfinalImageCacheActivity.class);
				startActivity(it);
			}
		});
		mImageList=new ArrayList<ImageView>();
		for(int i=0;i<3;i++){
			ImageView imageView=new ImageView(this);
			imageView.setBackgroundResource(url[i]);
			mImageList.add(imageView);
		}
		ImageView point1=(ImageView) findViewById(R.id.img1);
		ImageView point2=(ImageView) findViewById(R.id.img2);
		ImageView point3=(ImageView) findViewById(R.id.img3);
		imageviews[0]=point1;
		imageviews[1]=point2;
		imageviews[2]=point3;
		mViewPager.setAdapter(new MyAdapter());
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				select(arg0);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				handler.obtainMessage().sendToTarget();
			}
		}, 0, 2000);
	}
	class MyAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mImageList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0==arg1;
		}
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			ImageView imageView=mImageList.get(position);
			container.addView(imageView);
			return imageView;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			ImageView imageView=mImageList.get(position);
			container.removeView(imageView);
		}
	}

public void select(int p){
	for(int i=0;i<imageviews.length;i++){
		if(i==p){
			imageviews[i].setBackgroundColor(Color.RED);
		}else{
			imageviews[i].setBackgroundColor(Color.GREEN);

		}
	}
	
}
}
