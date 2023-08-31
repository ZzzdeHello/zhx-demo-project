package xml;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * @author zzzde
 * @version 1.0
 * @date 2023/2/22 17:12
 */
@Data
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WxEventParams implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 企业应用id
     */
    @XmlElement(name = "AgentID")
    private String agentId;

    /**
     * 开发者微信号
     */
    @XmlElement(name = "ToUserName")
    private String toUserName;

    /**
     * 发送方帐号（一个OpenID）
     */
    @XmlElement(name = "FromUserName")
    private String fromUserName;

    /**
     * 消息创建时间 （整型）
     */
    @XmlElement(name = "CreateTime")
    private Long createTime;

    /**
     * 消息类型，event
     */
    @XmlElement(name = "MsgType")
    private String msgType;

    /**
     * 事件类型，subscribe(订阅)、unsubscribe(取消订阅)
     */
    @XmlElement(name = "Event")
    private String event;

    /**
     * 审核结果
     */
    @XmlElement(name = "ret")
    private String ret;

    /**
     * 审核失败的驳回原因
     */
    @XmlElements({
            @XmlElement(name = "reason"),
            @XmlElement(name = "Reason")
    })
    private String reason;

    /**
     * 审核不通过的截图示例
     */
    @XmlElement(name = "ScreenShot")
    private String screenShot;

    /**
     * 新的UserID，变更时推送（userid由系统生成时可更改一次）.
     */
    @XmlElement(name = "NewUserID")
    private String newUserId;

    /**
     * 成员名称 或者部门名称
     */
    @XmlElement(name = "Name")
    private String name;

    /**
     * 手机号码.
     */
    @XmlElement(name = "Mobile")
    private String mobile;

    /**
     * 审批消息
     * <p>
     * 审批申请状态变化回调通知
     * https://developer.work.weixin.qq.com/document/path/91815
     * <p>
     * 自建应用审批状态变化通知回调
     * https://developer.work.weixin.qq.com/document/path/90269
     */
    @XmlElement(name = "ApprovalInfo")
    private WxcpApprovalXml approvalInfo = new WxcpApprovalXml();
}
