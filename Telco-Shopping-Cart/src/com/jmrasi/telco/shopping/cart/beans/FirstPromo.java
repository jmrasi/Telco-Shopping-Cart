package com.jmrasi.telco.shopping.cart.beans;

import java.util.Collections;
import java.util.List;

public class FirstPromo extends Promo {

	@Override
	public void availment(List<ShopItem> cart) {
		
		List<ShopItem> rqmts = this.getRequiredItems();
		int reqCount = Collections.frequency(rqmts, this.getCurrentItem());
		int itmCount = Collections.frequency(cart, this.getCurrentItem());
		
		if(itmCount >= reqCount){
			this.setAvailed(true);
		}else{
			this.setAvailed(false);
		}

	}
	@Override
	public List<CheckoutItem> applyPromo(List<CheckoutItem> cart){
			
		ShopItem item = this.getCurrentItem();
		CheckoutItem currentItem = null;
		CheckoutItem updated = null;
		for(CheckoutItem ch: cart){
			if(item.getCode().equals(ch.getCode())){
				
				currentItem = ch;
				CalculationType ct = this.getCalcType();
				if(ct.getBulkAmount()!= null){
					ch.setChkoutPrice(ct.getBulkAmount());
				}
				updated = ch;
				break;
			}
		}
		int index = cart.indexOf(currentItem);
		cart.set(index, updated);
		return cart;
	}
}
