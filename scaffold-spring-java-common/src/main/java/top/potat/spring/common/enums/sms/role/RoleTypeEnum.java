package top.potat.spring.common.enums.sms.role;

import lombok.Getter;

/**
 * 角色类型枚举
 * 1->用户角色；2->后台角色；
 */
@Getter
public enum RoleTypeEnum {
    USER_ROLE(1, "用户角色"),
    BACK_ROLE(2, "后台角色"),
    ;

    private final Integer codeType;
    private final String codeName;

    RoleTypeEnum(Integer codeType, String codeName) {
        this.codeType = codeType;
        this.codeName = codeName;
    }

    /**
     * 根据枚举值获取枚举
     *
     * @param codeType 枚举值
     * @return 枚举
     */
    public static RoleTypeEnum getCodeByEnum(Integer codeType) {
        for (RoleTypeEnum value : RoleTypeEnum.values()) {
            if (value.codeType.equals(codeType)) {
                return value;
            }
        }
        return BACK_ROLE;
    }

}
