package com.clostar.struts.actions;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.clostar.business.FileManager;
import com.clostar.db.dao.FavoritesDAO;
import com.clostar.db.dao.ProductDAO;
import com.clostar.db.model.Favorites;
import com.clostar.db.model.Product;
import com.clostar.db.model.User;
import com.clostar.utils.Constants;
import com.clostar.wrappers.ProductWrapper;

@SuppressWarnings("serial")
public class ProductsCriteriaAction extends SuperAction {
	private Integer typeId;
	private Integer genderId;
	private List<ProductWrapper> result = new ArrayList<ProductWrapper>();

	public String simpleCriteria() throws Exception {
		List<Product> resProd = new ProductDAO().findAvailByGenderAndType(genderId, typeId, null);
		
		String dir = ServletActionContext.getServletContext().getRealPath("/") + "temp_images";
		FileManager.removeUserDirectory(dir);
		if (resProd != null && !resProd.isEmpty()) {
			FileManager.createUserDirectory(dir);
			
			for (Product prod : resProd) {
				result.add(new ProductWrapper(prod, dir));
			}
		}
		
		return SUCCESS;
	}
	
	public String showFavorites() throws Exception {
		if(!getSessionManager().isSessionSignedIn()) {
			addActionMessage(getText("clostar.error.sessionexpired"));
			getSessionManager().putKey(Constants.TARGET, "show_favorites");
			return LOGIN;
		}
		
		User user = getSessionManager().getSessionUser();
		List<Favorites> favs = new FavoritesDAO().getByUser(user);
		List<Product> resProd = new ArrayList<Product>();
		
		for (Favorites fav : favs) {
			Product prod = new ProductDAO().getById(fav.getProdId());
			if (prod.getActiveInd() == com.clostar.db.utils.Constants.ACTIVE_IND)
				resProd.add(prod);
		}
		
		String dir = ServletActionContext.getServletContext().getRealPath("/") + "temp_images";
		FileManager.removeUserDirectory(dir);
		if (resProd != null && !resProd.isEmpty()) {
			FileManager.createUserDirectory(dir);
			
			for (Product prod : resProd) {
				result.add(new ProductWrapper(prod, dir));
			}
		}
		
		return SUCCESS;
	}
	
	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getGenderId() {
		return genderId;
	}

	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}

	public List<ProductWrapper> getResult() {
		return result;
	}

	public void setResult(List<ProductWrapper> result) {
		this.result = result;
	}
}
