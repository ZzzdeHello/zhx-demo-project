package file.excelHugePackage.writeExcel;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;
import org.junit.Test;

public class HugeExcelOut {

    private int totalRowNumber = 10002; //写入的excel数据行数
    private int totalCellNumber = 20; //excel每行共40列

    //普通的写入excel的方法，会消耗内存，写入的行数太大时，会报内存溢出
    @Test
    public void writeNormalExcelTest(){

        Workbook wb = null;
        FileOutputStream out = null;

        try {

            long startTime = System.currentTimeMillis();

            wb = new XSSFWorkbook();
            Sheet sheet = wb.createSheet("Sheet 1");
            //定义Row和Cell变量, Rows从0开始.
            Cell cell;
            Row row;
            for (int rowNumber = 0; rowNumber < totalRowNumber; rowNumber++) {
                row = sheet.createRow(rowNumber);

                for (int cellNumber = 0; cellNumber < totalCellNumber; cellNumber++) {
                    cell = row.createCell(cellNumber);
                    double random = Math.random();
                    System.out.println(random);
                    cell.setCellValue(random); //写入一个随机数
                }

                //打印测试，
                if(rowNumber % 10000 ==0) {
                    System.out.println(rowNumber);
                }
            }

            //Write excel to a file
            out = new FileOutputStream("d:\\temp\\normalExcel_" + totalRowNumber + ".xlsx");
            wb.write(out);
            long endTime = System.currentTimeMillis();

            System.out.println("process " + totalRowNumber + " spent time:" + (endTime - startTime) + " ms.");

        } catch (Exception e) {

            e.printStackTrace();

        } finally {


            try {
                if(out != null) out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if(wb != null) wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    //结合临时文件压缩等写入excel，默认超过100行就写到临时文件，不会报内存溢出
    @Test
    public void writeHugeExcelTest(){

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        SXSSFWorkbook wb = null;
        //excel表头构建
        List<String> colNameList = new ArrayList<>();//固定六个字段,自选字段
        List<String> colKeyList = new ArrayList<>();//固定六个字段,自选字段
        colNameList.add("工单ID");colKeyList.add("contact_order_id");
        colNameList.add("接单人");colKeyList.add("inputSalesstaff");
        colNameList.add("接单区域");colKeyList.add("inputArea");
        colNameList.add("活动名称");colKeyList.add("activity_title");
        colNameList.add("所属区域");colKeyList.add("contact_org_name");
        colNameList.add("客户编码");colKeyList.add("cust_number");

        String[] colName = new String[colNameList.size()];
        colNameList.toArray(colName);
        String[] colKey = new String[colKeyList.size()];
        colKeyList.toArray(colKey);

        String remark = "特别提示：\n" +
                "1.如果派单到人请在第2列接单人列补充接单人账号\n" +
                "2.如果派单到区域请在第3列接单区域列补充派发区域ID (本地IT获取下发，一般分局与支局的区域ID变化不大，派发人可自行存储)\n" +
                "3.当接单人账号与区域信息共存时，优先派发接单人账号\n" +
                "4.除接单人账号和派发区域列外，其他信息禁止更改";
        try {

            long startTime = System.currentTimeMillis();

            wb = new SXSSFWorkbook();//默认100行，超100行将写入临时文件
            wb.setCompressTempFiles(false); //是否压缩临时文件，否则写入速度更快，但更占磁盘，但程序最后是会将临时文件删掉的
            Sheet sheet = wb.createSheet("Sheet 1");
            int[] width = {5800, 4200, 4200, 8000, 8000, 4800}; // 固定六个字段的列宽
            if (colKey.length > width.length){
                // 动态字段列宽
                int dynamicNum = colKey.length - width.length;
                int[] dynamicWidth = new int[dynamicNum];
                for (int i = 0 ; i<dynamicNum ;i++ ) {
                    dynamicWidth[i] = 4200 ;
                }
                width =  concat(width,dynamicWidth);
            }
            for (int i = 0, m = width.length; i < m; i++) {
                sheet.setColumnWidth(i, width[i]);
            }
            sheet.setDefaultRowHeightInPoints((short) 2 * 256);//设置行高
            Font headfont = wb.createFont();

            headfont.setFontName("宋体");
            headfont.setFontHeightInPoints((short) 11);// 字体大小
            CellStyle headstyle = wb.createCellStyle();
            headstyle.setFont(headfont);
            headstyle.setWrapText(true); // 换行
            headstyle.setAlignment(HorizontalAlignment.LEFT);//左对齐
            headstyle.setVerticalAlignment(VerticalAlignment.CENTER);// 上下居中
            headstyle.setLocked(true);
            //写标题
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));
            Row headRow = sheet.createRow(0);
            headRow.setHeightInPoints(72);
            Cell rowHead = headRow.createCell(0);
            rowHead.setCellStyle(headstyle);
            rowHead.setCellValue(remark);

            //列名样式
            Font colFont = wb.createFont();
            colFont.setFontName("宋体");
            colFont.setFontHeightInPoints((short) 12);
            CellStyle colStyle = wb.createCellStyle();//列名单元格样式
            colStyle.setFont(colFont);
            colStyle.setBorderBottom(BorderStyle.THIN); //下边框
            colStyle.setBorderLeft(BorderStyle.THIN);//左边框
            colStyle.setBorderTop(BorderStyle.THIN);//上边框
            colStyle.setBorderRight(BorderStyle.THIN);//右边框
            headstyle.setAlignment(HorizontalAlignment.LEFT);//左对齐
            headstyle.setVerticalAlignment(VerticalAlignment.CENTER);// 上下居中
            Row rowColumn = sheet.createRow(1);
            rowColumn.setHeightInPoints(18);
            for (int i = 0; i < colName.length; i++) {
                Cell cell = rowColumn.createCell(i);
                cell.setCellStyle(colStyle);
                cell.setCellValue(colName[i]);
            }

            // 单元格格式
            Font textFont = wb.createFont();
            textFont.setFontName("宋体");
            textFont.setFontHeightInPoints((short) 12);
            CellStyle lockCellStyle = wb.createCellStyle();
            lockCellStyle.setLocked(true);
            lockCellStyle.setFont(textFont);
            lockCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            lockCellStyle.setBorderBottom(BorderStyle.THIN); //下边框
            lockCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
            lockCellStyle.setBorderTop(BorderStyle.THIN);//上边框
            lockCellStyle.setBorderRight(BorderStyle.THIN);//右边框
            lockCellStyle.setAlignment(HorizontalAlignment.CENTER);
            lockCellStyle.setWrapText(true);
            lockCellStyle.setAlignment(HorizontalAlignment.LEFT);//左对齐
            lockCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 上下居中
            lockCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());

            CellStyle unlockCellStyle = wb.createCellStyle();
            unlockCellStyle.setLocked(false);//设置未锁定
            unlockCellStyle.setFont(textFont);
            unlockCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            unlockCellStyle.setBorderBottom(BorderStyle.THIN); //下边框
            unlockCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
            unlockCellStyle.setBorderTop(BorderStyle.THIN);//上边框
            unlockCellStyle.setBorderRight(BorderStyle.THIN);//右边框
            unlockCellStyle.setAlignment(HorizontalAlignment.CENTER);
            unlockCellStyle.setWrapText(true);
            unlockCellStyle.setAlignment(HorizontalAlignment.LEFT);//左对齐
            unlockCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 上下居中
            unlockCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());

            //定义Row和Cell变量, Rows从0开始.
            Row row;
            Cell cell;

            for (int rowNumber = 2; rowNumber < totalRowNumber; rowNumber++) {
                row = sheet.createRow(rowNumber);
                for (int cellNumber = 0; cellNumber < totalCellNumber; cellNumber++) {
                    cell = row.createCell(cellNumber);
                    cell.setCellValue(Math.random()); //写入一个随机数
                    if (cellNumber == 1 || cellNumber == 2) {
                        cell.setCellStyle(unlockCellStyle);
                    }else {
                        cell.setCellStyle(lockCellStyle);
                    }
                }
                //打印测试，
                if(rowNumber % 10000 ==0) {
                    System.out.println(rowNumber);
                }

            }
            sheet.protectSheet("123321");

            wb.write(baos);
            byte[] bytes = baos.toByteArray();

            long endTime = System.currentTimeMillis();
            System.out.println("process " + totalRowNumber + " spent time:" + (endTime - startTime) + " ms.");
            //System.out.println(bytes.length);
        } catch (Exception ex) {

            ex.printStackTrace();

        } finally {

            if (wb != null) {
                wb.dispose();// 删除临时文件，很重要，否则磁盘可能会被写满
            }

            try {
                if(wb != null) wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
    //两个个合并
    public static int[] concat(int[] first, int[] second) {
        int[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
}

