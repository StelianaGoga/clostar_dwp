package com.clostar.struts.actions;

import com.clostar.utils.Constants;

@SuppressWarnings("serial")
public class UserProfileAction extends SuperAction {

	public String showAccount() throws Exception {
		if(!getSessionManager().isSessionSignedIn()) {
			addActionMessage(getText("clostar.error.sessionexpired"));
			getSessionManager().putKey(Constants.TARGET, "show_account");
			return LOGIN;
		}
	    return SUCCESS;
	}
}
