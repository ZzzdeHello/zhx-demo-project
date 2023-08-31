package jvm;

public class Main {
    public static void main(String[] args) {
        int i = 0;
        int i2 = 1 ;
        String s = "s" ;
        int i3 = i + i2;
        System.out.println(i3);
        System.out.println(s);
    }

    private void test(int i3){
        String s = "1";
        String i4 = i3 + s;
        System.out.println(s);
        System.out.println(i4);
    }

    private int add(int i){
        int j = 10 ;
        int x = i + j ;
        return x ;
    }
}
