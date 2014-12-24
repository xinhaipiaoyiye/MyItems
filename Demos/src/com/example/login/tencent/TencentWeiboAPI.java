package com.example.login.tencent;

import com.weibo.sdk.android.WeiboParameters;
import com.weibo.sdk.android.net.AsyncWeiboRunner;
import com.weibo.sdk.android.net.RequestListener;

/**
 * 腾讯微博API，可以根据自己的需要添加API
 * 
 * @author cstdingran@gmail.com
 * 
 */
public class TencentWeiboAPI {

	/**
	 * 访问微博服务接口的地址
	 */
	public static final String API_SERVER = "https://open.t.qq.com/api";

	private static final String URL_ADD = API_SERVER + "/t/add";

	private static final String URL_LOAD = API_SERVER + "/t/add_pic_url";

	private static final String URL_USER_INFO = API_SERVER + "/user/info";

	/**
	 * post请求方式
	 */
	public static final String HTTPMETHOD_POST = "POST";

	/**
	 * get请求方式
	 */
	public static final String HTTPMETHOD_GET = "GET";

	private TencentTO tencentTO;

	/**
	 * 构造函数
	 */
	public TencentWeiboAPI(TencentTO tencentTO) {
		this.tencentTO = tencentTO;
	}

	/**
	 * 执行请求
	 * 
	 * @param url
	 * @param params
	 * @param httpMethod
	 * @param listener
	 */
	private void request(final String url, final WeiboParameters params,
			final String httpMethod, RequestListener listener) {
		System.out.println(tencentTO);
		System.out.println(Tencents.APP_ID);
		System.out.println(tencentTO.getAccessToken());
		System.out.println(tencentTO.getOpenId());
		System.out.println(tencentTO.getClientIp());
		params.add(Tencents.TX_API_APP_KEY, Tencents.APP_ID);
		// params.add(ConstantS.TX_API_APP_KEY, tencentTO.getAppkey());
		params.add(Tencents.TX_API_ACCESS_TOKEN, tencentTO.getAccessToken());
		params.add(Tencents.TX_API_OPEN_ID, tencentTO.getOpenId());
		params.add(Tencents.TX_API_CLIENT_IP, tencentTO.getClientIp());
		params.add(Tencents.TX_API_OAUTH_VERSION, "2.a");
		params.add(Tencents.TX_API_SCOPE, "all");
		params.add(Tencents.TX_API_FORMAT, "json"); // 返回数据的格式（json或xml）
		AsyncWeiboRunner.request(url, params, httpMethod, listener);
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
			int syncflag, int compatibleflag, RequestListener listener) {
		WeiboParameters params = new WeiboParameters();
		params.add(Tencents.TX_API_CONTENT, content);
		params.add(Tencents.TX_API_LONGITUDE, longitude);
		params.add(Tencents.TX_API_LATITUDE, latitude);
		params.add(Tencents.TX_API_SYNCFLAG, syncflag);
		params.add(Tencents.TX_API_COMPATIBLEFLAG, compatibleflag);
		request(URL_ADD, params, HTTPMETHOD_POST, listener);
	}

	/**
	 * 发布一条带图片微博
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
			long latitude, int syncflag, int compatibleflag,
			RequestListener listener) {
		WeiboParameters params = new WeiboParameters();
		params.add(Tencents.TX_API_CONTENT, content);
		params.add(Tencents.TX_API_PIC_URL, pic_url);
		params.add(Tencents.TX_API_LONGITUDE, longitude);
		params.add(Tencents.TX_API_LATITUDE, latitude);
		params.add(Tencents.TX_API_SYNCFLAG, syncflag);
		params.add(Tencents.TX_API_COMPATIBLEFLAG, compatibleflag);
		System.out.println(URL_LOAD);
		System.out.println(params);
		System.out.println(HTTPMETHOD_POST);
		System.out.println(listener);
		request(URL_LOAD, params, HTTPMETHOD_POST, listener);
	}

	/**
	 * OAuth授权之后，获取授权用户的信息
	 * 
	 * @param listener
	 */
	public void getUserInfo(RequestListener listener) {
		WeiboParameters params = new WeiboParameters();
		request(URL_USER_INFO, params, HTTPMETHOD_GET, listener);
	}

}
