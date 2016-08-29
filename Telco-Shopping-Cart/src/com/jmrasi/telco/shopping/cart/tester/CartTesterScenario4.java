package com.jmrasi.telco.shopping.cart.tester;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jmrasi.telco.shopping.cart.beans.CalculationType;
import com.jmrasi.telco.shopping.cart.beans.CheckoutItem;
import com.jmrasi.telco.shopping.cart.beans.CodePromo;
import com.jmrasi.telco.shopping.cart.beans.Promo;
import com.jmrasi.telco.shopping.cart.beans.ShopItem;
import com.jmrasi.telco.shopping.cart.bo.ShoppingCart;

public class CartTesterScenario4 {
	
	public static String ULT_SMALL = "ult_small";
	public static String ULT_MEDIUM = "ult_medium";
	public static String ULT_LARGE = "ult_large";
	public static String ULT_1GB = "1gb";
	
	public static double ULT_SMALL_UNIT_PRICE = 24.9;
	public static double ULT_SMALL_DOUBLE_PRICE = 49.8;
	public static double ULT_MEDIUM_UNIT_PRICE = 29.90;
	public static double ULT_LARGE_DISCOUNTED_PRICE = 39.90;
	public static double ULT_LARGE_UNIT_PRICE = 44.90;
	public static double ULT_1GB_UNIT_PRICE = 9.90;
	
	private ShoppingCart shopCart;
	
	public void setShoppingCart(ShoppingCart shopCart){
		this.shopCart = shopCart;
	}
	public ShoppingCart getShoppingCart(){
		return shopCart;
	}
	public void prepare(){
		shopCart = new ShoppingCart();
		createPromos();
	}
	private void createPromos(){
		Set<Promo> promos = new HashSet<Promo>();
		Promo promo = new CodePromo();
		
		//Calculation Type
		CalculationType ct = new CalculationType();
		ct.setIsItemFreebie(false);
		ct.setBulkAmount(null);
		ct.setPercentage(0.10); //10%
		ct.setAmount(null); 
		
		promo.setName("Code Promo (10% discount)");
		promo.setCalcType(ct);
		promos.add(promo);
		shopCart.setPromos(promos);
	}
	public void addItem(ShopItem item){
		shopCart.add(item);
	}
	public void checkOut(){
		shopCart.checkOut();
	}
	public static void main(String[] args) {
		CartTesterScenario4 cartScene = new CartTesterScenario4();
		cartScene.prepare();
		
		ShopItem item = new ShopItem();
		item.setCode(ULT_SMALL);
		item.setName("Unlimited 1GB");
		item.setRegularPrice(ULT_SMALL_UNIT_PRICE);
		cartScene.addItem(item);
		
		item = new ShopItem();
		item.setCode(ULT_1GB);
		item.setName("1GB");
		item.setRegularPrice(ULT_1GB_UNIT_PRICE);
		item.setPromoCode(CodePromo.VALID_PROMO_CODE);
		cartScene.addItem(item);
		cartScene.checkOut();
		
		ShoppingCart scart = cartScene.getShoppingCart();
		List<CheckoutItem> outItems = scart.getChkoutCart();
		
		//Displaying checkoutItems
		System.out.println("Shopping Cart Items:");
		System.out.println("----------------------------------------------");
		for(CheckoutItem itm: outItems){
			System.out.println("(" +itm.getCode() + ") - " 
					+ itm.getName() 
					+ " : Unit Price : " 
					+ itm.getRegularPrice() 
					+ " : QTY : "
					+ itm.getQty()
					+ " : " + Math.round((itm.getRegularPrice() * itm.getQty()) *100.0) /100.0);
		}
		
		scart.performCalculation();
		
		//get the items again, to display discounted amount from promos availed, if any
		outItems = scart.getChkoutCart();
		//Displaying checkoutItems
		System.out.println("Check Out Items:");
		System.out.println("----------------------------------------------");
		for(CheckoutItem itm: outItems){
			System.out.println("(" +itm.getCode() + ") - " 
					+ itm.getName() 
					+ " : Unit Price : " 
					+ itm.getRegularPrice() 
					+ " : QTY : "
					+ itm.getQty()
					+ " : " + Math.round((itm.getRegularPrice() * itm.getQty()) *100.0) /100.0
					+ " Checkout Price: " + itm.getChkoutPrice());
		}
		System.out.println("----------------------------------------------");
		System.out.println("Amount Due : " + scart.getAmountDue());
		System.out.println("----------------------------------------------");
	}
}
