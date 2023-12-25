package zzzde.code.technic.codestring.length;

public class Main {
    public static void main(String[] args) {
        StringBuilder str = new StringBuilder("1Aa 人a");
        String strs = "1Aa 人a";
        for (int i=0 ;i<50 ;i++){
           str.append(strs);
        }
        System.out.println(str);
        System.out.println(str.substring(0,250));
        System.out.println(str.length());

    }
}
