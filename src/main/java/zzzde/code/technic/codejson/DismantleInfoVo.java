package zzzde.code.technic.codejson;

import java.util.List;

/**
 *
 * @Description:
 * @author: zsy
 */
public class DismantleInfoVo extends BaseVo {
    /**
     * 客户订单uuid
     * 同primaryKey
     */
    private String custOrderUUID;
    /**
     * 拆机原因实体
     */
    private List<AttrValueVo> dismantleResonList;
    /**
     * 终端回收实体
     */
    private List<AttrValueVo> dismantleTerminalList;
    //拆机原因
    private String dismantleResonValue;
    
    //终端回收选项
    private String dismantleTerminalValue;

    //终端不回收原因
    private String dismantleMaterialValue;

    //终端回收串号
    private String dismantleSerialNum;
    
    private String snNum;//管道编号
    
    private String otherAsset;//管道其他资产
    private String refundMethod;
    private String refundName;
    private String refundAcctId;
    
	public String getRefundMethod() {
		return refundMethod;
	}

	public void setRefundMethod(String refundMethod) {
		this.refundMethod = refundMethod;
	}

	public String getRefundName() {
		return refundName;
	}

	public void setRefundName(String refundName) {
		this.refundName = refundName;
	}

	public String getRefundAcctId() {
		return refundAcctId;
	}

	public void setRefundAcctId(String refundAcctId) {
		this.refundAcctId = refundAcctId;
	}

	public String getCustOrderUUID() {
		return custOrderUUID;
	}

	public void setCustOrderUUID(String custOrderUUID) {
		this.custOrderUUID = custOrderUUID;
	}

	public List<AttrValueVo> getDismantleResonList() {
		return dismantleResonList;
	}

	public void setDismantleResonList(List<AttrValueVo> dismantleResonList) {
		this.dismantleResonList = dismantleResonList;
	}

	public List<AttrValueVo> getDismantleTerminalList() {
		return dismantleTerminalList;
	}

	public void setDismantleTerminalList(List<AttrValueVo> dismantleTerminalList) {
		this.dismantleTerminalList = dismantleTerminalList;
	}

	public String getDismantleResonValue() {
		return dismantleResonValue;
	}

	public void setDismantleResonValue(String dismantleResonValue) {
		this.dismantleResonValue = dismantleResonValue;
	}

	public String getDismantleTerminalValue() {
		return dismantleTerminalValue;
	}

	public void setDismantleTerminalValue(String dismantleTerminalValue) {
		this.dismantleTerminalValue = dismantleTerminalValue;
	}

	public String getDismantleMaterialValue() {
		return dismantleMaterialValue;
	}

	public void setDismantleMaterialValue(String dismantleMaterialValue) {
		this.dismantleMaterialValue = dismantleMaterialValue;
	}

	public String getSnNum() {
		return snNum;
	}

	public void setSnNum(String snNum) {
		this.snNum = snNum;
	}

	public String getOtherAsset() {
		return otherAsset;
	}

	public void setOtherAsset(String otherAsset) {
		this.otherAsset = otherAsset;
	}

	public String getDismantleSerialNum() {
		return dismantleSerialNum;
	}

	public void setDismantleSerialNum(String dismantleSerialNum) {
		this.dismantleSerialNum = dismantleSerialNum;
	}

	@Override
	public String toString() {
		return "DismantleInfoVo{" +
				"custOrderUUID='" + custOrderUUID + '\'' +
				", dismantleResonList=" + dismantleResonList +
				", dismantleTerminalList=" + dismantleTerminalList +
				", dismantleResonValue='" + dismantleResonValue + '\'' +
				", dismantleTerminalValue='" + dismantleTerminalValue + '\'' +
				", dismantleMaterialValue='" + dismantleMaterialValue + '\'' +
				", dismantleSerialNum='" + dismantleSerialNum + '\'' +
				", snNum='" + snNum + '\'' +
				", otherAsset='" + otherAsset + '\'' +
				", refundMethod='" + refundMethod + '\'' +
				", refundName='" + refundName + '\'' +
				", refundAcctId='" + refundAcctId + '\'' +
				'}';
	}
}