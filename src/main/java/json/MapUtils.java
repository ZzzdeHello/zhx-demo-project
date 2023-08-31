package json;

import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MapUtils {

    private static final Map<String, Object>  fieldMap = newHashMap();

    public static Map<String, String> obj2MapStrConvert(Object object) {
        Map<String, String> resultMap = newHashMap();
        if (Objects.nonNull(object)) {
            Field[] fields = getFields(object.getClass());
            if (!ObjectUtils.isEmpty(fields)) {
                Arrays.stream(fields).forEach(field -> {
                    try {
                        field.setAccessible(true);
                        resultMap.put(field.getName(), field.get(object)==null ? "":field.get(object).toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }
        return resultMap;
    }



    public static Field[] getFields(Class clazz) {
        String key = clazz.getName();
        if (fieldMap.containsKey(key)) {
            return (Field[]) fieldMap.get(key);
        } else {
            Field[] fields = clazz.getDeclaredFields();
            synchronized (fieldMap) {
                fieldMap.put(key, fields);
            }
            return fields;
        }

    }

    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap();
    }
}
