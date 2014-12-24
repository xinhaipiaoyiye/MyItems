package com.example.login.renren;

public interface Renrens {

	public static final String APP_ID = "241105";

	public static final String API_KEY = "3cf7d2f489224d169f3bb6cd8eb8622f";

	public static final String SECRET_KEY = "fe0e490f45fd4edca4486f03c5de8fa4";

	// ����Ȩ�޷�Χ
	public static final String SCROPE = "read_user_blog read_user_photo read_user_status read_user_album "
			+ "read_user_comment read_user_share publish_blog publish_share "
			+ "send_notification photo_upload status_update create_album "
			+ "publish_comment publish_feed";

	public static String URL_USER = "https://api.renren.com/v2/user/get"; // ��ȡ�û�����
}
