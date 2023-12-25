package zzzde.code.technic.collection.map.filter;

public class Test2 {
    public static void main(String[] args) {
        String s = "dd";
        String[] statusCdByStatusName2 = TwBizOrderStatusCdEnum.getStatusCdByStatusName2(s);
        for(String stats :statusCdByStatusName2){
            if(stats!=null)
            System.out.println(stats);
        }
        //System.out.println(statusCdByStatusName2[0]+"--"+statusCdByStatusName2[1]+"--"+statusCdByStatusName2[2]+"--");
    }
}
