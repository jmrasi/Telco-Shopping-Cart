package com.jmrasi.telco.shopping.cart.beans;

import java.util.ArrayList;
import java.util.List;

public class CodePromo extends Promo {
	public static String VALID_PROMO_CODE = "I<3AMAYSIM";
	
	@Override
	public void availment(List<ShopItem> cart) {
		for(ShopItem item:cart){
			if(item.getPromoCode() != null){
				if(item.getPromoCode().equals(VALID_PROMO_CODE)){
					this.setAvailed(true);
					this.setApplyToAll(true);
				}
			}
		}
	}

	@Override
	public List<CheckoutItem> applyPromo(List<CheckoutItem> cart) {
		List<CheckoutItem> newCart = new ArrayList<CheckoutItem>(cart.size());
		for(CheckoutItem item: cart){
			if(item.getChkoutPrice() != null){
				item.setChkoutPrice(Math.round((100.0 * (item.getChkoutPrice() * this.getCalcType().getPercentage()))) /100.0 );
			}else{
				Double discount = (item.getRegularPrice() * item.getQty()) * this.getCalcType().getPercentage();
				Double amount = (item.getRegularPrice() * item.getQty()) - discount;
				item.setChkoutPrice(Math.round(amount * 100.0) / 100.0);
			}
			newCart.add(item);
		}
		return newCart;
	}

}
