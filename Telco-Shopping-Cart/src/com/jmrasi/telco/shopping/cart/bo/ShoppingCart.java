package com.jmrasi.telco.shopping.cart.bo;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.HashMap;

import com.jmrasi.telco.shopping.cart.beans.CalculationType;
import com.jmrasi.telco.shopping.cart.beans.CheckoutItem;
import com.jmrasi.telco.shopping.cart.beans.Promo;
import com.jmrasi.telco.shopping.cart.beans.ShopItem;
import com.jmrasi.telco.shopping.cart.services.CartService;
import com.jmrasi.telco.shopping.cart.services.Promotable;

public class ShoppingCart implements CartService, Promotable {

	private List<ShopItem> cart;
	private List<CheckoutItem> chkoutCart;
	private Set<Promo> promos;
	private Set<ShopItem> fnlCart;
	private Double amountDue;

	public List<CheckoutItem> getChkoutCart(){
		return chkoutCart;
	}
	public ShoppingCart(){
		super();
		prepareCart();
	}
	public void setPromos(Set<Promo> promos){
		this.promos = promos;
	}
	public void setAmountDue(Double amountDue){
		this.amountDue = amountDue;
	}
	public Double getAmountDue(){
		return amountDue;
	}
	@Override
	public void add(ShopItem item) {
		if(cart == null) 
			prepareCart();
		else{
			cart.add(item);
			fnlCart.add(item);			
		}
	}

	@Override
	public void add(ShopItem item, String promoCode) {
		item.setPromoCode(promoCode);
		if(cart == null) 
			prepareCart();
		else{
			cart.add(item);
			fnlCart.add(item);			
		}
	}

	@Override
	public void checkOut() {
		for(ShopItem sitem: fnlCart){
			
			int qty = Collections.frequency(cart, sitem);
			CheckoutItem chkout = CheckoutItem.copyParentValues(sitem);
			chkout.setQty(qty);
			chkoutCart.add(chkout);
		}
		for(Promo p: promos){
			p.availment(cart);
			if(!p.isAvailed()){
				promos.remove(p);
			}
		}
	}
	@Override
	public Set<Promo> getPromoList() {
		return promos;
	}
	@Override
	public void performCalculation() {
		//no promo has been availed. Applying regular prices
		if(promos.isEmpty()){
			
			amountDue = new Double(0);
			for(CheckoutItem itm: chkoutCart){
				int index = chkoutCart.indexOf(itm);
				itm.setChkoutPrice(Math.round((itm.getRegularPrice() * itm.getQty()) *100.0) /100.0);
				chkoutCart.set(index, itm);
				amountDue+=itm.getChkoutPrice();
			}
		}else{//at least 1 promo has been availed. Applying promo rules
			
			for(Promo p: promos){
				chkoutCart = p.applyPromo(chkoutCart);
			}
			for(CheckoutItem cop: chkoutCart){
				if(cop.getChkoutPrice() == null){
					int index = chkoutCart.indexOf(cop);
					cop.setChkoutPrice(Math.round((cop.getRegularPrice() * cop.getQty()) * 100.0) / 100.0 );
					chkoutCart.set(index, cop);
				}
			}
			amountDue = new Double(0);
			for(CheckoutItem chkItm : chkoutCart){
				amountDue+=chkItm.getChkoutPrice();
			}
			amountDue = Math.round(amountDue * 100.0) / 100.0;
		}
	}
	
	private void prepareCart(){
		cart = new ArrayList<ShopItem>();
		chkoutCart = new ArrayList<CheckoutItem>();
		fnlCart = new HashSet<ShopItem>();
	}
}
