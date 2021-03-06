package inventory.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import inventory.model.Auth;
import inventory.model.AuthForm;
import inventory.model.Category;
import inventory.model.Menu;
import inventory.model.Paging;
import inventory.model.Role;
import inventory.service.MenuService;
import inventory.service.RoleService;
import inventory.util.Constant;

@Controller
public class MenuController {
	Logger log = Logger.getLogger(MenuController.class);
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping(value = {"menu/list","menu/list/"})
	public String redirect() {
		return "redirect:/menu/list/1";
	}
	
	@RequestMapping("menu/list/{page}")
	public String menuList(Model model, @ModelAttribute("searchForm") Menu menu, @PathVariable("page") int page, HttpSession session) {
		Paging paging = new Paging(15);
		paging.setIndexPage(page);
		
		List<Menu> menuList = menuService.getListMenu(menu, paging);
		List<Role> roleList = roleService.getRoleList(null, null);
		// Sort role by id
		Collections.sort(roleList, (o1,o2)-> o1.getId() - o2.getId());
		// Check session notification is success or error than asign into model to show notify on frontend
		if (session.getAttribute(Constant.SUCCESS_MSG) != null) {
				model.addAttribute(Constant.SUCCESS_MSG, session.getAttribute(Constant.SUCCESS_MSG));
				session.removeAttribute(Constant.SUCCESS_MSG);
		}
		if (session.getAttribute(Constant.ERROR_MSG) != null) {
				model.addAttribute(Constant.ERROR_MSG, session.getAttribute(Constant.ERROR_MSG));
				session.removeAttribute(Constant.ERROR_MSG);
		}
		// Create Map 
		for (Menu menuItem : menuList) {
			Map<Integer, Integer> mapAuth = new TreeMap<>();
			// Init all role and set permission value = 0
			for (Role roleItem : roleList) {
				mapAuth.put(roleItem.getId(), 0); // admin -> <1,0> ; staff -> <2,0>
			}
			// Lookup role in auth table has permission or not => update to mapAuth
			for (Object obj : menuItem.getAuths()) {
				Auth auth = (Auth) obj;
				mapAuth.put(auth.getRole().getId(), auth.getPermission()); // admin <1,1> ; staff <2,0>
			}
			menuItem.setMapAuth(mapAuth);
		}
		
		//Show list all url at front end to choose
		List<Menu> menus = menuService.getListMenu(null, null);
		Map<String, String> mapUrl = new HashMap<>();
		for (Menu menu1 : menus) {
			mapUrl.put(String.valueOf(menu1.getId()), menu1.getUrl());
		}

		//store mapUrl in model
		model.addAttribute("mapUrl", mapUrl);
		
		model.addAttribute("pageInfo", paging);
		model.addAttribute("menus", menuList);
		model.addAttribute("roles", roleList);
		return "menu-list";
	}
	
	@GetMapping("/menu/change-status/{id}")
	public String changeStatus(Model model, @PathVariable("id") int id, HttpSession session) {
		try {
			menuService.changeStatus(id);
			session.setAttribute(Constant.SUCCESS_MSG, "Change status sucess !!!");
		}catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.setAttribute(Constant.ERROR_MSG, "Change status has error !!!");
		}
		return "redirect:/menu/list";
	}
	
	@GetMapping("/menu/permission")
	public String permission(Model model) {
		model.addAttribute("modelForm", new AuthForm());
		initSelectbox(model);
		return "menu-permission";
	}
	
	@PostMapping("/menu/update-permission")
	public String updatePermission(Model model, @ModelAttribute("modelForm") AuthForm authForm, HttpSession session) {
		try {
			menuService.updatePermission(authForm);
			session.setAttribute(Constant.SUCCESS_MSG, "Update permission sucess !!!");
		}catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.setAttribute(Constant.ERROR_MSG, "Update permission has error !!!");
		}
		return "redirect:/menu/list";
	}
	
	private void initSelectbox(Model model) {
		Map<Integer, String> mapRole = new HashMap<>();
		Map<Integer, String> mapMenu = new HashMap<>();
		List<Role> roleList = roleService.getRoleList(null, null);
		List<Menu> menuList = menuService.getListMenu(null, null);
		for (Role role : roleList) {
			mapRole.put(role.getId(), role.getRoleName());
		}
		for (Menu menu : menuList) {
			mapMenu.put(menu.getId(), menu.getUrl());
		}
		model.addAttribute("mapMenu", mapMenu);
		model.addAttribute("mapRole", mapRole);
	}
}
