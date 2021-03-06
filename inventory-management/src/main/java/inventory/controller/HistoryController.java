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
import inventory.model.History;
import inventory.model.Paging;
import inventory.service.HistoryService;
import inventory.service.ProductService;
import inventory.util.Constant;

@Controller
public class HistoryController {
		
		@Autowired
		private ProductService productService;
		
		@Autowired
		private HistoryService historyService;
		
		Logger log = Logger.getLogger(HistoryController.class);
		
		@GetMapping(value = {"history/list", "history/list/"}) 
		public String redirect() {
			return "redirect:/history/list/1";
		}
		
		@RequestMapping("history/list/{page}")
		public String list(Model model, @ModelAttribute("searchForm") History history, @PathVariable("page") int page) {
			Paging paging = new Paging(Constant.itemsPerPage);
			paging.setIndexPage(page);
			List<History> histories = historyService.getAll(history, paging);
			
			//Show list all category at front end to choose product belongs to its category
			List<Category> categories = productService.getAllCategory(null, null);
			Map<String, String> mapCategory = new HashMap<>();
			for (Category category : categories) {
				mapCategory.put(String.valueOf(category.getId()), category.getName());
			}
			
			//mapType
			Map<String, String> mapAction = new HashMap<>();
			mapAction.put(String.valueOf(Constant.ACTION_ADD), "Add");
			mapAction.put(String.valueOf(Constant.ACTION_EDIT), "Edit");
			mapAction.put(String.valueOf(Constant.ACTION_DELETE), "Delete");
			
			
			//mapType
			Map<String, String> mapType = new HashMap<>();
			mapType.put(String.valueOf(Constant.TYPE_ALL), "All");
			mapType.put(String.valueOf(Constant.TYPE_GOODS_RECEIPT), "Goods Receipt");
			mapType.put(String.valueOf(Constant.TYPE_GOODS_ISSUES), "Goods Issues");
			
			model.addAttribute("histories", histories);
			model.addAttribute("pageInfo", paging);
			model.addAttribute("mapCategory",mapCategory);
			model.addAttribute("mapAction", mapAction);
			model.addAttribute("mapType", mapType);
			return "history";
		}
}
