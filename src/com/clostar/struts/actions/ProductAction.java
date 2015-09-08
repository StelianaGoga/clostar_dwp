package com.clostar.struts.actions;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import com.clostar.db.dao.MeasureDAO;
import com.clostar.db.dao.ProdPicDAO;
import com.clostar.db.dao.ProductDAO;
import com.clostar.db.model.Measure;
import com.clostar.db.model.ProdPic;
import com.clostar.db.model.Product;
import com.clostar.db.model.User;
import com.clostar.utils.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class ProductAction extends SuperAction 
	implements ModelDriven<Product>{

	private Product product = new Product();
	private Integer measuresNo;
	private List<Integer> mQuantity = new ArrayList<Integer>();
	private List<String> measure = new ArrayList<String>();
	private List<Integer> seasons = new ArrayList<Integer>();
	
	private File profilePic;
	private String profilePicContentType;
	private String profilePicFileName;

	private List<File> fileUpload = new ArrayList<File>();
	private List<String> fileUploadContentType = new ArrayList<String>();
	private List<String> fileUploadFileName = new ArrayList<String>();
	
	public String addProduct() throws Exception {
		if(!getSessionManager().isSessionSignedIn()){
			addActionMessage(getText("clostar.error.sessionexpired"));
			getSessionManager().putKey(Constants.TARGET, "new_product_details");
			return LOGIN;
		}
		if (product.getName() == null || product.getName().equals("")) {
			addFieldError("name", getText("clostar.error.notnullable"));
		}
		else if (product.getName().length() > 45) {
			addFieldError("name", getText("clostar.error.length.exceeded"));
		}
		if (product.getGenderId() == null || product.getGenderId() < 1 || product.getGenderId() > 4) {
			addFieldError("gender", getText("clostar.error.notnullable"));
		}
		if (product.getTypeId() == null || product.getTypeId() < 0) {
			addFieldError("type", getText("clostar.error.notnullable"));
		}
		if (product.getPrice() == null || product.getPrice() < 0) {
			addFieldError("price", getText("clostar.error.notnullable"));
		}
		if (product.getCurrency() == null) {
			addFieldError("currency", getText("clostar.error.notnullable"));
		}
		if (product.getQuantity() == null || product.getQuantity() < 0) {
			addFieldError("quantity", getText("clostar.error.notnullable"));
		}
		if (getMeasuresNo() == null || getMeasuresNo() < 0) {
			addFieldError("measuresNo", getText("clostar.error.notnullable"));
		}
		else {
			int q = 0, pp = 0;
			for (int i = 0; i < mQuantity.size(); i++) {
				if (mQuantity.get(i) == null || mQuantity.get(i) < 0) {
					addFieldError("measures", getText("clostar.error.measure.nn"));
					pp = 1;
					break;
				}
				if (mQuantity.get(i) != null && mQuantity.get(i) > 0 && 
						(measure.get(i) == null || measure.get(i).equals(""))) {
					addFieldError("measures", getText("clostar.error.measure.nn"));
					pp = 1;
					break;
				}
				if (mQuantity.get(i) != null && mQuantity.get(i) > 0) {
					q += mQuantity.get(i);
				}
			}
			
			if (pp == 0 && (product.getQuantity() == null || q != product.getQuantity())) {
				addFieldError("measures", getText("clostar.error.measure.quantity"));
			}
		}
		if (profilePic == null || profilePicFileName == null || profilePicFileName.equals("")) {
			addFieldError("profilePicture", getText("clostar.error.prodpicnotnullable"));
		}
		
		if (hasErrors()) {
			return INPUT;
		}
		
		System.out.println(product);
		product.setUser((User)ActionContext.getContext().getSession().get(Constants.USER));
		product.setSoldQuantity(0);

		int season_id = 0;
		for (int s : seasons) {
			season_id += s;
		}
		product.setSeasonId(season_id);
		product.setActiveInd(com.clostar.db.utils.Constants.ACTIVE_IND);
		
		new ProductDAO().saveOrUpdate(product);

		RandomAccessFile file = new RandomAccessFile(profilePic, "r");
		byte[] picture = new byte[(int)profilePic.length()];
		if (file.read(picture) == (int)profilePic.length()) {
			ProdPic pic = new ProdPic();
			pic.setProduct(product);
			pic.setProfileInd(1);
			pic.setPicture(picture);
			pic.setSize((int)profilePic.length());
			pic.setContentType(profilePicContentType);
			pic.setFileName(profilePicFileName);
			pic.setActiveInd(com.clostar.db.utils.Constants.ACTIVE_IND);
			new ProdPicDAO().saveOrUpdate(pic);
			product.setPicture1Id(pic.getId());
			new ProductDAO().saveOrUpdate(product);
		}
		file.close();
		
		for (int i = 0; i < fileUpload.size(); i++) {
			file = new RandomAccessFile(fileUpload.get(i), "r");
			picture = new byte[(int)fileUpload.get(i).length()];
			if (file.read(picture) == (int)fileUpload.get(i).length()) {
				ProdPic pic = new ProdPic();
				pic.setProduct(product);
				pic.setProfileInd(0);
				pic.setPicture(picture);
				pic.setSize((int)profilePic.length());
				pic.setContentType(profilePicContentType);
				pic.setFileName(profilePicFileName);
				pic.setActiveInd(com.clostar.db.utils.Constants.ACTIVE_IND);
				new ProdPicDAO().saveOrUpdate(pic);
			}
			file.close();
		}
		
		for (int i = 0; i < mQuantity.size(); i++) {
			Measure m = new Measure();
			m.setMeasure(measure.get(i));
			m.setQuantity(mQuantity.get(i));
			m.setQuantityAvail(mQuantity.get(i));
			m.setQuantitySold(0);
			m.setProduct(product);
			m.setPrice(product.getPrice());
			m.setCurrency(product.getCurrency());
			m.setActiveInd(com.clostar.db.utils.Constants.ACTIVE_IND);
			new MeasureDAO().saveOrUpdate(m);
		}
	    return SUCCESS;
	}

	@Override
	public Product getModel() {
		return product;
	}

	public File getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(File profilePic) {
		this.profilePic = profilePic;
	}

	public String getProfilePicContentType() {
		return profilePicContentType;
	}

	public void setProfilePicContentType(String profilePicContentType) {
		this.profilePicContentType = profilePicContentType;
	}


	public String getProfilePicFileName() {
		return profilePicFileName;
	}

	public void setProfilePicFileName(String profilePicFileName) {
		this.profilePicFileName = profilePicFileName;
	}

	public List<File> getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(List<File> fileUpload) {
		this.fileUpload = fileUpload;
	}

	public List<String> getFileUploadContentType() {
		return fileUploadContentType;
	}

	public void setFileUploadContentType(List<String> fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}

	public List<String> getFileUploadFileName() {
		return fileUploadFileName;
	}

	public void setFileUploadFileName(List<String> fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}

	public Integer getMeasuresNo() {
		return measuresNo;
	}

	public void setMeasuresNo(Integer measuresNo) {
		this.measuresNo = measuresNo;
	}

	public List<Integer> getMQuantity() {
		return mQuantity;
	}

	public void setMQuantity(List<Integer> mQuantity) {
		this.mQuantity = mQuantity;
	}

	public List<String> getMeasure() {
		return measure;
	}

	public void setMeasure(List<String> measure) {
		this.measure = measure;
	}

	public List<Integer> getSeasons() {
		return seasons;
	}

	public void setSeasons(List<Integer> seasons) {
		this.seasons = seasons;
	}
}
