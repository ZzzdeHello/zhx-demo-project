import cn.hutool.core.date.LocalDateTimeUtil;
import com.alibaba.fastjson.JSON;
import collection.map.filter.CollectionUtil;

import java.time.LocalDateTime;
import java.util.*;

public class Main02 {
    public static void main(String[] args) {
        List<String> list = new ArrayList();
        list.add("cs");
        Set<String> redCabinetIds = new HashSet<>(list);
        boolean cs = redCabinetIds.contains("cs");
        if (cs){
            System.out.println(JSON.toJSONString(redCabinetIds));
        }
    }
}
