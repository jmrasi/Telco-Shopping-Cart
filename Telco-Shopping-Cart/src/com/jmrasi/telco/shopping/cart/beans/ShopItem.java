package com.jmrasi.telco.shopping.cart.beans;

import java.util.Date;

public class ShopItem {
	private String code;
	private String name;
	private Double regularPrice;
	private String promoCode;
	private Date dateCreated;
	
	public void setPromoCode(String promoCode){
		this.promoCode = promoCode;
	}
	public String getPromoCode(){
		return promoCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getRegularPrice() {
		return regularPrice;
	}
	public void setRegularPrice(Double regularPrice) {
		this.regularPrice = regularPrice;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	@Override
	public String toString() {
		return "ShopItem [code=" + code + ", name=" + name + ", regularPrice=" + regularPrice + ", promoCode="
				+ promoCode + ", dateCreated=" + dateCreated + "]";
	}
}
