package com.pt.api.http;

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
    private static final String APP_KEY = "303606";

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

    public static void main(String[] args) {
        String encrypt = encrypt("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2NvZGUiOiI3MzU1NjA4IiwidXNlcl9uYW1lIjoiNzM1NTYwOCIsInNjb3BlIjpbImFsbCJdLCJhdGkiOiJkZTkyN2ExMC1iN2RhLTQzZmQtODIyOC0wOTgyNzVjYTYxNmMiLCJqdGMiOiJHVVZvMkNqYTJpdk04TnRtVzJQVlZuR242cjhFUEFNRTNId0g0bGh2eEtwQ2lOcFN2U0dCRkZoVC9MT0ZFRnN1IiwiZXhwIjoxNzAyNjIyMDYxLCJhdXRob3JpdGllcyI6WyLmma7pgJrnlKjmiLciXSwianRpIjoiNjRmOTA0ZmEtYWMzZC00MDlhLWI5ZTUtNjE0N2M2NzBhMzAyIiwiY2xpZW50X2lkIjoibXktYXBwLWNsaWVudCJ9.r_1i-TRuCJEZcBPYCJVEB_D5daRZunGhdcHgNFx5qHkeAAEWwUc-FvS4TxuNJSY-zQmDCETxBNW0FHz-X2Yb2B5ApH4r6SQIqJ0TtZ7XzOT5Hy0e8ipAfnLwi0-1T0HsvXn9AZnhzFRNfDVNoYoWMIRXd93i5wtdgyR-8IgLG1VzrsW76Pq6NhVFQB9SBmuJ53LfSQ_ZoWobAxVMjisWpZ-pRnMFF2Yx5OVze--9X9qklT4W_hb0WQUkNZeBYeuaVMbh6rHigTT7iM0caJfqLAHp_k5ZGN9Zh1nfDeoYm_vTD1JrjppwFIe8VZ-bQVHuG7OsAIvyq5-P02PDBDXPkQ");
        System.out.println(encrypt);
        System.out.println();
        System.out.println(decrypt(encrypt));

    }
}
