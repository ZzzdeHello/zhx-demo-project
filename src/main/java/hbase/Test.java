package hbase;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;

public class Test {
    public static void main(String[] args) throws DecoderException, UnsupportedEncodingException {
        //Hbase UTF8编码
        String content = "2020\\xE5\\xB9\\xB408\\xE6\\x9C\\x8825\\xE6\\x97\\xA5 22\\xE6\\x97\\xB652\\xE5\\x88\\x86\\x5C";
        char[] chars = content.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 2; i < chars.length; i = i + 4) {
//            System.out.println(chars[i]);
            sb.append(chars[i]);
//            System.out.println(chars[i + 1]);
            sb.append(chars[i + 1]);
        }
        System.out.println(sb);
        String ouputStr = new String(Hex.decodeHex(sb.toString().toCharArray()), "UTF-8");
        System.out.println(ouputStr);
    }
}
