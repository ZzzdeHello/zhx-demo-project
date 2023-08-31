package xml;

import cn.hutool.core.util.JAXBUtil;
import cn.hutool.core.util.XmlUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.List;

/**
 * @author zzzde
 * @version 1.0
 * @date 2023/4/21 16:48
 */
public class Demo {

    public static void main(String[] args) {
        String xmlStr = "<xml><ToUserName><![CDATA[ww4a6741de0fa8b109]]></ToUserName><FromUserName><![CDATA[sys]]></FromUserName><CreateTime>1682066266</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[open_approval_change]]></Event><AgentID>1000002</AgentID><ApprovalInfo><ThirdNo><![CDATA[30]]></ThirdNo><OpenSpName><![CDATA[活动申请测试模板]]></OpenSpName><OpenTemplateId><![CDATA[3f4b6b2e4bc8d44d874ea77cb3c105d6_193813360]]></OpenTemplateId><OpenSpStatus>1</OpenSpStatus><ApplyTime>1682066265</ApplyTime><ApplyUserName><![CDATA[孙永花]]></ApplyUserName><ApplyUserId><![CDATA[LiuYi]]></ApplyUserId><ApplyUserParty><![CDATA[杭州港快]]></ApplyUserParty><ApplyUserImage><![CDATA[https://rescdn.qqmail.com/node/wwmng/wwmng/style/images/independent/DefaultAvatar$73ba92b5.png]]></ApplyUserImage><ApprovalNodes><ApprovalNode><NodeStatus>1</NodeStatus><Items><Item><ItemName><![CDATA[朱航轩]]></ItemName><ItemImage><![CDATA[https://rescdn.qqmail.com/node/wwmng/wwmng/style/images/independent/DefaultAvatar$73ba92b5.png]]></ItemImage><ItemUserId><![CDATA[XuanDaTou]]></ItemUserId><ItemStatus>1</ItemStatus><ItemSpeech><![CDATA[]]></ItemSpeech><ItemOpTime>0</ItemOpTime></Item></Items><NodeAttr>1</NodeAttr><NodeType>1</NodeType></ApprovalNode><ApprovalNode><NodeStatus>1</NodeStatus><Items><Item><ItemName><![CDATA[周禄斌]]></ItemName><ItemImage><![CDATA[https://rescdn.qqmail.com/node/wwmng/wwmng/style/images/independent/DefaultAvatar$73ba92b5.png]]></ItemImage><ItemUserId><![CDATA[NiCheng]]></ItemUserId><ItemStatus>1</ItemStatus><ItemSpeech><![CDATA[]]></ItemSpeech><ItemOpTime>0</ItemOpTime></Item></Items><NodeAttr>1</NodeAttr><NodeType>1</NodeType></ApprovalNode></ApprovalNodes><NotifyNodes><NotifyNode><ItemName><![CDATA[朱航轩]]></ItemName><ItemImage><![CDATA[https://rescdn.qqmail.com/node/wwmng/wwmng/style/images/independent/DefaultAvatar$73ba92b5.png]]></ItemImage><ItemUserId><![CDATA[XuanDaTou]]></ItemUserId></NotifyNode><NotifyNode><ItemName><![CDATA[周禄斌]]></ItemName><ItemImage><![CDATA[https://rescdn.qqmail.com/node/wwmng/wwmng/style/images/independent/DefaultAvatar$73ba92b5.png]]></ItemImage><ItemUserId><![CDATA[NiCheng]]></ItemUserId></NotifyNode></NotifyNodes><ApproverStep>0</ApproverStep></ApprovalInfo></xml>";
        WxEventParams msgInfo = JAXBUtil.xmlToBean(xmlStr, WxEventParams.class);
        WxcpApprovalXml approvalInfo = msgInfo.getApprovalInfo();
        // 审批实例Id
        String thirdNo = approvalInfo.getThirdNo();
        // 获取实例对象，更新审批状态变化

        // 审批单状态: 1-审批中；2-已通过；3-已驳回；4-已撤销
        Integer openSpStatus = approvalInfo.getOpenSpStatus();
        System.out.println("openSpStatus:" + openSpStatus);
        // 审批单节点位置
        Integer approverStep = approvalInfo.getApproverStep();
        List<WxcpApprovalXml.ApprovalNode> approvalNodeList = approvalInfo.getApprovalNodes();
        List<WxcpApprovalXml.NotifyNode> notifyNodes = approvalInfo.getNotifyNodes();
        System.out.println("-");
    }

}
