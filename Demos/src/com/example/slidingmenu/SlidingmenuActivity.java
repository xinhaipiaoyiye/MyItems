package com.example.slidingmenu;
import com.example.demos.R;
import com.example.takephoto.TakephotoActivity;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
public class SlidingmenuActivity extends SlidingFragmentActivity{
	private SlidingMenu slidingMenu;
	private Button left,right;
	private Button but1,but2;
	private Button mGoBtn;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getSupportFragmentManager().beginTransaction().replace(R.id.container,new SettingFragment()).commit();
		setContentView(R.layout.slidingmenu_main_layout);
		setBehindContentView(R.layout.slidingmenu_left);

		slidingMenu=getSlidingMenu();
		slidingMenu.setBehindOffset(200);
		slidingMenu.setMode(slidingMenu.LEFT_RIGHT);
		slidingMenu.setTouchModeAbove(slidingMenu.TOUCHMODE_FULLSCREEN);
		//	slidingMenu.showContent();
		//	slidingMenu.showMenu();
		slidingMenu.setSecondaryMenu(R.layout.slidingmenu_right);
		//slidingMenu.showSecondaryMenu();
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		left=(Button) findViewById(R.id.left);
		right=(Button) findViewById(R.id.right);
		but1=(Button) findViewById(R.id.button1);
		but2=(Button) findViewById(R.id.button2);
		mGoBtn=(Button) findViewById(R.id.go);
		mGoBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent(SlidingmenuActivity.this,TakephotoActivity.class);
				startActivity(it);
			}
		});

		but1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getSupportFragmentManager().beginTransaction().replace(R.id.container,new SettingFragment()).commit();
				slidingMenu.showContent();
			}
		});
		but2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getSupportFragmentManager().beginTransaction().replace(R.id.container,new ActionFragment()).commit();
				slidingMenu.showContent();
			}
		});
		left.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub





				boolean meunshowing=slidingMenu.isMenuShowing();
				if(meunshowing){
					slidingMenu.showContent();
				}else{
					slidingMenu.showMenu();
				}
			}
		});
		right.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				boolean meunshowing=slidingMenu.isMenuShowing();
				if(meunshowing){
					slidingMenu.showContent();
				}else{
					slidingMenu.showSecondaryMenu();
				}
			}
		});
	}
}
