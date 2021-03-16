package inventory.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import inventory.model.Category;
import inventory.model.Paging;
import inventory.service.ProductService;
import inventory.util.Constant;
import inventory.validate.CategoryValidator;

@Controller
public class CategoryController {
	
	@Autowired
	private ProductService productService; 
	
	@Autowired
	private CategoryValidator categoryValidator;
	
	static final Logger log = Logger.getLogger(CategoryController.class);
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		if(binder.getTarget() == null) {
			return; 
		}
		// Convert Create Date from String to TimeStamp to insert Category to db
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
		
		if(binder.getTarget().getClass()== Category.class) {
			binder.setValidator(categoryValidator);
		}
	}
	
	//  Handle if user set url /category/list => go to first page
	@RequestMapping(value= {"/category/list","/category/list/"})
	public String redirect() {
		return "redirect:/category/list/1";
	}
	
	/*Show List Cateogry (for show list and search action), and also paging
	 * @Model transfer data
	 * @HttpSession store message handle to show on frontend
	 * @ModelAttribute get searchForm to show list result
	 * @PathVariable for paging list result 
	 * */
	@RequestMapping("/category/list/{page}")
	public String showCategoryList(Model model, HttpSession session, @ModelAttribute("searchForm") Category category, @PathVariable("page") int page) {
		// Init paging
		Paging paging = new Paging(Constant.itemsPerPage);
		paging.setIndexPage(page);
		
		List<Category> categories = productService.getAllCategory(category, paging);
		// Check session notification is success or error than asign into model to show notify on frontend
		if (session.getAttribute(Constant.SUCCESS_MSG) != null) {
			model.addAttribute(Constant.SUCCESS_MSG, session.getAttribute(Constant.SUCCESS_MSG));
			session.removeAttribute(Constant.SUCCESS_MSG);
		}
		if (session.getAttribute(Constant.ERROR_MSG) != null) {
			model.addAttribute(Constant.ERROR_MSG, session.getAttribute(Constant.ERROR_MSG));
			session.removeAttribute(Constant.ERROR_MSG);
		}
		model.addAttribute("pageInfo", paging);
		model.addAttribute("categories", categories);
		return "category-list";	
	}
	
	// Show form add for user input
	@GetMapping("/category/add")
	public String add(Model model) {
		log.info("Add new Category");
		// Generate title page dynamic
		model.addAttribute("titlePage", "Add Category");
		// Generate form add category
		model.addAttribute("modelForm", new Category());
		// If in view mode => cannot input value
		model.addAttribute("viewOnly", false);
		return "category-action";
	}
	
	@GetMapping("/category/edit/{id}")
	public String edit(Model model , @PathVariable("id") int id) {
		log.info("Edit category with id=" + id);
		Category category = productService.findByIdCategory(id);
		// if category valid in db
		if(category!=null) {
			model.addAttribute("titlePage", "Edit Category");
			model.addAttribute("modelForm", category);
			model.addAttribute("viewOnly", false);
			return "category-action";
		}
		// if not valid => go back to category/list
		return "redirect:/category/list";
	}
	
	@GetMapping("/category/view/{id}")
	public String view(Model model , @PathVariable("id") int id) {
		log.info("View category with id="+id);
		Category category = productService.findByIdCategory(id);
		// if category valid in db
		if(category!=null) {
			model.addAttribute("titlePage", "View Category");
			model.addAttribute("modelForm", category);
			model.addAttribute("viewOnly", true);
			return "category-action";
		}
		// if not valid => go back to category/list
		return "redirect:/category/list";
	}
	
	@GetMapping("/category/delete/{id}")
	public String delete(Model model , @PathVariable("id") int id, HttpSession session) {
		log.info("Delete category with id="+id);
		Category category = productService.findByIdCategory(id);
		// if category valid in db
		if(category!=null) {
			try {
				productService.deleteCategory(category);
				session.setAttribute(Constant.SUCCESS_MSG, "Delete success !!!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error(e.getMessage());
				session.setAttribute(Constant.ERROR_MSG, "Delete has error !!!");
			}
		}
		// if not valid => go back to category/list
		return "redirect:/category/list";
	}
	
	/*
	 * Get data value from modelForm, detect action is insert or update then action => return list category
	 * @ModelAttribute get user input form 
	 * @BindingResult get result return from db
	 */
	@PostMapping("/category/save")
	public String save(Model model,@ModelAttribute("modelForm") @Validated Category category,BindingResult result, HttpSession session) {
		// If action has errors, stay at page
		if(result.hasErrors()) {
			if(category.getId()!=null) {
				model.addAttribute("titlePage", "Edit Category");
			}else {
				model.addAttribute("titlePage", "Add Category");
			}
			
			model.addAttribute("modelForm", category);
			model.addAttribute("viewOnly", false);
			
			return "category-action";
		}
		// If no error, detect action is insert or update
		// Insert has no id, update has id
		if(category.getId()!=null && category.getId()!=0) {
			try {
				productService.updateCategory(category);
				session.setAttribute(Constant.SUCCESS_MSG, "Update success !!!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error(e.getMessage());
				session.setAttribute(Constant.ERROR_MSG, "Update has error !!!");
			}
		}else {
			try {
				productService.saveCategory(category);
				session.setAttribute(Constant.SUCCESS_MSG, "Insert success !!!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error(e.getMessage());
				session.setAttribute(Constant.ERROR_MSG, "Insert has error !!!");
			}
		}
		// refresh category list after save
		return "redirect:/category/list";
	}
	
}
