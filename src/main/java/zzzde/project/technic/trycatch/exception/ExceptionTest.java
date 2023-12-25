package zzzde.project.technic.trycatch.exception;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;

import java.util.Date;

/**
 * @Author zzzde
 * @Date 2023/12/5
 */
public class ExceptionTest {

    public static void main(String[] args) {
        String collectTime = DateUtil.formatDateTime(new Date());
        System.out.println(collectTime);
        Double dtod = NumberUtil.div(NumberUtil.sub(0.122D, 0.121D), 0.121D, 5);
        System.out.println(dtod);
        System.out.println(NumberUtil.formatPercent(0.00072, 3));
    }
}
