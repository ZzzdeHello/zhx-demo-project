package zzzde.project.technic.file.excelHugePackage;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

// 工单管理excel处理类
public  class OrderManagerExcelUtils {

    /**
     *
     * @param param 请求参数的 colList 中的 colName （填写中文）和 colKey （填写Es中对应的key）
     * @param esListData 根据orderIds查询es得到的所有有效数据
     * @return
     */
    public static byte[] generateExcel( Map param ,List<Map<String,Object>> esListData) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        SXSSFWorkbook wb = null;
        try {
            //excel表头构建
            List<String> colNameList = new ArrayList<>();//固定六个字段,自选字段
            List<String> colKeyList = new ArrayList<>();//固定六个字段,自选字段
            colNameList.add("工单ID");colKeyList.add("contact_order_id");
            colNameList.add("接单人");colKeyList.add("inputSalesstaff");
            colNameList.add("接单区域");colKeyList.add("inputArea");
            colNameList.add("活动名称");colKeyList.add("activity_title");
            colNameList.add("所属区域");colKeyList.add("contact_org_name");
            colNameList.add("客户编码");colKeyList.add("cust_number");
            //colNameList.add("业务号码");colKeyList.add("acc_num");
            //colNameList.add("资产编码");colKeyList.add("prom_integ_id");
            List<Map<String,Object>> colList = param.get("colList") == null ? new ArrayList<>() :(List<Map<String,Object>>) param.get("colList") ;// 选择的自定义字段
            for (Map col : colList){
                colNameList.add(col.get("colName").toString());
                colKeyList.add(col.get("colKey").toString());
            }
            String[] colName = new String[colNameList.size()];
            colNameList.toArray(colName);
            String[] colKey = new String[colKeyList.size()];
            colKeyList.toArray(colKey);
            String remark = "特别提示：\n" +
                    "1.如果派单到人请在第2列接单人列补充接单人账号\n" +
                    "2.如果派单到区域请在第3列接单区域列补充派发区域ID (本地IT获取下发，一般分局与支局的区域ID变化不大，派发人可自行存储)\n" +
                    "3.当接单人账号与区域信息共存时，优先派发接单人账号\n" +
                    "4.除接单人账号和派发区域列外，其他信息禁止更改";
            wb = new SXSSFWorkbook();
            Sheet sheet = wb.createSheet("派单数据汇总");
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
            sheet.setDefaultRowHeightInPoints((short)16);//设置默认行高
            // 表头标题样式
            Font headfont = wb.createFont();
            headfont.setFontName("宋体");
            headfont.setFontHeightInPoints((short) 11);// 字体大小
            CellStyle headstyle = wb.createCellStyle();
            headstyle.setFont(headfont);
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
            colStyle.setAlignment(HorizontalAlignment.LEFT);//左对齐
            colStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 上下居中
            colStyle.setLocked(true);
            Row rowColumn = sheet.createRow(1);
            rowColumn.setHeightInPoints(18);
            for (int i = 0; i < colName.length; i++) {
                Cell cell = rowColumn.createCell(i);
                cell.setCellStyle(colStyle);
                cell.setCellValue(colName[i]);
            }

            // 普通单元格样式（中文）
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

            Row row;
            Cell cell;

            //写入数据
            for (int i = 0, n = esListData.size(); i < n; i++) {
                row = sheet.createRow(i + 2);
                rowColumn.setHeightInPoints(18);
                for (int j = 0; j < colKey.length; j++) {
                    cell = row.createCell(j);
                    if (j == 1 || j == 2) {
                        cell.setCellStyle(unlockCellStyle);
                    }else {
                        cell.setCellStyle(lockCellStyle);
                    }
                    setCellValue(cell, esListData.get(i).get(colKey[j]));
                }
            }
            wb.write(baos);
            sheet.protectSheet("123321");
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (wb != null) {
                wb.dispose();// 删除临时文件，很重要，否则磁盘可能会被写满
            }
            try {
                if(wb != null) wb.close();// wb 流关闭
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                baos.flush();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //两个个合并
    public static int[] concat(int[] first, int[] second) {
        int[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    private static void setCellValue(Cell cell, Object object){
        if(object == null){
            cell.setCellValue("");
        }else{
            if (object instanceof String) {
                cell.setCellValue(String.valueOf(object));
            }else if(object instanceof Long){
                Long temp = (Long)object;
                cell.setCellValue(temp);
            }else if(object instanceof Double){
                Double temp = (Double)object;
//                String cellvalue = new DecimalFormat("#0.00").format(temp.doubleValue());
                cell.setCellValue(temp.doubleValue());
            }else if(object instanceof Float){
                Float temp = (Float)object;
//                String cellvalue = new DecimalFormat("#0.00").format(temp.doubleValue());
                cell.setCellValue(temp.doubleValue());
            }else if(object instanceof Integer){
                Integer temp = (Integer)object;
                cell.setCellValue(temp.toString());
            }else if(object instanceof BigDecimal){
                BigDecimal temp = (BigDecimal)object;
//                String cellvalue = new DecimalFormat("#0.00").format(temp.doubleValue());
                cell.setCellValue(temp.toString());
            }else{
                cell.setCellValue("");
            }
        }
    }
}
