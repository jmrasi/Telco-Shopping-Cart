package com.jmrasi.telco.shopping.cart.beans;


public class CalculationType {
	private Double percentage;
	private Double amount;
	private Double bulkAmount;
	private boolean isItemFreebie;
	
	public Double getBulkAmount(){
		return bulkAmount;
	}
	public void setBulkAmount(Double bulkAmount){
		this.bulkAmount = bulkAmount;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public void setIsItemFreebie(boolean isItemFreebie){
		this.isItemFreebie = isItemFreebie;
	}
	public boolean getIsItemFreebie(){
		return isItemFreebie;
	}
	@Override
	public String toString() {
		return "CalculationType [percentage=" + percentage + ", amount=" + amount + ", isItemFreebie=" + isItemFreebie + "]";
	}
}
