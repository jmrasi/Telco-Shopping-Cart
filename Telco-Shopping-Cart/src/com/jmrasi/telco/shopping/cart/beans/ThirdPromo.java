package com.jmrasi.telco.shopping.cart.beans;

import java.util.Collections;
import java.util.List;

public class ThirdPromo extends Promo {

	private Integer freeItemCount;
	public static double FREE_AMOUNT = 0.0;
	
	@Override
	public void availment(List<ShopItem> cart) {
		
		List<ShopItem> rqmts = this.getRequiredItems();
		int reqCount = Collections.frequency(rqmts, this.getCurrentItem());
		int itmCount = Collections.frequency(cart, this.getCurrentItem());
		
		if(itmCount >= reqCount){
			freeItemCount = itmCount;
			this.setAvailed(true);
		}else{
			this.setAvailed(false);
		}
	}

	@Override
	public List<CheckoutItem> applyPromo(List<CheckoutItem> cart) {
		for(ShopItem perk:this.getPerks()){
			CheckoutItem item = CheckoutItem.copyParentValues(perk);
			item.setQty(freeItemCount);
			item.setChkoutPrice(FREE_AMOUNT);
			cart.add(item);
		}
		return cart;
	}

}
