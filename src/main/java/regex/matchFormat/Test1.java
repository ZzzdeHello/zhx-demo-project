package regex.matchFormat;

import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//正则表达式匹配 代码
public class Test1 {
//    public static void main(String[] args) {
//        final String warning = "合同号%s已欠费%s元，该合同号下最后一个资产拆机需要收取这笔欠费金额";
//        Item item = new Item();
//        item.setS("HT00101");
//        item.setL(100000L);
//        String result = String.format(warning, item.getS(), item.getL() / 100);
//        System.out.println(result);
//        System.out.println("基础成员列表：{}"+JSON.toJSONString(result));
//    }

//    public static void main(String[] args) {
//        String fileSuffixPattern = "_%04d.json.gz";
//        List list= new ArrayList();
//        list.add("2");list.add("3");list.add("4");list.add("5");list.add("6");
//        list.add("2");list.add("3");list.add("4");list.add("5");list.add("6");
//        list.add("2");list.add("3");list.add("4");list.add("5");list.add("6");
//        list.add("2");list.add("3");list.add("4");list.add("5");list.add("6");
//        list.add("2");list.add("3");list.add("4");list.add("5");list.add("6");
//        list.add("2");list.add("3");list.add("4");list.add("5");list.add("6");
//       //System.out.println(list.subList(0,2));
//        //文件名新建
//        //取除法结果作为文件后缀名称
//        int fileNum = list.size() % 2 == 0? list.size() / 2 :(list.size()/ 2) + 1;
//        ArrayList<String> fileSuffixList = new  ArrayList<String>();
//        if(list.size() <= 2) {
//            //不拆分固定 0000
//            fileSuffixList.add("_0000.json.gz");
//            uploadFileToSftp("_0000.json.gz",list);
//        }else {
//            for(int i = 1 ; i <= fileNum ;i++ ){
//                String fileSuffix = String.format(fileSuffixPattern,i);
//                if (i != fileNum) uploadFileToSftp(fileSuffix,list.subList(2 * (i-1), 2 * i));
//                else uploadFileToSftp(fileSuffix,list.subList(2 * (i-1) , list.size()));
//            }
//        }
//    }

    public static  void uploadFileToSftp(String fileSuffix  , List list){
        System.out.println("sftp 上传文件");
        System.out.println("入参1 为：" + fileSuffix);
        System.out.println("入参2 为：" + JSONObject.toJSONString(list));
        System.out.println("sftp 上传文件结束");
    }


    // 校验字符串是否是数字
    public static boolean isNum(String nameValue){
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher m = pattern.matcher(nameValue);
        if(!m.matches()){
            return false;
        }
        return true;
    }

//    public static void main(String[] args) {
//        Map<String,Object> map = new HashMap<>();
//        map.put("type","0100");
//        String type = Optional.ofNullable(map.get("type")).orElse("99").toString();
//        Integer typeInt = Integer.valueOf(type);
//        boolean b = isNum(type) && (typeInt >= 0 && typeInt < 100);
//        System.out.println(b);
//    }

    public static void main(String[] args) {
        String a = "ab"; String b = "leet_code/arithmetic" + "b";
        if(a==b){
            System.out.println("1");
        };
        if(a.equals(b)){
            System.out.println("2");
        }
    }

}
