package top.potat.spring.common.utils;

/**
 * 身份证工具类
 */
public class IDCardValidator {
    private static final int[] WEIGHTS = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    private static final char[] CHECK_CODES = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};

    /**
     * 验证身份证
     * @param idCard
     * @return true:有效,false:无效
     */
    public static boolean validateIDCard(String idCard) {
        if (idCard == null || idCard.length() != 18) {
            return false;
        }

        String first17Digits = idCard.substring(0, 17);
        if (!first17Digits.matches("\\d{17}")) {
            return false;
        }

        char lastDigit = idCard.charAt(17);
        if (!Character.isDigit(lastDigit) && lastDigit != 'X') {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 17; i++) {
            int digit = Character.getNumericValue(idCard.charAt(i));
            sum += digit * WEIGHTS[i];
        }

        int index = sum % 11;
        char expectedCheckCode = CHECK_CODES[index];

        return lastDigit == expectedCheckCode;
    }
}
