package multithreading;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThreadFileParseTest {

    private final static String FIELDS_FS = "\\$%\\$";//字段分割符号

    private static volatile int callNum = 0;

    public static void main(String[] args) {
        parseFile("QD_YF_20210706_160216");
    }


    private static void parseFile(String batch) {
        ExecutorService fileParsePool = Executors.newFixedThreadPool(5);

        FileInputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bfReader = null;
        try {
            String localDir = System.getProperty("user.dir") + "/file/ccView/";
            File localFile = new File(localDir);
            File[] listFile = localFile.listFiles();
            if (listFile == null) {
                System.out.println("listFile为null直接返回");
                return;
            }
            for (File parsingFile : listFile) {
                // todo 取其中一个文件来分
                if (parsingFile.getName().endsWith(".dat") && parsingFile.getName().contains(batch)) {
                    // 开启固定线程池：多线程执行
                    // 逐行读取本地文件存放 数据集合
                    List<String> dataList = new ArrayList<>();
                    // 解析文件
                    inputStream = new FileInputStream(parsingFile);
                    inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                    bfReader = new BufferedReader(inputStreamReader, 5 * 1024 * 1024);// 分5MB 缓存文件读取
                    //解析数据
                    String lineData = "";
                    long fileLength = parsingFile.length();
                    LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(parsingFile));
                    lineNumberReader.skip(fileLength);
                    int lines = lineNumberReader.getLineNumber();//总行数
                    System.out.println("行数:" + lines);
                    double newlines = (double) (lines / 5000.0);
                    //计数器大小
                    int latchNumber = (int) Math.ceil(newlines);
                    CountDownLatch latch = new CountDownLatch(latchNumber);//记数器大小

                    int line = 0;//行数
                    while ((lineData = bfReader.readLine()) != null) {
                        line++;
                        // 加入集合
                        dataList.add(lineData);
                        if (dataList.size() == 5000) {
                            System.out.println("五千行一线程：此时为" + line + "行");
                            //每次传入线程的集合
                            final List<String> parcelList = new ArrayList<>();
                            parcelList.addAll(dataList);
                            // 清空集合
                            dataList.clear();
                            // 开启线程
                            try {
                                Runnable task = new BatchHandlerThreadTask(batch, line, parcelList);
                                fileParsePool.submit(task);
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                latch.countDown();//计数器-1
                            }
                        }
                    }
                    //判断最后一次
                    if (dataList.size() != 0) {
                        final List<String> parcelList = new ArrayList<>();
                        dataList.clear();
                        try {
                            Runnable task = new BatchHandlerThreadTask(batch, line, parcelList);
                            fileParsePool.submit(task);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            latch.countDown();//计数器-1
                        }
                    }
                    inputStream.close();
                    inputStreamReader.close();
                    bfReader.close();
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    fileParsePool.shutdown(); //关闭线程
                    boolean isDatOver = parsingFile.delete();
                    if (isDatOver) System.out.println("----[##--##]------删除" + parsingFile.getName() + "成功");
                }
                //dat文件处理完成，最后删除flag文件
                if (parsingFile.getName().endsWith(".flg") && parsingFile.getName().contains(batch)) {
                    boolean delete = parsingFile.delete();
                    if (delete) System.out.println("----[##--##]------删除" + parsingFile.getName() + "成功");
                }

            }
        } catch (Exception e) {
            System.out.println("该批次" + batch + "文件解析失败");
            e.printStackTrace();
        } finally {
            try {
                if (bfReader != null) {
                    bfReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                System.out.println("流关闭异常");
                e.printStackTrace();
            }

        }

    }


    public static class BatchHandlerThreadTask implements Runnable {

        //文件批次
        private String batch;

        //行数
        private int line;

        //待处理数据集合
        private List dataList;

        public BatchHandlerThreadTask(String batch, int line, List dataList) {
            this.batch = batch;
            this.line = line;
            this.dataList = dataList;
        }

        public BatchHandlerThreadTask() {
            super();
        }

        public BatchHandlerThreadTask(List dataList) {
            super();
            this.dataList = dataList;
        }

        public List getDataList() {
            return dataList;
        }

        public void setDataList(List dataList) {
            this.dataList = dataList;
        }

        public String getBatch() {
            return batch;
        }

        public void setBatch(String batch) {
            this.batch = batch;
        }

        public int getLine() {
            return line;
        }

        public void setLine(int line) {
            this.line = line;
        }

        @Override
        public void run() {
            long t1 = System.currentTimeMillis();

            for (int y = 0; y < dataList.size(); y++) {
                String lineStr = (String) dataList.get(y);
                String[] fields = lineStr.split(FIELDS_FS, -1);
                CustAccessTraceDto custAccessTraceDto;
                try {
                    custAccessTraceDto = assembleDubboMsg(fields);
                    if (StringUtils.isEmpty(custAccessTraceDto.getCustNumber())) {
                        continue;
                    }
                } catch (Exception e) {
                    System.out.println("批次" + batch + "的第" + (line + y) + "行的字段有问题出现异常");
                    continue;
                }

                try {

                    Object[] params = {custAccessTraceDto};
                    //OrderResultObject<String> stringOrderResultObject = (OrderResultObject<String>) dubboKitService.invoke(dubboService, "receiveCustAccessTrace", params);
                    System.out.println("------------------------------------> 调用方法,客户编码" + custAccessTraceDto.getCustNumber());

                    callNum++;
                } catch (Exception e) {
                    System.out.println("调用dubbo方法接口调用异常");
                    e.printStackTrace();
                }

            }
            System.out.println("--h--线程名: " + Thread.currentThread().getName() + "--当前线程耗时：" + (System.currentTimeMillis() - t1) + "--当前批次处理总数据" + dataList.size());
        }

    }


    public static CustAccessTraceDto assembleDubboMsg(String[] fields) {
        CustAccessTraceDto dto = new CustAccessTraceDto();
        String sysSource = isNotBlank(fields[0]);
        String custNumber = isNotBlank(fields[1]);
        String custId = isNotBlank(fields[2]);
        String prodNbr = isNotBlank(fields[3]);
        //String prodName
        String accNum = isNotBlank(fields[4]);
        String prodInstId = isNotBlank(fields[5]);
        String integrationNbr = isNotBlank(fields[6]);
        String lanId = isNotBlank(fields[7]);
        String acctCd = isNotBlank(fields[8]);
        String accessTime = dateFormat(isNotBlank(fields[9]));//时间格式变换
        String accessChannel = isNotBlank(fields[10]);
        String isOnLine = isNotBlank(fields[11]);
        String isActive = isNotBlank(fields[12]);
        String companyInfoFirst = isNotBlank(fields[13]);
        String companyInfoSecond = isNotBlank(fields[14]);
        String companyInfoThird = isNotBlank(fields[15]);
        String accessStaffCode = isNotBlank(fields[16]);
        String accessStaffName = isNotBlank(fields[17]);
        String accessStaffPhone = isNotBlank(fields[18]);
        String accessBusiType = isNotBlank(fields[19]);
        String busiTypeLevelFirst = isNotBlank(fields[20]);
        String busiTypeLevelSecond = isNotBlank(fields[21]);
        String busiTypeLevelThird = isNotBlank(fields[22]);
        String busiTypeLevelFourth = isNotBlank(fields[23]);
        String accessSuccess = isNotBlank(fields[24]);
        String accessResult = isNotBlank(fields[25]);
        String accessResultDesc = isNotBlank(fields[26]);
        String isOnceSolve = isNotBlank(fields[27]);
        String dispatchType = isNotBlank(fields[28]);
        String custOrderNbr = isNotBlank(fields[29]);
        String duration = isNotBlank(fields[30]);
        String relatedOrderNbr = isNotBlank(fields[31]);
        String accessAccount = isNotBlank(fields[32]);
        String recordingFilePath = isNotBlank(fields[33]);
        String AccessContent = isNotBlank(fields[34]);
        String evaluateTime = dateFormat(isNotBlank(fields[35]));//时间格式变换
        String evaluateChannel = isNotBlank(fields[36]);
        String evaluateResult = isNotBlank(fields[37]);
        String remark = isNotBlank(fields[38]) + isNotBlank(fields[39]) + isNotBlank(fields[40]) + isNotBlank(fields[41]) + isNotBlank(fields[42]) + isNotBlank(fields[43]) + isNotBlank(fields[44]) + isNotBlank(fields[45]);
        dto.setAccNum(accNum);
        dto.setProdNbr(prodNbr);
        dto.setProdInstId(prodInstId);
        dto.setIntegrationNbr(integrationNbr);
        dto.setSysSource(sysSource);
        dto.setCustNumber(custNumber);
        dto.setCustId(custId);
        dto.setLanId(lanId);
        dto.setAcctCd(acctCd);
        dto.setCompanyInfoFirst(companyInfoFirst);
        dto.setCompanyInfoSecond(companyInfoSecond);
        dto.setCompanyInfoThird(companyInfoThird);
        dto.setAccessTime(accessTime);
        dto.setAccessChannel(accessChannel);
        dto.setIsOnLine(isOnLine);
        dto.setIsActive(isActive);
        dto.setAccessStaffCode(accessStaffCode);
        dto.setAccessStaffName(accessStaffName);
        dto.setAccessStaffPhone(accessStaffPhone);
        dto.setAccessBusiType(accessBusiType);
        dto.setBusiTypeLevelFirst(busiTypeLevelFirst);
        dto.setBusiTypeLevelSecond(busiTypeLevelSecond);
        dto.setBusiTypeLevelThird(busiTypeLevelThird);
        dto.setBusiTypeLevelFourth(busiTypeLevelFourth);
        dto.setAccessSuccess(accessSuccess);
        dto.setAccessResult(accessResult);
        dto.setAccessResultDesc(accessResultDesc);
        dto.setIsOnceSolve(isOnceSolve);
        dto.setDispatchType(dispatchType);
        dto.setCustOrderNbr(custOrderNbr);
        dto.setDuration(duration);
        dto.setRelatedOrderNbr(relatedOrderNbr);
        dto.setAccessAccount(accessAccount);
        dto.setRecordingFilePath(recordingFilePath);
        dto.setAccessContent(AccessContent);
        dto.setEvaluateTime(evaluateTime);
        dto.setEvaluateChannel(evaluateChannel);
        dto.setEvaluateResult(evaluateResult);
        dto.setRemark(remark);
        return dto;
    }


    public static class CustAccessTraceDto implements Serializable {
        private static final long serialVersionUID = 3450017435361353867L;
        private String sysSource;
        private String custNumber;
        private String custId;
        private String prodNbr;
        private String prodName;
        private String accNum;
        private String prodInstId;
        private String integrationNbr;
        private String lanId;
        private String acctCd;
        private String accessTime;
        private String accessChannel;
        private String isOnLine;
        private String isActive;
        private String companyInfoFirst;
        private String companyInfoSecond;
        private String companyInfoThird;
        private String accessStaffCode;
        private String accessStaffName;
        private String accessStaffPhone;
        private String accessBusiType;
        private String busiTypeLevelFirst;
        private String busiTypeLevelSecond;
        private String busiTypeLevelThird;
        private String busiTypeLevelFourth;
        private String accessSuccess;
        private String accessResult;
        private String accessResultDesc;
        private String isOnceSolve;
        private String dispatchType;
        private String custOrderNbr;
        private String duration;
        private String relatedOrderNbr;
        private String accessAccount;
        private String recordingFilePath;
        private String AccessContent;
        private String evaluateTime;
        private String evaluateChannel;
        private String evaluateResult;
        private String remark;

        public CustAccessTraceDto() {
        }

        public String getSysSource() {
            return this.sysSource;
        }

        public void setSysSource(String sysSource) {
            this.sysSource = sysSource;
        }

        public String getCustNumber() {
            return this.custNumber;
        }

        public void setCustNumber(String custNumber) {
            this.custNumber = custNumber;
        }

        public String getCustId() {
            return this.custId;
        }

        public void setCustId(String custId) {
            this.custId = custId;
        }

        public String getProdNbr() {
            return this.prodNbr;
        }

        public void setProdNbr(String prodNbr) {
            this.prodNbr = prodNbr;
        }

        public String getProdName() {
            return this.prodName;
        }

        public void setProdName(String prodName) {
            this.prodName = prodName;
        }

        public String getProdInstId() {
            return this.prodInstId;
        }

        public void setProdInstId(String prodInstId) {
            this.prodInstId = prodInstId;
        }

        public String getLanId() {
            return this.lanId;
        }

        public void setLanId(String lanId) {
            this.lanId = lanId;
        }

        public String getAcctCd() {
            return this.acctCd;
        }

        public void setAcctCd(String acctCd) {
            this.acctCd = acctCd;
        }

        public String getAccessTime() {
            return this.accessTime;
        }

        public void setAccessTime(String accessTime) {
            this.accessTime = accessTime;
        }

        public String getAccessChannel() {
            return this.accessChannel;
        }

        public void setAccessChannel(String accessChannel) {
            this.accessChannel = accessChannel;
        }

        public String getIsOnLine() {
            return this.isOnLine;
        }

        public void setIsOnLine(String isOnLine) {
            this.isOnLine = isOnLine;
        }

        public String getIsActive() {
            return this.isActive;
        }

        public void setIsActive(String isActive) {
            this.isActive = isActive;
        }

        public String getCompanyInfoFirst() {
            return this.companyInfoFirst;
        }

        public void setCompanyInfoFirst(String companyInfoFirst) {
            this.companyInfoFirst = companyInfoFirst;
        }

        public String getCompanyInfoSecond() {
            return this.companyInfoSecond;
        }

        public void setCompanyInfoSecond(String companyInfoSecond) {
            this.companyInfoSecond = companyInfoSecond;
        }

        public String getCompanyInfoThird() {
            return this.companyInfoThird;
        }

        public void setCompanyInfoThird(String companyInfoThird) {
            this.companyInfoThird = companyInfoThird;
        }

        public String getAccessStaffCode() {
            return this.accessStaffCode;
        }

        public void setAccessStaffCode(String accessStaffCode) {
            this.accessStaffCode = accessStaffCode;
        }

        public String getAccessStaffName() {
            return this.accessStaffName;
        }

        public void setAccessStaffName(String accessStaffName) {
            this.accessStaffName = accessStaffName;
        }

        public String getAccessStaffPhone() {
            return this.accessStaffPhone;
        }

        public void setAccessStaffPhone(String accessStaffPhone) {
            this.accessStaffPhone = accessStaffPhone;
        }

        public String getAccessBusiType() {
            return this.accessBusiType;
        }

        public void setAccessBusiType(String accessBusiType) {
            this.accessBusiType = accessBusiType;
        }

        public String getBusiTypeLevelFirst() {
            return this.busiTypeLevelFirst;
        }

        public void setBusiTypeLevelFirst(String busiTypeLevelFirst) {
            this.busiTypeLevelFirst = busiTypeLevelFirst;
        }

        public String getBusiTypeLevelSecond() {
            return this.busiTypeLevelSecond;
        }

        public void setBusiTypeLevelSecond(String busiTypeLevelSecond) {
            this.busiTypeLevelSecond = busiTypeLevelSecond;
        }

        public String getBusiTypeLevelThird() {
            return this.busiTypeLevelThird;
        }

        public void setBusiTypeLevelThird(String busiTypeLevelThird) {
            this.busiTypeLevelThird = busiTypeLevelThird;
        }

        public String getBusiTypeLevelFourth() {
            return this.busiTypeLevelFourth;
        }

        public void setBusiTypeLevelFourth(String busiTypeLevelFourth) {
            this.busiTypeLevelFourth = busiTypeLevelFourth;
        }

        public String getAccessSuccess() {
            return this.accessSuccess;
        }

        public void setAccessSuccess(String accessSuccess) {
            this.accessSuccess = accessSuccess;
        }

        public String getAccessResult() {
            return this.accessResult;
        }

        public void setAccessResult(String accessResult) {
            this.accessResult = accessResult;
        }

        public String getAccessResultDesc() {
            return this.accessResultDesc;
        }

        public void setAccessResultDesc(String accessResultDesc) {
            this.accessResultDesc = accessResultDesc;
        }

        public String getIsOnceSolve() {
            return this.isOnceSolve;
        }

        public void setIsOnceSolve(String isOnceSolve) {
            this.isOnceSolve = isOnceSolve;
        }

        public String getDispatchType() {
            return this.dispatchType;
        }

        public void setDispatchType(String dispatchType) {
            this.dispatchType = dispatchType;
        }

        public String getCustOrderNbr() {
            return this.custOrderNbr;
        }

        public void setCustOrderNbr(String custOrderNbr) {
            this.custOrderNbr = custOrderNbr;
        }

        public String getDuration() {
            return this.duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getRelatedOrderNbr() {
            return this.relatedOrderNbr;
        }

        public void setRelatedOrderNbr(String relatedOrderNbr) {
            this.relatedOrderNbr = relatedOrderNbr;
        }

        public String getAccessAccount() {
            return this.accessAccount;
        }

        public void setAccessAccount(String accessAccount) {
            this.accessAccount = accessAccount;
        }

        public String getRecordingFilePath() {
            return this.recordingFilePath;
        }

        public void setRecordingFilePath(String recordingFilePath) {
            this.recordingFilePath = recordingFilePath;
        }

        public String getAccessContent() {
            return this.AccessContent;
        }

        public void setAccessContent(String accessContent) {
            this.AccessContent = accessContent;
        }

        public String getEvaluateTime() {
            return this.evaluateTime;
        }

        public void setEvaluateTime(String evaluateTime) {
            this.evaluateTime = evaluateTime;
        }

        public String getEvaluateChannel() {
            return this.evaluateChannel;
        }

        public void setEvaluateChannel(String evaluateChannel) {
            this.evaluateChannel = evaluateChannel;
        }

        public String getEvaluateResult() {
            return this.evaluateResult;
        }

        public void setEvaluateResult(String evaluateResult) {
            this.evaluateResult = evaluateResult;
        }

        public String getRemark() {
            return this.remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getIntegrationNbr() {
            return this.integrationNbr;
        }

        public void setIntegrationNbr(String integrationNbr) {
            this.integrationNbr = integrationNbr;
        }

        public String getAccNum() {
            return this.accNum;
        }

        public void setAccNum(String accNum) {
            this.accNum = accNum;
        }
    }

    private static String isNotBlank(String str) {
        if (StringUtils.isNotBlank(str) && !str.equals("null")) {
            //过滤换行符\r\n
            return str;
        }
        return null;
    }

    private static String dateFormat(String dateStr) {
        if (StringUtils.isEmpty(dateStr) || "null".equals(dateStr)) {
            return "";
        }
        SimpleDateFormat sdfNew = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdfOld = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDateStr;
        try {
            Date parse = sdfOld.parse(dateStr);
            newDateStr = sdfNew.format(parse);
        } catch (ParseException e) {
            e.printStackTrace();
            newDateStr = "";
        }
        return newDateStr;
    }
}
