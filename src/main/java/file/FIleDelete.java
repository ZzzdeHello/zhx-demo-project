package file;

import java.io.File;

public class FIleDelete {
    public static void main(String[] args) {
        System.out.println("开始--------");
        RuntimeException r  = new RuntimeException();
        File file = null ;
        try {
            file = new File(System.getProperty("user.dir") + "11111111111.txt");
            //file.createNewFile();
            //throw  r;

        }catch (Exception e ){
            e.printStackTrace();
        }finally {
            file.delete();
        }
    }
}
