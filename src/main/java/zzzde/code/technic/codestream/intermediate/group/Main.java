package zzzde.code.technic.codestream.intermediate.group;

import com.alibaba.fastjson.JSON;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author zzzde
 * @Date 2023/11/27
 */
public class Main {
    public static void main(String[] args) {
        // 数据初始化
        List<BatteryInfoDTO> batteryInfoDTOS = new ArrayList<>();
        BatteryInfoDTO ceshi101 = BatteryInfoDTO.builder().batteryType(1).batteryId("ceshi101").batterySku(10001).volume(98).build();
        BatteryInfoDTO ceshi102 = BatteryInfoDTO.builder().batteryType(1).batteryId("ceshi102").batterySku(10001).volume(95).build();
        BatteryInfoDTO ceshi103 = BatteryInfoDTO.builder().batteryType(2).batteryId("ceshi103").batterySku(20001).volume(31).build();
        BatteryInfoDTO ceshi104 = BatteryInfoDTO.builder().batteryType(2).batteryId("ceshi104").batterySku(30001).volume(35).build();
        BatteryInfoDTO ceshi105 = BatteryInfoDTO.builder().batteryType(2).batteryId("ceshi105").batterySku(30001).volume(92).build();
        BatteryInfoDTO ceshi106 = BatteryInfoDTO.builder().batteryType(2).batteryId("ceshi106").batterySku(null).volume(92).build();
        BatteryInfoDTO ceshi107 = BatteryInfoDTO.builder().batteryType(2).batteryId("ceshi107").batterySku(null).volume(93).build();
        batteryInfoDTOS.add(ceshi101);batteryInfoDTOS.add(ceshi102);batteryInfoDTOS.add(ceshi103);batteryInfoDTOS.add(ceshi104);batteryInfoDTOS.add(ceshi105);
        batteryInfoDTOS.add(ceshi106);
        batteryInfoDTOS.add(ceshi107);

        batteryInfoDTOS = batteryInfoDTOS.stream().peek(x -> x.setBatterySku(x.getBatterySku() == null ? -1 : x.getBatterySku())).collect(Collectors.toList());


        Map<Integer, Map<Integer, List<BatteryInfoDTO>>> collect = batteryInfoDTOS.stream().collect(Collectors.groupingBy(BatteryInfoDTO::getBatteryType, Collectors.groupingBy(BatteryInfoDTO::getBatterySku)));
        System.out.println(JSON.toJSONString(collect));

        Optional.ofNullable(collect).orElseGet(HashMap::new).forEach((batteryType,tempMapBattery) -> {
            Optional.ofNullable(tempMapBattery).orElseGet(HashMap::new).forEach( (batterySku,tempGroupBattery) -> {
                List<BatteryInfoDTO> tempGroup = tempGroupBattery.stream().map(x ->
                        BatteryInfoDTO.builder()
                                .batteryId(x.getBatteryId())
                                .capacity(x.getCapacity())
                                .volume(x.getVolume())
                                .boxNum(x.getBoxNum())
                                .batteryType(x.getBatteryType())
                                .batterySku(x.getBatterySku())
                                .extra(x.getExtra())
                                .build()
                ).collect(Collectors.toList());
                // 规则计算
                System.out.println("规则计算");
                System.out.println(JSON.toJSONString(tempGroup));
              }
            );
          }
        );
    }
}
