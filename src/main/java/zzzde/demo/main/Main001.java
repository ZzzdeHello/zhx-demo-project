package zzzde.demo.main;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author zzzde
 * @Date 2023/11/22
 */
public class Main001 {


    public static void main(String[] args) {
        String s = ",2,33,4,3," ;
        boolean b = enableFilterCabinetByConfig("2021434339", s);
        System.out.println(b);
    }


    /**
     * 是否根据规则过滤柜子
     *
     * @param cabinetId 柜子Id
     * @param strip nacos柜子配置
     * @return true 标识可通过 ;false标识不满足规则直接返回
     */
    private static boolean enableFilterCabinetByConfig(String cabinetId, String strip) {
        // 是否进入规则计算
        boolean b = false;
        if (StringUtils.isNotBlank(strip)) {
            String[] split = StringUtils.split(strip,",");
            for (String end : split) {
                if (cabinetId.endsWith(end)) {
                    b = true;
                }
            }
        }
        return b;
    }
}
