package com.clostar.business;

import java.util.Date;

import com.clostar.db.dao.FavoritesDAO;
import com.clostar.db.model.User;
import com.clostar.utils.Constants;
import com.opensymphony.xwork2.ActionContext;

public class SessionManager {
	
	private ActionContext context;
	
	public SessionManager(ActionContext context) {
		this.setContext(context);
	}

	public boolean hasKey(String key) {
		return context.getSession() != null &&
				context.getSession().containsKey(key);
	}
	
	public Object getValueKey(String key) {
		if (!hasKey(key))
			return null;
		return context.getSession().get(key);
	}
	
	public boolean isSessionSignedIn() {
    	return getValueKey(Constants.USER_ID) != null;
    }
	
	public Integer getSessionId() {
		return (Integer) getValueKey(Constants.USER_ID);
    }
	
	public User getSessionUser() {
		return (User) getValueKey(Constants.USER);
    }

	public void initSession(User user) {
    	putKey(Constants.USER_ID, user.getId());
    	putKey(Constants.USER, user);
    	putKey(Constants.TIME, new Date().getTime());
    	putKey(Constants.FAVS_SIZE, new FavoritesDAO().getCountByUser(user));
    }
	
	public void removeSession() {
		removeKey(Constants.USER_ID);
		removeKey(Constants.USER);
		removeKey(Constants.CART);
		removeKey(Constants.CART_SIZE);
		removeKey(Constants.FAVS_SIZE);
	}

	public ActionContext getContext() {
		return context;
	}

	public void setContext(ActionContext context) {
		this.context = context;
	}

	public void removeKey(String key) {
		if (context.getSession().containsKey(key))
			context.getSession().remove(key);
	}

	public void putKey(String key, Object value) {
		context.getSession().put(key, value);
	}

}
