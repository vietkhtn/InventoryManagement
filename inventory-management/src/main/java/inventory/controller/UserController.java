package inventory.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import inventory.model.Paging;
import inventory.model.Role;
import inventory.model.UserRole;
import inventory.model.Users;
import inventory.service.RoleService;
import inventory.service.UserService;
import inventory.util.Constant;
import inventory.validate.UserValidator;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService; 
	
	@Autowired
	private RoleService roleService; 
	
	@Autowired
	private UserValidator userValidator;
	
	static final Logger log = Logger.getLogger(UserController.class);
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		if(binder.getTarget() == null) {
			return; 
		}
		// Convert Create Date from String to TimeStamp to insert Category to db
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
		
		if(binder.getTarget().getClass()== Users.class) {
			binder.setValidator(userValidator);
		}
	}
	
	//  Handle if user set url /user/list => go to first page
	@RequestMapping(value= {"/user/list","/user/list/"})
	public String redirect() {
		return "redirect:/user/list/1";
	}
	
	/*Show List Cateogry (for show list and search action), and also paging
	 * @Model transfer data
	 * @HttpSession store message handle to show on frontend
	 * @ModelAttribute get searchForm to show list result
	 * @PathVariable for paging list result 
	 * */
	@RequestMapping("/user/list/{page}")
	public String showCategoryList(Model model, HttpSession session, @ModelAttribute("searchForm") Users user, @PathVariable("page") int page) {
		// Init paging
		Paging paging = new Paging(Constant.itemsPerPage);
		paging.setIndexPage(page);
		
		List<Users> users = userService.getAllUser(user, paging);
		// Check session notification is success or error than asign into model to show notify on frontend
		if (session.getAttribute(Constant.SUCCESS_MSG) != null) {
			model.addAttribute(Constant.SUCCESS_MSG, session.getAttribute(Constant.SUCCESS_MSG));
			session.removeAttribute(Constant.SUCCESS_MSG);
		}
		if (session.getAttribute(Constant.ERROR_MSG) != null) {
			model.addAttribute(Constant.ERROR_MSG, session.getAttribute(Constant.ERROR_MSG));
			session.removeAttribute(Constant.ERROR_MSG);
		}
		
		// Get role of user in UserRole
		for (Users user1 : users) {
			// Set Role Id of user
			UserRole userRole = (UserRole) user1.getUserRoles().iterator().next();
			// Set Role Id
			user1.setRoleId(userRole.getRole().getId());
		}
		
		model.addAttribute("pageInfo", paging);
		model.addAttribute("users", users);
		return "user-list";	
	}
	
	// Show form add for user input
	@GetMapping("/user/add")
	public String add(Model model) {
		log.info("Add New User");
		model.addAttribute("mapRole", initMapRole());
		// Generate title page dynamic
		model.addAttribute("titlePage", "Add User");
		// Generate form add user
		model.addAttribute("modelForm", new Users());
		// If in view mode => cannot input value
		model.addAttribute("viewOnly", false);
		return "user-action";
	}
	
	@GetMapping("/user/edit/{id}")
	public String edit(Model model , @PathVariable("id") int id) {
		log.info("Edit user with id=" + id);
		Users user = userService.findUserById(id);
		// if user valid in db
		if(user != null) {
			UserRole userRole = (UserRole) user.getUserRoles().iterator().next();
			
			// Set Role Id
			user.setRoleId(userRole.getRole().getId());
			
			model.addAttribute("mapRole", initMapRole());
			model.addAttribute("titlePage", "Edit User");
			model.addAttribute("modelForm", user);
			model.addAttribute("viewOnly", false);
			return "user-action";
		}
		// if not valid => go back to user/list
		return "redirect:/user/list";
	}
	
	@GetMapping("/user/view/{id}")
	public String view(Model model , @PathVariable("id") int id) {
		log.info("View user with id="+id);
		Users user = userService.findUserById(id);
		// if user valid in db
		if(user!=null) {
			UserRole userRole = (UserRole) user.getUserRoles().iterator().next();
			// Set Role Id
			user.setRoleId(userRole.getRole().getId());
			
			model.addAttribute("mapRole", initMapRole());
			model.addAttribute("titlePage", "View User");
			model.addAttribute("modelForm", user);
			model.addAttribute("viewOnly", true);
			return "user-action";
		}
		// if not valid => go back to user/list
		return "redirect:/user/list";
	}
	
	@GetMapping("/user/delete/{id}")
	public String delete(Model model , @PathVariable("id") int id, HttpSession session) {
		log.info("Delete user with id = " + id);
		Users user = userService.findUserById(id);
		// if user valid in db
		if(user!=null) {
			try {
				userService.deleteUser(user);
				session.setAttribute(Constant.SUCCESS_MSG, "Delete success !!!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error(e.getMessage());
				session.setAttribute(Constant.ERROR_MSG, "Delete has error !!!");
			}
		}
		// if not valid => go back to user/list
		return "redirect:/user/list";
	}
	
	/*
	 * Get data value from modelForm, detect action is insert or update then action => return list user
	 * @ModelAttribute get user input form 
	 * @BindingResult get result return from db
	 */
	@PostMapping("/user/save")
	public String save(Model model,@ModelAttribute("modelForm") @Validated Users user,BindingResult result, HttpSession session) {
		// If action has errors, stay at page
		if(result.hasErrors()) {
			if(user.getId()!=null) {
				model.addAttribute("titlePage", "Edit User");
				model.addAttribute("editMode", true);
			}else {
				model.addAttribute("titlePage", "Add User");
			}
			model.addAttribute("mapRole", initMapRole());
			model.addAttribute("modelForm", user);
			model.addAttribute("viewOnly", false);
			
			return "user-action";
		}
		// If no error, detect action is insert or update
		// Insert has no id, update has id
		if(user.getId()!=null && user.getId()!=0) {
			try {
				userService.updateUser(user);
				session.setAttribute(Constant.SUCCESS_MSG, "Update success !!!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error(e.getMessage());
				session.setAttribute(Constant.ERROR_MSG, "Update has error !!!");
			}
		}else {
			try {
				userService.saveUser(user);
				session.setAttribute(Constant.SUCCESS_MSG, "Insert success !!!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error(e.getMessage());
				session.setAttribute(Constant.ERROR_MSG, "Insert has error !!!");
			}
		}
		// refresh user list after save
		return "redirect:/user/list";
	}
	
	// Show list product in <select> tag for invoice
	private Map<String,String> initMapRole(){
		List<Role> roles = roleService.getRoleList(null, null);
		// Create List Roles for select tag
		Map<String, String> mapRole = new HashMap<>();
		for (Role role : roles) {
			mapRole.put(String.valueOf(role.getId()), role.getRoleName());
		}
		return mapRole;
	}
	
}
