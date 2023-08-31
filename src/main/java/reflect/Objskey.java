package reflect;

import lambda.Item;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *  反射方法
 *  利用对象Class类来获取属性名称和属性值。
 */
public class Objskey {
    /**
     * 获取属性名数组
     * */
    private static String[] getFiledName(Object o){
        Field[] fields=o.getClass().getDeclaredFields();
        String[] fieldNames=new String[fields.length];
        for(int i=0;i<fields.length;i++){
            fieldNames[i]=fields[i].getName();
        }
        return fieldNames;
    }

    //根据属性名获取属性值
    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {

            return null;
        }
    }

    /**
     * 获取对象所有属性对应值的数组
     * @param object
     * @return
     */
    private static String[] returnObjValue(Object object){
        String[] sbValue = new String[80];
        String[] fieldNames = getFiledName(object);
        for (int j=0 ; j<fieldNames.length; j++){     //遍历所有属性
            String name = fieldNames[j];    //获取属性的名字
            Object value = getFieldValueByName(name,object);
            sbValue[j] = "".equals(value)||value==null ? null :value.toString();
        }
        return  sbValue;
    }


    /**
     * 测试直接赋值与反射的方式获取属性名称和属性值的 效率
     * 肯定是直接赋值快，但是属性太多的话 代码太冗杂 不合理。
     * @param args
     */
    public static void main(String[] args) {
        List<String[]> list = new ArrayList<>();
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(now);
        Item item = new Item(1,"test",0.0,10000L,now);
        Item item2 = new Item(2,"test2",0.0,10000L,now);
        Item item3 = new Item();
        item3.setId(1);
        item3.setCreateDate(now);
        double v = item3.getV();
        System.out.println(v);
        System.out.println(item3);

        long start = System.currentTimeMillis();
        for(int i = 0 ; i<20000  ;i++){
            String[] strings1 = returnObjValue(item);
            String[] strings2 = returnObjValue(item2);
            String[] strings3 = returnObjValue(item3);
            list.add(strings1);
            list.add(strings2);
            list.add(strings3);
        }
        long end = System.currentTimeMillis();
        System.out.println("反射模式共耗时"+(end-start)+"毫秒");

        long start2 = System.currentTimeMillis();
        String[] string1 = new String[5];
        String[] string2 = new String[5];
        String[] string3 = new String[5];
        for(int i = 0 ; i<20000  ;i++){
            string1[0] = item.getId().toString();
            string1[1] = item.getS().toString();
            string1[2] = String.valueOf(item.getV());
            string1[3] = String.valueOf(item.getL());
            string1[4] = item2.getCreateDate().toString();
            string2[0] = item2.getId().toString();
            string2[1] = item2.getS().toString();
            string2[2] = String.valueOf(item2.getV());
            string2[3] = String.valueOf(item2.getL());
            string2[4] = item2.getCreateDate().toString();
            string3[0] = item3.getId().toString();
            string3[1] = item3.getS()== null || item3.getS().equals("") ? "" :item3.getS();
            string3[2] = String.valueOf(item3.getV());
            string3[3] = String.valueOf(item3.getL());
            string3[4] = item3.getCreateDate().toString();
            list.add(string1);
            list.add(string2);
            list.add(string3);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("直接赋值共耗时"+(end2-start2)+"毫秒");
        //遍历
//        Iterator it = list.iterator();
//        while(it.hasNext()){
//            System.out.println(Arrays.toString((String[])it.next()));
//        }
        //经测试反射效率第六倍

    }

}
