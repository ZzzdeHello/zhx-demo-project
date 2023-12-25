package zzzde.code.technic.collection.map.filter;

/**
 * 协同商机单订单类型
 */
public enum TwBizOrderTypeEnum {

    TW_ORDER_ONLINE("3", "EVT0000000101", "线上扫码"),
    TW_ORDER_UNDERLINE("O2O", "EVT0000000102", "电话到家"),
    TW_ORDER_REMOVE("4", "EVT0000000103", "预约拆机"),
    TW_ORDER_FACE2FACE("5", "EVT0000000109", "面对面扫码"),
    BSS_ORDER("9", "", "3.0预受理订单");

    /**
     * 订单类型orderType
     */
    private String value;

    /**
     * 事件编码eventCode
     */
    private String code;

    /**
     * 订单类型
     */
    private String valueName;

    TwBizOrderTypeEnum(String value, String code, String valueName) {
        this.value = value;
        this.code = code;
        this.valueName = valueName;
    }

    public static String getValueByCode(String code) {
        TwBizOrderTypeEnum[] values = TwBizOrderTypeEnum.values();
        for (TwBizOrderTypeEnum orderTypeEnum : values) {
            if (orderTypeEnum.getCode().equals(code)) {
                return orderTypeEnum.getValue();
            }
        }
        return null;
    }

    public static String getValueNameByCode(String code) {
        TwBizOrderTypeEnum[] values = TwBizOrderTypeEnum.values();
        for (TwBizOrderTypeEnum orderTypeEnum : values) {
            if (orderTypeEnum.getCode().equals(code)) {
                return orderTypeEnum.getValueName();
            }
        }
        return null;
    }

    public static String getValueNameByValue(String value) {
        TwBizOrderTypeEnum[] values = TwBizOrderTypeEnum.values();
        for (TwBizOrderTypeEnum orderTypeEnum : values) {
            if (orderTypeEnum.getValue().equals(value)) {
                return orderTypeEnum.getValueName();
            }
        }
        return null;
    }

    public static String getCodeByValueName(String valueName) {
        TwBizOrderTypeEnum[] values = TwBizOrderTypeEnum.values();
        for (TwBizOrderTypeEnum orderTypeEnum : values) {
            if (orderTypeEnum.getValueName().equals(valueName)) {
                return orderTypeEnum.getCode();
            }
        }
        return null;
    }

    public static String getValueByValueName(String valueName) {
        TwBizOrderTypeEnum[] values = TwBizOrderTypeEnum.values();
        for (TwBizOrderTypeEnum orderTypeEnum : values) {
            if (orderTypeEnum.getValueName().equals(valueName)) {
                return orderTypeEnum.getValue();
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public String getCode() {
        return code;
    }

    public String getValueName() {
        return valueName;
    }

}