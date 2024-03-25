package zzzde.project.technic.localdemo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zzzde
 * @Date 2024/3/8
 */
public class Demo2 {

    public static void main(String[] args) {
        List<Integer> prizeList = new ArrayList<>();
        prizeList.add(1);
        prizeList.add(2);
        boolean b = checkPrizeBelong(prizeList, 1, 3);
        System.out.println(b);
    }


    private static boolean checkPrizeBelong(List<Integer> prizeList, Integer stairLevel, Integer customerLaxinCount) {
        // 计算骑手实际应该命中的档位
        int actualStairLevel = 0;
        for (int i = 1; i <= prizeList.size(); i++) {
            if (customerLaxinCount >= prizeList.get(i - 1)) {
                actualStairLevel = i;
            }
        }
        return stairLevel.equals(actualStairLevel);
    }
}
