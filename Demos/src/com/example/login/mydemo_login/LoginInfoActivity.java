package com.example.login.mydemo_login;

import java.util.HashMap;

import com.example.demos.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class LoginInfoActivity extends Activity {

	private final static String TAG = "MainActivity";

	private TextView textView, textView2;
	
	private int tag;
	private HashMap<String, Object> hashMap;
	

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_info);
		hashMap = new HashMap<String, Object>();
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		tag = bundle.getInt("tag");
		textView = (TextView) findViewById(R.id.textView1);
		textView2 = (TextView) findViewById(R.id.textView2);
		switch (tag) {
		case 0:
			Log.e(TAG, "普通登录");
			textView2.setText("普通登录帐号信息");
			textView.setText("帐号:" + bundle.getString("name") + "\r\n密码："
					+ bundle.getString("password"));
			break;
		case 1:
			Log.e(TAG, "新浪微博个人信息");
			textView2.setText("");
			hashMap = (HashMap<String, Object>) bundle.get("hashmap");
			textView.setText(hashMap.toString());
			break;
		case 2:
			Log.e(TAG, "腾讯微博登录");
			textView2.setText("腾讯微博个人信息");
			hashMap = (HashMap<String, Object>) bundle.get("hashmap");
			textView.setText(hashMap.toString());
			break;
		case 3:
			Log.e(TAG, "人人网登录");
			textView2.setText("人人网个人信息");
			hashMap = (HashMap<String, Object>) bundle.get("hashmap");
			textView.setText(hashMap.toString());
			break;
		default:
			break;
		}
	}
}
