package com.clostar.struts.actions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.clostar.db.model.Prodohol;
import com.clostar.db.model.Shopaholic;
import com.clostar.utils.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class ProdoholAction extends SuperAction 
	implements ModelDriven<Prodohol>{

	private Prodohol prodohol = new Prodohol();
	private Integer measuresNo;
	private List<Integer> mQuantity = new ArrayList<Integer>();
	private List<String> measure = new ArrayList<String>();
	
	private File profilePic;
	private String profilePicContentType;
	private String profilePicFileName;

	private List<File> fileUpload = new ArrayList<File>();
	private List<String> fileUploadContentType = new ArrayList<String>();
	private List<String> fileUploadFileName = new ArrayList<String>();
	
	public String addProdohol() throws Exception {
		if(!getSessionManager().isSessionSignedIn()){
			addActionMessage(getText("clostar.error.sessionexpired"));
			getSessionManager().putKey(Constants.TARGET, "new_product_details");
			return LOGIN;
		}

		if (prodohol.getName() == null || prodohol.getName().equals("")) {
			addFieldError("name", getText("clostar.error.notnullable"));
		}
		if (prodohol.getGenderId() == null || prodohol.getGenderId() < 0 || prodohol.getGenderId() > 6) {
			addFieldError("gender", getText("clostar.error.notnullable"));
		}
		if (prodohol.getTypeId() == null || prodohol.getTypeId() < 0) {
			addFieldError("type", getText("clostar.error.notnullable"));
		}
		if (prodohol.getPrice() == null || prodohol.getPrice() < 0) {
			addFieldError("price", getText("clostar.error.notnullable"));
		}
		if (prodohol.getCurrency() == null) {
			addFieldError("currency", getText("clostar.error.notnullable"));
		}
		if (prodohol.getQuantity() == null || prodohol.getQuantity() < 0) {
			addFieldError("quantity", getText("clostar.error.notnullable"));
		}
		if (getMeasuresNo() == null || getMeasuresNo() < 0) {
			addFieldError("measuresNo", getText("clostar.error.notnullable"));
		}
		for (int i = 0; i < mQuantity.size(); i++) {
			if (mQuantity.get(i) != null && mQuantity.get(i) < 0) {
				addFieldError("measures", getText("clostar.error.measure.nn"));
			}
			if (mQuantity.get(i) != null && mQuantity.get(i) > 0 && measure.get(i) == null) {
				addFieldError("measures", getText("clostar.error.measure.nn"));
			}
		}
		
		if (hasErrors()) {
			return INPUT;
		}
/*
		boolean isExistent = new ShopaholicDAO().isUserExistent(shopaholic.getEmail(), shopaholic.getPassword());
		if (!isExistent) {
			addActionError(getText("clostar.error.signin.userinexistent"));
			return INPUT;
		}*/
		
		System.out.println(prodohol);
		prodohol.setShopaholic((Shopaholic)ActionContext.getContext().getSession().get(Constants.USER));
		System.out.println(profilePicFileName);
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//        session.saveOrUpdate(prodohol);
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
	public Prodohol getModel() {
		return prodohol;
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
}
