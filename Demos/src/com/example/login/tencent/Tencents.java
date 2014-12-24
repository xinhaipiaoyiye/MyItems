package com.example.login.tencent;

public interface Tencents {
	// 腾讯微博======================================================

	// 1. 修改配置文件，目标文件config.properties在Android_SDK.jar包config文件夹下(我已经改名为
	// TencentWeiboSDK.jar)，把appkey、appsecret修改成自己应用对应的appkey和appsecret.
	public static String APP_ID = "100459450"; // 开发者的app_id （腾讯授权)
	public static String APP_KEY = "3fd5be88523a2dbfe3e5fd448ba28d78 s";
	public static String URL_INFO = "https://graph.qq.com/user/get_info"; // 获取腾讯资料信息
	public static String share_tencent_URL = "https://open.t.qq.com/api/t/add_pic_url"; // 腾讯发布带图片的微博
	// 腾讯微博首选项

	String PREF_TX_ACCESS_TOKEN = "TX_ACCESS_TOKEN";

	String PREF_TX_EXPIRES_IN = "TX_EXPIRES_IN";

	String PREF_TX_OPEN_ID = "TX_OPEN_ID";

	String PREF_TX_OPEN_KEY = "TX_OPEN_KEY";

	String PREF_TX_REFRESH_TOKEN = "TX_REFRESH_TOKEN";

	String PREF_TX_NAME = "TX_NAME";

	String PREF_TX_CLIENT_ID = "TX_CLIENT_ID";

	String PREF_TX_EXPIRES_TIME = "TX_EXPIRES_TIME";

	String PREF_TX_CLIENT_IP = "TX_CLIENT_IP";

	String PREF_TX_UID = "TX_UID";

	// TX API请求字段

	String TX_API_APP_KEY = "oauth_consumer_key";

	String TX_API_ACCESS_TOKEN = "access_token";

	String TX_API_OPEN_ID = "openid";

	String TX_API_CLIENT_IP = "clientip";

	String TX_API_OAUTH_VERSION = "oauth_version";

	String TX_API_SCOPE = "scope";

	String TX_API_CONTENT = "content";

	String TX_API_FORMAT = "format";

	String TX_API_LONGITUDE = "longitude";

	String TX_API_LATITUDE = "latitude";

	String TX_API_SYNCFLAG = "syncflag";

	String TX_API_PIC_URL = "pic_url";

	String TX_API_COMPATIBLEFLAG = "compatibleflag";

}
