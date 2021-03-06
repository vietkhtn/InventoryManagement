package inventory.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import inventory.model.Category;
import inventory.model.Paging;
import inventory.model.ProductInStock;
import inventory.service.ProductInStockService;
import inventory.service.ProductService;
import inventory.util.Constant;

@Controller
public class ProductInStockController {

		@Autowired
		private ProductService productService;
	
		@Autowired
		private ProductInStockService productInStockService;
		
		Logger log = Logger.getLogger(ProductInStockController.class);
		
		@GetMapping(value = {"product-in-stock/list", "product-in-stock/list/"}) 
		public String redirect() {
			return "redirect:/product-in-stock/list/1";
		}
		
		@RequestMapping("product-in-stock/list/{page}")
		public String list(Model model, @ModelAttribute("searchForm") ProductInStock productInStock, @PathVariable("page") int page) {
			Paging paging = new Paging(Constant.itemsPerPage);
			paging.setIndexPage(page);
			List<ProductInStock> productInStocks = productInStockService.getAll(productInStock, paging);
			
			//Show list all category at front end to choose product belongs to its category
			List<Category> categories = productService.getAllCategory(null, null);
			Map<String, String> mapCategory = new HashMap<>();
			for (Category category : categories) {
				mapCategory.put(String.valueOf(category.getId()), category.getName());
			}
			//store mapCategory in model
			model.addAttribute("mapCategory",mapCategory);	
			model.addAttribute("products", productInStocks);
			model.addAttribute("pageInfo", paging);
			return "product-in-stock";
		}
}
