package com.clostar.struts.actions;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.clostar.db.model.Prodohol;
import com.clostar.session.HibernateUtil;
import com.clostar.utils.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ProdoholAction extends ActionSupport 
	implements ModelDriven{

	/**
	 * 
	 */
	private Prodohol prodohol = new Prodohol();
	
	public String addProdohol() throws Exception {
		System.out.println(prodohol);

//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//        session.saveOrUpdate(shopaholic);
//        session.getTransaction().commit();
	    return "success";
	}

	@Override
	public Object getModel() {
		return prodohol;
	}
}
