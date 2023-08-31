package json;

/**
 * 下拉框页面对象
 * @author hesj@zjhcsoft.com
 */
public class AttrValueVo extends BaseVo {
	
	/**显示值*/
	private String attrValueName;
	/**真实值*/
	private String attrValue;
	/**值ID*/
	private Long attrValueId;
	
	public String getAttrValueName() {
		return attrValueName;
	}
	public void setAttrValueName(String attrValueName) {
		this.attrValueName = attrValueName;
	}
	public String getAttrValue() {
		return attrValue;
	}
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}
	public Long getAttrValueId() {
		return attrValueId;
	}
	public void setAttrValueId(Long attrValueId) {
		this.attrValueId = attrValueId;
	}

	public AttrValueVo(String attrValueName, String attrValue) {
		this.attrValueName = attrValueName;
		this.attrValue = attrValue;
	}
}
