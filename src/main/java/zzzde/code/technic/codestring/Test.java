package zzzde.code.technic.codestring;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        String id = "0123";
        List<String> oldList = new ArrayList();
        oldList.add("1-DDDD");
        oldList.add("2-DDDD");
        oldList.add("3-DDDD");
        oldList.add("4-DDDD");
        oldList.add("5-DDDD");
        StringBuilder targetObjNbrResult = new StringBuilder();
        for(String temps : oldList){
            targetObjNbrResult.append( temps + ",");
        }
        String s = targetObjNbrResult.toString();
//        String sequence = String.format("%08d", Integer.parseInt(id));//自动补全至八位
//        System.out.println(sequence);
        String targetObjNbrRs  = s.substring(0,s.length() - 1);
        System.out.println(targetObjNbrRs);

        StringBuilder targetObjNbrResultsss = new StringBuilder();
        String s1 = targetObjNbrResultsss.toString();
        if(s1.equals("")) System.out.println(targetObjNbrResultsss.toString() + "11111111111" );

    }

}
