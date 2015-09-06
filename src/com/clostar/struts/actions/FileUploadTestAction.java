package com.clostar.struts.actions;

import java.io.File;

import com.opensymphony.xwork2.ActionSupport;

public class FileUploadTestAction extends ActionSupport{

	private File fileUpload;
	private String fileUploadContentType;
	private String fileUploadFileName;

	public String getFileUploadContentType() {
		return fileUploadContentType;
	}

	public void setFileUploadContentType(String fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}

	public String getFileUploadFileName() {
		return fileUploadFileName;
	}

	public void setFileUploadFileName(String fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}

	public File getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}

	@Override
	public String execute() throws Exception{
		
		return SUCCESS;
		
	}
	
	public String display() {
		return NONE;
	}
	
}
