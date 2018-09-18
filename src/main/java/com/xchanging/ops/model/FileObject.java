package com.xchanging.ops.model;

import org.springframework.web.multipart.MultipartFile;

public class FileObject {

	MultipartFile file;
	
	String description;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}