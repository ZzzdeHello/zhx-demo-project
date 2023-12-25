package zzzde.project.technic.httpClient;

import com.alibaba.druid.support.json.JSONUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OTO {
    public static void main(String[] args) {
        //String url = "https://134.108.5.200:8001/ccoop-78f4d4b644-xgw58/api-platfrom/tool/bssContactEvtInstTest";
        String url = "https://134.108.3.248:8006/ccoop-78f4d4b644-xgw58/api-platfrom/tool/bssContactEvtInstTest";

        //String evtUrl = "https://134.108.5.200:8001/ccoop-78f4d4b644-xgw58/api-platfrom/tool/contact/log/";
        String evtUrl = "https://134.108.3.248:8006/ccoop-78f4d4b644-xgw58/api-platfrom/tool/contact/log/";

        List<String> evtSerialNums = new ArrayList<>();
        evtSerialNums.add("EVT2021011512291601809673");//EVT2021011516070295840415
        evtSerialNums.add("EVT2021011515235351753290");//EVT2021011516074624717098
        evtSerialNums.add("EVT2021011510263221435451");//EVT2021011516082914392814
        evtSerialNums.add("EVT2021011514300462351249");//EVT2021011516090469512637
        evtSerialNums.add("EVT2021011510153757104680");//EVT2021011516094188411559
        evtSerialNums.add("EVT2021011312074302885328");//EVT2021011516102528957867
        evtSerialNums.add("EVT2021011312301510050027");//EVT2021011516110444302140
        evtSerialNums.add("EVT2021011314414750835369");//EVT2021011516114019670109
        for(String evtSerialNum: evtSerialNums){

            // 查询habse 报文
            // String type = "1";
            String tableName = "bsscoop:contact_evt_inst_req_log_"+evtSerialNum.substring(9, 11);
            String keyWord = evtSerialNum;
//          Map<String, Object> param = new HashMap<String, Object>();
//          param.put("tableName", tableName);
//          param.put("rowName", keyWord);
            // Map<String, Object> returnValue = getOneMap(param);
            // String reqInstContent = returnValue.get("reqInstContent").toString();
            try {
                String evtMsg = doPostForEvt(evtUrl,"1",tableName,keyWord);
                Map<String, Object> parse = (Map<String, Object>) JSONUtils.parse(evtMsg);
                Map<String, Object> data =  (Map)parse.get("data");
                List<Map<String, Object>> list = (ArrayList)data.get("list");
                String reqInstContent = list.get(0).get("reqInstContent").toString();//todo 转化为json格式
                System.out.println( reqInstContent );

                //post 请求报文
                String postResultMsg = doPostForForm(url, reqInstContent);
                System.out.println( "原流水号" +evtSerialNum  +"|||||请求结果为"+ postResultMsg );
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("HttpClient请求失败"+ "该 请求的 原始 evtSerialNum为 ：" +evtSerialNum + " 请检查 or 重试 ");
            }
    }

}



    //本地方法： list 拼接成 string字符串 “,” 隔开 的格式
    private String methodForString(List<String> list ){
        StringBuffer value = new StringBuffer();
        for(String temp : list){
            value.append("'" + temp + "',");
        }
        return value.toString().substring(0, value.toString().length() - 1);
    }

    public static String doPostForEvt( String url , String type , String tableName ,String keyWord ){
        // 创建Post请求
        CloseableHttpClient closeableHttpClient = wrapClient();
        //配置连接超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(5000)
                .setRedirectsEnabled(true)
                .build();
        HttpPost httpPost = new HttpPost(url);
        //设置超时时间
        httpPost.setConfig(requestConfig);


        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("type",type);
        paramsMap.put("tableName",tableName);
        paramsMap.put("keyWord",keyWord);

        List<NameValuePair> list = new ArrayList<NameValuePair>();
        for (String key : paramsMap.keySet()) {
            list.add(new BasicNameValuePair(key, String.valueOf(paramsMap.get(key))));
        }

        try {
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "utf-8");
            httpPost.setEntity(urlEncodedFormEntity);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            //执行 post请求
            CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost);
            String strRequest = "";
            if (null != closeableHttpResponse && !"".equals(closeableHttpResponse)) {
                System.out.println(closeableHttpResponse.getStatusLine().getStatusCode());
                if (closeableHttpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity httpEntity = closeableHttpResponse.getEntity();
                    strRequest = EntityUtils.toString(httpEntity);
                } else {
                    strRequest = "Error Response" + closeableHttpResponse.getStatusLine().getStatusCode();
                }
            }
            return strRequest;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return "协议异常";
        } catch (ParseException e) {
            e.printStackTrace();
            return "解析异常";
        } catch (IOException e) {
            e.printStackTrace();
            return "传输异常";
        } finally {
            try {
                if (closeableHttpClient != null) {
                    closeableHttpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static String doPostForForm(String url , String eventData ) {

        // 创建Post请求
        CloseableHttpClient closeableHttpClient = wrapClient();
        //配置连接超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(5000)
                .setRedirectsEnabled(true)
                .build();
        HttpPost httpPost = new HttpPost(url);
        //设置超时时间
        httpPost.setConfig(requestConfig);


        //请求体重中放入 参数 ；
        HashMap<String, String> paramsMap = new HashMap();
        paramsMap.put("paramStr",eventData);
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        for (String key : paramsMap.keySet()) {
            list.add(new BasicNameValuePair(key, String.valueOf(paramsMap.get(key))));
        }

        try {
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "utf-8");
            httpPost.setEntity(urlEncodedFormEntity);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            //执行 post请求
            CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost);
            String strRequest = "";
            if (null != closeableHttpResponse && !"".equals(closeableHttpResponse)) {
                System.out.println(closeableHttpResponse.getStatusLine().getStatusCode());
                if (closeableHttpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity httpEntity = closeableHttpResponse.getEntity();
                    strRequest = EntityUtils.toString(httpEntity);
                } else {
                    strRequest = "Error Response" + closeableHttpResponse.getStatusLine().getStatusCode();
                }
            }
            return strRequest;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return "协议异常";
        } catch (ParseException e) {
            e.printStackTrace();
            return "解析异常";
        } catch (IOException e) {
            e.printStackTrace();
            return "传输异常";
        } finally {
            try {
                if (closeableHttpClient != null) {
                    closeableHttpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    // 跳过https 域名认证 防止ssl异常
    public static CloseableHttpClient wrapClient() {
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] arg0,
                                               String arg1) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] arg0,
                                               String arg1) throws CertificateException {
                }
            };
            ctx.init(null, new TrustManager[] { tm }, null);
            SSLConnectionSocketFactory ssf = new SSLConnectionSocketFactory(
                    ctx, NoopHostnameVerifier.INSTANCE);
            CloseableHttpClient httpclient = HttpClients.custom()
                    .setSSLSocketFactory(ssf).build();
            return httpclient;
        } catch (Exception e) {
            return HttpClients.createDefault();
        }
    }
}
