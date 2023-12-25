package zzzde.code.technic.collection.map.filter;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 协同商机订单信息vo
 *
 * @author lintao
 * @date 2020/5/8
 * @see
 */
public class TwBizOrderInfoVo implements Serializable {

    private static final long serialVersionUID = -1431289614484040594L;

    /**
     * 协同单号
     */
    private String custOrderNbr;

    /**
     * 记录订单的类型
     */
    private String orderType;

    /**
     * 客户名称-400600000008
     */
    private String custName;

    /**
     * 套包名称-400600000011
     */
    private String offerName;

    /**
     * 证件号码-400600000009
     */
    private String idNumber;

    /**
     * 业务号码
     */
    private String accNum;

    /**
     * 装维人员账号
     */
    private String contactStaff;

    /**
     * bss3.0订单号-400600000022
     */
    private String bssOrderNbr;

    /**
     * bss3.0订单id-400600000034
     */
    private String custOrderId;

    /**
     * 电渠单号-400600000021
     */
    private String extOrderNbr;

    /**
     * 地市C3-400600000013
     */
    private String c3Name;

    /**
     * 县市C4-根据C4的orgId查询orgName
     */
    private String c4Name;

    /**
     * 支局C5-根据C5的orgId查询orgName
     */
    private String c5Name;

    /**
     * 县市C4的orgId-400600000040
     */
    private String orgC4Id;

    /**
     * 支局C5的orgId-400600000041
     */
    private String orgC5Id;

    /**
     * 详细地址-400600000016
     */
    private String address;

    /**
     * 客户联系电话-400600000020
     */
    private String custTelePhone;

    /**
     * 订单创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderCreateDate;

    /**
     * 订单更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    /**
     * 预约时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bookingDate;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    /**
     * 接单人员姓名
     */
    private String contactName;

    /**
     * 接单人员手机
     */
    private String contactPhone;

    /**
     * 协同单订单状态
     */
    private String statusCd;

    /**
     * 3.0订单状态
     */
    private String bssStatusCd;

    /**
     * 销售员姓名
     */
    private String bssStaffName;

    /**
     * 销售员编码
     */
    private String bssStaffCode;

    /**
     * 销售员电话
     */
    private String bssStaffPhone;

    /**
     * 受理网点ID
     */
    private Long bssOrgId;

    /**
     * 受理网点编码-渠道编码
     */
    private String channelCode;

    /**
     * 受理网点名称
     */
    private String bssOrgName;

    /**
     * 组织
     */
    private String orgPathName;

    /**
     * 记录备注信息
     */
    private String remark;

    /**
     * 是否可以转派
     */
    private boolean canSend;

    /**
     * app的订单分类
     */
    private String appStatusCd;

    public String getAppStatusCd() {
        return appStatusCd;
    }

    public void setAppStatusCd(String appStatusCd) {
        this.appStatusCd = appStatusCd;
    }

    public String getCustOrderNbr() {
        return custOrderNbr;
    }

    public void setCustOrderNbr(String custOrderNbr) {
        this.custOrderNbr = custOrderNbr;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getAccNum() {
        return accNum;
    }

    public void setAccNum(String accNum) {
        this.accNum = accNum;
    }

    public String getContactStaff() {
        return contactStaff;
    }

    public void setContactStaff(String contactStaff) {
        this.contactStaff = contactStaff;
    }

    public String getBssOrderNbr() {
        return bssOrderNbr;
    }

    public void setBssOrderNbr(String bssOrderNbr) {
        this.bssOrderNbr = bssOrderNbr;
    }

    public String getCustOrderId() {
        return custOrderId;
    }

    public void setCustOrderId(String custOrderId) {
        this.custOrderId = custOrderId;
    }

    public String getExtOrderNbr() {
        return extOrderNbr;
    }

    public void setExtOrderNbr(String extOrderNbr) {
        this.extOrderNbr = extOrderNbr;
    }

    public String getC3Name() {
        return c3Name;
    }

    public void setC3Name(String c3Name) {
        this.c3Name = c3Name;
    }

    public String getC4Name() {
        return c4Name;
    }

    public void setC4Name(String c4Name) {
        this.c4Name = c4Name;
    }

    public String getC5Name() {
        return c5Name;
    }

    public void setC5Name(String c5Name) {
        this.c5Name = c5Name;
    }

    public String getOrgC4Id() {
        return orgC4Id;
    }

    public void setOrgC4Id(String orgC4Id) {
        this.orgC4Id = orgC4Id;
    }

    public String getOrgC5Id() {
        return orgC5Id;
    }

    public void setOrgC5Id(String orgC5Id) {
        this.orgC5Id = orgC5Id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustTelePhone() {
        return custTelePhone;
    }

    public void setCustTelePhone(String custTelePhone) {
        this.custTelePhone = custTelePhone;
    }

    public Date getOrderCreateDate() {
        return orderCreateDate;
    }

    public void setOrderCreateDate(Date orderCreateDate) {
        this.orderCreateDate = orderCreateDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
    }

    public String getBssStatusCd() {
        return bssStatusCd;
    }

    public void setBssStatusCd(String bssStatusCd) {
        this.bssStatusCd = bssStatusCd;
    }

    public String getBssStaffName() {
        return bssStaffName;
    }

    public void setBssStaffName(String bssStaffName) {
        this.bssStaffName = bssStaffName;
    }

    public String getBssStaffCode() {
        return bssStaffCode;
    }

    public void setBssStaffCode(String bssStaffCode) {
        this.bssStaffCode = bssStaffCode;
    }

    public String getBssStaffPhone() {
        return bssStaffPhone;
    }

    public void setBssStaffPhone(String bssStaffPhone) {
        this.bssStaffPhone = bssStaffPhone;
    }

    public Long getBssOrgId() {
        return bssOrgId;
    }

    public void setBssOrgId(Long bssOrgId) {
        this.bssOrgId = bssOrgId;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getBssOrgName() {
        return bssOrgName;
    }

    public void setBssOrgName(String bssOrgName) {
        this.bssOrgName = bssOrgName;
    }

    public String getOrgPathName() {
        return orgPathName;
    }

    public void setOrgPathName(String orgPathName) {
        this.orgPathName = orgPathName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean getCanSend() {
        return canSend;
    }

    public void setCanSend(boolean canSend) {
        this.canSend = canSend;
    }

    @Override
    public String toString() {
        return "TwBizOrderInfoVo{" +
                "custOrderNbr='" + custOrderNbr + '\'' +
                ", orderType='" + orderType + '\'' +
                ", custName='" + custName + '\'' +
                ", offerName='" + offerName + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", statusCd='" + statusCd + '\'' +
                ", appStatusCd='" + appStatusCd + '\'' +
                '}';
    }
}
