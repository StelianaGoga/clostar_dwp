package com.clostar.wrappers;

import java.util.ArrayList;
import java.util.List;

import com.clostar.db.dao.OrderDataDAO;
import com.clostar.db.model.OrderData;
import com.clostar.db.model.UserOrder;

public class UserOrderWrapper {
	private UserOrder userOrder;
	private List<OrderData> orderDatas = new ArrayList<OrderData>();
	
	public UserOrderWrapper(UserOrder userOrder) {
		this.userOrder = userOrder;
		orderDatas = new OrderDataDAO().getByOrderId(userOrder);
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
	
}
