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
import com.xchanging.ops.model.RelProjComp;
import com.xchanging.ops.model.RelComp;
import com.xchanging.ops.model.RelProject;
import com.xchanging.ops.model.ServiceComponent;
import com.xchanging.ops.model.TranRelease;
import com.xchanging.ops.service.ConfigService;
import com.xchanging.ops.service.RelDocService;
import com.xchanging.ops.service.RelComponentService;
import com.xchanging.ops.service.ServiceComponentService;
import com.xchanging.ops.utils.CommonUtils;

@Controller
@RequestMapping("/relComp")
public class RelComponentController {

	@Autowired
	RelComponentService relCompService;
	@Autowired
	RelDocService relDocService;
	@Autowired
	ConfigService configService;
	@Autowired
	ServiceComponentService compService;
	
	@RequestMapping(value = { "/{id}" }, method = RequestMethod.GET)
	public String list(@PathVariable Integer id, ModelMap model) {
		listByRelId(id,model);
		return "releaseProjCompList";
	}
	
	@RequestMapping(value = { "/proj/{id}" }, method = RequestMethod.GET)
	public String listProjComp(@PathVariable Integer id, ModelMap model) {
		//List<RelComp> relCompList = relCompService.findByProject(id);
		return "releaseProjCompList";
	}
	
	
	private ModelMap listByRelId(Integer id,ModelMap model){
		List<RelComp> relCompList = relCompService.findByRelease(id);
		model.addAttribute("newlink","/relComp/new/"+id);
		model.addAttribute("relId", id);
		model.addAttribute("relCompList", relCompList);
		model.addAttribute("deleteurl","/relComp/delete");
		return model;
	}
	
	
	
	@RequestMapping(value = { "/new/{relId}" }, method = RequestMethod.GET)
	public String newProject(@PathVariable int relId, ModelMap model) {
		RelComp comp = new RelComp();
		comp.setRelId(relId);
		List<ServiceComponent> compList = compService.findOrderedAll();
		model.addAttribute("compList", compList);
		model.addAttribute("relComp", comp);
		model.addAttribute("edit", false);
		return "relProjComp";
	}
	
	
	@RequestMapping(value = { "/new/{relId}" }, method = RequestMethod.POST)
	public String saveRelease(@Valid RelComp projComp, BindingResult result,
			ModelMap model) {
		projComp.setCreatedBy(CommonUtils.getCurrentUser());
		relCompService.save(projComp);
		listByRelId(projComp.getRelId(),model);
		return "releaseProjCompList";
		
	}
	
	@RequestMapping(value = { "/edit-{id}" }, method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
	public String editProject(@PathVariable Integer id, ModelMap model) {
		RelComp relComp = relCompService.findById(id);
		relComp.setDocs(relDocService.findByRelComp(id));
		model.addAttribute("relComp", relComp);
		model.addAttribute("relId", relComp.getRelId());
		model.addAttribute("edit", true);
		return "relProjComp";
	}
	
	@RequestMapping(value = { "/edit-{id}" }, method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
	public String updateService(@Valid RelComp relComp, BindingResult result,
			ModelMap model, @PathVariable Integer id) {	
		relComp.setCreatedBy(CommonUtils.getCurrentUser());
		relCompService.update(relComp);
		listByRelId(id,model);
		return "releaseProjCompList";
	}
	
	@RequestMapping(value = { "/doc/{relId}" }, method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
	public String addDocument(@PathVariable Integer relId, ModelMap model) {
		RelDoc doc = new RelDoc();
		doc.setType(3);
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
	
	
	@RequestMapping(value = { "/delete/{id}" }, method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
	public String deleteService(@PathVariable int id,ModelMap model) {
		RelComp relComp= relCompService.findById(id);
		try{
			relCompService.deleteById(id);
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("error", "Cannot delete the service due to DB Constraints");
		}
		return "redirect:/relComp/"+relComp.getRelId();
	}
	
}
