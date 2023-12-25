package zzzde.project.technic.file.excelHugePackage;

import com.monitorjbl.xlsx.StreamingReader;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 4.1.1版本 工单管理大文件excel 解析工具类
 */
public class XLSparseUtil {
	static private SimpleDateFormat sdf =new SimpleDateFormat("yyyy/MM/dd");
	static private SimpleDateFormat sdf_full =new SimpleDateFormat("yyyy年MM月dd日");
	static private SimpleDateFormat sdf_half =new SimpleDateFormat("M月dd日");
	static private DateFormat sdf_time =new SimpleDateFormat("H时mm分");
	static private String Context = File.separator+"app";
	static private String relativePath = "upLoad";//+File.separator+sdf1.format(new Date());
	static private int maxRow = 65534;//去掉表头

	private SimpleDateFormat sdf_date = new SimpleDateFormat("yyyyMMdd");
	static String FTP_Path=File.separator+"app"+File.separator+"FTP-Local";
	private  static NumberFormat numberFormat = NumberFormat.getNumberInstance();
	static {
		numberFormat.setGroupingUsed(false);
	}
	/**
	 * 
	 * @Title: toFile 
	 * @Package: com.ctzj.biz.stms.util 
	 * @Description: TODO(方法功能描述) 文件生成 数据多的时候会创建多个sheet
	 * @author: josephy
	 * @param title 表头  格式String[]
	 * @param data  表数据  格式List<Map<String,String>>,其中key为col-num
	 * @param fileName 文件名称 不需要加后缀
	 * @return
	 * @throws IOException (参数描述，入参中文描述，返回值描述)
	 * @date: 2017年11月3日 下午4:43:25  
	 * @version: V2.0
	 */
	static public File toFile(String[] title, List<Map<String,String>> data,String fileName) throws IOException{
		File file = new File(Context+File.separator+relativePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		file = new File(Context+File.separator+relativePath, fileName);
		XSSFWorkbook workbook = new XSSFWorkbook();
		int sheetNum = (data.size()-1)/maxRow + 1;
		for (int index = 0; index < sheetNum; index++) {
			Sheet sheet = workbook.createSheet();
			int rownum = 0;
			if (title!=null) {
				Row row = sheet.createRow(rownum);
				for (int column = 0; column < title.length; column++) {
					Cell cell = row.createCell(column, CellType.STRING);
					cell.setCellValue(title[column]);
				}
				rownum+=1;
			}
			List<Map<String,String>> tempData=data.subList(index*maxRow, (index+1)*maxRow>data.size()?data.size():(index+1)*maxRow);
			for (int i = 0; i < tempData.size(); i++) {
				Map<String, String> temp = tempData.get(i);
				Row row = sheet.createRow(rownum++);
				Iterator<String> it = temp.keySet().iterator();
				while (it.hasNext()) {
					String key = it.next();
					String value = String.valueOf(temp.get(key));
					if("col-".equals(String.valueOf(key).substring(0,4))){
					int index_ = Integer.parseInt(String.valueOf(key).substring(4));
					Cell cell = row.createCell(index_, CellType.STRING);
					cell.setCellValue(value);
					}
				}
			} 
		}
		FileOutputStream fis = new FileOutputStream(file);
		workbook.write(fis);
		fis.flush();
		fis.close();
		return file;
	}
	/**
	 * 
	 * @Title: toFile 
	 * @Package: com.ctzj.biz.stms.util 
	 * @Description: TODO(方法功能描述)
	 * @author: josephy
	 * @param title 表头
	 * @param name sql里对应属性
	 * @param data 数据
	 * @param fileName 生成文件的名字
	 * @param dateFormat 时间类型返回的格式
	 * @return
	 * @throws IOException (参数描述，入参中文描述，返回值描述)
	 * @date: 2018年1月10日 下午3:10:46  
	 * @version: V1.0
	 */
	static public File toFile(String[] title, String[] name, List<Map<String,Object>> data,String fileName,String dateFormat) throws IOException{
		if (dateFormat!=null) {
			sdf=new SimpleDateFormat(dateFormat);
		}
		return toFile(title, name, data, fileName);
	}

	static public File toFile(String[] title, String[] name, List<Map<String,Object>> exportData,String fileName) throws IOException{
		File csvFile = null;
        BufferedWriter csvFileOutputStream = null;
    	String	outPutPath=File.separator+"app"+File.separator+"upLoad"+File.separator;
        File file = new File(outPutPath);
        if (!file.exists()) {
            file.mkdir();
        }
        // 定义文件名格式并创建
        fileName = fileName.replaceAll(".xls", "");
        csvFile = File.createTempFile(fileName, ".csv", new File(outPutPath));
        System.out.println("csvFile：" + csvFile);
        // UTF-8使正确读取分隔符","
        csvFileOutputStream = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(csvFile), "gbk"),1024);
            // 写入文件头部
            for (int i=0;title!=null && i<title.length;i++) {
            	csvFileOutputStream.write("" + title[i]==null?"":title[i] + "");
            	if(i==title.length-1){
            		csvFileOutputStream.newLine();
            		break;
            	}
            	csvFileOutputStream.write(",");
			}
            // 写入文件内容
            for (Iterator iterator = exportData.iterator(); iterator.hasNext();) {
            	Map object = (Map) iterator.next();
            	int len = title==null?name.length:Math.min(name.length,title.length);
                for (int i=0;i<len;i++) {
//	                    csvFileOutputStream.write((String) BeanUtils.getProperty(
//	                    		object, name[i])+ "\t");
                	if(object.get(name[i])!=null){
                		csvFileOutputStream.write(object.get(name[i])+ "\t");
                	}
                    csvFileOutputStream.write(",");
                }
                if (iterator.hasNext()) {
                    csvFileOutputStream.newLine();
                }
            }
            csvFileOutputStream.flush();
            csvFileOutputStream.close();
	        return csvFile;
	}

	// 大数据类型、日期类型 （yyyy/MM/dd）转化为 String类型
	static public String ObjectToString(Object obj){
		String value;
		if(obj instanceof BigDecimal){
			value= obj.toString();
		}else if(obj instanceof Date){
			value = sdf.format(obj);
		}else{
			value=(String)obj;
		}
		if(value==null||"null".equals(value)){
			value="";
		}
		return value;
	}
	
	static public Map<String, Object> parse(File f,String dateFormat,String[][] limit, int head) throws Exception{
		if(dateFormat!=null){
			sdf = new SimpleDateFormat(dateFormat);
		}
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("success", false);
		String name = f.getName();
		String[] split = name.split("[.]");
		int size = split.length-1;
		if(split[size].equals("xls")||split[size].equals("xlsx")){ //HSSF or XSSF
			try {
				Map<String, Object> map = parseXLS(f, limit,head);
				String error = (String) map.get("error");
				if (error != null) {
					result.put("msg", error);
				} else {
					List<Map<String, String>> data = (List<Map<String, String>>) map.get("data");
					result.put("success", true);
					result.put("data", data);
				}
			} catch (Exception e) {
				e.printStackTrace();
//				result.put("msg", "文件解析异常,请确认文档无误后重试!");
				throw e;
			}
		}else{
//			result.put("msg", "文件格式错误,请确认文档无误后重试!");
			throw new Exception("文件格式错误,请确认文档无误后重试!");
		} 
		return result;
	}

	/**
	 * 获取数据行数，不包含空行
	 * @param f 输入excel文件
	 * @return
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public static int parseXlsDataNum(File f)  throws Exception{
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
	
	static private Map<String,Object> parseXLS(File f,String[][] limit, int head) throws InvalidFormatException, IOException {
		FileInputStream fis = new FileInputStream(f);
		Workbook xssfWorkbook =  WorkbookFactory.create(fis);
		Map<String,Object> returnMap = new HashMap<String, Object>();
		List<Map<String,String>> result=new ArrayList<Map<String,String>>();
		Map<String,String> temp;
		for (int i = 0; i < xssfWorkbook.getNumberOfSheets(); i++) {
			Sheet xssfSheet = xssfWorkbook.getSheetAt(i);
			if(xssfSheet==null){
				continue;
			}
			for (int rowNum = head; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {//the first row is doc, second row is title
				Row row = xssfSheet.getRow(rowNum);
				temp = new HashMap<String, String>();
				for (int cellNum = 0; cellNum <= limit[0].length || cellNum<=row.getLastCellNum(); cellNum++) {
					Cell cell = row.getCell(cellNum);
					String value = getValue(cell);
					if (!compare(limit, cellNum, value)) {
						String msg= "第"+(rowNum+1)+"行第"+ (cellNum+1)+"列数据无效,请确认后重新上传";
						returnMap.put("error", msg);
						return returnMap;
					}
					temp.put("col-"+cellNum, value);
				}
				result.add(temp);
			}
		}
		fis.close();
		returnMap.put("data", result);
		return returnMap;
	}

	//第0行 类型  第1行 限制值 
	//包含类型如果可以为空  值中请多加一个空格
	//数字类型,如果是几个固定值 请用包含类型  如果是范围 可以写"1,3"代表起始值
	static boolean compare(String[][] limit, int cellNum, Object obj){
		if (limit[0].length<=cellNum) {
			return true;
		}
		String orign=limit[0][cellNum];
		if (orign==null) {
			return true;
		}
		if(orign.equals("非空")){
			return obj!=null;
		}
		if(orign.equals("包含")){
			if(obj==null){
				return limit[1][cellNum].contains(" ");
				}
			String[] check = limit[1][cellNum].split("[,]");
			for (String str : check) {
				if(str.equals(obj)){
					return true;
				}
			}
			return false;
		}
		if(orign.equals("数字")){
			if(obj==null) {
                return true;
            }
			boolean isNumeric = StringUtils.isNumeric(obj.toString());
			if(limit[1][cellNum]==null){return isNumeric;}
			if(isNumeric){
				int num = Integer.parseInt(obj.toString());
				String[] split = limit[1][cellNum].split("[,]");
				int start = Integer.parseInt(split[0]);
				int end = Integer.parseInt(split[1]);
				if(num<end && num>start){
					return true;
				}
			}
			return false;
		}
		return true;
	}
	static public String getValue(Cell cell){
		if(cell==null) {
            return null;
        }
		CellType type=cell.getCellType();
		String value;
		switch (type) {
		case STRING:
			value=cell.getStringCellValue();
			break;
		case BOOLEAN:
			value=""+cell.getBooleanCellValue();
			break;
		case BLANK:
			value="";
			break;
		case ERROR:
			value="";
			break;
		case NUMERIC:
//			yyyy年m月d日----->31
//			m月d日---->58
//			h时mm分--->32
			short dateType = cell.getCellStyle().getDataFormat();
			double v1 = cell.getNumericCellValue();
			if(DateUtil.isCellDateFormatted(cell)){
				Date date = DateUtil.getJavaDate(v1);
				value = sdf.format(date);
			}else if(dateType==58){
				double value1 = cell.getNumericCellValue();
				 Date date = DateUtil.getJavaDate(value1);
				 value = sdf_half.format(date);
			}else if(dateType==31){
				double value1 = cell.getNumericCellValue();
				 Date date = DateUtil.getJavaDate(value1);
				 value = sdf_full.format(date);
			}else if(dateType==32){
				double value1 = cell.getNumericCellValue();  
				 Date date = DateUtil.getJavaDate(value1);
				 value = sdf_time.format(date);
			}else{
//				BigDecimal v3 = BigDecimal.valueOf(v1).setScale(2,BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
				value = numberFormat.format(v1);
			}
			break;
	    case FORMULA:
	    	  try{
			value = String.valueOf(cell.getNumericCellValue());
	    	  }catch (IllegalStateException e){
			value = String.valueOf(cell.getRichStringCellValue());
	    	  }
            if (value.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串  
                value = cell.getStringCellValue().toString();  
            }
		    break; // 公式类型
		default:
			value=cell.getStringCellValue();
			break;
		}
		value=value.trim();
		if("".equals(value.trim())) {
            value = null;
        }
		return value;
	}


	/**
	 * parse_newForScienNo、parseXLS_newForScienNo解决可能出现的科学计数法问题
	 */
	static public Map<String, Object> parse_newForScienNo(File f,String dateFormat,String[][] limit, int head,int sheetNum) throws Exception{
		if(dateFormat!=null){
			sdf = new SimpleDateFormat(dateFormat);
		}
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("success", false);
		String name = f.getName();
		String[] split = name.split("[.]");
		int size = split.length-1;
		if(split[size].equals("xls")||split[size].equals("xlsx")){ //HSSF or XSSF
			try {
				Map<String, Object> map = parseXLS_newForScienNo(f, limit,head,sheetNum);
				String error = (String) map.get("error");
				if (error != null) {
					result.put("msg", error);
				} else {
					List<Map<String, String>> data = (List<Map<String, String>>) map.get("data");
					result.put("success", true);
					result.put("data", data);
					result.put("errorList", map.get("errorList"));
				}
			} catch (Exception e) {
				e.printStackTrace();
//				result.put("msg", "文件解析异常,请确认文档无误后重试!");
				throw e;
			}
		}else{
//			result.put("msg", "文件格式错误,请确认文档无误后重试!");
			throw new Exception("文件格式错误,请确认文档无误后重试!");
		}
		return result;
	}


	static public Map<String,Object> parseXLS_newForScienNo(File f,String[][] limit, int head,int sheetNum) throws Exception {
		FileInputStream fis = new FileInputStream(f);
		Workbook xssfWorkbook =  WorkbookFactory.create(fis);
		Map<String,Object> returnMap = new HashMap<String, Object>();
		List<Map<String,String>> result=new ArrayList<Map<String,String>>();
		Map<String,String> temp;
		List<String> errorList=new ArrayList<String>();
		for (int i = 0; i < xssfWorkbook.getNumberOfSheets(); i++) {
			if(i != sheetNum) {
				continue;
			}
			Sheet xssfSheet = xssfWorkbook.getSheetAt(i);
			if(xssfSheet==null){
				continue;
			}
			for (int rowNum = head; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {//the first row is doc, second row is title
				Row row = xssfSheet.getRow(rowNum);
				temp = new HashMap<String, String>();
				temp.put("col-r", String.valueOf(rowNum+1));
				boolean isError = true;
				for (int cellNum = 0; cellNum <= limit[0].length || cellNum<=row.getLastCellNum(); cellNum++) {
					Cell cell = row.getCell(cellNum);
					String value = "";
					try{
						CellType cellType = cell.getCellType();
						if(cellType == CellType.NUMERIC) {
							DecimalFormat df = new DecimalFormat("0");
							value = df.format(cell.getNumericCellValue());
						}else {
							value = getValue(cell);
						}
					}catch(Exception e){
						e.printStackTrace();
						String msg = e.toString();
						throw new Exception("error zzzde.code.technic.date sheet:"+i+" rowNum:"+rowNum+"  cellNum:"+cellNum+"   "+(msg.length()>100?msg.substring(0, 100):msg));
					}
					if (!compare(limit, cellNum, value)) {
						String msg= "第"+(rowNum+1)+"行第"+ (cellNum+1)+"列数据无效,请确认后重新上传";
//						returnMap.put("error", msg);
//						return returnMap;
						errorList.add(msg);
						isError=false;
						continue;
//						temp.put("col-"+cellNum, value);
					}
					temp.put("col-"+cellNum, value);

				}
				if(isError){
					result.add(temp);
				}
			}
		}
		fis.close();
		returnMap.put("errorList", errorList);
		returnMap.put("data", result);
		return returnMap;
	}


	/**
	 * 以流的形式，解析超大excel文件。避免内存溢出问题
	 * 注意：只能解析xlsx格式的excel文件。
	 * @param f 文件对象
	 * @param head excel头，在sheet中需要跳过的row行数
	 * @return Map {
	 *     success 是否成功
	 *     msg 返回信息
	 *     rowNum excel中的行数
	 *     data List集合数据{
	 *         col-0：value
	 *         col-1：value
	 *         col-2：value
	 *     }
	 * }
	 */
	public static Map<String,Object> parseHugeExcelFile(File f, int head) {
		String[][] limit = {{},{}}; // 第一个值不可为空
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("success", true);
		returnMap.put("msg","解析正常");
		List<Map> dataList = new ArrayList<>();
		InputStream stream = null;
		try{
			//获取文件对象转化为输入流
			stream = new FileInputStream(f);
			// 将输入流转换为工作簿对象
			Workbook wb = StreamingReader.builder()
					.rowCacheSize(100)//读取到内存中的行数，默认10
					.bufferSize(4096)//读取资源，缓存到内存的字节大小。默认1024
					.open(stream);//打开资源。只能是xlsx文件
			//获取所有sheet
			// 解析行
			int rowNum = 0 ;//总行数
			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
				Sheet sheet = wb.getSheetAt(i);
				if (sheet == null) continue;
				rowLoop:for (Row row : sheet) {
					rowNum ++ ;
					Map<Integer,String> temp = new HashMap<>();
					if ( rowNum <= head ) continue; //the first row is remark , second row is title
					// 解析前三列
					for (int cellNum = 0; cellNum <= 2 ; cellNum++) {
						Cell cell = row.getCell(cellNum);
						String value = getValue(cell);
						if ( cellNum == 0 && StringUtils.isEmpty(value)){ //第一列的值为空时跳过该行循环
							continue rowLoop;
						}
						temp.put(cellNum,value);
					}
					dataList.add(temp);
				}
			}
			returnMap.put("data", dataList);
			returnMap.put("rowNum",rowNum - head );
		}catch (Exception e){
			e.printStackTrace();
			returnMap.put("success",false);
			returnMap.put("msg","解析异常");
			return returnMap;
		}finally{
			try {
				if (f!=null) f.delete();
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return returnMap;
	}
}
