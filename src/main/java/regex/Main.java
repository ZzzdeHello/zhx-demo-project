package regex;

import com.github.pagehelper.util.StringUtil;

// 正则表达式
public class Main {

    public static void main(String[] args) {
        String string = "【市】金华市_4升5行动_1月星级美好大升级_20220107_34471_101_202201071107_" ;
        boolean b = fileNameMatches(string);
        System.out.println(b);
        String startDate = "";
        String endDate = "2011-10-10";
        if (StringUtil.isNotEmpty(startDate)){
            System.out.println(startDate.length());
            if (!isDateStr(startDate)) {
                System.out.println("日期格式错误");
            }
        }
        if ( StringUtil.isNotEmpty(endDate)){
            if (!isDateStr(endDate)) {
                System.out.println("日期格式错误");
            }
        }
        System.out.println(isDateStr(startDate));
        System.out.println(isDateStr(endDate));
    }

    //taskTitle_42010_101_20211219112210111.xls 文件格式规则。FILE_initId_batchNbr_yyyyMMddHHmmssSSS
    private static boolean fileNameMatches(String fileName){
        String regex="(.*)(_\\d+){3}$";
        boolean matches = fileName.matches(regex);
        return matches;
    }

    public static boolean isDateStr(String string){
        String regex = "^((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29))$"; ;
        boolean b = string.matches(regex);
        return  b;
    }

}
