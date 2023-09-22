package file.ftp;



import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ftpTest {
    public static void main(String[] args) {
        System.out.println("-----------开始");
        Date neo = new Date();
        String s ="";
        String sequence = "1";
        try {
            uploadFileToYfxtFtp(neo, sequence,s);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 备份本地 ftp 服务器文件上传方法
     * @param now 当前时间
     * @param sequence 序列
     * @param fileSuffix 文件后缀
     * @throws Exception
     */
    public static void uploadFileToYfxtFtp(Date now , String sequence, String fileSuffix) throws Exception{

        //连接ftp
        String username ="ftp";
        String pwd ="V1p9*2_9%3#";
        String host ="134.108.3.130";
        String port ="22";
        String localDir = System.getProperty("user.dir");

         //重传标识
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String fileName = "5GREMINDER_1055_10551001_H3_"+sdf.format(now)+"_8330000_"+sequence+"_"+fileSuffix;
        System.out.println("fileName:"+fileName);

        File file = new File(localDir+fileName);


        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        StringBuffer buf = new StringBuffer();
        buf.append("1-1-22222222222222222222222");

        InputStream inputStream = new FileInputStream(file);

        Date today = new Date();
        SimpleDateFormat sdfForDay = new SimpleDateFormat("dd");
        String dateValue = sdfForDay.format(today);

        FTPClient yfxt_ftpClient = new FTPClient();
        yfxt_ftpClient.connect(host, Integer.parseInt(port)); //连接sftp服务器
        yfxt_ftpClient.login(username, pwd);
        int replyCode = yfxt_ftpClient.getReplyCode(); //是否成功登录服务器
        if(!FTPReply.isPositiveCompletion(replyCode)){
                System.out.println("connect failed...");
        }
        try{
                yfxt_ftpClient.makeDirectory("/home/ftp/groupBak");
                yfxt_ftpClient.makeDirectory("/home/ftp/groupBak"+dateValue);
                yfxt_ftpClient.changeWorkingDirectory("/home/ftp/groupBak"+dateValue);
                yfxt_ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                yfxt_ftpClient.enterLocalPassiveMode();
                boolean flg = yfxt_ftpClient.storeFile(fileName, inputStream);
                System.out.println(fileName+"上传结果"+flg);
                inputStream.close();
                yfxt_ftpClient.logout();
                file.delete();
        }catch(Exception e){
                e.printStackTrace();
        }
    }
}
