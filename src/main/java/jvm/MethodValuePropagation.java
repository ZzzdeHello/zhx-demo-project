package jvm;

import com.model.Student;
import org.junit.Test;

//java中的值传递问题。
// java中的基础数据类型和引用数据类型都是值传递。下面来验证为何java中的引用数据类型是值传递的
public class MethodValuePropagation {

    @Test
    public  void  main() {
        Student a = new Student();
        Student b = new Student();
        swap(a,b);
        System.out.println(a.toString());// com.model.Student@31221be2
        System.out.println(b.toString());// com.model.Student@377dca04
    }
    private static void swap(Student x,Student y){
        Student temp = x ;
        x = y ;
        y = temp ;
        System.out.println(x.toString());//com.model.Student@377dca04
        System.out.println(y.toString());//com.model.Student@31221be2
    }

    @Test
    public void main01() {
        Student s1 = new Student();
        System.out.println(s1);
        change(s1);
        System.out.println(s1);
    }
    void change(Student student){
        student = new Student("ccc");
        System.out.println(student);
    }
}
