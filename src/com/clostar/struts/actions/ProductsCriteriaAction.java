package com.clostar.struts.actions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.clostar.db.dao.ProductDAO;
import com.clostar.db.model.Product;
import com.clostar.wrappers.ProductWrapper;

@SuppressWarnings("serial")
public class ProductsCriteriaAction extends SuperAction {
	private Integer typeId;
	private Integer genderId;
	private List<ProductWrapper> result = new ArrayList<ProductWrapper>();
	
	public String simpleCriteria() throws Exception {
		List<Product> resProd = new ProductDAO().findByGenderAndType(genderId, typeId, null);
		
		String dir = ServletActionContext.getServletContext().getRealPath("/") + "temp_images";
		removeUserDirectory(dir);
		if (resProd != null && !resProd.isEmpty()) {
			createUserDirectory(dir);
			
			for (Product prod : resProd) {
				result.add(new ProductWrapper(prod, dir));
			}
		}
		
		return SUCCESS;
	}

	private void createUserDirectory(String dir) {
		new File(dir).mkdir();
	}

	private void removeUserDirectory(String dir) {
		File directory = new File(dir);

    	if(directory.exists()) {
           try {
               delete(directory);
           }
           catch(IOException e) {
               e.printStackTrace();
           }
        }
	}

	public void delete(File file)
	    	throws IOException{
	 
	    	if(file.isDirectory()) {
	    		if(file.list().length == 0) {
	    		   file.delete();
	    		   System.out.println("Directory is deleted : " 
                           			+ file.getAbsolutePath());
	    		}
	    		else {
	        	   String files[] = file.list();
	     
	        	   for (String temp : files) {
	        		   File fileDelete = new File(file, temp);
	        		   delete(fileDelete);
	        	   }

	        	   if(file.list().length == 0) {
	        		   file.delete();
	        		   System.out.println("Directory is deleted : " 
	                                                  + file.getAbsolutePath());
	        	   }
	    		}	
	    	}
	    	else {
	    		file.delete();
	    		System.out.println("File is deleted : " + file.getAbsolutePath());
	    	}
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
