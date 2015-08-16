package com.clostar.struts.forms;

public class LoginForm {
	//private final static Logger    logger               = LoggerFactory.getLogger(FaultsHttpContext.class);
    private String username = null;

    public LoginForm() {
        super();
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
