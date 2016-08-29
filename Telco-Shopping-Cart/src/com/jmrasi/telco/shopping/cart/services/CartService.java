package com.jmrasi.telco.shopping.cart.services;

import com.jmrasi.telco.shopping.cart.beans.ShopItem;

public interface CartService {
	public void add(ShopItem item);
	public void add(ShopItem item, String promoCode);
	public void checkOut();
}
