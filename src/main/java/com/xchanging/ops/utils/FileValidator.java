package com.xchanging.ops.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.xchanging.ops.model.FileObject;





@Component
public class FileValidator implements Validator {
		
	//final long MAX_FILE_SIZE=Long.parseLong(System.getProperty("MAX_FILE_SIZE"));
	public boolean supports(Class<?> clazz) {
		return FileObject.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		 MultipartFile file = ( MultipartFile) obj;
		 long MAX_FILE_SIZE =Long.parseLong(System.getProperty("MAX_FILE_SIZE"));
		if(file!=null){
				if(file.getSize() > MAX_FILE_SIZE){
				errors.rejectValue("file", "file.size.exceeds","File size exceeds the max size limit!");
			}
		}
	}
}

