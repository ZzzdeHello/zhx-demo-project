package zzzde.project.technic.annotation;

public class MainTest {
    public static void main(String[] args) {
        Student student = new Student("小明",11);
        System.out.println(getMsg(student));
    }

    @MyTag
    public static String getMsg(Student student){
        return student.toString();
    }

//    public static void dealMyTag(MainTest mainTest){
//        Class clazz = mainTest.getClass();
//        for ( Method method :clazz.getDeclaredMethods()){
//          if ( method.getName ().equals("getMsg") && method.isAnnotationPresent(MyTag.class)) {
//              MyTag annotation = method.getAnnotation(MyTag.class);
//              String content = annotation.content();
//              System.out.println(content);
//          }
//        }
//
//    }
}

