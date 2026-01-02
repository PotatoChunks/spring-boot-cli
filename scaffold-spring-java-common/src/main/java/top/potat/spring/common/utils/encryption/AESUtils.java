package top.potat.spring.common.utils.encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * AES加密工具
 */
public class AESUtils {
    private static final String ALGORITHM = "AES";
    /**
     * 设置加密密码处理长度。
     */
    private static final int PWD_SIZE = 16;
    /**
     * 密钥
     */
    private static final String APP_KEY = "mmddHeard";

    /**
     * 加密
     */
    public static String encrypt(String valueToEnc, String key) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(pwdHandler(key), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encryptedValue = cipher.doFinal(valueToEnc.getBytes());
            return Base64.getEncoder().encodeToString(encryptedValue);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    public static String encrypt(String valueToEnc){
        return encrypt(valueToEnc,APP_KEY);
    }

    /**
     * 解密
     */
    public static String decrypt(String encryptedValue, String key) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(pwdHandler(key), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encryptedValue));
            return new String(original);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String encryptedValue){
        return decrypt(encryptedValue,APP_KEY);
    }

    /**
     * 密码处理方法
     */
    private static byte[] pwdHandler(String password) throws UnsupportedEncodingException {
        byte[] data = null;
        if (password == null) {
            password = "";
        }
        StringBuffer sb = new StringBuffer(PWD_SIZE);
        sb.append(password);
        while (sb.length() < PWD_SIZE) {
            sb.append("0");
        }
        if (sb.length() > PWD_SIZE) {
            sb.setLength(PWD_SIZE);
        }
        data = sb.toString().getBytes(StandardCharsets.UTF_8);
        return data;
    }


    /**
     * 生成银行卡号掩码(保留前4位和后4位)
     */
    public static String maskCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() < 8) {
            return cardNumber;
        }
        return cardNumber.substring(0, 4) +
                "****" +
                cardNumber.substring(cardNumber.length() - 4);
    }

}
