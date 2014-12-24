package com.example.login.tencent;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.login.tools.LOG;
import com.example.login.tools.PreferenceUtil;
import com.example.login.tools.WeiboListener;
import com.tencent.tauth.Tencent;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.tencent.weibo.sdk.android.component.Authorize;
import com.tencent.weibo.sdk.android.component.sso.AuthHelper;
import com.tencent.weibo.sdk.android.component.sso.OnAuthListener;
import com.tencent.weibo.sdk.android.component.sso.WeiboToken;
import com.weibo.sdk.android.WeiboException;
import com.weibo.sdk.android.net.RequestListener;

/**
 * 腾讯微博工具类
 * 
 * @author cstdingran@gmail.com
 * 
 */
public class TencentUtil {

	private static final String TAG = "TencentWeiboUtil";

	private static Context mContext;

	private static TencentUtil tencentWeiboUtil;

	private static Tencent tencent;

	/** 保存token等参数 **/
	private static TencentTO tencentTO;

	private WeiboListener listener;

	public TencentUtil() {
		// tencentTO = new TencentTO();
		tencent = Tencent.createInstance(Tencents.APP_ID, mContext);
	}

	public static Tencent getInstance(Context context) {
		mContext = context;
		if (tencentWeiboUtil == null) {
			tencentWeiboUtil = new TencentUtil();
		}
		return tencent;
	}

	/**
	 * 网页授权回调函数
	 */
	public void webAuthOnResult() {
		LOG.cstdr(TAG, "initTencentWeibo=======listener = " + listener);
		if (listener != null) {
			listener.onResult();
		}
	}

	/**
	 * 初始化腾讯微博
	 * 
	 * @param l
	 *            授权是否过期回调函数
	 */
	public void initTencentWeibo(WeiboListener l) {
		String accessToken = PreferenceUtil.getInstance(mContext).getString(
				"token_Tencent", "");
		System.out.println("能不能打印：" + accessToken.trim().length());
		if (accessToken.isEmpty() || accessToken.equals("")
				|| accessToken.trim().length() == 0) { // 未授权
			l.init(false);
		} else {
			long expiresTime = (PreferenceUtil.getInstance(mContext).getLong(
					"expires_in", 0));
			LOG.cstdr(TAG, "expiresTime = " + expiresTime);
			LOG.cstdr(TAG, "expiresTime - System.currentTimeMillis() = "
					+ (expiresTime - System.currentTimeMillis()));
			if (expiresTime - System.currentTimeMillis() > 0) { // 已授权未过期
				String openId = PreferenceUtil.getInstance(mContext).getString(
						Tencents.PREF_TX_OPEN_ID, "");
				String clientId = PreferenceUtil.getInstance(mContext)
						.getString(Tencents.PREF_TX_CLIENT_ID, "");
				String clientIp = PreferenceUtil.getInstance(mContext)
						.getString(Tencents.PREF_TX_CLIENT_IP, "");
				tencentTO.setAccessToken(accessToken);
				System.out.println(tencentTO.getAccessToken());
				tencentTO.setOpenId(openId);
				tencentTO.setAppkey(clientId);
				tencentTO.setClientIp(clientIp);
				l.init(true);
			} else { // 已过期
				l.init(false);
			}
		}
	}

	/**
	 * SSO授权
	 * 
	 * @param appId
	 * @param appSecket
	 * @param l
	 */
	public void auth(final WeiboListener l) {
		long appId = Long.valueOf(Tencents.APP_ID);
		String appSecket = Tencents.APP_KEY;
		listener = l;
		auth(appId, appSecket);
	}

	/**
	 * 腾讯微博网页授权组件
	 * 
	 * @param appid
	 * @param app_secket
	 */

