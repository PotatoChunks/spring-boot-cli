package com.pt.api.http;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * RSA加密解密工具
 */
public class RSAUtils {
    private static final String RSA_ALGORITHM = "RSA";

    /**
     * 生成密钥对（公钥和私钥）
     *
     * @return 公钥和私钥的键值对
     */
    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        keyPairGen.initialize(2048, random); // 密钥长度为2048位

        return keyPairGen.generateKeyPair();
    }

    /**
     * 使用公钥加密字符串
     *
     * @param str 待加密的字符串
     * @param publicKey 公钥
     * @return 加密后的字节数组
     */
    public static byte[] encrypt(String str, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        int inputLen = bytes.length;

        while (inputLen - offSet > 0) {
            if (inputLen - offSet > 117) {
                out.write(cipher.doFinal(bytes, offSet, 117));
                offSet += 117;
            } else {
                out.write(cipher.doFinal(bytes, offSet, inputLen - offSet));
                break;
            }
        }
        return out.toByteArray();
    }

    /**
     * 使用私钥解密字符串
     *
     * @param data 待解密的字节数组
     * @param privateKey 私钥
     * @return 解密后的字符串
     */
    public static String decrypt(byte[] data, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] bytes = data;
        int inputLen = bytes.length;

        while (inputLen - offSet > 0) {
            if (inputLen - offSet > 128) {
                out.write(cipher.doFinal(bytes, offSet, 128));
                offSet += 128;
            } else {
                out.write(cipher.doFinal(bytes, offSet, inputLen - offSet));
                break;
            }
        }
        return new String(out.toByteArray(), StandardCharsets.UTF_8);
    }

    /**
     * 将公钥字符串转换成公钥对象
     *
     * @param publicKeyStr 公钥字符串（Base64编码）
     * @return 公钥对象
     */
    public static RSAPublicKey stringToPublicKey(String publicKeyStr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] decoded = Base64.getDecoder().decode(publicKeyStr);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
        KeyFactory kf = KeyFactory.getInstance(RSA_ALGORITHM);
        return (RSAPublicKey) kf.generatePublic(spec);
    }

    /**
     * 将私钥字符串转换成私钥对象
     *
     * @param privateKeyStr 私钥字符串（Base64编码）
     * @return 私钥对象
     */
    public static RSAPrivateKey stringToPrivateKey(String privateKeyStr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] decoded = Base64.getDecoder().decode(privateKeyStr);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
        KeyFactory kf = KeyFactory.getInstance(RSA_ALGORITHM);
        return (RSAPrivateKey) kf.generatePrivate(spec);
    }

    /**
     * 获取公钥字符串（Base64编码）
     *
     * @param publicKey 公钥对象
     * @return 公钥字符串（Base64编码）
     */
    public static String getPublicKeyString(PublicKey publicKey) throws IOException {
        byte[] publicKeyBytes = publicKey.getEncoded();
        return Base64.getEncoder().encodeToString(publicKeyBytes);
    }

    /**
     * 获取私钥字符串（Base64编码）
     *
     * @param privateKey 私钥对象
     * @return 私钥字符串（Base64编码）
     */
    public static String getPrivateKeyString(PrivateKey privateKey) throws IOException {
        byte[] privateKeyBytes = privateKey.getEncoded();
        return Base64.getEncoder().encodeToString(privateKeyBytes);
    }

    /**
     * 根据模数和指数生成公钥
     *
     * @param modulus 模数（Base64编码）
     * @param exponent 指数（Base64编码）
     * @return 公钥对象
     */
    public static PublicKey getPublicKeyFromModulusExponent(String modulus, String exponent) throws Exception {
        BigInteger b1 = new BigInteger(modulus);
        BigInteger b2 = new BigInteger(exponent);
        KeyFactory fact = KeyFactory.getInstance("RSA");
        RSAPublicKeySpec spec = new RSAPublicKeySpec(b1, b2);
        return fact.generatePublic(spec);
    }
}
