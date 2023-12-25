package zzzde.project.technic.projmybatis.persistent.entity;

import java.util.Date;

public class ProductDo {
	private static final long serialVersionUID = 1L;
	
	private String productId;
	private String productName;
	private String productType;
	private String salePrice;
	private String purchasePrice;
	private Date registDate;
	private Long da;

	public String getProductId() {
 		return this.productId;
	}
	
	public void setProductId(String productId) {
 		this.productId = productId;
	}

	public String getProductName() {
 		return this.productName;
	}
	
	public void setProductName(String productName) {
 		this.productName = productName;
	}

	public String getProductType() {
 		return this.productType;
	}
	
	public void setProductType(String productType) {
 		this.productType = productType;
	}

	public String getSalePrice() {
 		return this.salePrice;
	}
	
	public void setSalePrice(String salePrice) {
 		this.salePrice = salePrice;
	}

	public String getPurchasePrice() {
 		return this.purchasePrice;
	}
	
	public void setPurchasePrice(String purchasePrice) {
 		this.purchasePrice = purchasePrice;
	}

	public Date getRegistDate() {
 		return this.registDate;
	}
	
	public void setRegistDate(Date registDate) {
 		this.registDate = registDate;
	}

	public Long getDa() {
 		return this.da;
	}
	
	public void setDa(Long da) {
 		this.da = da;
	}
}
