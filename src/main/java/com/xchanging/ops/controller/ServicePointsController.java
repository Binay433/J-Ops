package com.xchanging.ops.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xchanging.ops.model.ServicePoint;
import com.xchanging.ops.model.ServicePointsContainer;
import com.xchanging.ops.model.User;
import com.xchanging.ops.service.ServicePointsService;
import com.xchanging.ops.service.UserService;
import com.xchanging.ops.utils.CommonMailUtil;
import com.xchanging.ops.utils.CommonUtils;

@Controller
@RequestMapping(value={"SP"})
public class ServicePointsController {
	
    private static Logger logger = LoggerFactory.getLogger(ServicePointsController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	ServicePointsService servicePointsService;
	
	@RequestMapping(value = { "/newSPScreen" }, method = RequestMethod.GET)
	@PreAuthorize("hasRole('SUPER')")
	public String getServicePointsScreen(ModelMap model){
		ServicePoint sp = new ServicePoint();
		List<User> users = userService.findAll();
		List<ServicePoint> spList = servicePointsService.findDraftPoints();
		model.addAttribute("users", users);
		model.addAttribute("sp", sp);
		model.addAttribute("spList", spList);
		return "addServicePoints";
	}
	
	@RequestMapping(value = { "/newSPScreen" }, method = RequestMethod.POST)
	@PreAuthorize("hasRole('SUPER')")
	public String postServicePointsScreen(@Valid ServicePoint sp, ModelMap model){
		if(sp.getPoint()==null){
			logger.info("sp is null so returning back");
			return "redirect:/SP/newSPScreen";
		}
		User currentUser = CommonUtils.getCurrentUser();
			sp.setCreated(new Date());
			sp.setCredited(false);
			sp.setCreatedby(currentUser);
			sp.setType(1);
        	servicePointsService.save(sp);
			logger.info("sp saved successfully");
		return "redirect:/SP/newSPScreen";
	}
	
	@RequestMapping(value = { "/allearned" }, method = RequestMethod.GET)
	@PreAuthorize("hasRole('SUPER')")
	public String getAllEarnedServicePoints(ModelMap model){
		ServicePointsContainer spContainer = new ServicePointsContainer();
		List<ServicePoint> spList = servicePointsService.findAllUserEarnedPoints();
		spContainer.setServicePointsRecords(spList);
		model.addAttribute("spContainer", spContainer);
		return "earnedServicePoint";
	}

	
	@RequestMapping(value = { "/allearned" }, method = RequestMethod.POST)
	@PreAuthorize("hasRole('SUPER')")
	public String getAllEarnedServicePoints(@Valid ServicePointsContainer spContainer, BindingResult result,ModelMap model){
		servicePointsService.appendServicePoints();
        return "earnedServicePoint";
	}
	
	@RequestMapping(value = { "/delete-{id}-{kb}" }, method = RequestMethod.GET)
	public String delete(@PathVariable Integer id,@PathVariable boolean kb) {
		servicePointsService.deleteById(id);
		if(kb){
			return "redirect:/SP/allearned";
		}else{
			return "redirect:/SP/newSPScreen";
		}
	}

	@RequestMapping(value = { "/updateAll" }, method = RequestMethod.GET)
	//@PreAuthorize("hasRole('ADMIN')")
	public String updateServicePoints(ModelMap model){
		servicePointsService.updateAllServicePoints();
		return "redirect:/SP/newSPScreen";
	}
	
	
	@RequestMapping(value={"/list"}, method={RequestMethod.GET})
    public String listServiceComponentService(ModelMap model) {

		List<ServicePoint> splist=servicePointsService.findAllUserSPList();
		Map<String,ServicePoint> map = new HashMap<String,ServicePoint>();
		for(ServicePoint sp:splist){
			map.put(sp.getUser().getFirstName()+" "+sp.getUser().getLastName(), sp);
		}
		Set<String> sortedKeays = new TreeSet<String>(map.keySet());
		Iterator<String> itr = sortedKeays.iterator();
		
		List<ServicePoint> finalList = new ArrayList<ServicePoint>();
		while(itr.hasNext()){
			finalList.add(map.get(itr.next()));
		}
        model.addAttribute("splist", finalList);
        return "spList";
    }
	
	
	@RequestMapping(value={"/list-{id}"}, method={RequestMethod.GET})
    public String listServiceComponentService(@PathVariable Integer id, ModelMap model) {

		User user = userService.findById(id);
		List<ServicePoint> spList =servicePointsService.findByUserId(user);
		model.addAttribute("spList", spList);
		model.addAttribute("userName", user.getFirstName()+" "+user.getLastName());
        return "servicePoints";
    }
	
}
