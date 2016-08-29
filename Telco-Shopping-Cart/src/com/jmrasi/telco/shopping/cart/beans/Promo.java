package com.jmrasi.telco.shopping.cart.beans;

import java.util.List;
import java.util.Set;

public abstract class Promo {
	private String name;
	private CalculationType calcType;
	private List<ShopItem> requiredItems;
	private Set<ShopItem> perks;
	private EffectivityPeriod periodEffective;
	private Boolean availed;
	private Boolean applyToAll;
	private ShopItem currentItem;
	
	public abstract void availment(List<ShopItem> cart);
	public abstract List<CheckoutItem> applyPromo(List<CheckoutItem> cart);
	
	public void setApplyToAll(Boolean applyToAll){
		this.applyToAll = applyToAll;
	}
	public Boolean isAppliedToAll(){
		return applyToAll;
	}
	public void setCurrentItem(ShopItem item){
		currentItem = item;
	}
	public ShopItem getCurrentItem(){
		return currentItem;
	}
	public void setAvailed(Boolean availed){
		this.availed = availed;
	}
	public Boolean isAvailed(){
		return availed;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CalculationType getCalcType() {
		return calcType;
	}
	public void setCalcType(CalculationType calcType) {
		this.calcType = calcType;
	}
	public Set<ShopItem> getPerks() {
		return perks;
	}
	public void setPerks(Set<ShopItem> perks) {
		this.perks = perks;
	}
	public EffectivityPeriod getPeriodEffective() {
		return periodEffective;
	}
	public void setPeriodEffective(EffectivityPeriod periodEffective) {
		this.periodEffective = periodEffective;
	}
	public void setRequiredItems(List<ShopItem> requiredItems){
		this.requiredItems = requiredItems;
	}
	public List<ShopItem> getRequiredItems(){
		return requiredItems;
	}
	@Override
	public String toString() {
		return "Promo [name=" + name + ", calcType=" + calcType + ", perks=" + perks + ", periodEffective="
				+ periodEffective + "]";
	}
}
