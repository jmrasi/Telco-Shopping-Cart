package com.jmrasi.telco.shopping.cart.services;

import java.util.Set;

import com.jmrasi.telco.shopping.cart.beans.Promo;

public interface Promotable{
	public Set<Promo> getPromoList();
	public void performCalculation();
}
