package com.clostar.struts.actions;

import java.util.Locale;

import org.hibernate.Session;

import com.clostar.db.model.Shopaholic;
import com.clostar.session.HibernateUtil;
import com.clostar.utils.ResourceManager;
import com.opensymphony.xwork2.ActionSupport;

public class SignUpAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	
	public String perform() throws Exception {
		Shopaholic shopaholic = new Shopaholic();
		shopaholic.setFirstName(this.getFirstname());
		shopaholic.setLastName(this.getLastname());
		shopaholic.setEmail(this.getEmail());
		shopaholic.setUserPassword(this.getPassword());

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(shopaholic);
        session.getTransaction().commit();
		
		//HibernateUtil<Shopaholic> ShopaholicDao = new HibernateUtil<Shopaholic>();
		//ShopaholicDao.attachDirty(Shopaholic);
//		SendEmail se = new SendEmail("steliana.goga@gmail.com");
//		se.sendTextMail("Subject - clostar", "You have been signed up!");
	    return "success";
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
