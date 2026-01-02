package top.potat.spring.common.utils;

import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机工具类
 */
public class RandomUtils {
    // 定义包含所有可能字符的字符串
    private static final String ALL_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int BASE = ALL_CHARACTERS.length();
    private static final SecureRandom RANDOM = new SecureRandom();

    /**随机字母*/
    public static String getRandomLetter(int length){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(Integer.toString(new SecureRandom().nextInt(34) + 2,36));
        }
        return stringBuilder.toString().toUpperCase();
    }
    /**随机六位数字 不带4*/
    public static String generateRandomString() {
        StringBuilder sb = new StringBuilder();
        String allowedChars = "012356789";

        for (int i = 0; i < 6; i++) {
            int index = ThreadLocalRandom.current().nextInt(allowedChars.length());
            sb.append(allowedChars.charAt(index));
        }

        return sb.toString();
    }

    /**
     * 随机32字符
     */
    public static String generate32CharRandomString() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        String base64Encoded = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
        // 截取前 32 个字符
        return base64Encoded.substring(0, Math.min(base64Encoded.length(), 32));
    }

    /**生成11位完全随机不会重复的 随机字符串*/
    public static String getRandomCode(){
        return Long.toString(System.currentTimeMillis(),36) + String.format("%03d",new SecureRandom().nextInt(999));
    }

    /**生成 12位 随机不重复无规律的字符串*/
    public static String getRandomNoLaw(){
        List<String> stringList = Arrays.asList((Long.toString(System.currentTimeMillis(), 36).substring(3).replace("0",getRandomLetter(1)).toUpperCase().replace("1",getRandomLetter(1)).toUpperCase() + getRandomLetter(7)).split(""));
        Collections.shuffle(stringList);
        return String.join("", stringList);
    }

    /**
     * 生成 n位 随机不重复无规律的数字字母字符串
     */
    public static String getRandomNoLaw(int n){
        List<String> stringList = Arrays.asList((Long.toString(System.currentTimeMillis(), 36).substring(3).replace("0",getRandomLetter(1)).toUpperCase().replace("1",getRandomLetter(1)).toUpperCase() + getRandomLetter(n)).split(""));
        Collections.shuffle(stringList);
        return String.join("", stringList);
    }

    /**
     * 随机字符串
     */
    public static String generateRandomString(int n) {
        if (n < 1) n = 1;

        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int randomIndex = RANDOM.nextInt(ALL_CHARACTERS.length());
            sb.append(ALL_CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }

    /**
     * 生成指定位数的全球唯一字符串（包含大小写字母和数字）
     * @param length 生成的字符串长度
     */
    public static String generateOnlyOne(int length) {
        if (length < 1) length = 10;

        StringBuilder sb = new StringBuilder(length);
        UUID uuid = UUID.randomUUID();
        long mostSignificantBits = uuid.getMostSignificantBits();
        long leastSignificantBits = uuid.getLeastSignificantBits();

        // 使用UUID和随机数组合填充所需长度
        for (int i = 0; i < length; i++) {
            // 混合UUID和随机数的位，提高唯一性
            if (i % 3 == 0) {
                // 使用mostSignificantBits的位
                int index = (int) ((mostSignificantBits >>> (i * 5)) & 0x3F) % BASE;
                sb.append(ALL_CHARACTERS.charAt(index));
            } else if (i % 3 == 1) {
                // 使用leastSignificantBits的位
                int index = (int) ((leastSignificantBits >>> (i * 5)) & 0x3F) % BASE;
                sb.append(ALL_CHARACTERS.charAt(index));
            } else {
                // 使用随机字符增强唯一性
                sb.append(ALL_CHARACTERS.charAt(RANDOM.nextInt(BASE)));
            }
        }

        return sb.toString();
    }

    /**
     * 方式 1：基于 UUID 生成 8 位邀请码（纯字母数字，唯一概率高）
     */
    public static String generateByUUID() {
        // 生成 UUID（32 位），去除横杠后取前 8 位
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        // 取前 8 位并转为大写（可选）
        return uuid.substring(0, 8).toUpperCase();
    }

    /**
     * 方式 2：自定义字符集生成指定长度邀请码（可控制长度和字符范围）
     * @param length 邀请码长度（推荐 6-10 位）
     */
    public static String generateCustom(int length) {
        // 自定义字符集：排除易混淆的字符（如 0 和 O，1 和 I）
        String chars = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            // 随机从字符集中选取一个字符
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }

    /**
     * 方式 3：结合用户 ID 生成（可反向解析，适合需要关联用户的场景）
     * @param userId 用户 ID（确保唯一）
     */
    public static String generateWithUserId(Long userId) {
        // 1. 将用户 ID 转为 36 进制（缩短长度）
        String base36 = Long.toString(userId, 36).toUpperCase();
        // 2. 补充随机字符（避免直接暴露 ID）
        String randomStr = generateCustom(4); // 4 位随机字符
        // 3. 组合：随机字符 + 36 进制 ID（总长度动态，推荐控制在 8-12 位）
        return randomStr + base36;
    }


    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception ignored) {
        }
    }

}
