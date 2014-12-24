package com.example.login.sina;

public interface Sinas {

	public static final String CLIENT_ID = "client_id";

	public static final String RESPONSE_TYPE = "response_type";

	public static final String USER_REDIRECT_URL = "redirect_uri";

	public static final String DISPLAY = "display";

	public static final String USER_SCOPE = "scope";

	public static final String PACKAGE_NAME = "packagename";

	public static final String KEY_HASH = "key_hash";

	// ����΢��======================================================

	public static String CONSUMER_KEY = "1593535702";
	public static String CONSUMER_ACC = "13199a498acec001425aa63b5dac6db5";
	public static String REDIRECT_URL = "http://weibo.com/u/3513447497";// ����΢�����õĽӿ�
	public static String URL_USERS_SHOW = "https://api.weibo.com/2/users/show.json"; // ��ȡ��������
	public static String share_sina_URL = "https://api.weibo.com/2/statuses/upload_url_text.json"; // ���˷�����ͼƬ��΢��

	// ��֧��scope ֧�ִ�����scopeȨ�ޣ��ö��ŷָ�����ʱ�ò���
	String SINA_SCOPE = "email,direct_messages_read,direct_messages_write,"
			+ "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
			+ "follow_app_official_microblog";

	/** ��֤Code **/
	String SINA_CODE = "code";

	String SINA_ACCESS_TOKEN = "access_token";

	String SINA_EXPIRES_IN = "expires_in";

	String SINA_UID = "uid";

	String SINA_USER_NAME = "userName";

	String SINA_NAME = "name";

	String SINA_REMIND_IN = "remind_in";

	String SINA_DATE_PATTERN = "yyyy/MM/dd HH:mm:ss";

	String SINA_BASEURL = "https://api.weibo.com/oauth2/";

	String SINA_CLIENT_ID = "client_id";

	String SINA_CLIENT_SECRET = "client_secret";

	String SINA_GRANT_TYPE = "grant_type";

	String SINA_GRANT_TYPE_VALUE = "authorization_code";

	String SINA_REDIRECT_URI = "redirect_uri";

	// ����΢����ѡ��

	String PREF_SINA_ACCESS_TOKEN = "SINA_ACCESS_TOKEN";

	String PREF_SINA_EXPIRES_TIME = "SINA_EXPIRES_TIME";

	String PREF_SINA_UID = "SINA_UID";

	String PREF_SINA_USER_NAME = "SINA_USER_NAME";

	String PREF_SINA_REMIND_IN = "SINA_REMIND_IN";

}
