import java.util.Scanner;

public  class Main002 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String [] str=sc.nextLine().split(" ");
        int d,e;
        d=Integer.valueOf(str[0]);
        e=Integer.valueOf(str[1]);
        if(d-e >0 ) {
            for(int i=e;i<d;i++){
                int a,b,c;
                a=i/100;
                b=(i/10)%10;
                c=i%10;
                if(i==a*a*a+b*b*b+c*c*c){
                    System.out.println(i);
                }
            }
        }else {
            for(int i=d;i<e;i++){
                int a,b,c;
                a=i/100;
                b=(i/10)%10;
                c=i%10;
                if(i==a*a*a+b*b*b+c*c*c){
                    System.out.println(i);
                }
            }
        }

    }
}