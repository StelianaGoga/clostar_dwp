package com.clostar.struts.actions;

import java.util.List;

import com.clostar.db.dao.UserOrderDAO;
import com.clostar.db.model.UserOrder;
import com.clostar.utils.Constants;
import com.clostar.wrappers.UserOrderWrapper;

@SuppressWarnings("serial")
public class UserProfileAction extends SuperAction {
	List<UserOrderWrapper> userOrders;

	public String showAccount() throws Exception {
		if(!getSessionManager().isSessionSignedIn()) {
			addActionMessage(getText("clostar.error.sessionexpired"));
			getSessionManager().putKey(Constants.TARGET, "show_account");
			return LOGIN;
		}
	    return SUCCESS;
	}
	
	public String showOrders() throws Exception {
		if(!getSessionManager().isSessionSignedIn()) {
			addActionMessage(getText("clostar.error.sessionexpired"));
			getSessionManager().putKey(Constants.TARGET, "show_orders");
			return LOGIN;
		}
		
		List<UserOrder> orders = new UserOrderDAO().getByUser(getSessionManager().getSessionUser());
		
		for (UserOrder order : orders) {
			userOrders.add(new UserOrderWrapper(order));
		}
		
	    return SUCCESS;
	}
}
