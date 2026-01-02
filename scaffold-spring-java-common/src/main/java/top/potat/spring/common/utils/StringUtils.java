package top.potat.spring.common.utils;

public class StringUtils {

    /** 空字符串 */
    private static final String ENPTYSTR = "";

    /**
     * * 判断一个字符串是否为空串
     *
     * @param str String
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(String str)
    {
        return isNull(str) || ENPTYSTR.equals(str.trim());
    }

    /**
     * * 判断一个字符串是否为非空串
     *
     * @param str String
     * @return true：非空串 false：空串
     */
    public static boolean isNotEmpty(String str)
    {
        return !isEmpty(str);
    }

    /**
     * * 判断一个对象是否为空
     *
     * @param object Object
     * @return true：为空 false：非空
     */
    public static boolean isNull(Object object)
    {
        return object == null;
    }

    /**
     * * 判断一个对象是否非空
     *
     * @param object Object
     * @return true：非空 false：空
     */
    public static boolean isNotNull(Object object)
    {
        return !isNull(object);
    }

    /**
     * 去空格
     * @param str String
     * @return  String
     */
    public static String trim(String str)
    {
        return (str == null ? ENPTYSTR : str.trim());
    }

    /**
     * 是否是json字符串
     * 开头和结尾要么是"{}" 要么是"[]"
     * @param str String
     * @return  true:json字符串, false:非json字符串
     */
    public static boolean isJson(String str){
        if(isEmpty(str)) return false;
        return (str.startsWith("{") && str.endsWith("}")) || (str.startsWith("[") && str.endsWith("]"));
    }


    /**
     * 手机号脱敏处理，保留前3位，后面用星号替换
     * @param phone 手机号
     * @return 脱敏后的手机号
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