	private void auth(long appid, String app_secket) {
		AuthHelper.register(mContext, appid, app_secket, new OnAuthListener() {
			@Override
			public void onWeiBoNotInstalled() {
				Toast.makeText(mContext, "onWeiBoNotInstalled", 1000).show();
				Intent i = new Intent(mContext, Authorize.class);
				mContext.startActivity(i);
			}

			@Override
			public void onWeiboVersionMisMatch() {
				Toast.makeText(mContext, "onWeiboVersionMisMatch", 1000).show();
				Intent i = new Intent(mContext, Authorize.class);
				mContext.startActivity(i);
			}

			@Override
			public void onAuthFail(int result, String err) {
				Toast.makeText(mContext, "result : " + result, 1000).show();
			}

			@Override
			public void onAuthPassed(String name, WeiboToken token) {
				// StringBuffer sb = new StringBuffer();
				// sb.append("token.accessToken = " + token.accessToken)
				// .append("\ntoken.expiresIn = " + token.expiresIn)
				// .append("\ntoken.omasKey = " + token.omasKey)
				// .append("\ntoken.omasToken = " + token.omasToken)
				// .append("\ntoken.openID = " + token.openID)
				// .append("\ntoken.refreshToken = " + token.refreshToken);
				// LOG.cstdr(TAG, "onAuthPassed---name = " + name + " token = "
				// + token);
				// LOG.cstdr(TAG, "onAuthPassed = " + sb.toString());
				// String clientId =ConstantS.TX_APP_KEY;
				// String clientIp = getClientIp();
				// PreferenceUtil.getInstance(mContext).saveString(
				// ConstantS.PREF_TX_ACCESS_TOKEN, token.accessToken);
				// PreferenceUtil.getInstance(mContext).saveString(
				// ConstantS.PREF_TX_EXPIRES_IN,
				// String.valueOf(token.expiresIn));
				// PreferenceUtil.getInstance(mContext).saveString(
				// ConstantS.PREF_TX_OPEN_ID, token.openID);
				// PreferenceUtil.getInstance(mContext).saveString(
				// ConstantS.PREF_TX_OPEN_KEY, token.omasKey);
				// PreferenceUtil.getInstance(mContext).saveString(
				// ConstantS.PREF_TX_REFRESH_TOKEN, token.refreshToken); //
				// 总是为null
				// PreferenceUtil.getInstance(mContext).saveString(
				// ConstantS.PREF_TX_CLIENT_ID, clientId);
				// PreferenceUtil.getInstance(mContext).saveString(
				// ConstantS.PREF_TX_EXPIRES_TIME,
				// String.valueOf(System.currentTimeMillis()
				// + token.expiresIn * 1000));
				// PreferenceUtil.getInstance(mContext).saveString(
				// ConstantS.PREF_TX_CLIENT_IP, clientIp);
				//
				// tencentTO.setAccessToken(token.accessToken);
				// tencentTO.setAppkey(clientId);
				// tencentTO.setClientIp(clientIp);
				// tencentTO.setOpenId(token.openID);
				//
				// LOG.cstdr(TAG, "clientIp = " + clientIp);
				// getUserInfo(listener);
				// }
				Toast.makeText(mContext, "passed", 1000).show();
				//
				Util.saveSharePersistent(mContext, "ACCESS_TOKEN",
						token.accessToken);
				Util.saveSharePersistent(mContext, "EXPIRES_IN",
						String.valueOf(token.expiresIn));
				Util.saveSharePersistent(mContext, "OPEN_ID", token.openID);
				// Util.saveSharePersistent(context, "OPEN_KEY", token.omasKey);
				Util.saveSharePersistent(mContext, "REFRESH_TOKEN", "");
				// Util.saveSharePersistent(context, "NAME", name);
				// Util.saveSharePersistent(context, "NICK", name);
				Util.saveSharePersistent(mContext, "CLIENT_ID", Util
						.getConfig().getProperty("APP_KEY"));
				Util.saveSharePersistent(mContext, "AUTHORIZETIME",
						String.valueOf(System.currentTimeMillis() / 1000l));
				//
			}

		});

		AuthHelper.auth(mContext, "");
	}

