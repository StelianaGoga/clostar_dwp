package com.clostar.struts.actions;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.clostar.db.model.Shopaholic;
import com.clostar.session.HibernateUtil;
import com.clostar.utils.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SignAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	
	public String signUp() throws Exception {
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

	@SuppressWarnings("unchecked")
	public String signIn() throws Exception {
		/*TODO - generalize */
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria crit = session.createCriteria(Shopaholic.class);
        crit.add(Restrictions.eq("email", this.getEmail()));
        List<Shopaholic> list = crit.list();
        if (list == null || list.isEmpty() || list.size() > 1) {
        	System.out.println("list response null");
        	session.close();
        	return ERROR;
        }
        if (list.get(0).getUserPassword().equals(this.getPassword())) {
        	System.out.println("Shopaholic found");
        	initSession(list.get(0));
        	session.close();
        	return SUCCESS;
        }
        System.out.println("wrong password");
    	session.close();
	    return ERROR;
	}
	
    private void initSession(Shopaholic user) {
    	ActionContext.getContext().getSession().put(Constants.USER_ID, user.getId());
    }
	
	@SuppressWarnings("unchecked")
	public String signOut() throws Exception {
    	ActionContext.getContext().getSession().remove(Constants.USER_ID);
    	return SUCCESS;
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
