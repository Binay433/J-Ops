package com.xchanging.ops.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.xchanging.ops.model.KbSuggest;
import com.xchanging.ops.model.KnowledgeBase;
import com.xchanging.ops.model.OpsDocument;
import com.xchanging.ops.model.Search;
import com.xchanging.ops.model.ServicePoint;
import com.xchanging.ops.model.User;
import com.xchanging.ops.model.UserProfile;
import com.xchanging.ops.service.DocumentService;
import com.xchanging.ops.service.KBService;
import com.xchanging.ops.service.KBSugestService;
import com.xchanging.ops.service.ServicePointsService;
import com.xchanging.ops.utils.CommonUtils;
import com.xchanging.ops.utils.MailService;

@Controller
@Scope("session")
@RequestMapping(value = { "kb" })
public class KBController {
	private static Logger logger = LoggerFactory.getLogger(KBController.class);
	@Autowired
	KBService kBService;

	@Autowired
	KBSugestService sugest;

	@Autowired
	ServicePointsService servicepoint;
	
	@Autowired
	MailService mailservice;

	@Autowired
	DocumentService documentService;
	// @Autowired
	// UserProfileService userProfileService
	List<UserProfile> userProfileList = new ArrayList<UserProfile>();

	@RequestMapping(value = { "/list-{offset}-{maxResult}-{all}" }, method = { RequestMethod.GET })
	@PreAuthorize("isAuthenticated()")
	public String listServiceComponentService(@PathVariable Integer offset, @PathVariable Integer maxResult,
			@PathVariable Integer all, ModelMap model) {
		if (maxResult == null) {
			maxResult = 10;
		}
		if (offset == null) {
			offset = 0;
		}
		List kbList = null;
		long count = 0;

		User currentUser = CommonUtils.getCurrentUser();
		// userProfileList.addAll(currentUser.getUserProfiles());
		if (CommonUtils.isAdmin(currentUser.getUserProfiles())) {
			if (all != null && all > 0) {
				kbList = this.kBService.findAll(offset, maxResult);
				count = this.kBService.countAll();
				model.addAttribute("kbList", kbList);
				model.addAttribute("all", "");
				model.addAttribute("allcaption", "Show Only Draft");
			} else {
				model = this.kBService.findAllDrafts(model);
				count = ((Integer) model.get("count")).intValue();
				model.addAttribute("all", 1);
				model.addAttribute("allcaption", "Show All");
			}
		} else {
			model = this.kBService.findMyKbItems(model, currentUser);
			count = ((Integer) model.get("count")).intValue();
			model.addAttribute("all", "");
			model.addAttribute("allcaption", "Show Only Draft");
		}

		model.addAttribute("pages", CommonUtils.pages((long) count, (int) maxResult));
		int prev = offset - maxResult;
		int next = offset + maxResult;
		if ((long) next >= count) {
			next = 0;
		}
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("offset", offset);
		model.addAttribute("previous", prev);
		model.addAttribute("next", next);
		model.addAttribute("maxResult", maxResult);
		return "kbList";
	}

	@RequestMapping(value = { "/newKBcomp" }, method = { RequestMethod.GET })
	@PreAuthorize("isAuthenticated()")
	public String addNewKBComponent(ModelMap model) {
		KnowledgeBase kbComponent = null;
		kbComponent = new KnowledgeBase();
		model.addAttribute("kbComponent", kbComponent);
		model.addAttribute("user", CommonUtils.getCurrentUser());
		return "kbComponent";
	}

