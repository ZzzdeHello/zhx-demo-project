package number;

import org.apache.lucene.util.BitUtil;

// 位运算 的用法
public class BitOperation {
    public static void main(String[] args) {

        //judgeNumber(9);

        //int highBitNumber = getHighBitNumber(17, 24);// 10001 与 11000  得到 10000
        //System.out.println(highBitNumber);
        //String s = Integer.toBinaryString(highBitNumber);
        //System.out.println(s);

        int i = leftBitOperation(31);
        System.out.println(i);


        int j = rightBitOperation(31);
        System.out.println(j);
    }

    /**
     * 左移 位运算
     * @param num
     * @return
     */
    public static int rightBitOperation(int num ){
        return num >> 1;
    }

    /**
     * 右移 位运算 正数左补0，负数左补1，右边丢弃
     * @param num
     * @return
     */
    public static int leftBitOperation(int num ){
        return num << 1;
    }

    /**
     * 获取数字的高位的二进制
     * @param oldNum
     * @param param
     * @return
     */
    public static int getHighBitNumber(int oldNum , int param){
        String s = Integer.toBinaryString(oldNum);
        System.out.println(s);
        String p = Integer.toBinaryString(param);
        System.out.println(p);
        return oldNum & param;
    }

    /**
     * 利用位运算：判断奇数偶数
     * @param number
     * @return
     */
    public static String judgeNumber( int number ){
        String value = "" ;
        int flag = number & 1 ;
        if ( flag == 0 ) value = "奇数";
        if ( flag ==  1 ) value ="偶数";
        return value;
    }
}
