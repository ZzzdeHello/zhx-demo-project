package zzzde.project.technic.file.excelHugePackage.readExcel;

import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadHugeExcel {

    String path = "D:\\temp\\hugeExcel_100002.xlsx" ;

    @Test
    public void testHugeReader() throws Exception{

        //path为文件路径     获取文件
        InputStream stream = new FileInputStream(new File(path));
        // 将输入流转换为工作簿对象
        Workbook wb = StreamingReader.builder()
                .rowCacheSize(100)//读取到内存中的行数，默认10
                .bufferSize(4096)//读取资源，缓存到内存的字节大小。默认1024
                .open(stream);//打开资源。只能是xlsx文件
        //获取第一个sheet
        Sheet rows = wb.getSheetAt(0);
        try {
            int i = 0;// 行号
            // Thread.sleep(5000L);
            for (Row row : rows) {
                System.out.println("开始遍历第" + row.getRowNum() + "行数据：");
                try {
                    //每行数据遍历 （列）
                    for (Cell cell : row) {
                        i++;
                    }
                   // Thread.sleep(10L);

                } catch (Exception e) {
                   e.printStackTrace();
                }
            }
            int lastRowNum = rows.getLastRowNum();
            System.out.println(lastRowNum);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //关闭stream
        stream.close();
    }

    @Test
    public void testPareNum(){
        File file = new File(path);
        try {
            Thread.sleep(5000);
            int i = parseXlsDataNum(file);
            System.out.println(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据行数，不包含空行
     * @param f 输入excel文件
     * @return
     * @throws InvalidFormatException
     * @throws IOException
     */
    private int parseXlsDataNum(File f)  throws Exception{
        Workbook workbook = null;
        FileInputStream fis = new FileInputStream(f);
        if (f.getName().split("\\.")[1].toLowerCase().equals("xls")) {
            workbook = new XSSFWorkbook(fis);
        } else if (f.getName().split("\\.")[1].toLowerCase().equals("xlsx")) {
            workbook = new XSSFWorkbook(fis);
        }else {
            throw new Exception("文件类型异常");
        }
        // workbook =  WorkbookFactory.create(fis);
        int excelDataNum = 0 ;
        //循环所有sheet
        for (int i = 0 ; i < workbook.getNumberOfSheets() ; i++) {
            Sheet xssfSheet = workbook.getSheetAt(i);
            if(xssfSheet==null){
                continue;
            }
            int rowNum = xssfSheet.getLastRowNum() - 1 ;  // xssfSheet.getLastRowNum()从 0开始算。如果一行列头一行标题，数据量计算只需要 -1 。
            excelDataNum =+ rowNum;
        }
        return excelDataNum;
    }
}
