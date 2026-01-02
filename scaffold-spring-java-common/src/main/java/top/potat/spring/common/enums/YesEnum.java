package top.potat.spring.common.enums;

/**
 * 1->是;2->否;
 */
public enum YesEnum {
    YES(1, "是"),
    NO(2, "否");
    private final Integer codeType;
    private final String codeName;
    YesEnum(Integer codeType, String codeName) {
        this.codeType = codeType;
        this.codeName = codeName;
    }


    /**
     * 通过codeType获取codeName
     */
    public static String getCodeTypeByCodeName(Integer codeType) {
        for (YesEnum value : YesEnum.values()) {
            if (value.codeType.equals(codeType)) {
                return value.codeName;
            }
        }
        return null;
    }

    /**
     * 通过codeType获取枚举
     */
    public static YesEnum getCodeTypeByEnum(Integer codeType) {
        for (YesEnum value : YesEnum.values()) {
            if (value.codeType.equals(codeType)) {
                return value;
            }
        }
        return YesEnum.YES;
    }

    public Integer getCodeType() {
        return codeType;
    }

    public String getCodeName() {
        return codeName;
    }
}
