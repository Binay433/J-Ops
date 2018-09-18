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
import com.xchanging.ops.model.RelDoc;
import com.xchanging.ops.model.TranRelease;
import com.xchanging.ops.service.ConfigService;
import com.xchanging.ops.service.RelDocService;
import com.xchanging.ops.service.ReleaseService;
import com.xchanging.ops.utils.CommonUtils;

@Controller
@RequestMapping("/release")
public class ReleaseController {
	

	@Autowired
	ReleaseService releaseService;
	
	@Autowired
	RelDocService relDocService;
	
	@Autowired
	ConfigService configService;
	
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
	public String list(ModelMap model) {
		listReleases(model);
		return "releaselist";
	}
	
	private ModelMap listReleases(ModelMap model) {
		List<TranRelease> releases = releaseService.findAll();
		model.addAttribute("releases", releases);
		model.addAttribute("deleteurl","/release/delete");
		return model;
	}
	
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
	public String newRelease(ModelMap model) {
		TranRelease release = new TranRelease();
		release.setCreatedby(CommonUtils.getCurrentUser());
		model.addAttribute("release", release);
		model.addAttribute("edit", false);
		return "release";
	}
	
	
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
	public String saveRelease(@Valid TranRelease release, BindingResult result,
			ModelMap model) {
		release.setCreatedby(CommonUtils.getCurrentUser());
		releaseService.save(release);
		listReleases(model);
		return "releaselist";
		
	}
	
	

	@RequestMapping(value = { "/edit-release-{id}" }, method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
	public String editService(@PathVariable Integer id, ModelMap model) {
		TranRelease release = releaseService.findById(id);
		release.setDocs(relDocService.findByRelease(id));
		model.addAttribute("release", release);
		model.addAttribute("edit", true);
		return "release";
	}
	
	@RequestMapping(value = { "/edit-release-{id}" }, method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
	public String updateService(@Valid TranRelease release, BindingResult result,
			ModelMap model, @PathVariable Integer id) {	
		release.setCreatedby(CommonUtils.getCurrentUser());
		releaseService.update(release);
		listReleases(model);
		return "releaselist";
	}
	

	@RequestMapping(value = { "/delete/{id}" }, method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
	public String deleteService(@PathVariable String id,ModelMap model) {
		try{
			releaseService.deleteById(id);
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("error", "Cannot delete the service due to DB Constraints");
		}
		
		listReleases(model);
		return "releaselist";
	}
	
	@RequestMapping(value = { "/doc/{relId}" }, method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
	public String addDocument(@PathVariable Integer relId, ModelMap model) {
		RelDoc doc = new RelDoc();
		doc.setType(1);
		doc.setParentId(relId);
		model.addAttribute("relDoc",doc);
		model.addAttribute("edit", false);
		return "addDocument";
	}
	
	
	@RequestMapping(value = { "/doc/{relId}" }, method = RequestMethod.POST)
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
	
	

}
