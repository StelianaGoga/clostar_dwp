package com.clostar.struts.actions;

import com.clostar.business.SessionManager;
import com.clostar.utils.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SuperAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	private String targetAction = "home";

	public String home() throws Exception {
		return SUCCESS;
	}
	
	public SessionManager getSessionManager() {
		return new SessionManager(ActionContext.getContext());
	}
	
	public String getTargetAction() {
		return targetAction;
	}

	public void setTargetAction(String targetAction) {
		this.targetAction = targetAction;
	}

	public void checkTargetAction() {
		if (getSessionManager().hasKey(Constants.TARGET)) {
			setTargetAction((String)getSessionManager().getValueKey(Constants.TARGET));
		}
		else {
			setTargetAction("home");
		}
		getSessionManager().removeKey(Constants.TARGET);
	}
	
}
