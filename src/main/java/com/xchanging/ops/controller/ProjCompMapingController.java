package com.xchanging.ops.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xchanging.ops.model.RelComp;
import com.xchanging.ops.model.RelProjComp;
import com.xchanging.ops.model.RelProject;
import com.xchanging.ops.model.TranRelease;
import com.xchanging.ops.service.RelComponentService;
import com.xchanging.ops.service.RelProjCompMapingService;
import com.xchanging.ops.service.RelProjService;
import com.xchanging.ops.utils.CommonUtils;

@Controller
@RequestMapping("/maping")
public class ProjCompMapingController {

	@Autowired
	RelProjCompMapingService mapService;
	@Autowired
	RelComponentService relCompService;
	@Autowired
	RelProjService relProjService;

	@RequestMapping(value = { "/listComp/{projId}" }, method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public String listComp(@PathVariable Integer projId, ModelMap model) {
		model.addAttribute("newlink", "/maping/new/" + projId);
		RelProject proj = relProjService.findById(projId);
		List<RelComp> compList=relCompService.findByRelease(proj.getRelId());
		List<RelProjComp> list =mapService.findByProject(projId);
		
		List<RelComp> relCompList = new ArrayList<RelComp>();
		for(RelComp comp :compList){
			for(RelProjComp map :list){
				if(map.getComp()==comp.getId()){
					relCompList.add(comp);
				}
			}
		}
		model.addAttribute("deleteurl","/maping/delete/"+projId);
		model.addAttribute("proj", projId);
		model.addAttribute("relCompList", relCompList);
		return "releaseProjCompList";
	}

	@RequestMapping(value = { "/listProj/{compId}" }, method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public String listProj(@PathVariable Integer compId,ModelMap model) {
		RelComp comp = relCompService.findById(compId);
		List<RelProject> projList = relProjService.findByRelease(comp.getRelId());
		List<RelProjComp> list =mapService.findByComponent(compId);
		List<RelProject> relProjList = new ArrayList<RelProject>();
		
		for(RelProject proj :projList){
			for(RelProjComp map :list){
				if(map.getProj()==proj.getId()){
					relProjList.add(proj);
				}
			}
		}
		model.addAttribute("noAddNew",true);
		model.addAttribute("projList",relProjList);
		return "releaseProjectList";
	}

	@RequestMapping(value = { "/new/{projId}" }, method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public String mapProjComp(@PathVariable Integer projId, ModelMap model) {
		RelProjComp maping = new RelProjComp();
		RelProject proj = relProjService.findById(projId);
		List<RelComp> compList=relCompService.findByRelease(proj.getRelId());
		maping.setProj(projId);
		model.addAttribute("compList", compList);
		model.addAttribute("maping", maping);
		model.addAttribute("edit", false);
		return "relProjCompMaping";
	}

	@RequestMapping(value = { "/new/{projId}" }, method = RequestMethod.POST)
	@PreAuthorize("isAuthenticated()")
	public String saveProjComp(@Valid RelProjComp maping, BindingResult result, ModelMap model) {
		RelProjComp map = mapService.findByProjAndComp(maping.getProj(), maping.getComp());
		if(map==null){
			mapService.save(maping);
		}
		
		return "redirect:/maping/listComp/"+maping.getProj();
	}
	
	
	@RequestMapping(value = { "/delete/{projId}/{id}" }, method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
	public String deleteService(@PathVariable int projId,@PathVariable int id,ModelMap model) {
		try{
			RelProjComp map = mapService.findByProjAndComp(projId, id);
			mapService.deleteById(map.getId());
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("error", "Cannot delete the service due to DB Constraints");
		}
		return "redirect:/maping/listComp/"+projId;
	}
	
	

}
