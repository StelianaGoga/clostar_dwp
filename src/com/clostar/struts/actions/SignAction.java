package com.clostar.struts.actions;

import com.clostar.db.dao.ShopaholicDAO;
import com.clostar.db.model.Shopaholic;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class SignAction extends SuperAction implements ModelDriven<Shopaholic>{

	private Shopaholic shopaholic = new Shopaholic();
	
	public String signUp() throws Exception {
		
		if (shopaholic.getEmail() == null || shopaholic.getEmail().equals("")) {
			addFieldError("email", getText("clostar.error.notnullable"));
		}
		if (shopaholic.getFirstName() == null || shopaholic.getFirstName().equals("")) {
			addFieldError("firstName", getText("clostar.error.notnullable"));
		}
		if (shopaholic.getLastName() == null || shopaholic.getLastName().equals("")) {
			addFieldError("lastName", getText("clostar.error.notnullable"));
		}
		if (shopaholic.getPassword() == null || shopaholic.getPassword().equals("")) {
			addFieldError("password", getText("clostar.error.notnullable"));
		}
		
		if (hasErrors()) {
			return INPUT;
		}

		boolean isExistent = new ShopaholicDAO().isUserExistent(shopaholic.getEmail());
		if (isExistent) {
			addActionError(getText("clostar.error.signup.userexistent"));
			return INPUT;
		}
		
		new ShopaholicDAO().saveOrUpdate(shopaholic, false);
		
//		SendEmail se = new SendEmail("steliana.goga@gmail.com");
//		se.sendTextMail("Subject - clostar", "You have been signed up!");
	    return SUCCESS;
	}

	public String signIn() throws Exception {

		if (shopaholic.getEmail() == null || shopaholic.getEmail().equals("")) {
			addFieldError("email", getText("clostar.error.notnullable"));
		}
		if (shopaholic.getPassword() == null || shopaholic.getPassword().equals("")) {
			addFieldError("password", getText("clostar.error.notnullable"));
		}
		
		if (hasErrors()) {
			return INPUT;
		}

		boolean isExistent = new ShopaholicDAO().isUserExistent(shopaholic.getEmail(), shopaholic.getPassword());
		if (!isExistent) {
			addActionError(getText("clostar.error.signin.userinexistent"));
			return INPUT;
		}
		shopaholic = new ShopaholicDAO().getUser(shopaholic.getEmail());
		if (shopaholic == null) {
			return ERROR;
		}
		new ShopaholicDAO().saveOrUpdate(shopaholic, true);
		getSessionManager().initSession(shopaholic);
		checkTargetAction();
	    return SUCCESS;
	}

	public String signOut() throws Exception {
		//if(!isSessionSignedIn()) return LOGIN;
		getSessionManager().removeSession();
    	return SUCCESS;
	}
    
	@Override
	public Shopaholic getModel() {
		return shopaholic;
	}
}
