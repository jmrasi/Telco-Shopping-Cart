package com.jmrasi.telco.shopping.cart.beans;
/* -------------------------------------------------------------------------------------------------------------
 * CalculationType
 * - responsible for the behavior calculations inside every instance of Promo
 * 
 * if PERCENTAGE is not null, 
 * 		PERCENTAGE will be multiplied to (qty * unitprice). Intended for percentage discounts.
 * 
 * if AMOUNT is not null,
 * 		unitPrice for a specific ShopItem will be ignored. 
 * 		AMOUNT will replace unitPrice and be multiplied to qty. Intended for price drops if requirements are fulfilled.
 * 
 * if BULKAMOUNT is not null,
 * 		computed Check-out Price (qty * unitprice) for a specific ShopItem will be ignored. 
 * 		BULKAMOUNT is instead applied to chkoutAmount. Intended for bundles
 * 
 * if ISITEMFREEBIE is TRUE,
 * 		No computations will be made. Instead, Perk Items will be added to shopping cart, with 0 checkoutPrice.
 *  
 * -------------------------------------------------------------------------------------------------------------
 * */

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
