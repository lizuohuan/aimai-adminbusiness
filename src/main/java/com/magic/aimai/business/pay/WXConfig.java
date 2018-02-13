package com.magic.aimai.business.pay;


/**
 *  微信支付配置
 * @author QimouXie
 *
 */
public class WXConfig {

	/**密钥*/
	public static final String KEY = "6e79baf90fcc44c1871a4e07ac031512";

	/**APP ID*/
	public static final String APP_ID = "wx94b9103030fc353b";

	/**商户号*/
	public static final String MCH_ID = "1486671522";

	public static final String SIGN_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";


	public static final String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";


}
