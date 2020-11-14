package com.zgw.pdpcommon.constant;

/**
 * @author 忘忧症
 * @Distribute
 */
public enum CommonStatusEnum {

    SUCCESS(200,"成功"),
    INTERNAL_SERVER_EXCEPTION(500,"失败");

    private final Integer code;
    private final String value;

    CommonStatusEnum(Integer code,String value){
        this.code=code;
        this.value=value;
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
