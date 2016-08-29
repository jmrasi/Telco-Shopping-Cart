package com.jmrasi.telco.shopping.cart.tester;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jmrasi.telco.shopping.cart.beans.*;
import com.jmrasi.telco.shopping.cart.bo.ShoppingCart;
import com.jmrasi.telco.shopping.cart.services.CartService;

public class CartTesterScenario2 {
	
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
	
	public void setShoppingCar(ShoppingCart shopCart){
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
		Promo promo = new SecondPromo();
		
		//Calculation Type
		CalculationType ct = new CalculationType();
		ct.setIsItemFreebie(false);
		ct.setBulkAmount(null);
		ct.setPercentage(null);
		ct.setAmount(ULT_LARGE_DISCOUNTED_PRICE);
		
		//Add Required Items to Promo
		ShopItem item = new ShopItem();
		List<ShopItem> requiredItems = new ArrayList<ShopItem>();
		item.setCode(ULT_LARGE);
		item.setName("Unlimited 5GB");
		item.setRegularPrice(ULT_LARGE_UNIT_PRICE);
		
		//4 ULT_LARGE are required to avail the promo
		requiredItems.add(item); 
		requiredItems.add(item); 
		requiredItems.add(item); 
		requiredItems.add(item);
		
		promo.setName("Unli-5GB Price Drop");
		promo.setCalcType(ct);
		promo.setRequiredItems(requiredItems);
		promo.setCurrentItem(item);
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
		CartTesterScenario2 cartScene = new CartTesterScenario2();
		cartScene.prepare();
		
		ShopItem item = null;
		for(Promo p : cartScene.getShoppingCart().getPromoList()){
			item = p.getCurrentItem();
			break;
		}
		//Add 3 items
		cartScene.addItem(item);
		cartScene.addItem(item);
		cartScene.addItem(item);
		cartScene.addItem(item);
		
		item = new ShopItem();
		item.setCode(ULT_SMALL);
		item.setName("Unlimited 1GB");
		item.setRegularPrice(ULT_SMALL_UNIT_PRICE);
		cartScene.addItem(item);
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
