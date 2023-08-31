package string;

public class splitTest {
    //String 分割
    public static void main(String[] args) {
        String string = "xiaoming【市场部】";
        System.out.println(string.split("【")[0]);
        String s = "1";
        String[] split = s.split(",");
        if(split.length>0){
            for(String temps : split){
                System.out.println("jilnai l ");
                System.out.println(temps);
            }
        }
        String s2 = "1-21";
        String[] split2 = s2.split(",");
        StringBuilder sb = new StringBuilder();
        for(String temp : split2){
            sb.append("'" + temp + "',");
        }
        String targetObjNbrsForDb  = sb.toString();
        String tp = targetObjNbrsForDb.substring(targetObjNbrsForDb.length() - 1, targetObjNbrsForDb.length());
        targetObjNbrsForDb = targetObjNbrsForDb.substring(0,targetObjNbrsForDb.length() - 1);

        System.out.println(targetObjNbrsForDb);
    }
}
