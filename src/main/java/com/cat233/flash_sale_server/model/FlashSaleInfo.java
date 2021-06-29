package com.cat233.flash_sale_server.model;

public class FlashSaleInfo {
	private Long skuId;
	private Long promotionId;
	private Integer quantity;
	private Long startTime;
	private Long endTime;
	private String key;
	private boolean isSoldOut;
	public Long getSkuId() {
		return skuId;
	}
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	public Long getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public boolean getIsSoldOut() {
		return isSoldOut;
	}
	public void setIsSoldOut(boolean isSoldOut) {
		this.isSoldOut = isSoldOut;
	}
	
}
