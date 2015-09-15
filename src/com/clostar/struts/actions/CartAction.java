package com.clostar.struts.actions;

import java.util.ArrayList;
import java.util.List;

import com.clostar.business.SessionManager;
import com.clostar.db.dao.AddressDAO;
import com.clostar.db.dao.MeasureDAO;
import com.clostar.db.dao.OrderDataDAO;
import com.clostar.db.dao.ProductDAO;
import com.clostar.db.dao.UserOrderDAO;
import com.clostar.db.model.Address;
import com.clostar.db.model.Measure;
import com.clostar.db.model.OrderData;
import com.clostar.db.model.Product;
import com.clostar.db.model.User;
import com.clostar.db.model.UserOrder;
import com.clostar.utils.Constants;
import com.clostar.wrappers.CartWrapper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class CartAction extends SuperAction 
	implements ModelDriven<Product>{

	private Product product = new Product();
	private String chooseMeasure;
	private Integer chooseQuantity;
	
	private List<Address> addresses;
	
	private String city;
	private String addr;
	private String phone;
	private String chooseAddr;
	
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
				if (cw.getProductId() == product.getId() && getChooseMeasure().equals(cw.getMeasure())) {
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
		User user = getSessionManager().getSessionUser();
		setAddresses(new AddressDAO().getByUser(user));
		return SUCCESS;
	}
	
	public String sendOrder() throws Exception {
		if(!getSessionManager().isSessionSignedIn()){
			addActionMessage(getText("clostar.error.sessionexpired"));
			getSessionManager().putKey(Constants.TARGET, "show_cart");
			return LOGIN;
		}
		
		User user = getSessionManager().getSessionUser();
		Address userAddr;
		if (getChooseAddr().equals("new")) {
			userAddr = new Address(user, city, addr, phone);
			userAddr.setActiveInd(com.clostar.db.utils.Constants.ACTIVE_IND);
		}
		else {
			userAddr = new AddressDAO().getById(Integer.parseInt(getChooseAddr()));
		}
		new AddressDAO().saveOrUpdate(userAddr);
		
		@SuppressWarnings("unchecked")
		List<CartWrapper> cart = (List<CartWrapper>) getSessionManager().getValueKey(Constants.CART);
		if (!cart.isEmpty()) {
			UserOrder userOrder = new UserOrder(user, userAddr, 
					(Float)getSessionManager().getValueKey(Constants.CART_PRICE));
			new UserOrderDAO().saveOrUpdate(userOrder);
			
			for (CartWrapper cw : cart) {
				Product prod = new ProductDAO().getById(cw.getProductId());
				prod.setQuantity(prod.getQuantity() - cw.getQuantity());
				prod.setSoldQuantity(prod.getSoldQuantity() + cw.getQuantity());
				new ProductDAO().saveOrUpdate(prod);
				
				Measure m = new MeasureDAO().getByMeasureAndProduct(cw.getMeasure(), prod);
				m.setQuantityAvail(m.getQuantityAvail() - cw.getQuantity());
				m.setQuantitySold(m.getQuantitySold() + cw.getQuantity());
				new MeasureDAO().saveOrUpdate(m);
				
				OrderData od = new OrderData(userOrder, cw.getProductId());
				od.setMeasure(cw.getMeasure());
				od.setQuantity(cw.getQuantity());
				od.setPrice(cw.getPrice());
				od.setCurrency(cw.getCurrency());
				od.setOwnerId(prod.getUser().getId());
				new OrderDataDAO().saveOrUpdate(od);
			}
		}
		getSessionManager().removeKey(Constants.CART);
		getSessionManager().removeKey(Constants.CART_PRICE);
		getSessionManager().removeKey(Constants.CART_SIZE);
		return SUCCESS;
	}
		
	@Override
	public Product getModel() {
		return product;
	}

	public String getChoosenMeasure() {
		return getChooseMeasure();
	}

	public void setChoosenMeasure(String chooseMeasure) {
		this.setChooseMeasure(chooseMeasure);
	}

	public Integer getChoosenQuantity() {
		return chooseQuantity;
	}

	public void setChoosenQuantity(Integer chooseQuantity) {
		this.chooseQuantity = chooseQuantity;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getChooseAddr() {
		return chooseAddr;
	}

	public void setChooseAddr(String chooseAddr) {
		this.chooseAddr = chooseAddr;
	}

	public String getChooseMeasure() {
		return chooseMeasure;
	}

	public void setChooseMeasure(String chooseMeasure) {
		this.chooseMeasure = chooseMeasure;
	}
}
