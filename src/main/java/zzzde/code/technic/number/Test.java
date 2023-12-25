package zzzde.code.technic.number;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
//        int allCount = 9000;
//        int cycleNum = allCount % 3000 == 0 ? allCount / 3000 : allCount / 3000 + 1 ;
//        System.out.println( cycleNum );
//
//        int listSize = 100;
//        int length = 3;
//        for(int j = 0 ; j <= listSize ; j++){
//            int scale = listSize / length + 1 ;
//            System.out.println( j % length );
//            System.out.println( scale );
//            System.out.println( 2/100 );
//        }
        List list = new ArrayList();
        int personSize = 3;
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
        System.out.println( list.size() % personSize);
        int scale = list.size() % personSize == 0 ? list.size() / personSize : list.size()/ personSize +  1 ;
        int y = list.size() % personSize  ;
        System.out.println(scale);
        for(int i = 1 ; i<= personSize ;i++){
//            for(int j =0 ; j < scale ;j++ ){
//                List list1 = list.subList( (i - 1) * scale, i * scale - 1);
//                System.out.println(list1.size());
//                System.out.println(list1.toString());
//            }
//            List list1 = list.subList( ( i-1) * scale  , scale * i  );
//            System.out.println(list1.toString());
        }
        System.out.println("--------------------");
        String s = "3,23";
        String[] orgIdPathS = s.split(",");
        String org4AId = orgIdPathS[orgIdPathS.length-1];// areaLv2开始算
        System.out.println(org4AId);
        System.out.println("--------------------");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("123").append(",");
        System.out.println(stringBuffer.length());
    }
}
