package com.magic.aimai.business.pay;

import com.magic.aimai.business.util.Base64;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class SignUtils {

    private static final String ALGORITHM = "RSA2";

    private static final String SIGN_ALGORITHMS = "SHA256WithRSA";

    private static final String DEFAULT_CHARSET = "UTF-8";

    public static String sign(String content, String privateKey) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
                    Base64.decode(privateKey));
            KeyFactory keyf = KeyFactory.getInstance(ALGORITHM);
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);

            java.security.Signature signature = java.security.Signature
                    .getInstance(SIGN_ALGORITHMS);

            signature.initSign(priKey);
            signature.update(content.getBytes(DEFAULT_CHARSET));

            byte[] signed = signature.sign();

            return Base64.encode(signed);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String sign(Map<String, String> param, String privateKey) {
        String ret = "";
        for (String key : param.keySet()) {
            if (key.equals("sign")) {
                continue;
            } else {
                ret += key + "=\"" + param.get(key) + "\"&";
            }
        }
        ret = subLastChart(ret, "&");
        return sign(ret, privateKey);
    }




    /**
     * 去除字符串最后一个特点字符
     */
    public static String subLastChart(String str, String c) {
        if (str == null || "".equals(str)) {
            return "";
        }
        if (c == null || "".equals(c)) {
            return str;
        }
        if (str.length() <= c.length()) {
            if (str.equals(c)) {
                return "";
            } else {
                return str;
            }
        }
        String lastc = str.substring(str.length() - c.length());
        if (lastc.equals(c)) {
            return str.substring(0, str.length() - c.length());
        } else {
            return str;
        }
    }
}
