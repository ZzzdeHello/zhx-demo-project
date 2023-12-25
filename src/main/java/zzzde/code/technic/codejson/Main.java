package zzzde.code.technic.codejson;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        DismantleInfoVo dismantleInfoVo = new DismantleInfoVo();
        String filePath = "E:\\WorkProject\\DemoDATEBASE1\\src\\main\\java\\json2Collection\\dismantleResonJson";
        DismantleInfoVo dismantleInfoVo1 = transferObject(filePath, dismantleInfoVo);
        //System.out.println(dismantleInfoVo1);
        Gson gson = new Gson();
        //解析成json格式字符串
        String s = gson.toJson(dismantleInfoVo1);
        System.out.println(s);

        //根据json字符串 和 对象类转化为对象
        //DismantleInfoVo dismantleInfoVo2 = gson.fromJson(s, dismantleInfoVo.getClass());
        //System.out.println(dismantleInfoVo2);
        List<AttrValueVo> dismantleResonList = dismantleInfoVo1.getDismantleResonList();
        List<AttrValueVo> collect = dismantleResonList.stream().sorted(Comparator.comparing(AttrValueVo::getAttrValueName)).collect(Collectors.toList());
        String s1 = gson.toJson(collect);
        System.out.println(s1);
    }

    public  static <T> List<T> transferList(String filePath,T obj){
        File file=new File(filePath);
        StringBuffer sb = new StringBuffer() ;
        JsonParser jsonParser=new JsonParser();
        List<T> objs=new ArrayList<T>();
        Gson gson = new Gson();
        String line;
        BufferedReader br=null;
        try {
            br=new BufferedReader(new FileReader(file));
            while((line=br.readLine())!=null){
                sb.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String json=sb.toString();
        JsonElement jsonElement=jsonParser.parse(json);
        JsonArray jsonArray=jsonElement.getAsJsonArray();
        Iterator<JsonElement> it=jsonArray.iterator();
        while(it.hasNext()){
            jsonElement=it.next();
            json=jsonElement.toString();
            @SuppressWarnings("unchecked")
            T transferObj = (T) gson.fromJson(json, obj.getClass());
            objs.add(transferObj);
        }
        return objs;

    }

    public static <T> T  transferObject(String filePath,T obj){
        File file=new File(filePath);
        StringBuffer sb = new StringBuffer() ;
        JsonParser jsonParser=new JsonParser();
        Gson gson = new Gson();
        String line;
        BufferedReader br=null;
        try {
            br=new BufferedReader(new FileReader(file));
            while((line=br.readLine())!=null){
                sb.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String json=sb.toString();
        JsonElement jsonElement=jsonParser.parse(json);
        json = jsonElement.toString();
        @SuppressWarnings("unchecked")
        T transferObj = (T) gson.fromJson(json, obj.getClass());
        return transferObj;
    }

//    public boolean Mapfindkey(String s ,Map<String,Object> map){
//        Set<Map.Entry<String, Object>> entries = map.entrySet();
//    }
}
