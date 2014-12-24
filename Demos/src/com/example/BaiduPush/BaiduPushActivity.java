package com.example.BaiduPush;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.example.demos.R;
import com.example.fileBrower.FileBrowerActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
public class BaiduPushActivity extends Activity {
	private Button mGoBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mGoBtn=(Button) findViewById(R.id.go);
		mGoBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent(BaiduPushActivity.this,FileBrowerActivity.class);
				startActivity(it);
			}
		});
		//在主activity中启动
		PushManager.startWork(BaiduPushActivity.this, PushConstants.LOGIN_TYPE_API_KEY, "n0BCb5NN1RbtmgHoFKKqMoR8");
	}
}
