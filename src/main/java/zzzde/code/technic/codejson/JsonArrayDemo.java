package zzzde.code.technic.codejson;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author zzzde
 * @Date 2023/9/20
 */
public class JsonArrayDemo {

    public static void main(String[] args) {
        String json = "[\n" +
                "  {\n" +
                "    \"crowdValue\": \"S_AUTH_SUCCESS\",\n" +
                "    \"crowdName\": \"实名认证成功\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"crowdValue\": \"S_NOT_AUTH\",\n" +
                "    \"crowdName\": \"未完成认证\"\n" +
                "  }\n" +
                "]";

        List<Crowd> crowds = JSON.parseArray(json, Crowd.class);
        System.out.println(crowds.size());
        System.out.println(crowds.get(0).toString());
    }

}
@Data
class Crowd {
    private String crowdValue;
    private String crowdName;
    private Date creteTime;
}

