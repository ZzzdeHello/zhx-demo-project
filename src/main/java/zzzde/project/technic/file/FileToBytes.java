package zzzde.project.technic.file;

import java.io.*;

public class FileToBytes {
    public static void main(String[] args) {
        String fileName = "FILE_34454__20211201103251878.xls";
        String filePath = System.getProperty("user.dir");
        System.out.println(filePath);
        File path = new File(filePath);//判断文件路径下的文件夹是否存在,不存在则创建
        if (!path.exists()) {
            boolean mkdirs = path.mkdirs();
            System.out.println("originalFilename上传文件的方法：文件新建-" + mkdirs);
        }
        File localFile = new File(filePath,fileName);
        byte[] Bfile = File2byte(localFile);
        System.out.println(Bfile);
    }

    /**
     * 将文件转换成byte数组
     * @param tradeFile
     * @return
     */
    public static byte[] File2byte(File tradeFile){
        byte[] buffer = null;
        try
        {
            FileInputStream fis = new FileInputStream(tradeFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1)
            {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return buffer;
    }
}
