package com.magic.aimai.business.pay;

import com.magic.aimai.business.sms.SMSCode;
import com.sun.org.apache.bcel.internal.generic.PUSH;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


/**
 *  支付宝 配置类
 * @author QimouXie
 *
 */
public class AliPayConfig {
	
	/**支付宝私钥*/
	public static final String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALHh3Ntycct67KHVuGJXu83N8beTrwXzPAIrP6oPURQm1pPevgroSjh3WAB88/bRCkbJ1+YtMzWRuolrLTNPANy6CIW2UyzVdCx3D7xNPvyXaOaTm/4pch6Km+SReOgympM8WQEg0U8DrW4tqfUg4G7KpdRsz21ZdG4qBSSs+jSJAgMBAAECgYBkABC0QatddBaRiF+m0aGfulZYGKs9Uhd7NKsOxJuVE5xv9rmp0hrfkaAkbhqMt8nqL9Cy8tgmFm3ikSERBin5/D/5BRnkVZIiLjRjg1V6FZ0Xqxb/4NYR5ArWSDmaBDSwo8VRF6wp8XfY8ekU8nsWnBR0yOswrarSUwEfBSFWAQJBAOGzKuifsZp6IiyJ8kEvmY0//Lk34TcGFasxBShQmkuFKKLX6cicIGKPVEgt1foj5MRyz+yFNyyBhRd8dTIGVEkCQQDJw03p6Yer9+ilA8B5YR/+cY6cwtO0ejRQOEBXLsdOdsraRw8f6r1YXMCFU2JaM/tIMduvARGO+TSsFy0bDF5BAkEAyjPUK1h2qyjkWPa1PMVCUhOTfPSdJsiivR5LupSLx0Di6RF69yk4slQqdAfsfg+OMpmRwhqPFaZr8GCmNk0JUQJAfN2I552mYFAxw4YZ5UStlzvSBcQxXv1OSOM3Nn11zWKJUiMuAhO1kOz+HMUpFigbeGeazKi9pB0ocuR4/sddwQJBALzTduJxYNIGES3r6ReOQonpUYvc+2p5osqQgrOBhnb6KneO7ca6HYHdn3+osvTt2WSWV8JPyHn25KWikH/OaeE=";

	public static final String SERVICE = "mobile.securitypay.pay"; // 固定值


	public static final String SERVICE_PC = "create_direct_pay_by_user"; // 固定值

	public static final String PARTNER = "2088721451146603"; // 合作者ID

	public static final String SELLER_ID = "2088721451146603"; // 商户账户



	public static final String CHARACTER = "utf-8";

	// PC 参数

	public static final String APP_ID = "2017072507887100";


	// 签名方式
	public static String SIGN_TYPE = "RSA2";

	// 字符编码格式
	public static String CHARSET = "utf-8";

	// 支付宝网关
	public static String GATE_WAY_URL = "https://openapi.alipay.com/gateway.do";


