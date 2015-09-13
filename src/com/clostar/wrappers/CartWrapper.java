package com.clostar.wrappers;

import com.clostar.utils.Constants;

public class CartWrapper {
	private Integer productId;
	private String name;
	private String measure;
	private Integer quantity;
	private Float price;
	private Float euroPrice;
	private Integer currency;
	
	public CartWrapper(Integer productId, String name, String measure, Integer qunatity, Float price, Integer currency){
		setProductId(productId);
		setName(name);
		setMeasure(measure);
		setQuantity(qunatity);
		setPrice(price * quantity);
		setCurrency(currency);
		computeEuroPrice();
	}
	
	private void computeEuroPrice() {
		if (Constants.Currency.EURO.intValue() == getCurrency()) {
			double val = price * Constants.Rates.EUR.doubleValue();
			Integer i = (int) (val * 10000 / 100);
			setEuroPrice((float)((i + 0.0) / 100));
		}
		else if (Constants.Currency.GBP.intValue() == getCurrency()) {
			double val = price * Constants.Rates.GBP.doubleValue();
			Integer i = (int) (val * 10000 / 100);
			setEuroPrice((float)((i + 0.0) / 100));
		}
		else if (Constants.Currency.RON.intValue() == getCurrency()) {
			double val = price * Constants.Rates.RON.doubleValue();
			Integer i = (int) (val * 10000 / 100);
			setEuroPrice((float)((i + 0.0) / 100));
		}
		else if (Constants.Currency.USD.intValue() == getCurrency()) {
			double val = price * Constants.Rates.USD.doubleValue();
			Integer i = (int) (val * 10000 / 100);
			setEuroPrice((float)((i + 0.0) / 100));
		}
	}

	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getEuroPrice() {
		return euroPrice;
	}

	public void setEuroPrice(Float euroPrice) {
		this.euroPrice = euroPrice;
	}

	public Integer getCurrency() {
		return currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
	}
	
}