	@RequestMapping(value = { "/newKBcomp" }, method = { RequestMethod.POST })
	@PreAuthorize("isAuthenticated()")
	public String saveNewKBComponent(@Valid KnowledgeBase kbComponent, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("kbComponent", kbComponent);
			return "kbComponent";
		}
		kbComponent.setUpdatedby(CommonUtils.getCurrentUser());
		kbComponent.setUpdated(new Date());
		this.kBService.save(kbComponent);
		OpsDocument document = null;
		for (MultipartFile file : kbComponent.getFiles()) {
			if (file.getSize() == 0)
				continue;
			document = CommonUtils.getBasicDocument((String) "KB");
			document.setParentId(kbComponent.getId());
			try {
				document = CommonUtils.saveDocument((MultipartFile) file, (DocumentService) this.documentService,
						(OpsDocument) document);
				continue;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (kbComponent.getId() != null && !kbComponent.isSpadded() && !kbComponent.isApproved()) {
			servicepoint.save(new ServicePoint(null, new Date(), false, 0, CommonUtils.getCurrentUser(),
					"KB Item Added Item Number KB-" + kbComponent.getId(),
					Integer.parseInt(System.getProperty("kb_service_point")), kbComponent.getId(),
					kbComponent.isApproved(), new Date(), CommonUtils.getCurrentUser(), CommonUtils.getCurrentUser()));

			kbComponent.setSpadded(true);
			this.kBService.update(kbComponent);
		}
		if (kbComponent.getId() != null && kbComponent.isApproved()) {
			servicepoint.kbApproved(kbComponent.getId());
		}

		model.addAttribute("message", "Knowledge Item added succesfully");
		return "kbSuccess";
	}

	@RequestMapping(value = { "/search" }, method = { RequestMethod.GET })
	@PreAuthorize("isAuthenticated()")
	public String doSearch(ModelMap model) {
		model.addAttribute("search", new Search());
		return "kbSearch";
	}

	@RequestMapping(value = { "/search" }, method = { RequestMethod.POST })
	@PreAuthorize("isAuthenticated()")
	public String doSearch(@Valid Search search, ModelMap model) {
		long count = 0;
		if (search.getOffset() == null) {
			search.setOffset(Integer.valueOf(0));
		}
		if (search.getMaxresult() == null) {
			search.setMaxresult(Integer.valueOf(10));
		}
		if (search != null && !StringUtils.isEmpty(search.getSearchText().trim())) {
			model = this.kBService.doSearch(search.getSearchText(), model, search.getOffset(), search.getMaxresult());
			count = ((Integer) model.get("count")).intValue();
		}
		model.addAttribute("txt", search.getSearchText());
		model.addAttribute("pages", CommonUtils.pages((long) count, (int) search.getMaxresult()));
		int prev = search.getOffset() - search.getMaxresult();
		int next = search.getOffset() + search.getMaxresult();
		if ((long) next >= count) {
			next = 0;
		}
		model.addAttribute("offset", search.getOffset());
		model.addAttribute("previous", prev);
		model.addAttribute("next", next);
		model.addAttribute("maxResult", search.getMaxresult());
		return "kbSearch";
	}

	@RequestMapping(value = { "/page-{txt}-{offset}-{max}" }, method = { RequestMethod.GET })
	public String doSearchPage(@PathVariable String txt, @PathVariable Integer offset, @PathVariable Integer max,
			ModelMap model) {
		Search search = new Search();
		search.setSearchText(txt);
		model = this.kBService.doSearch(txt, model, offset, max);
		model.addAttribute("txt", txt);
		long count = ((Integer) model.get("count")).intValue();
		model.addAttribute("pages", CommonUtils.pages((long) count, (int) max));
		int prev = offset - max;
		int next = offset + max;
		if ((long) next >= count) {
			next = 0;
		}
		model.addAttribute("offset", offset);
		model.addAttribute("previous", prev);
		model.addAttribute("maxResult", max);
		model.addAttribute("next", next);
		model.addAttribute("search", search);
		return "kbSearch";
	}

	@RequestMapping(value = { "/page-{txt}-{offset}-{max}" }, method = { RequestMethod.POST })
	public String doSearchPage(@Valid Search search, ModelMap model) {
		search.setOffset(null);
		this.doSearch(search, model);
		return "kbSearch";
	}

	@RequestMapping(value = { "/searchpopup" }, method = { RequestMethod.GET })
	@PreAuthorize("isAuthenticated()")
	public String doSearchPopUp(ModelMap model) {
		model.addAttribute("search", new Search());
		return "kbSearchPopUp";
	}

	@RequestMapping(value = { "/searchpopup" }, method = { RequestMethod.POST })
	@PreAuthorize("isAuthenticated()")
	public String doSearchPopUp(@Valid Search search, ModelMap model) {
		this.doSearch(search, model);
		return "kbSearchPopUp";
	}

	@RequestMapping(value = { "/popuppage-{txt}-{offset}-{max}" }, method = { RequestMethod.GET })
	public String doSearchPopUpPage(@PathVariable String txt, @PathVariable Integer offset, @PathVariable Integer max,
			ModelMap model) {
		this.doSearchPage(txt, offset, max, model);
		return "kbSearchPopUp";
	}

	@RequestMapping(value = { "/popuppage-{txt}-{offset}-{max}" }, method = { RequestMethod.POST })
	public String doSearchPopUpPage(@Valid Search search, ModelMap model) {
		search.setOffset(null);
		this.doSearch(search, model);
		return "kbSearchPopUp";
	}

	@RequestMapping(value = { "/search-{id}" }, method = { RequestMethod.GET })
	@PreAuthorize("isAuthenticated()")
	public String doSearch(@PathVariable Integer id, ModelMap model) {
		KnowledgeBase kbComponent = null;
		if (id != null) {
			kbComponent = (KnowledgeBase) this.kBService.findById(id);
		}
		ArrayList<OpsDocument> doclist = new ArrayList(this.documentService.findAllByParentId(kbComponent.getId()));
		kbComponent.setDocuments(doclist);
		model.addAttribute("kbComponent", kbComponent);
		model.addAttribute("user", CommonUtils.getCurrentUser());
		return "kbComponent";
	}

	@RequestMapping(value = { "/search-{id}" }, method = { RequestMethod.POST })
	public String doSearch(@Valid KnowledgeBase kbComponent, BindingResult result, ModelMap model) {
		this.saveNewKBComponent(kbComponent, result, model);
		model.addAttribute("message", "Knowledge Item updated succesfully");
		return "kbSuccess";
	}

	@RequestMapping(value = { "/searchpopup-{id}" }, method = { RequestMethod.GET })
	public String doSearchPopUp(@PathVariable Integer id, ModelMap model) {
		this.doSearch(id, model);
		return "kbComponentPopUp";
	}

	@RequestMapping(value = { "/delete-{id}" }, method = { RequestMethod.GET })
	@PreAuthorize("isAuthenticated()")
	public String deleteKB(@PathVariable Integer id, ModelMap model) {
		this.kBService.deleteById(id);
		return "kbSuccess";
	}

	@RequestMapping(value = { "/suggest" }, method = { RequestMethod.GET })
	public String suggest(ModelMap model) {
		model.addAttribute("suggest", new KbSuggest());
		return "kbSuggest";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/suggest" }, method = { RequestMethod.POST })
	public String suggest(@Valid KbSuggest kbSuggest, BindingResult result, ModelMap model,HttpSession session) throws Exception {
		logger.info(kbSuggest.getKeyword());

		// CHANGES START
		model = this.sugest.doSearch(kbSuggest.getKeyword(), model, Integer.valueOf(0), Integer.valueOf(10));
		List<KbSuggest> listSuggestion = new ArrayList<KbSuggest>();
		listSuggestion=(List<KbSuggest>) model.get("kblistsuggestions");
		kbSuggest.setCreated_by(CommonUtils.getCurrentUser().getId());
		if(listSuggestion.isEmpty()){
			sugest.save(kbSuggest);	
			String[] toAddress = {"Ashwani.Pundir@xchanging.com"};
			String[] ccAddress = {"Sunil.Rana@xchanging.com"};
			mailservice.sendMail(CommonUtils.getCurrentUser().getEmail(), toAddress, ccAddress, "New Knowledge Base Suggestion Added", "Hi,<br>Please Check Dashboard for further details.<br>Thanks!!");
			return "kbSuggestionsSuccess";
		}
		session.setAttribute("kbSuggestSession", kbSuggest);	
		//model.addAttribute("suggest", kbSuggest);		
		return "kbSuggestions";
	}
	
	@RequestMapping(value = { "/suggestSave" }, method = { RequestMethod.POST })
	public String suggestSave(@Valid KbSuggest kbSuggest, BindingResult result, ModelMap model,HttpSession session) {
		logger.info(kbSuggest.getKeyword());
		KbSuggest kbsuggest = (KbSuggest) session.getAttribute("kbSuggestSession");
		sugest.save(kbsuggest);		
		return "kbSuggestionsSuccess";
	}
}
