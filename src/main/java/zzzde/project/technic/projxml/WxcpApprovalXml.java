package zzzde.project.technic.projxml;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 自建应用审批信息
 *
 * @author zzzde
 * @version 1.0
 * @date 2023/2/20 15:57
 */
@Data
@XmlRootElement(name = "ApprovalInfo")
@XmlAccessorType(XmlAccessType.FIELD)
public class WxcpApprovalXml implements Serializable {

    private static final long serialVersionUID = 1L;

    //******************** 自建应用审批状态变化通知回调 ********************/
    /**
     * 审批单编号，由开发者在发起申请时自定义
     */
    @XmlElement(name = "ThirdNo")
    private String thirdNo;

    /**
     * 审批模板名称
     */
    @XmlElement(name = "OpenSpName")
    private String openSpName;

    /**
     * 审批模板id
     */
    @XmlElement(name = "OpenTemplateId")
    private String openTemplateId;

    /**
     * 申请单当前审批状态：1-审批中；2-已通过；3-已驳回；4-已撤销
     */
    @XmlElement(name = "OpenSpStatus")
    private Integer openSpStatus;

    /**
     * 提交者姓名
     */
    @XmlElement(name = "ApplyUserName")
    private String applyUserName;

    /**
     * 提交者userid
     */
    @XmlElement(name = "ApplyUserId")
    private String applyUserId;

    /**
     * 提交者所在部门
     */
    @XmlElement(name = "ApplyUserParty")
    private String applyUserParty;

    /**
     * 提交者头像
     */
    @XmlElement(name = "ApplyUserImage")
    private String applyUserImage;

    /**
     * 当前审批节点：0-第一个审批节点；1-第二个审批节点…以此类推
     */
    @XmlElement(name = "ApproverStep")
    private Integer approverStep;

    /**
     * 审批流程信息
     */
//    @XmlElement(name = "ApprovalNodes")
    @XmlElementWrapper(name = "ApprovalNodes")
    @XmlElement(name = "ApprovalNode")
    private List<ApprovalNode> approvalNodes;

    /**
     * 抄送信息，可能有多个抄送人
     */
    @XmlElementWrapper(name = "NotifyNodes")
    @XmlElement(name = "NotifyNode")
    private List<NotifyNode> notifyNodes;

    /**
     * 抄送人信息
     */
    @XmlRootElement(name = "NotifyNode")
    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class NotifyNode implements Serializable{
        /**
         * 抄送人姓名
         */
        @XmlElement(name = "ItemName")
        private String itemName;

        /**
         * 抄送人userid
         */
        @XmlElement(name = "ItemUserid")
        private String itemUserId;

        /**
         * 抄送人所在部门
         */
        @XmlElement(name = "ItemParty")
        private String itemParty;

        /**
         * 抄送人头像
         */
        @XmlElement(name = "ItemImage")
        private String itemImage;

    }

    /**
     * 审批流程信息，可以有多个审批节点
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class ApprovalNode implements Serializable{
        /**
         * 节点审批操作状态：1-审批中；2-已同意；3-已驳回；4-已转审
         */
        @XmlElement(name = "NodeStatus")
        private Integer nodeStatus;

        /**
         * 审批节点属性：1-或签；2-会签
         */
        @XmlElement(name = "NodeAttr")
        private Integer nodeAttr;

        /**
         * 审批节点类型：1-固定成员；2-标签；3-上级
         */
        @XmlElement(name = "NodeType")
        private Integer nodeType;

        /**
         * 审批节点信息，当节点为标签或上级时，一个节点可能有多个分支
         */
        @XmlElementWrapper(name = "Items")
        @XmlElement(name = "Item")
        private List<Item> items;

    }

    /**
     * 审批节点分支，当节点为标签或上级时，一个节点可能有多个分支
     */
    @XmlRootElement(name = "Item")
    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class Item implements Serializable{
        /**
         * 分支审批人姓名
         */
        @XmlElement(name = "ItemName")
        private String itemName;

        /**
         * 分支审批人userid
         */
        @XmlElement(name = "ItemUserId")
        private String itemUserId;

        /**
         * 分支审批人所在部门
         */
        @XmlElement(name = "ItemParty")
        private String itemParty;

        /**
         * 分支审批人头像
         */
        @XmlElement(name = "ItemImage")
        private String itemImage;

        /**
         * 分支审批人审批意见
         */
        @XmlElement(name = "ItemSpeech")
        private String itemSpeech;

        /**
         * 分支审批审批操作状态：1-审批中；2-已同意；3-已驳回；4-已转审
         */
        @XmlElement(name = "ItemStatus")
        private Integer itemStatus;

        /**
         * 分支审批人操作时间
         */
        @XmlElement(name = "ItemOpTime")
        private Long itemOpTime;

    }

    //******************** 审批申请状态变化回调通知 ********************/
    /**
     * 审批编号
     */
    @XmlElement(name = "SpNo")
    private String spNo;

    /**
     * 审批申请类型名称（审批模板名称）
     */
    @XmlElement(name = "SpName")
    private String spName;

    /**
     * 申请单状态：1-审批中；2-已通过；3-已驳回；4-已撤销；6-通过后撤销；7-已删除；10-已支付
     */
    @XmlElement(name = "SpStatus")
    private Integer spStatus;

    /**
     * 审批模板id。
     */
    @XmlElement(name = "TemplateId")
    private String templateId;

    /**
     * 审批申请提交时间,Unix时间戳
     */
    @XmlElement(name = "ApplyTime")
    private Long applyTime;



}
