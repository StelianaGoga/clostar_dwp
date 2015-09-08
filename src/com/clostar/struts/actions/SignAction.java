package com.clostar.struts.actions;

import com.clostar.db.dao.UserDAO;
import com.clostar.db.model.User;
import com.clostar.db.utils.Constants;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class SignAction extends SuperAction implements ModelDriven<User>{

	private User user = new User();
	
	public String signUp() throws Exception {
		
		if (user.getEmail() == null || user.getEmail().equals("")) {
			addFieldError("email", getText("clostar.error.notnullable"));
		}
		if (user.getFirstName() == null || user.getFirstName().equals("")) {
			addFieldError("firstName", getText("clostar.error.notnullable"));
		}
		if (user.getLastName() == null || user.getLastName().equals("")) {
			addFieldError("lastName", getText("clostar.error.notnullable"));
		}
		if (user.getPassword() == null || user.getPassword().equals("")) {
			addFieldError("password", getText("clostar.error.notnullable"));
		}
		
		if (hasErrors()) {
			return INPUT;
		}

		boolean isExistent = new UserDAO().isUserExistent(user.getEmail());
		if (isExistent) {
			addActionError(getText("clostar.error.signup.userexistent"));
			return INPUT;
		}

		user.setActiveInd(Constants.ACTIVE_IND);
		user.setEConfirm(Constants.INACTIVE_IND);
		new UserDAO().saveOrUpdate(user, false);
		
//		SendEmail se = new SendEmail("steliana.goga@gmail.com");
//		se.sendTextMail("Subject - clostar", "You have been signed up!");
	    return SUCCESS;
	}

	public String signIn() throws Exception {

		if (user.getEmail() == null || user.getEmail().equals("")) {
			addFieldError("email", getText("clostar.error.notnullable"));
		}
		if (user.getPassword() == null || user.getPassword().equals("")) {
			addFieldError("password", getText("clostar.error.notnullable"));
		}
		
		if (hasErrors()) {
			return INPUT;
		}

		boolean isExistent = new UserDAO().isUserExistent(user.getEmail(), user.getPassword());
		if (!isExistent) {
			addActionError(getText("clostar.error.signin.userinexistent"));
			return INPUT;
		}
		user = new UserDAO().getByEmail(user.getEmail());
		if (user == null) {
			return ERROR;
		}
		new UserDAO().saveOrUpdate(user, true);
		getSessionManager().initSession(user);
		checkTargetAction();
	    return SUCCESS;
	}

	public String signOut() throws Exception {
		//if(!isSessionSignedIn()) return LOGIN;
		getSessionManager().removeSession();
    	return SUCCESS;
	}
    
	@Override
	public User getModel() {
		return user;
	}
}
