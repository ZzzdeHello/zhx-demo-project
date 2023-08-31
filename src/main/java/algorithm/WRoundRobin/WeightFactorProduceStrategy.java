package algorithm.WRoundRobin;

import org.apache.commons.lang3.StringUtils;
import trycatch.AssertUtil;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeightFactorProduceStrategy implements IProduceStrategy {
    private int i = -1; //表示上一次选择的服务器
    private int cw = 0; //表示当前调度的权值
    private int gcd = 0; //当前所有权重的最大公约数 比如 2，4，8 的最大公约数为：2
    private int maxWeight;

    private List<Integer> weights = null;  //作用计算最大公约数
    private PartitionWeightRRParameter weightRRParametersDns[] = null;

    /**
     *  按照轮询调研权重配置，格式如下:partition1:weight,partition2:weight
     * @param partConfig
     */
    public WeightFactorProduceStrategy(String partConfig) {
        validate(partConfig);
        this.initWeigthParam(parseCsvMap(partConfig));
    }

    private Pattern pattern = Pattern.compile("([\\d+\\:\\d+],?){1,}");

    private void validate(String partConfig) {
        if (partConfig.length() <= 0)
            throw new InvalidPartitonConfigException("partition config is incorrect :" + partConfig);
        else if (partConfig.equals(".") || partConfig.equals(".."))
            throw new InvalidPartitonConfigException("partition config is incorrect :" + partConfig);

        Matcher matcher = pattern.matcher(partConfig);
        if(matcher.find()) {
            String rexStr = matcher.group();
            if (!rexStr.equals(partConfig))
                throw new InvalidPartitonConfigException("partition config is incorrect :" + partConfig);
        } else {
            throw new InvalidPartitonConfigException("partition config is incorrect :" + partConfig);
        }
    }

    /**
     * 格式如下:partition1:weight,partition2:weight
     * @param csvMap
     */
    private void initWeigthParam(Map<String, String> csvMap) {
        weightRRParametersDns = new PartitionWeightRRParameter[csvMap.size()];
        int numPart = 0;
        weights = new ArrayList<Integer>(csvMap.size());
        Set<Map.Entry<String, String>> entrySet;
        entrySet = csvMap.entrySet();
        for(Iterator<Map.Entry<String, String>> its = entrySet.iterator(); its.hasNext(); ) {
            Map.Entry<String, String> entry = its.next();
            weights.add(Integer.valueOf(entry.getValue()));
            weightRRParametersDns[numPart++] = new PartitionWeightRRParameter(Integer.valueOf(entry.getKey()), Integer.valueOf(entry.getValue()));
        }

        gcd = getGcdByList(weights);
        maxWeight = getMaxWeight();
    }

    /**
     * 计算最大公约数
     * @param weight_m 权重数
     * @param weight_n 权重数
     * @return
     */
    private int GCD(int weight_m,int weight_n)
    {
        int temp;
        while(weight_n != 0){
            if(weight_m < weight_n){
                temp = weight_m;
                weight_m = weight_n;
                weight_n = temp;
            }
            temp = weight_m - weight_n;
            weight_m = weight_n;
            weight_n = temp;
        }
        return weight_m;
    }

    /**
     *
     * @param weights      权重列表
     * @param startIndex   list索引值,起始位置。
     * @param nextGcd      传入最大公约数
     * @return
     */
    private int getGcdByList(List<Integer> weights, int startIndex, int nextGcd) {
        if ( weights.size() < 2) {
            throw new IllegalArgumentException("At least a number of parameters for 2");
        }
        if (weights.size() == 2 && startIndex == 0) {
            return this.GCD(weights.get(startIndex), weights.get(startIndex + 1));
        }

        if (startIndex + 1 > weights.size() -1 )
            return nextGcd;
        int curGcd = nextGcd > 0 ? nextGcd : weights.get(startIndex);
        int nextIndex = startIndex + 1;
        nextGcd = GCD(curGcd, weights.get(startIndex + 1));              //0,1

        return getGcdByList(weights, nextIndex, nextGcd);
    }

    private int getGcdByList(List<Integer> weights) {
        return this.getGcdByList(weights, 0, 0);
    }

    private int getWeightDns() {
        for ( ; ; ) {
            i = (i + 1) % weightRRParametersDns.length;
            if (i == 0) {
                cw = cw - gcd;  //表示当前调度的权值
                if (cw <= 0) {
                    cw = maxWeight;
                    if (cw == 0) {
                        return 0;
                    }
                }
            }

            if (weightRRParametersDns[i].getWeight() >= cw ) {
                return weightRRParametersDns[i].getPartition();
            }
        }
    }

    private int getMaxWeight() {
        int max = 0;
        for (int i = 0; i< weightRRParametersDns.length;i++) {
            if (weightRRParametersDns[i].getWeight() >= max) {
                max = weightRRParametersDns[i].getWeight();
            }
        }

        return max;
    }

    public int getPartitionIdForTopic() {
        return this.getWeightDns();
    }

    /**
     * 分区权重参数类
     */
    static class PartitionWeightRRParameter {
        private int partition;
        private int weight;

        public PartitionWeightRRParameter(int partition, int weight) {
            this.partition = partition;
            this.weight = weight;
        }

        public int getPartition() {
            return partition;
        }

        public int getWeight() {
            return weight;
        }
    }

    //解析string类型为map 根据“，”“：”
    private Map<String, String> parseCsvMap(String patternConfig){
        Map<String, String> csvMap = new HashMap<>();
        AssertUtil.isTrue(StringUtils.isNotBlank(patternConfig),"配置信息不可为空");
        String[] nameServices = patternConfig.split(",");
        for(String nameSvr : nameServices){
            String[] split = nameSvr.split(":");
            csvMap.put(split[0],split[1]);
        }
        return csvMap;
    }

}
