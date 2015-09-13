package com.clostar.wrappers;

import java.util.ArrayList;
import java.util.List;

import com.clostar.db.dao.OrderDataDAO;
import com.clostar.db.dao.ProductDAO;
import com.clostar.db.model.OrderData;
import com.clostar.db.model.UserOrder;

public class UserOrderWrapper {
	private UserOrder userOrder;
	private List<OrderData> orderDatas = new ArrayList<OrderData>();
	private List<String> prodName = new ArrayList<String>();
	
	public UserOrderWrapper(UserOrder userOrder) {
		this.userOrder = userOrder;
		orderDatas = new OrderDataDAO().getByOrderId(userOrder);
		for (OrderData od : orderDatas) {
			prodName.add(new ProductDAO().getById(od.getProdId()).getName());
		}
	}

	public UserOrder getUserOrder() {
		return userOrder;
	}

	public void setUserOrder(UserOrder userOrder) {
		this.userOrder = userOrder;
	}

	public List<OrderData> getOrderDatas() {
		return orderDatas;
	}

	public void setOrderDatas(List<OrderData> orderDatas) {
		this.orderDatas = orderDatas;
	}

	public List<String> getProdName() {
		return prodName;
	}

	public void setProdName(List<String> prodName) {
		this.prodName = prodName;
	}
	
}
