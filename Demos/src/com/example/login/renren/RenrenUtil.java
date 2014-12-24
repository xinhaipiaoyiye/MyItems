package com.example.login.renren;

import com.renn.rennsdk.RennClient.LoginListener;

import android.content.Context;

public class RenrenUtil {

	private static Context mContext;

	private static RennClientAPI rennClient;

	private static RenrenUtil renrenUtil;

	public RenrenUtil() {
		rennClient = RennClientAPI.getInstance(mContext);
		rennClient.init(Renrens.APP_ID, Renrens.API_KEY, Renrens.SECRET_KEY);
		rennClient.setScope(Renrens.SCROPE);
		rennClient.setTokenType("bearer");
	}

	public static RennClientAPI getInstance(Context context) {
		mContext = context;
		if (renrenUtil == null) {
			new RenrenUtil();
		}
		return rennClient;
	}

	public void setLoginListener(LoginListener listrner) {

	}

}
