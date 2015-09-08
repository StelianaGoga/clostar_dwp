package com.clostar.wrappers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.clostar.db.dao.ProdPicDAO;
import com.clostar.db.model.ProdPic;
import com.clostar.db.model.Product;

public class ProductWrapper {
	private Product product;
	private String firstPicPath;
	private Integer secondPicId;
	private String secondPicPath;
	
	public ProductWrapper(Product product, String dir) {
		this.product = product;
		List<ProdPic> pics = new ProdPicDAO().findByProdId(product);

		this.firstPicPath = product.getPicture1Id() + ".jpg";
		this.secondPicPath = this.firstPicPath;
		this.secondPicId = product.getPicture1Id();
		ProdPic firstPic = null;
		ProdPic secondPic = null;
		for (ProdPic pic : pics) {
			if (pic.getId() == product.getPicture1Id()) {
				firstPic = pic;
				break;
			}
		}
		
		for (ProdPic pic : pics) {
			if (pic.getId() != product.getPicture1Id()) {
				secondPicPath = pic.getId() + ".jpg";
				secondPic = pic;
				secondPicId = pic.getId();
				break;
			}
		}
		
		try {
			File file = new File(dir + File.separator + firstPicPath);
			FileOutputStream out = new FileOutputStream(file);
			out.write(firstPic.getPicture());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (secondPic != null) {
			try {
				File file = new File(dir + File.separator + secondPicPath);
				FileOutputStream out = new FileOutputStream(file);
				out.write(secondPic.getPicture());
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public String getFirstPicPath() {
		return firstPicPath;
	}

	public void setFirstPicPath(String firstPicPath) {
		this.firstPicPath = firstPicPath;
	}

	public Integer getSecondPicId() {
		return secondPicId;
	}

	public void setSecondPicId(Integer secondPicId) {
		this.secondPicId = secondPicId;
	}

	public String getSecondPicPath() {
		return secondPicPath;
	}

	public void setSecondPicPath(String secondPicPath) {
		this.secondPicPath = secondPicPath;
	}

}
