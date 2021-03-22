package inventory.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import inventory.model.Auth;
import inventory.model.Menu;
import inventory.model.Role;
import inventory.model.UserRole;
import inventory.model.Users;
import inventory.service.UserService;
import inventory.util.Constant;
import inventory.validate.LoginValidator;

@Controller
public class LoginController {
	@Autowired 
	private UserService userService;
	@Autowired
	private LoginValidator loginValidator;
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		if(binder.getTarget()==null) return;
		if(binder.getTarget().getClass() == Users.class) {
			binder.setValidator(loginValidator);
		}
	}
	// Init when fo to system => login page
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("loginForm", new Users());
		return "login/login";
	}
	
	/*Check user valid in database
	 *@Model transfer data from db to ui
	 *@ModelAttribute get user input form username/password
	 *@BindingResult return result from db
	 *@HttpSession store user info during login system
	 *Return link redirect to homepage and create function that user can access + store in session */
	@PostMapping("/processLogin")
	public String processLogin(Model model , @ModelAttribute("loginForm")@Validated Users users , BindingResult result , HttpSession session) {
		// If user not valid => stay at login page
		if(result.hasErrors()) {
			return "login/login";
		}
		// If user valid => Create menu function for user => store in session => go to home page
		/*1. Create menu function for user
		 *@User find user by username
		 *@UserRole get role of user
		 *@Role map with role in table Role
		 *List<Menu> menuList main functions control
		 *List<Menu> menuChildList sub functions in main functions 
		 */
		Users user  = userService.findByProperty("userName", users.getUserName()).get(0);
		UserRole userRole =(UserRole) user.getUserRoles().iterator().next();
		Role role = userRole.getRole();
		List<Menu> menuList = new ArrayList<>();
		List<Menu> menuChildList = new ArrayList<>();
		/* Get list menu function that role is authorized to access
		*@Auth get auth
		*@Menu get in menu table that role is authorized
		*/
		for(Object obj : role.getAuths()) {
			Auth auth = (Auth) obj;
			Menu menu = auth.getMenu();
			// Get list main functions - ParentId == 0
			if(menu.getParentId()==0 && menu.getOrderIndex()!=-1 && menu.getActiveFlag()==1 && auth.getPermission()==1 && auth.getActiveFlag()==1) {
				menu.setIdMenu(menu.getUrl().replace("/", "") + "Id"); 
				menuList.add(menu);
			}
			// Get list sub functions - ParentId != 0
			else if( menu.getParentId()!=0 && menu.getOrderIndex()!=-1 && menu.getActiveFlag()==1 && auth.getPermission()==1 && auth.getActiveFlag()==1) {
				menu.setIdMenu(menu.getUrl().replace("/", "") + "Id"); 
				menuChildList.add(menu);
			}
		}
		// Map sub functions into it's main function due to Parent_Id
		for(Menu menu : menuList) {
			List<Menu> childList = new ArrayList<>();
			for(Menu childMenu : menuChildList) {
				// If function in menuChildList has ParentId == function in menuList Order_Index => function belongs to main function
				if(childMenu.getParentId()== menu.getId()) {
					childList.add(childMenu);
				}
			}
			menu.setChild(childList);
		}
		// Sort menu due to Order_Index
		sortMenu(menuList);
		for(Menu menu : menuList) {
			sortMenu(menu.getChild());
		}
		/*2. Store in session*/
		session.setAttribute(Constant.MENU_SESSION, menuList);
		session.setAttribute(Constant.USER_INFO, user);
		/*Redirect to home page*/
		return "redirect:/index";
	}

	@GetMapping("/access-denied")
	public String accessDenied() {
		return "access-denied";
	}
	
	@GetMapping("/logout")
	public String logOut(HttpSession session) {
		session.removeAttribute(Constant.MENU_SESSION);
		session.removeAttribute(Constant.USER_INFO);
		return "redirect:/login";
	}
	
	public void sortMenu(List<Menu> menus) {
		Collections.sort(menus, new Comparator<Menu>() {
			@Override
			public int compare(Menu o1, Menu o2) {
				return o1.getOrderIndex() - o2.getOrderIndex();
			}
		});
	}
}
