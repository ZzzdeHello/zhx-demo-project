package collection.map.filter;

/**
 * 协同商机单订单状态
 *
 * @author lintao
 * @date 2020/4/18
 */
public enum TwBizOrderStatusCdEnum {

    INIT("1000", "待接单","待接单"),
    DEAL("10000", "待处理","待处理"),
    DISPATCH("10001", "待派单",""),
    CANCEL("20000", "已撤单","已处理"),
    FAILED("30000", "已退单","已处理"),
    FINISH("40000", "已归档","已处理");

    /**
     * 订单状态
     */
    private String statusCd;

    /**
     * 描述
     */
    private String name;

    /**
     * app上的描述
     */
    private String name2;

    TwBizOrderStatusCdEnum(String statusCd, String name, String name2) {
        this.statusCd = statusCd;
        this.name = name;
        this.name2 = name2;
    }

    public static String getStatusNameByStatusCd(String statusCd) {
        TwBizOrderStatusCdEnum[] values = TwBizOrderStatusCdEnum.values();
        for (TwBizOrderStatusCdEnum orderStatusCdEnum : values) {
            if (orderStatusCdEnum.getStatusCd().equals(statusCd)) {
                return orderStatusCdEnum.getName();
            }
        }
        return null;
    }

    public static String getStatusCdByStatusName(String statusName) {
        TwBizOrderStatusCdEnum[] values = TwBizOrderStatusCdEnum.values();
        for (TwBizOrderStatusCdEnum orderStatusCdEnum : values) {
            if (orderStatusCdEnum.getName().equals(statusName)) {
                return orderStatusCdEnum.getStatusCd();
            }
        }
        return null;
    }

    public static String[] getStatusCdByStatusName2(String statusName2) {
        String[] strings = new String[3];
        int i = 0;
        TwBizOrderStatusCdEnum[] values = TwBizOrderStatusCdEnum.values();
        for (TwBizOrderStatusCdEnum orderStatusCdEnum : values) {
            if (orderStatusCdEnum.getName2().equals(statusName2)) {
                strings[i]=orderStatusCdEnum.getStatusCd();
                i++;
            }
        }
        return strings;
    }

    public String getStatusCd() {
        return statusCd;
    }

    public String getName() {
        return name;
    }
    public String getName2() {
        return name2;
    }
}