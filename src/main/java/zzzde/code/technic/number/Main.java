package zzzde.code.technic.number;

import java.text.NumberFormat;

public class Main {
    public static void main(String[] args) {
        int processingAndHandleOrder = 100 ;
        int handleOrder = 10 ;
        String per = "";
        if (processingAndHandleOrder == 0){
            per = "100";
        }else {
            NumberFormat numberFormat = NumberFormat.getInstance();
            numberFormat.setMaximumFractionDigits(1);
            per = numberFormat.format((float)  handleOrder/ (float)processingAndHandleOrder* 100);//所占百分比
        }
        System.out.println(per);
    }
}
