package top.potat.spring.common.utils.encryption;

/**
 * 加密工具类
 */
public class EncryptUtils {

    /**
     * 字符串脱敏处理，保留第一个字符，其余部分替换为星号(*)
     * 如果输入字符串长度小于等于1，则在后面追加两个星号
     *
     * @param input 原始字符串
     * @return 脱敏后的字符串
     */
    public static String desensitize(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        // 保留第一个字符，其余替换为星号
        StringBuilder result = new StringBuilder();
        // 处理特殊字符（如emoji）时确保正确截取第一个字符
        int firstCharCount = Character.isSurrogate(input.charAt(0)) ? 2 : 1;
        result.append(input, 0, firstCharCount);

        // 根据原始字符串长度添加对应数量的星号
        // 如果字符串长度小于等于1，默认追加两个星号
        int asteriskCount = Math.max(2, input.codePointCount(0, input.length()) - 1); // 至少添加2个星号

        for (int i = 0; i < asteriskCount; i++) {
            result.append('*');
        }

        return result.toString();
    }


    /**
     * 手机号脱敏处理，保留前3位，后面用星号替换
     */
    public static String desensitizePhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            return phone;
        }
        
        // 检查手机号长度是否足够
        if (phone.length() < 3) {
            // 如果长度不足3位，返回原字符串加上默认的星号掩码（例如：对于123 -> 123****）
            return phone + "****";
        } else {
            return phone.substring(0, 3) + "****";
        }
    }

}
