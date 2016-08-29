package com.jmrasi.telco.shopping.cart.beans;
/* -------------------------------------------------------------------------------------------------------------
 * CheckoutItem
 * - extension of ShopItem. This is where we apply how many quantities and what will be price if ever a promo has been availed
 * 	 Value of chkoutPrice often change which will invalidate locating them inside a set or list
 *   
 * -------------------------------------------------------------------------------------------------------------
 * */
public class CheckoutItem extends ShopItem{
	private Integer qty;
	private Double chkoutPrice;
	
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public Double getChkoutPrice(){
		return chkoutPrice;
	}
	public void setChkoutPrice(Double chkoutPrice){
		this.chkoutPrice = chkoutPrice;
	}
	public static CheckoutItem copyParentValues(ShopItem parent){
		CheckoutItem item = new CheckoutItem();
		item.setCode(parent.getCode());
		item.setName(parent.getName());
		item.setDateCreated(parent.getDateCreated());
		item.setPromoCode(parent.getPromoCode());
		item.setRegularPrice(parent.getRegularPrice());
		return item;
	}
	@Override
	public String toString() {
		return "CheckoutItem [qty=" + qty + ", chkoutPrice=" + chkoutPrice + ", getPromoCode()=" + getPromoCode()
				+ ", getCode()=" + getCode() + ", getName()=" + getName() + ", getRegularPrice()=" + getRegularPrice()
				+ ", getDateCreated()=" + getDateCreated() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}
}
