package zzzde.n.knowledge.jvm;

import java.util.ArrayList;
import java.util.List;

public class SeeGcTest {
    public static void main(String[] args) {
        List<byte[]> n = new ArrayList<>();
        try{
            while(true){
                byte[] bytes = new  byte[100];
                n.add(bytes);
                Thread.sleep(20);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
