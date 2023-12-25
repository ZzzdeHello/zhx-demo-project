package zzzde.project.technic.annotation.demo;

//自定义的类

import zzzde.project.technic.annotation.MyTag;

import java.lang.reflect.Field;

/**
 * Created by WANG on 17/11/21.
 */
public class AnnotationCar {
    private static AnnotationCar annotationCar;
    public static AnnotationCar instance(){
        synchronized (AnnotationCar.class){
            if(annotationCar == null){
                annotationCar = new AnnotationCar();
            }
            return annotationCar;
        }
    }

    // 获取对象名称只能是 car 十分局限
    public void inject(Object o){
        Class<?> aClass = o.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field:declaredFields) {
            if(field.getName().equals("car") && field.isAnnotationPresent(MyTag.class)) {
                MyTag annotation = field.getAnnotation(MyTag.class);
                Class<?> type = field.getType();
                if(Car.class.equals(type)) {
                    try {
                        field.setAccessible(true);
                        field.set(o, new Car(annotation.name(), annotation.size()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}