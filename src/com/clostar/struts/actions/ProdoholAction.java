package com.clostar.struts.actions;

import org.hibernate.Session;

import com.clostar.db.model.Prodohol;
import com.clostar.db.model.Shopaholic;
import com.clostar.session.HibernateUtil;
import com.clostar.utils.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class ProdoholAction extends SuperAction 
	implements ModelDriven<Prodohol>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Prodohol prodohol = new Prodohol();
	
	public String addProdohol() throws Exception {
		//if(!isSessionSignedIn()) return LOGIN;
		System.out.println(prodohol);
		Shopaholic shopaholic = new Shopaholic();
		Integer id = (Integer) ActionContext.getContext().getSession().get(Constants.USER_ID);
		if (id == null) id = 4;
		shopaholic.setId((Integer) ActionContext.getContext().getSession().get(Constants.USER_ID));
		prodohol.setShopaholic(shopaholic);
		
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(prodohol);
        session.getTransaction().commit();
	    return "success";
	}

	@Override
	public Prodohol getModel() {
		return prodohol;
	}
}
