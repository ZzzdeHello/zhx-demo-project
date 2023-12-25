package zzzde.code.technic.uuid;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UuidKit {
//	private static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",  
//        "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",  
//        "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",  
//        "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",  
//        "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  
//        "W", "X", "Y", "Z" };
	private static String[] chars = new String[] { "0", "1", "2", "3", "4", "5",
      "6", "7", "8", "9"};
	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");//17位
	private static SimpleDateFormat sdf2=new SimpleDateFormat("yyyyMMddHHmmssS");//15位
	private static String shortUuid() {
	    StringBuffer shortBuffer = new StringBuffer();  
	    String uuid = UUID.randomUUID().toString().replace("-", "");  
	    for (int i = 0; i < 6; i++) {
	        String str = uuid.substring(i * 4, i * 4 + 4);  //（0，4）、（4,8）、（8,12）···
	        //要求十六进制的String转化成10进制的值
	        int x = Integer.parseInt(str, 16);  
//	        shortBuffer.append(chars[x % 0x3E]);  
	        shortBuffer.append(chars[x % 10]);
	    }  
	    return shortBuffer.toString();  
	}
	public static String getUuid4Time(String prefix){
		String suid=sdf.format(new Date())+shortUuid();
		return StringUtils.isBlank(prefix)?("EVT"+suid):(prefix+suid);
	}
	public static String getUuid5Time(){
		String suid=sdf2.format(new Date())+shortUuid();
		return suid;
	}
	public static void main(String[] args) {
//		String a = UuidKit.getUuid4Time("");
//		System.out.println(a);
//		String b = UuidKit.getUuid5Time();
//		System.out.println("eee"+b);
		//获取六位
		System.out.println(UuidKit.shortUuid());
		// 随机生成uuid
		String uuid = UUID.randomUUID().toString().replace("-", "");
		System.out.println(uuid);

	}
	// 1、shortUUid 获取流程 将uuid 截取成 六份 四个的字符串
	//	要求十六进制的String转化成10进制的值
	// 2、x%10 取余数
	// 3、char[4] 常量数组的 第五个数 就是4
	// 4、赋值给 buffer

}