	public static final String PRIVATE_KEY_PC = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDLl0cR2EHW8jYoJj8q4b5z6JhOmUNLmM8jf6quNZGShPrmog8pjOeQWfjfJZLIJtp1eujkl6S0fWTUmsbKAKqPGs1m5zLYPRQ3aRNCqJ2Xd7m9pvoHZw7qRak9lkl7N6IAw+7dJGOMCkaxTZehvT5a0HBlnRda1GJMdkUj9HHKadIyvdFM7D6pgnSq40Z8VBXxCZj1ll5bB4JVn5mLaAwQTM3Jf3DvHKnjOO/HwKQIz+SchP6zwAttsDC4GCqHx1aLs+3QQC6VXmq2UOw8t8sqOlHn+JNAAD9JrNa/93CYpYtdF4fk9+pajENQIKZ03qTEE0mAXNi9ob30yTYJLZY3AgMBAAECggEAOFO+Yf2HGcUhnrIWP/L9EmyD1xsoNz4r8RJdUp2XjQYltLGZqFFHr/R0mqh2l9HIF0DONpCODTG0N+4qmbDdf4JW2eWVd6ZOdZYosgSctU92oDYWbv3gboifbl6qLeESfUu4Ro7Oy+v76wograsCGXYZ4sM3tvWTzLvcHTqAURO+4u+//7aj9TZLzdXgcrLdG1TosvSpc4xCgCPqmK+G27TQDSQV/OLb+BHfZQlllBL7oHv88ePKxjlz2IFCEirXOxPP0HnRFhj82b0bgncxGPmuA01/1tQbYxQj1Zprq1bxNHtLlQGYspWBSbC9PK/hOAw0RDD5qvK09l6s22mQIQKBgQD/0C40h1ERCyeQnp7OL3yq1g6gSPaJj0Kk9E5jbCezU30p1tUTBQr56FtAHL9GWQMq6Eku7YX92ut7i5yHri5F0xy1DpXWQ1O5ICQFd+/EZa1mAWUAsxGIKdU5LEGK/lY3xCANLcvxKrkcJ843Pct/vgvRSu86l/GZ29uQnAZiqwKBgQDLvVXMGvVt8gTPGAwE+HpZywpGjg1i8J9zFsr9IWyELW0m4p/ua2JgtFwHGzWf6SuM+8rl/7UomKN4qWO2AMdMY0VZVp9sXsaZmlOulRQapP+UWdkpVVyyZFpQPQvj08OoJfoh/Uv4czl8q++eaGr+Dpfe/LXEJ+KhdMLn3h/6pQKBgQDjL6TJW9+gDlL9+hE5Ni02n1JwATyKYGOkkZ/IZnY/qYcAEF49PP5JgZUAtEcfF9BbW94awjF8iN8XYp8KVKOduWbGWYSyvCpvv96dyK10s4Qykkx8OYv5bIWD+Azhfw1dC14XtjNoJDQvkB5xKv0+nq5qGm2pkOWtafduT1LQbwKBgBnX3V8zeDhG/7cpLLUgs5xaNeZ3NFl9fixCEuOC7tnhTTwmePTsKCFuH1JDcEzlbcHHWphMFDWfDvVKPzyh4qXc3boUU8qSRwzK15yIinFKxqSRtQsaKdTzB8WvyKdQgDv+/Th8gbt1GjCnOpDUW+X4AemmEoRcLtIeFm8E7PeFAoGBAKoqQhr2X8eH9IeZjupBebODKYcyoBAhvA4pF84AJM4VZp0GsOR3zDBvTTC1IKW/Md9s9p0T2bhN0msRLEKDxNkpm86S9eW+VpHUNVzOLo8TAQYOimoCGdmD4d3Tozsw7SS6xe+EKTY3Z+G8c1Yy1AYwPEefBmSpCIKSM6sHxJAJ";

	public static final String PUBLIC_KEY_PC = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAy5dHEdhB1vI2KCY/KuG+c+iYTplDS5jPI3+qrjWRkoT65qIPKYznkFn43yWSyCbadXro5JektH1k1JrGygCqjxrNZucy2D0UN2kTQqidl3e5vab6B2cO6kWpPZZJezeiAMPu3SRjjApGsU2Xob0+WtBwZZ0XWtRiTHZFI/RxymnSMr3RTOw+qYJ0quNGfFQV8QmY9ZZeWweCVZ+Zi2gMEEzNyX9w7xyp4zjvx8CkCM/knIT+s8ALbbAwuBgqh8dWi7Pt0EAulV5qtlDsPLfLKjpR5/iTQAA/SazWv/dwmKWLXReH5PfqWoxDUCCmdN6kxBNJgFzYvaG99Mk2CS2WNwIDAQAB";



	/**
	 * 支付宝提供给商户的服务接入网关URL(新)
	 */
	public static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";


	public static String buildNumber(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String code = simpleDateFormat.format(new Date()) + SMSCode.createRandomCode();
		return code;
	}



}
