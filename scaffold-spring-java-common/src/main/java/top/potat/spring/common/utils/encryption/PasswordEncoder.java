package top.potat.spring.common.utils.encryption;

import top.potat.spring.common.utils.StringUtils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

/**
 * 生产级密码加密工具
 * 使用PBKDF2WithHmacSHA256算法，自动管理盐值和迭代次数
 */
public class PasswordEncoder {
    // 加密算法（PBKDF2WithHmacSHA256是NIST推荐的标准算法）
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";

    // 盐值长度（字节）
    private static final int SALT_LENGTH = 16;

    // 密钥长度（位）
    private static final int KEY_LENGTH = 256;

    // 迭代次数（可根据服务器性能调整，建议至少100,000次）
    private static final int ITERATIONS = 100000;

    // 加密版本标识（用于未来算法升级）
    private static final String VERSION_PREFIX = "$1$";

    // 安全随机数生成器
    private static final SecureRandom secureRandom;

    static {
        try {
            // 使用更强的随机数生成器
            secureRandom = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("无法初始化安全随机数生成器", e);
        }
    }

    /**
     * 加密密码（静态方法）
     * @param rawPassword 明文密码
     * @return 加密后的密码字符串（包含版本、迭代次数、盐值和哈希值）
     */
    public static String encrypt(String rawPassword) {
        if (StringUtils.isEmpty(rawPassword)) {
            return null;
        }

        byte[] salt = generateSalt();
        byte[] hash = pbkdf2(rawPassword.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        return formatEncryptedPassword(salt, hash);
    }

    /**
     * 验证密码（静态方法）
     * @param rawPassword 明文密码
     * @param encryptedPassword 加密后的密码
     * @return 是否匹配
     */
    public static boolean verify(String rawPassword, String encryptedPassword) {
        if(StringUtils.isEmpty(rawPassword) || StringUtils.isEmpty(encryptedPassword))
            return false;

        PasswordComponents components = parseEncryptedPassword(encryptedPassword);
        if (components == null) {
            return false;
        }

        byte[] hashToVerify = pbkdf2(
                rawPassword.toCharArray(),
                components.salt,
                components.iterations,
                KEY_LENGTH
        );

        return slowEquals(components.hash, hashToVerify);
    }

    /**
     * 生成随机盐值
     */
    private static byte[] generateSalt() {
        byte[] salt = new byte[SALT_LENGTH];
        secureRandom.nextBytes(salt);
        return salt;
    }

    /**
     * 执行PBKDF2哈希计算
     */
    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int keyLength) {
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("密码加密失败", e);
        }
    }

    /**
     * 格式化加密后的密码
     * 格式: $版本$迭代次数$盐值(Base64)$哈希值(Base64)
     */
    private static String formatEncryptedPassword(byte[] salt, byte[] hash) {
        return String.format("%s%d$%s$%s",
                VERSION_PREFIX,
                ITERATIONS,
                Base64.getEncoder().encodeToString(salt),
                Base64.getEncoder().encodeToString(hash)
        );
    }

    /**
     * 解析加密后的密码
     */
    private static PasswordComponents parseEncryptedPassword(String encrypted) {
        if (!encrypted.startsWith(VERSION_PREFIX)) {
            return null;
        }

        String[] parts = encrypted.substring(VERSION_PREFIX.length()).split("\\$");
        if (parts.length != 3) {
            return null;
        }

        try {
            int iterations = Integer.parseInt(parts[0]);
            byte[] salt = Base64.getDecoder().decode(parts[1]);
            byte[] hash = Base64.getDecoder().decode(parts[2]);

            return new PasswordComponents(iterations, salt, hash);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 慢比较算法，防止时序攻击
     */
    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++) {
            diff |= a[i] ^ b[i];
        }
        return diff == 0;
    }

    /**
     * 密码组件内部类
     */
    private static class PasswordComponents {
        final int iterations;
        final byte[] salt;
        final byte[] hash;

        PasswordComponents(int iterations, byte[] salt, byte[] hash) {
            this.iterations = iterations;
            this.salt = salt;
            this.hash = hash;
        }
    }


}
