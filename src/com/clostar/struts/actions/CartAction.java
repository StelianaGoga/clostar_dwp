package com.clostar.struts.actions;

import java.util.ArrayList;
import java.util.List;

import com.clostar.business.SessionManager;
import com.clostar.db.dao.AddressDAO;
import com.clostar.db.model.Address;
import com.clostar.db.model.Product;
import com.clostar.db.model.User;
import com.clostar.utils.Constants;
import com.clostar.wrappers.CartWrapper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class CartAction extends SuperAction 
	implements ModelDriven<Product>{

	private Product product = new Product();
	private String choosenMeasure;
	private Integer choosenQuantity;
	private List<Address> addresses;
	private boolean showModal;
	
	@SuppressWarnings("unchecked")
	public String addToCart() throws Exception {
		if(!getSessionManager().isSessionSignedIn()){
			addActionMessage(getText("clostar.error.sessionexpired"));
			getSessionManager().putKey(Constants.TARGET, "show_product?id=" + product.getId());
			return LOGIN;
		}
		SessionManager sm = new SessionManager(ActionContext.getContext());
		List<CartWrapper> cart = new ArrayList<CartWrapper>();
		if(sm.getValueKey(Constants.CART) != null) {
			cart = (List<CartWrapper>) sm.getValueKey(Constants.CART);
		}
		cart.add(new CartWrapper(product.getId(), product.getName(), getChoosenMeasure(), getChoosenQuantity(),
				product.getPrice(), product.getCurrency()));
		sm.putKey(Constants.CART, cart);
		Integer size = 0;
		if(sm.getValueKey(Constants.CART_SIZE) != null) {
			size = (Integer) sm.getValueKey(Constants.CART_SIZE);
		}
		size += getChoosenQuantity();
		sm.putKey(Constants.CART_SIZE, size);
		return SUCCESS;
	}

	public String showCart() throws Exception {
		if(!getSessionManager().isSessionSignedIn()){
			addActionMessage(getText("clostar.error.sessionexpired"));
			getSessionManager().putKey(Constants.TARGET, "show_cart");
			return LOGIN;
		}

		SessionManager sm = new SessionManager(ActionContext.getContext());
		if(sm.getValueKey(Constants.CART) != null) {
			@SuppressWarnings("unchecked")
			List<CartWrapper> cart = (List<CartWrapper>) sm.getValueKey(Constants.CART);
			Float total = new Float(0);
			for (CartWrapper cw : cart) {
				total += cw.getEuroPrice();
			}
			sm.putKey(Constants.CART_PRICE, total);
		}
		return SUCCESS;
	}
	
	public String removeFromCart() throws Exception {
		if(!getSessionManager().isSessionSignedIn()){
			addActionMessage(getText("clostar.error.sessionexpired"));
			getSessionManager().putKey(Constants.TARGET, "show_cart");
			return LOGIN;
		}
		SessionManager sm = new SessionManager(ActionContext.getContext());
		if(sm.getValueKey(Constants.CART) != null) {
			@SuppressWarnings("unchecked")
			List<CartWrapper> cart = (List<CartWrapper>) sm.getValueKey(Constants.CART);
			for (CartWrapper cw : cart) {
				if (cw.getProductId() == product.getId()) {
					Integer size = (Integer) sm.getValueKey(Constants.CART_SIZE);
					size -= cw.getQuantity();
					sm.putKey(Constants.CART_SIZE, size);
					cart.remove(cw);
					break;
				}
			}
		}
		return SUCCESS;
	}
	
	public String prepareAddress() throws Exception {
		if(!getSessionManager().isSessionSignedIn()){
			addActionMessage(getText("clostar.error.sessionexpired"));
			getSessionManager().putKey(Constants.TARGET, "show_cart");
			return LOGIN;
		}
		User user = new SessionManager(ActionContext.getContext()).getSessionUser();
		setAddresses(new AddressDAO().getByUser(user));
		return SUCCESS;
	}
		
	@Override
	public Product getModel() {
		return product;
	}

	public String getChoosenMeasure() {
		return choosenMeasure;
	}

	public void setChoosenMeasure(String choosenMeasure) {
		this.choosenMeasure = choosenMeasure;
	}

	public Integer getChoosenQuantity() {
		return choosenQuantity;
	}

	public void setChoosenQuantity(Integer choosenQuantity) {
		this.choosenQuantity = choosenQuantity;
	}

	public boolean isShowModal() {
		return showModal;
	}

	public void setShowModal(boolean showModal) {
		this.showModal = showModal;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
}
