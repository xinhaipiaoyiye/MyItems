package com.example.login.mydemo_login;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demos.R;
import com.example.login.renren.RennClientAPI;
import com.example.login.renren.RenrenUtil;
import com.example.login.sina.SinaWeiboUtil;
import com.example.login.sina.Sinas;
import com.example.login.tencent.TencentUtil;
import com.example.login.tencent.Tencents;
import com.example.login.tools.HttpUtil;
import com.example.login.tools.LOG;
import com.example.login.tools.PreferenceUtil;
import com.example.login.tools.WeiboListener;
import com.example.saoyisao.fire.view.Capture;
import com.example.splash.SplashActivity;
import com.renn.rennsdk.RennResponse;
import com.renn.rennsdk.RennClient.LoginListener;
import com.renn.rennsdk.RennExecutor.CallBack;
import com.renn.rennsdk.exception.RennException;
import com.renn.rennsdk.param.BatchUserParam;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.weibo.sdk.android.Oauth2AccessToken;
public class LoginActivity extends Activity implements OnClickListener {
	protected SharedPreferences sharedPreferences;
	public static Oauth2AccessToken accessToken;
	private int tag = 0;
	private EditText name, password;
	private Button btn_show; // 控制密码的显示和隐藏
	private Button btn_enter; // 登录
	private Button btn_Sina, btn_Tenent, btn_Renren;
	private String str_name, str_password;
	private static Handler mHandler;
	private HashMap<String, Object> hashMap;
	private ProgressDialog progressDialog;
	private Context mContext;
	// 新浪微博登陆变量
	private SinaWeiboUtil weibo_sina;
	private String token_sina;
	private String mUserId;
	// 腾讯微博登陆变量
	private Tencent tencent;
	private String openid;
	private String token_tencent;
	// 人人网登录变量
	private RennClientAPI rennClient;
	private Long userId;
	private String token_renren;
	private Button mGoBtn;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (progressDialog != null) {
				progressDialog.dismiss();
			}
			if (msg.what == 0) {
				System.out.println(hashMap);
				Intent intent = new Intent();
				intent.putExtra("tag", tag);
				intent.putExtra("hashmap", hashMap);
				intent.setClass(LoginActivity.this, LoginInfoActivity.class);
				startActivity(intent);
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		mContext = this;
		mHandler = new Handler();
		sharedPreferences = this.getSharedPreferences("sharpandroid",
				Context.MODE_WORLD_WRITEABLE);
		weibo_sina = SinaWeiboUtil.getInstance(mContext);
		tencent = TencentUtil.getInstance(mContext);
		rennClient = RenrenUtil.getInstance(mContext);
		progressDialog = new ProgressDialog(LoginActivity.this);
		name = (EditText) findViewById(R.id.editText1);
		password = (EditText) findViewById(R.id.editText2);
		btn_show = (Button) findViewById(R.id.button5);
		mGoBtn=(Button) findViewById(R.id.go);
		btn_show.setText("显示");
		btn_show.setTag(1);
		btn_enter = (Button) findViewById(R.id.button4);
		btn_Sina = (Button) findViewById(R.id.button1);
		btn_Tenent = (Button) findViewById(R.id.button2);
		btn_Renren = (Button) findViewById(R.id.button3);
		btn_show.setOnClickListener(this);
		btn_enter.setOnClickListener(this);
		btn_Sina.setOnClickListener(this);
		btn_Tenent.setOnClickListener(this);
		btn_Renren.setOnClickListener(this);
		mGoBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent(LoginActivity.this,SplashActivity.class);
				startActivity(it);
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button5:
			isshow();
			break;
		case R.id.button1:
			tag = 1;
			logonToSina();
			break;
		case R.id.button2:
			tag = 2;
			logonToTencent();
			break;
		case R.id.button3:
			tag = 3;
			logonToRenRen();
			break;
		case R.id.button4:
			logoning();
			break;

		default:
			break;
		}

	}

	private void isshow() {
		if (btn_show.getTag().equals(1)) {
			password.setTransformationMethod(HideReturnsTransformationMethod
					.getInstance());
			btn_show.setText("隐藏");
			btn_show.setTag(2);
		} else {
			btn_show.setTag(1);
			password.setTransformationMethod(PasswordTransformationMethod
					.getInstance());
			btn_show.setText("显示");
		}
	}

	/**
	 * 普通登录
	 */
	private void logoning() {
		str_name = name.getText().toString();
		str_password = password.getText().toString();
		if (isLegal(str_name, str_password)) {
			Intent intent = new Intent();
			intent.putExtra("tag", tag);
			intent.putExtra("name", str_name);
			intent.putExtra("password", str_password);
			intent.setClass(LoginActivity.this, LoginInfoActivity.class);
			this.startActivity(intent);
		}
	}

	/*** 判断输入是否合法 ***/
	private boolean isLegal(String name, String password) {
		if (name == null || name.length() <= 0) {
			Toast.makeText(getBaseContext(), "帐号不能为空", 2000).show();
			return false;
		} else {
			if (password == null || password.trim().length() <= 0) {
				Toast.makeText(getBaseContext(), "密码不能为空", 2000).show();
				return false;
			} else {
				return true;
			}
		}
	}

	/**
	 * 新浪微博登录
	 * 
	 */
	private void logonToSina() {
		if (HttpUtil.checkNet(this)) {
			weibo_sina.auth(new WeiboListener() {
				@Override
				public void onResult() {
					LoginActivity.getHandler().post(new Runnable() {
						@Override
						public void run() {
							refreshView();
						}
					});
				}
			});
		}
	}

	/*
	 * 腾讯微博登录
	 */
	private void logonToTencent() {
		if (!tencent.isSessionValid()) {
			IUiListener listener = new BaseUiListener() {
				@Override
				protected void doComplete(JSONObject values) {
				}
			};
			tencent.login(this, "all", listener);
		} else {
			tencent.logout(this);
			// }
		}
	}

	/**
	 * 人人网登录
	 */
	private void logonToRenRen() {
		rennClient.setLoginListener(new LoginListener() {
			@Override
			public void onLoginSuccess() {
				// TODO Auto-generated method stub
				userId = rennClient.getUid();
				token_renren = rennClient.getAccessToken().accessToken
						.toString();
				Log.e("Uid", userId + "");
				Log.e("token", token_renren);
				Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT)
						.show();
				toHttpRenrenURL(handler);
			}

			@Override
			public void onLoginCanceled() {
			}
		});
		rennClient.login(this);
	}

	/***
	 * 异步获取人人网个人信息
	 * 
	 * @param handler
	 */
	private void toHttpRenrenURL(final Handler handler) {
		BatchUserParam param1 = new BatchUserParam();
		Long[] userIds = { userId };
		// Long[] userIds = { (long) 221579439 };
		param1.setUserIds(userIds);
		if (progressDialog == null) {
			progressDialog = new ProgressDialog(LoginActivity.this);
			progressDialog.setCancelable(true);
			progressDialog.setTitle("请等待");
			progressDialog.setIcon(android.R.drawable.ic_dialog_info);
			progressDialog.setMessage("正在获取信息");
			progressDialog.show();
		}
		try {
			rennClient.getRennService().sendAsynRequest(param1, new CallBack() {

				@Override
				public void onSuccess(RennResponse response) {
					Toast.makeText(LoginActivity.this, "获取成功",
							Toast.LENGTH_SHORT).show();
					if (progressDialog != null) {
						progressDialog.dismiss();
						progressDialog = null;
					}
					hashMap = getMessage_Renren(response.toString());
					handler.obtainMessage(0, hashMap).sendToTarget();
				}

				@Override
				public void onFailed(String errorCode, String errorMessage) {
					Log.e("获取信息失败", errorCode + ":" + errorMessage);
					Toast.makeText(LoginActivity.this, "获取失败",
							Toast.LENGTH_SHORT).show();
					if (progressDialog != null) {
						progressDialog.dismiss();
						progressDialog = null;
					}
				}
			});
		} catch (RennException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	protected HashMap<String, Object> getMessage_Renren(String query) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String string = query.substring(query.indexOf("{"),
				query.lastIndexOf("]"));
		try {
			JSONObject jsonObject = new JSONObject(string);
			JSONArray jsonArray2 = jsonObject.getJSONArray("response");
			JSONObject jsonObject2 = jsonArray2.getJSONObject(0);
			map.put("id", jsonObject2.get("id"));
			map.put("name", jsonObject2.get("name"));
			map.put("id", jsonObject2.get("id"));
			map.put("work", jsonObject2.get("work").toString());
			map.put("education", jsonObject2.get("education").toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		System.err.println(map);
		return map;
	}

	/**
	 * Get方式获取用户信息（新浪）
	 * 
	 * @param uid
	 * @param access_token
	 * @return
	 */
	private String query(String http, String uid, String access_token) {
		// 查询参数
		String queryString = "uid=" + uid + "&access_token=" + access_token;
		// url
		String url = http + "?" + queryString;
		Log.e("我的链接", url);
		// 查询返回结果
		String string = HttpUtil.queryStringForGet(url);
		System.out.println("获取用户信息：" + string);
		return string;

	}

	/*
	 * 异步获取资料
	 */
	private void toHttpSinaURL(final Handler handler) {
		progressDialog = ProgressDialog
				.show(LoginActivity.this, "", "登陆中，请稍候…");
		new Thread(new Runnable() {
			@Override
			public void run() {
				hashMap = getMessage_Sina(query(Sinas.URL_USERS_SHOW, mUserId,
						token_sina));
				handler.obtainMessage(0, hashMap).sendToTarget();
			}
		}).start();

	}

	/**
	 * 解析数据返回用户信息
	 * 
	 * @param string
	 * @return
	 */
	protected HashMap<String, Object> getMessage_Sina(String string) {
		// TODO Auto-generated method stub
		JSONObject object = null;
		HashMap<String, Object> hash = new HashMap<String, Object>();
		if (string != "" && string != null && string.trim().length() > 0) {
			try {
				object = new JSONObject(string);
				hash.put("id", object.getString("idstr"));
				hash.put("type", "xl_set");
				if (object.getString("gender").equals("f")) {
					hash.put("sex", "0");
				} else {
					hash.put("sex", "1");
				}
				hash.put("name", object.getString("screen_name"));
				hash.put("sign", object.getString("description"));
				hash.put("location", object.getString("location"));
				hash.put("head_image", object.getString("avatar_large"));
				hash.put("age", "");
				hash.put("mail", "");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return hash;
	}

	@SuppressLint("NewApi")
	private void refreshView() {
		String sinaToken = PreferenceUtil.getInstance(mContext).getString(
				Sinas.PREF_SINA_ACCESS_TOKEN, "");
		if (sinaToken.isEmpty()) { // 未授权
			LoginActivity.getHandler().post(new Runnable() {
				@Override
				public void run() {
					LOG.showToast(mContext, "未授权");
				}
			});
		} else {
			// 初始化新浪微博，判断是否授权过期
			SinaWeiboUtil.getInstance(mContext).initSinaWeibo(
					new WeiboListener() {
						@Override
						public void init(boolean isValid) {
							if (isValid) {
								token_sina = PreferenceUtil.getInstance(
										mContext).getString(
										Sinas.PREF_SINA_ACCESS_TOKEN, "");
								mUserId = PreferenceUtil.getInstance(mContext)
										.getString(Sinas.PREF_SINA_UID, "");

								Editor editor = sharedPreferences.edit();
								editor.putString("token_Sina", token_sina);
								editor.putString("uid_Sina", mUserId);
								editor.commit();
								LoginActivity.getHandler().post(new Runnable() {

									@Override
									public void run() {
										LOG.showToast(mContext, "新浪微博已授权");
										toHttpSinaURL(handler);
									}
								});
							} else {
								LoginActivity.getHandler().post(new Runnable() {

									@Override
									public void run() {
										LOG.showToast(mContext,
												"新浪微博授权已过期，请重新绑定。");
									}
								});
							}
						}
					});

		}
	}

	public static Handler getHandler() {
		if (mHandler == null) {
			mHandler = new Handler();
		}
		return mHandler;
	}

	private class BaseUiListener implements IUiListener {
		@Override
		public void onComplete(JSONObject response) {
			System.out.println("AAAA" + response.toString());
			doComplete(response);
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(response.toString());
				System.out.println(jsonObject);
				openid = jsonObject.getString("openid");
				token_tencent = jsonObject.getString("access_token");
				long expirse_time = Long.parseLong(jsonObject
						.getString("expires_in"))
						* 1000
						+ System.currentTimeMillis();
				Editor editor = sharedPreferences.edit();
				editor.putString("token_Tencent", token_tencent);
				editor.putLong("expires_in", expirse_time);
				editor.commit();
				Toast.makeText(LoginActivity.this, "认证成功", Toast.LENGTH_SHORT)
						.show();
				toHttpTencentURL(handler);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		/**
		 * 异步获取腾讯微博资料并上传
		 * 
		 * @param handler
		 */
		private void toHttpTencentURL(final Handler handler) {
			progressDialog = ProgressDialog.show(LoginActivity.this, "",
					"登陆中，请稍候…");
			new Thread(new Runnable() {
				@Override
				public void run() {
					hashMap = getMessage_Tencent(onClickUserInfo(token_tencent,
							Tencents.APP_ID, openid));
					handler.obtainMessage(0, hashMap).sendToTarget();
				}
			}).start();
		}

		/**
		 * 获取腾讯微博的资料信息
		 */
		private String onClickUserInfo(String access_token,
				String oauth_consumer_key, String openid) {
			Log.e("开始获取用户资料信息", "");
			// 查询参数
			String query = "access_token=" + access_token
					+ "&oauth_consumer_key=" + oauth_consumer_key + "&openid="
					+ openid;
			// url
			String url = Tencents.URL_INFO + "?" + query + "&format=json";
			System.err.println("获取腾讯微博资料的链接：" + url);
			String string = HttpUtil.queryStringForGet(url);
			return string;
		}

		/**
		 * 解析返回的腾讯微博信息资料
		 * 
		 * @param string返回的json字符串
		 */
		protected HashMap<String, Object> getMessage_Tencent(String string) {
			JSONObject jsonObject = null;
			JSONObject object = null;
			HashMap<String, Object> hash = null;
			if (string != "" && string != null && string.trim().length() > 0) {
				hash = new HashMap<String, Object>();
				try {
					jsonObject = new JSONObject(string);
					// if (jsonObject.getInt("ret") == 0) {
					object = jsonObject.getJSONObject("data");
					hash.put("sex", object.getInt("sex"));
					hash.put("name", object.getString("nick"));
					hash.put("sign", object.getString("introduction"));
					hash.put("location", object.getString("location"));
					hash.put("head_image", object.getString("head") + "/120");
					hash.put(
							"age",
							object.get("birth_year") + "-"
									+ object.get("birth_month") + "-"
									+ object.get("birth_day"));
					hash.put("mail", object.get("email"));
					// }
				} catch (JSONException e) {
					e.printStackTrace();
				}

			}
			System.out.println(hash);
			return hash;
		}

		protected void doComplete(JSONObject values) {

		}

		@Override
		public void onError(UiError e) {
			showResult("onError:", "code:" + e.errorCode + ", msg:"
					+ e.errorMessage + ", detail:" + e.errorDetail);
		}

		@Override
		public void onCancel() {
			showResult("onCancel", "");
			
		}
	}

	/**
	 * 
	 * @param base
	 * @param msg
	 */
	private void showResult(final String base, final String msg) {
		
		
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				if (progressDialog.isShowing())
					progressDialog.dismiss();
				// System.out.println("CCCC" + msg);

			}
		});
	}
}
