package com.xchanging.ops.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xchanging.ops.model.Config;
import com.xchanging.ops.model.RelComp;
import com.xchanging.ops.model.RelDoc;
import com.xchanging.ops.model.RelProject;
import com.xchanging.ops.service.ConfigService;
import com.xchanging.ops.service.RelDocService;
import com.xchanging.ops.service.RelProjService;
import com.xchanging.ops.utils.CommonUtils;

@Controller
@RequestMapping("/relproj")
public class ReleaseProjectController {

	@Autowired
	RelProjService relProjService;
	
	@Autowired
	RelDocService relDocService;
	
	@Autowired
	ConfigService configService;
	
	@RequestMapping(value = { "/{id}" }, method = RequestMethod.GET)
	public String list(@PathVariable Integer id, ModelMap model) {
		listByRelease(id,  model);
		return "releaseProjectList";
	}
	
	
	private ModelMap listByRelease(Integer id, ModelMap model){
		List<RelProject> projList = relProjService.findByRelease(id);
		model.addAttribute("relId", id);
		model.addAttribute("deleteurl","/relproj/delete");
		model.addAttribute("projList", projList);
		return model;
	}
	
	
	@RequestMapping(value = { "/new/{relId}" }, method = RequestMethod.GET)
	public String newProject(@PathVariable int relId, ModelMap model) {
		RelProject proj = new RelProject();
		proj.setRelId(relId);
		model.addAttribute("proj", proj);
		model.addAttribute("edit", false);
		return "relProject";
	}
	
	
	@RequestMapping(value = { "/new/{relId}" }, method = RequestMethod.POST)
	public String saveRelease(@Valid RelProject proj, BindingResult result,
			ModelMap model) {
		
		proj.setCreatedby(CommonUtils.getCurrentUser());
		relProjService.save(proj);
		listByRelease(proj.getRelId(),  model);
		return "releaseProjectList";
		
	}
	
	@RequestMapping(value = { "/edit-{id}" }, method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
	public String editProject(@PathVariable Integer id, ModelMap model) {
		RelProject proj = relProjService.findById(id);
		proj.setDocs(relDocService.findByProj(id));
		model.addAttribute("proj", proj);
		model.addAttribute("relId", proj.getRelId());
		model.addAttribute("edit", true);
		return "relProject";
	}
	
	@RequestMapping(value = { "/edit-{id}" }, method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
	public String editProject(@Valid RelProject proj, BindingResult result,
			ModelMap model, @PathVariable Integer id) {	
		proj.setCreatedby(CommonUtils.getCurrentUser());
		relProjService.update(proj);
		listByRelease(proj.getRelId(),  model);
		return "releaseProjectList";
	}

	
	
	@RequestMapping(value = { "/doc/{projId}" }, method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
	public String addDocument(@PathVariable Integer projId, ModelMap model) {
		RelDoc doc = new RelDoc();
		doc.setType(2);
		doc.setParentId(projId);
		model.addAttribute("relDoc",doc);
		model.addAttribute("edit", false);
		return "addDocument";
	}
	
	
	@RequestMapping(value = { "/doc/{projId}" }, method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
	public String addDocument(@Valid RelDoc doc, BindingResult result,
			ModelMap model) {
		
		Config conf = configService.findByKey("rel_doc_location");
		String location=conf.getValue();
		try{
			doc.setCreatedBy(CommonUtils.getCurrentUser());
			CommonUtils.decorateDoc(doc.getDocFile(), doc,location);
			relDocService.save(doc);
			CommonUtils.saveOnDisk(doc.getDocFile(),doc.getLocation());
		}catch(Exception e){
			e.printStackTrace();
		}
		model.addAttribute("success", true);
		return "addDocument";
		
	}
	
	@RequestMapping(value = { "/document-{docId}" }, method = RequestMethod.GET)
	public void downloadDocument(@PathVariable int docId, HttpServletResponse response) throws IOException {
		RelDoc document = relDocService.findById(docId);
		File filedocument=null;
		if(document==null){
			Config conf = configService.findByKey("rel_doc_location");
			filedocument=new File(conf.getValue()+"/error.txt");
		}else{
			filedocument=new File(document.getLocation());
		}
		response.setContentType(document.getExt());
        response.setHeader("Content-Disposition","attachment; filename=\"" + document.getName() +"\"");
        InputStream inputStream = new FileInputStream(filedocument);
        FileCopyUtils.copy(inputStream, response.getOutputStream());
		
 		//return "add-document";
	}
	
	@RequestMapping(value = { "/delete/{id}" }, method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
	public String deleteService(@PathVariable int id,ModelMap model) {
		RelProject proj= relProjService.findById(id);
		try{
			relProjService.deleteById(id);
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("error", "Cannot delete the service due to DB Constraints");
		}
		return "redirect:/relproj/"+proj.getRelId();
	}
	
}
