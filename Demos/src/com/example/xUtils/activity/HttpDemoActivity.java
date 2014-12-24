package com.example.xUtils.activity;

import java.lang.reflect.Type;
import java.util.List;

import com.example.demos.R;
import com.example.login.mydemo_login.LoginActivity;
import com.example.xUtils.adapter.GoodsAdapter;
import com.example.xUtils.entity.GoodsEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnItemClick;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class HttpDemoActivity extends Activity {
	private final static String URL = "http://192.172.10.32:8080/HttpUrlServer/jsons/1302B_1.json";
	private static GoodsAdapter adapter;
	private static List<GoodsEntity> goodsList;
	@ViewInject(value = R.id.XUtils_Lv, parentId = 1)
	private static ListView mListView;
	@ViewInject(R.id.go)
	private Button mGoBtn;
	private static Context mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xutils_listview);
		//注意这个必须写  否则@ViewInject()这个不会被执行
		ViewUtils.inject(this); 
		mContext = this;
		mGoBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent(HttpDemoActivity.this,LoginActivity.class);
				startActivity(it);
			}
		});
		getGoodsList();

	}

	public static void getGoodsList() {

		HttpUtils http = new HttpUtils();
		//设置请求的时间
		http.configCurrentHttpCacheExpiry(5 * 1000);
		http.send(HttpMethod.GET, URL, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
			}
            //连接成功
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// 接收获取到的数据
				String json = arg0.result;
				Type type = new TypeToken<List<GoodsEntity>>() {
				}.getType();
				Gson gson = new Gson();
				if (json != null) {
					// 解析数据
					goodsList = gson.fromJson(json, type);
				}
				// 给listVIew赋值
				adapter = new GoodsAdapter(goodsList, mContext);
				mListView.setAdapter(adapter);
			}

		});
	}

	// item的点击事件
	@OnItemClick(R.id.XUtils_Lv)
	public void onItemClsk(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		goodsList.remove(arg2);
		adapter.notifyDataSetChanged();
	}

}
