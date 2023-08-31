package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class containTest {
    public static void main(String[] args) {
        String ivrCode = "EVTS000001121,EVTS000001147,EVTS000001148";
        String  eventCode = "EVTS0000011480";
        String kong = "";
        boolean contains = ivrCode.contains(eventCode);
        boolean contains1 = ivrCode.contains(kong);

        if (   contains ) System.out.println( "kekekekkekekek");
        if ( contains1 ) System.out.println( "会包含空字符串的" );
        if( contains || contains1) System.out.println( " yic ");

        List<String> resultList = Arrays.asList("1","2","3");
        boolean contains2 = resultList.contains("");
        System.out.println("11111111 List<String> 的场景 "+contains2);

    }


}
