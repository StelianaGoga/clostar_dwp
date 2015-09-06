package com.clostar.struts.actions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
		System.out.println(seasons);
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
				}
				if (mQuantity.get(i) != null && mQuantity.get(i) > 0 && 
						(measure.get(i) == null || measure.get(i).equals(""))) {
					addFieldError("measures", getText("clostar.error.measure.nn"));
					pp = 1;
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
/*
		boolean isExistent = new UserDAO().isUserExistent(user.getEmail(), user.getPassword());
		if (!isExistent) {
			addActionError(getText("clostar.error.signin.userinexistent"));
			return INPUT;
		}*/
		
		System.out.println(product);
		product.setUser((User)ActionContext.getContext().getSession().get(Constants.USER));
		System.out.println(profilePicFileName);
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//        session.saveOrUpdate(product);
//        session.getTransaction().commit();
		for (File file: fileUpload) {
	        System.out.println("File :" + file);
	    }
	    
	    for (String fileName: fileUploadFileName) {
	        System.out.println("Filename : " + fileName);
	    }

	    for (String fileContentType: fileUploadContentType) {
	        System.out.println("File type : " + fileContentType);
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
