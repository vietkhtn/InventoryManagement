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

import inventory.model.ProductInfo;
import inventory.model.Category;
import inventory.model.Paging;
import inventory.service.ProductService;
import inventory.util.Constant;
import inventory.validate.ProductInfoValidator;

@Controller
public class ProductInfoController {
	
	@Autowired
	private ProductService productService; 
	
	@Autowired
	private ProductInfoValidator productInfoValidator;
	
	static final Logger log = Logger.getLogger(ProductInfoController.class);
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		if(binder.getTarget() == null) {
			return; 
		}
		// Convert Create Date from String to TimeStamp to insert ProductInfo to db
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
		
		if(binder.getTarget().getClass()== ProductInfo.class) {
			binder.setValidator(productInfoValidator);
		}
	}
	
	//  Handle if user set url /productInfo/list => go to first page
	@RequestMapping(value= {"/product-info/list","/product-info/list/"})
	public String redirect() {
		return "redirect:/product-info/list/1";
	}
	
	/*Show List ProductInfo (for show list and search action), and also paging
	 * @Model transfer data
	 * @HttpSession store message handle to show on front-end
	 * @ModelAttribute get searchForm to show list result
	 * @PathVariable for paging list result 
	 * */
	@RequestMapping("/product-info/list/{page}")
	public String showProductInfoList(Model model, HttpSession session, @ModelAttribute("searchForm") ProductInfo productInfo, @PathVariable("page") int page) {
		// Init paging
		Paging paging = new Paging(Constant.itemsPerPage);
		paging.setIndexPage(page);
		
		List<ProductInfo> products = productService.getAllProductInfo(productInfo, paging);
		// Check session notification is success or error than asign into model to show notify on frontend
		if (session.getAttribute(Constant.SUCCESS_MSG) != null) {
			model.addAttribute(Constant.SUCCESS_MSG, session.getAttribute(Constant.SUCCESS_MSG));
			session.removeAttribute(Constant.SUCCESS_MSG);
		}
		if (session.getAttribute(Constant.ERROR_MSG) != null) {
			model.addAttribute(Constant.ERROR_MSG, session.getAttribute(Constant.ERROR_MSG));
			session.removeAttribute(Constant.ERROR_MSG);
		}
		//Show list all category at front end to choose product belongs to its category
		List<Category> categories = productService.getAllCategory(null, null);
		Map<String, String> mapCategory = new HashMap<>();
		for (Category category : categories) {
			mapCategory.put(String.valueOf(category.getId()), category.getName());
		}
		//store mapCategory in model
		model.addAttribute("mapCategory",mapCategory);		
		model.addAttribute("pageInfo", paging);
		model.addAttribute("products", products);
		return "productInfo-list";	
	}
	
	// Show form add for user input
	@GetMapping("/product-info/add")
	public String add(Model model) {
		log.info("Add new ProductInfo");
		// Generate title page dynamic
		model.addAttribute("titlePage", "Add ProductInfo");
		// Generate form add productInfo
		model.addAttribute("modelForm", new ProductInfo());
		//Show list all category at front end to choose product belongs to its category
		List<Category> categories = productService.getAllCategory(null, null);
		Map<String, String> mapCategory = new HashMap<>();
		for (Category category : categories) {
			mapCategory.put(String.valueOf(category.getId()), category.getName());
		}
		//store mapCategory in model
		model.addAttribute("mapCategory",mapCategory);
		// If in view mode => cannot input value
		model.addAttribute("viewOnly", false);
		model.addAttribute("viewImage", false);
		return "productInfo-action";
	}
	
	@GetMapping("/product-info/edit/{id}")
	public String edit(Model model , @PathVariable("id") int id) {
		log.info("Edit productInfo with id=" + id);
		ProductInfo productInfo = productService.findByIdProductInfo(id);
		// if productInfo valid in db
		if(productInfo!=null) {
			//Show list all category at front end to choose product belongs to its category
			List<Category> categories = productService.getAllCategory(null, null);
			Map<String, String> mapCategory = new HashMap<>();
			for (Category category : categories) {
				mapCategory.put(String.valueOf(category.getId()), category.getName());
			}
			// Set Category Id of product
			productInfo.setCateId(productInfo.getCategory().getId());
			
			//store mapCategory in model
			model.addAttribute("mapCategory",mapCategory);
			
			model.addAttribute("titlePage", "Edit ProductInfo");
			model.addAttribute("modelForm", productInfo);
			model.addAttribute("viewOnly", false);
			model.addAttribute("viewImage", true);
			return "productInfo-action";
		}
		// if not valid => go back to productInfo/list
		return "redirect:/product-info/list";
	}
	
	@GetMapping("/product-info/view/{id}")
	public String view(Model model , @PathVariable("id") int id) {
		log.info("View productInfo with id="+id);
		ProductInfo productInfo = productService.findByIdProductInfo(id);
		// if productInfo valid in db
		if(productInfo!=null) {
			model.addAttribute("titlePage", "View ProductInfo");
			model.addAttribute("modelForm", productInfo);
			model.addAttribute("viewOnly", true);
			model.addAttribute("viewImage", true);
			return "productInfo-action";
		}
		// if not valid => go back to productInfo/list
		return "redirect:/product-info/list";
	}
	
	@GetMapping("/product-info/delete/{id}")
	public String delete(Model model , @PathVariable("id") int id, HttpSession session) {
		log.info("Delete productInfo with id=" + id);
		ProductInfo productInfo = productService.findByIdProductInfo(id);
		// if productInfo valid in db
		if(productInfo!=null) {
			try {
				productService.deleteProductInfo(productInfo);
				session.setAttribute(Constant.SUCCESS_MSG, "Delete success !!!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error(e.getMessage());
				session.setAttribute(Constant.ERROR_MSG, "Delete has error !!!");
			}
		}
		// if not valid => go back to productInfo/list
		return "redirect:/product-info/list";
	}
	
	/*
	 * Get data value from modelForm, detect action is insert or update then action => return list productInfo
	 * @ModelAttribute get user input form 
	 * @BindingResult get result return from db
	 */
	@PostMapping("/product-info/save")
	public String save(Model model,@ModelAttribute("modelForm") @Validated ProductInfo productInfo, BindingResult result, HttpSession session) {
		// If action has errors, stay at page
		if(result.hasErrors()) {
			if(productInfo.getId()!=null) {
				model.addAttribute("titlePage", "Edit ProductInfo");
			}else {
				model.addAttribute("titlePage", "Add ProductInfo");
			}
			//Show list all category at front end to choose product belongs to its category
			List<Category> categories = productService.getAllCategory(null, null);
			Map<String, String> mapCategory = new HashMap<>();
			for (Category category : categories) {
				mapCategory.put(String.valueOf(category.getId()), category.getName());
			}
			//store mapCategory in model
			model.addAttribute("mapCategory", mapCategory);
			
			model.addAttribute("modelForm", productInfo);
			model.addAttribute("viewOnly", false);
			
			return "productInfo-action";
		}
		// If no error, detect action is insert or update
		// Insert has no id, update has id
		Category category = new Category();
		category.setId(productInfo.getCateId());
		productInfo.setCategory(category);
		if(productInfo.getId()!=null && productInfo.getId()!=0) {
			try {
				productService.updateProductInfo(productInfo);
				session.setAttribute(Constant.SUCCESS_MSG, "Update success !!!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error(e.getMessage());
				session.setAttribute(Constant.ERROR_MSG, "Update has error !!!");
			}
		}else {
			try {
				productService.saveProductInfo(productInfo);
				session.setAttribute(Constant.SUCCESS_MSG, "Insert success !!!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error(e.getMessage());
				session.setAttribute(Constant.ERROR_MSG, "Insert has error !!!");
			}
		}
		// refresh productInfo list after save
		return "redirect:/product-info/list";
	}
	
}
