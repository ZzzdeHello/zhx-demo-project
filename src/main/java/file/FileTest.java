package file;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileTest {
    public static void main(String[] args) {
        String txtSplidStr = "$%$";
        List<Map<String, Object>> list = new ArrayList<>();
        Map mao = new HashMap();
        mao.put("contactOrderId",1);mao.put("salesstaffCode",11);mao.put("batchId",112);mao.put("lanId",311);
        list.add(mao);
        StringBuffer stringBuffer = new StringBuffer();
        for(Map tempMap :list){
            stringBuffer.append(tempMap.get("contactOrderId")+ txtSplidStr + tempMap.get("batchId") + txtSplidStr +
                    tempMap.get("salesstaffCode") + txtSplidStr + tempMap.get("lanId") + "\r\n" +"111111111111" );
        }
        System.out.println(stringBuffer);
    }
}
