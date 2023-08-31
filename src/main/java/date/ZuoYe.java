package date;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * java实现：三天打鱼两天晒网 问题
 *
 *
 * 中国有句俗语叫“三天打鱼两天晒网”。某人从2010年1月1日起开始“三天打鱼两天晒网”，问这个人在以后的某一天中是“打鱼”还是“晒网”。用java实现程序解决问题。
 * 基本要求：1.程序风格良好(使用自定义注释模板)，提供友好的输入输出。
 * 提高要求：1.输入数据的正确性验证。
 * 2.使用文件进行数据测试。如将日期 20100101 20111214 等数据保存在in.txt文件中，程序读入in.dat文件进行判定，并将结果输出至out.txt文件。

    1、根据输入的日期 计算离第一天的余数，如果为 1，2，3 则在打鱼；否则 在 晒网。
    2、难点：根据年份判断出每天的日期；根据月份判断出每天的日期
    3、难点：文件读取、与写出
 */
public class ZuoYe {

    public static void main(String[] args) throws Exception  {
        //创建一个字符输入缓冲流对象
        BufferedReader br = null;
        try {
             br = new BufferedReader (new FileReader("in.dat"));
        }catch (Exception e) {
            e.printStackTrace();
        }
        //创建数组存放读取到的字符
        char[] duqu = new char[1024];
        int len =0;
        String str1 =null;
        //使用循环读取文本
        while((len=br.read(duqu))!=-1){
            str1 =new String(duqu,0,len);
        }
        //创建一个字符存放截取到的字符
        String str2;
        //使用str.substring()截取字符，下同
        str2=str1.substring(0,4);
        //将读取到的字符型转换为int型方便后续计算，下同
        int year =Integer.parseInt(str2);
        str2=str1.substring(4,6);
        int month =Integer.parseInt(str2);
        str2=str1.substring(6,8);
        int day =Integer.parseInt(str2);
        System.out.println("读取到的日期为"+year+"年"+month+"月"+day+"日");
        //创建一个字符输出缓冲流对象
        BufferedWriter  bw = new BufferedWriter(new FileWriter("out.txt"));
        //声明一个字符串变量str
        String str=null;
        //分别排除年月日输入错误的情况
        if(year<2010){
            System.out.println("你输入的年份错误。");
            return;
        }
        if(month>12) {
            System.out.println("你输入的月份错误。");
            return;
        }
        if(day>31){
            System.out.println("你输入的日期错误。");
            return;
        }
        //定义变量，其中s代表总日期，s1，s2,s3分别表示年月日的天数
        int s,s1=0,s2=0,s3;
        int yue;
        //定义新变量yue替代month在switch语句中进行减一操作
        yue=month;
        //将day的值赋给s3
        s3=day;
        //使用switch可以方便的加上每个月的日期数，比if else语句更加简洁
        switch(yue-1)
        {
            case 1:s2+=31;
            case 2://闰年时需要加29天
                if(year%4==0&&year%100!=0||year%400==0)
                    s2+=29;
                else s2+=28;
            case 3:s2+=31;
            case 4:s2+=30;
            case 5:s2+=31;
            case 6:s2+=30;
            case 7:s2+=31;
            case 8:s2+=31;
            case 9:s2+=30;
            case 10:s2+=31;
            case 11:s2+=30;
            case 12:s2+=31;
        }//最后一年的月份的所有日期全部加在了变量s2中
        //将s2与s3加在变量s中
        s=s2+s3;
        if(year==2010){
            //若为2010年，只需减去1月1日当天
            s=s-1;
        }
        else if(year>2010){
            for(int i=2011;i<year;i++){
                //判断闰年，闰年为366天
                if(i%4==0&&i%100!=0||i%400==0)
                    s1+=366;
                else s1+=365;
            }
            s1=364+s1;
            s=s1+s2+s3;
        }
        if(s%5==1||s%5==2||s%5==0){
            str="在"+year+"年"+month+"月"+day+"日此人在打渔";
            //通过缓冲流对象写入文件
            bw.write(str);
            System.out.println("在"+year+"年"+month+"月"+day+"日此人在打渔");
            System.out.println("结果已写入文件！请前往out.txt查看！");
        }
        else {
            str="在"+year+"年"+month+"月"+day+"日此人在晒网";
            //通过缓冲流对象写入文件
            bw.write(str);
            System.out.println("在"+year+"年"+month+"月"+day+"日此人在晒网");
            System.out.println("结果已写入文件！请前往out.txt查看！");
        }
        //关闭流
        bw.close();
        br.close();
    }

    //获取从2010年1月1号开始的日期数量
    public int  getDateNumber(int year,int month,int day) throws exception {
        System.out.println("读取到的日期为"+year+"年"+month+"月"+day+"日");
        if(year<2010){
            throw new exception ("你输入的年份错误~") ;
        }
        if(month>12) {
            throw new  exception("你输入的月份错误~");
        }
        if(day>31){
            throw new exception("你输入的日期错误~") ;
        }


        return 0;
    }
    public String isYorS(int num){
        if (num %  5 == 1 || num %  5 == 2||num %  5 == 3)  return "Y";
        else return "S";
    }
    class exception extends Exception {
        public exception() {
        }

        public exception(String message) {
            super(message);// 把参数传递给Throwable的带String参数的构造方法
        }
    }
}



