package collection.ListOrder;

import java.util.ArrayList;
import java.util.List;

public class RemoveTest {
    public static void main(String[] args) {
        List<String> arr = new ArrayList();
        String s = "12";
        String s1 = "13";
        arr.add(0,s);
        arr.add(1,s1);
        arr.add("34");
        arr.add("32");
        for(int i = 0 ; i< arr.size() ; i++){
            if (i==2) {arr.remove(i);i--;}
            if (i==3) {arr.remove(i);i--;}
        }
        System.out.println(arr);
        System.out.println(arr.size());
    }
}
