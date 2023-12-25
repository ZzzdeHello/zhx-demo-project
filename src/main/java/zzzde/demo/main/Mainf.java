package zzzde.demo.main;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.NumberUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 快速构建ArrayList
 *
 * @Author zzzde
 * @Date 2023/9/21
 */
public class Mainf {
    public static void main(String[] args) {
        String mailSendToList = ",ss,sda,,,";
        String[] split = StringUtils.split(mailSendToList, ",");
        Set<String> set = Arrays.stream(split).collect(Collectors.toSet());
        System.out.println(set);

        String s = NumberUtil.formatPercent(0.011123, 3);
        System.out.println(s);
    }

}