	/**
	 * 获得客户端IP
	 * 
	 * @return
	 */
	public static String getClientIp() {
		try {
			for (Enumeration<NetworkInterface> mEnumeration = NetworkInterface
					.getNetworkInterfaces(); mEnumeration.hasMoreElements();) {
				NetworkInterface intf = mEnumeration.nextElement();
				for (Enumeration<InetAddress> enumIPAddr = intf
						.getInetAddresses(); enumIPAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIPAddr.nextElement();
					// 如果不是回环地址
					if (!inetAddress.isLoopbackAddress()) {
						// 直接返回本地IP地址
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
			Log.e("Error", ex.toString());
		}
		return null;
	}

	/**
	 * 发送一条微博
	 * 
	 * @param content
	 *            微博内容（若在此处@好友，需正确填写好友的微博账号，而非昵称），不超过140字
	 * @param longitude
	 *            经度，为实数，如113.421234（最多支持10位有效数字，可以填空）不是必填
	 * @param latitude
	 *            纬度，为实数，如22.354231（最多支持10位有效数字，可以填空） 不是必填
	 * @param syncflag
	 *            微博同步到空间分享标记（可选，0-同步，1-不同步，默认为0），目前仅支持oauth1.0鉴权方式 不是必填
	 * @param compatibleflag
	 *            容错标志，支持按位操作，默认为0。 0x20-微博内容长度超过140字则报错 0-以上错误做容错处理，即发表普通微博
	 *            不是必填
	 * @param listener
	 *            回调函数
	 */
	public void addWeibo(String content, long longitude, long latitude,
			int syncflag, int compatibleflag) {
		TencentWeiboAPI weiboAPI = new TencentWeiboAPI(tencentTO);
		weiboAPI.addWeibo(content, longitude, latitude, syncflag,
				compatibleflag, new RequestListener() {

					@Override
					public void onIOException(IOException e) {
						LOG.cstdr(TAG, "onIOException---e = " + e.getMessage());
						LOG.showToast(mContext,
								"分享失败，请检查网络连接。出错信息：" + e.getMessage());
					}

					@Override
					public void onError(WeiboException e) {
						LOG.cstdr(TAG, "onError---e = " + e.getMessage());
						LOG.showToast(mContext,
								"分享失败，请检查网络连接。出错信息：" + e.getMessage());
					}

					@Override
					public void onComplete(String str) {
						LOG.cstdr(TAG, "onComplete---str = " + str);
						LOG.showToast(mContext,
								"分享成功，去你绑定的腾讯微博看看吧！");
					}
				});
	}

	/**
	 * 发一条带图片的微博
	 * 
	 * @param content
	 *            微博内容（若在此处@好友，需正确填写好友的微博账号，而非昵称），不超过140字
	 * @param longitude
	 *            经度，为实数，如113.421234（最多支持10位有效数字，可以填空）不是必填
	 * @param latitude
	 *            纬度，为实数，如22.354231（最多支持10位有效数字，可以填空） 不是必填
	 * @param syncflag
	 *            微博同步到空间分享标记（可选，0-同步，1-不同步，默认为0），目前仅支持oauth1.0鉴权方式 不是必填
	 * @param compatibleflag
	 *            容错标志，支持按位操作，默认为0。 0x20-微博内容长度超过140字则报错 0-以上错误做容错处理，即发表普通微博
	 *            不是必填
	 * @param pic_url
	 *            图片链接
	 * @param listener
	 *            回调函数
	 */
	public void upload(String content, String pic_url, long longitude,
			long latitude, int syncflag, int compatibleflag) {
		TencentWeiboAPI weiboAPI = new TencentWeiboAPI(tencentTO);
		weiboAPI.upload(content, pic_url, longitude, latitude, syncflag,
				compatibleflag, new RequestListener() {

					@Override
					public void onIOException(IOException e) {
						LOG.cstdr(TAG, "onIOException---e = " + e.getMessage());
						LOG.showToast(mContext,
								"分享失败，请检查网络连接。出错信息：" + e.getMessage());
					}

					@Override
					public void onError(WeiboException e) {
						LOG.cstdr(TAG, "onError---e = " + e.getMessage());
						LOG.showToast(mContext,
								"分享失败，请检查网络连接。出错信息：" + e.getMessage());
					}

					@Override
					public void onComplete(String str) {
						LOG.cstdr(TAG, "onComplete---str = " + str);
						LOG.showToast(mContext,
								"分享成功，去你绑定的腾讯微博看看吧！");
					}
				});
	}

	/**
	 * 注销授权
	 * 
	 * @param l
	 */
	public void logout(WeiboListener l) {
		PreferenceUtil.getInstance(mContext).remove(
				Tencents.PREF_TX_ACCESS_TOKEN);
		l.onResult();
	}

	/**
	 * 检查是否已授权
	 * 
	 * @return true 已授权，false 未授权
	 */
	public boolean isAuth() {
		String token = PreferenceUtil.getInstance(mContext).getString(
				Tencents.PREF_TX_ACCESS_TOKEN, "");
		if (TextUtils.isEmpty(token)) {
			return false;
		}
		return true;
	}

}
