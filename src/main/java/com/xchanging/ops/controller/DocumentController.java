package com.xchanging.ops.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.xchanging.ops.model.FileObject;
import com.xchanging.ops.model.OpsDocument;
import com.xchanging.ops.service.DocumentService;
import com.xchanging.ops.service.UserService;
import com.xchanging.ops.utils.CommonUtils;
import com.xchanging.ops.utils.FileValidator;

@Controller
@RequestMapping("/")
public class DocumentController {

/*	@Autowired
	UserService userService;*/
	@Autowired
	DocumentService documentService;
	
	@Autowired
	MessageSource messageSource;

	@Autowired
	FileValidator fileValidator;
	
	@InitBinder("fileObject")
	protected void initBinder(WebDataBinder binder) {
	   binder.setValidator(fileValidator);
	}
	

	
	
	@RequestMapping(value = { "/add-document" }, method = RequestMethod.GET)
	public String addDocuments(ModelMap model) {
		FileObject fileModel = new FileObject();
		model.addAttribute("fileObject", fileModel);

		List<OpsDocument> documents = documentService.findAll();
		model.addAttribute("documents", documents);

		
		return "managedocuments";
	}
	

	@RequestMapping(value = { "/download-document-{docId}" }, method = RequestMethod.GET)
	public void downloadDocument(@PathVariable int docId, HttpServletResponse response) throws IOException {
		OpsDocument document = documentService.findById(docId);
		if(document==null){
			document=documentService.findById(0);
		}
		response.setContentType(document.getType());
        response.setContentLength(document.getContent().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + document.getName() +"\"");
 
        FileCopyUtils.copy(document.getContent(), response.getOutputStream());
		
 		//return "add-document";
	}

	
	@RequestMapping(value = { "/doc/delete-{docId}" }, method = RequestMethod.GET)
	public String deleteDoc(@PathVariable int docId) {
		documentService.deleteById(docId);
		return "docDeleteConfirm";
	}
	
	@RequestMapping(value = { "/delete-document-{docId}" }, method = RequestMethod.GET)
	public String deleteDocument(@PathVariable int docId) {
		documentService.deleteById(docId);
		return "redirect:/add-document";
	}

	@RequestMapping(value = { "/add-document" }, method = RequestMethod.POST)
	public String uploadDocument(@Valid FileObject fileObject, BindingResult result, ModelMap model) throws IOException{
		
		if (result.hasErrors()) {
			List<OpsDocument> documents = documentService.findAll();
			model.addAttribute("documents", documents);
			
			return "managedocuments";
		} else {
			
			System.out.println("Fetching file");
			saveDocument(fileObject);

			return "redirect:/add-document";
		}
	}
	
	private void saveDocument(FileObject fileObject) throws IOException{
		
		OpsDocument document = new OpsDocument();
		
		MultipartFile multipartFile = fileObject.getFile();
		
		document.setName(multipartFile.getOriginalFilename());
		document.setDescription(fileObject.getDescription());
		//document.setType(multipartFile.getContentType());
		document.setType(getFileExtension(multipartFile));
		document.setContent(multipartFile.getBytes());
		document.setUser(CommonUtils.getCurrentUser());
		documentService.save(document);
	}
	
	
	
    private static String getFileExtension(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
	
	
}
