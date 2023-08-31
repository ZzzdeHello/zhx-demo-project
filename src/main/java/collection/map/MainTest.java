package collection.map;

/**
 * << : 左移运算符，num << 1,相当于num乘以2
 * >> : 右移运算符，num >> 1,相当于num除以2
 * >>> : 无符号右移，忽略符号位，空位都以0补齐
 */
public class MainTest {
    public static void main(String[] args) {

        Object obj= "101";

        int h =  obj.hashCode();
        System.out.println( h ); // 48626
        int h16 = obj.hashCode() >>> 16 ;
        System.out.println( h16 ); // 0
        int r = h ^ h16 ;
        System.out.println( r );// 48626
        int hash = hash(obj);
        System.out.println(hash);// 48626


        int h4 = 16 >>> 4 ; //  10000 2^4
        System.out.println( h4 ); // 1
        int h5 = 15 >>> 4 ; //  01111 2^3 * 2 ^2 * 2^1 * 2^0
        System.out.println( h5 ); // 0
        int x = 2 ^ 4; // 0010 与 0100 -> 0110 = 6  如果相对应位值相同，则结果为0，否则为1
        System.out.println(x);
    }
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
