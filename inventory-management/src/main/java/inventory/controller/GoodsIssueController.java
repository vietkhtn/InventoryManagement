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
import org.springframework.web.servlet.ModelAndView;

import inventory.model.Invoice;
import inventory.model.Paging;
import inventory.model.ProductInfo;
import inventory.service.InvoiceReport;
import inventory.service.InvoiceService;
import inventory.service.ProductService;
import inventory.util.Constant;
import inventory.validate.InvoiceValidator;

@Controller
public class GoodsIssueController {
	
	@Autowired
	private ProductService productService; 
	
	@Autowired
	private InvoiceService invoiceService; 
	
	@Autowired
	private InvoiceValidator invoiceValidator;
	
	static final Logger log = Logger.getLogger(GoodsIssueController.class);
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		if(binder.getTarget() == null) {
			return; 
		}
		// Convert Create Date from String to TimeStamp to insert Invoice to db
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
		
		if(binder.getTarget().getClass()== Invoice.class) {
			binder.setValidator(invoiceValidator);
		}
	}
	
	//  Handle if user set url /invoice/list => go to first page
	@RequestMapping(value= {"/goods-issue/list","/goods-issue/list/"})
	public String redirect() {
		return "redirect:/goods-issue/list/1";
	}
	
	/*Show List invoice issue (for show list and search action), and also paging
	 * @Model transfer data
	 * @HttpSession store message handle to show on frontend
	 * @ModelAttribute get searchForm to show list result
	 * @PathVariable for paging list result 
	 * */
	@RequestMapping("/goods-issue/list/{page}")
	public String showInvoiceList(Model model, HttpSession session, @ModelAttribute("searchForm") Invoice invoice, @PathVariable("page") int page) {
		// Init paging
		Paging paging = new Paging(Constant.itemsPerPage);
		paging.setIndexPage(page);
		//Set invoice type
		if (invoice == null) {
			invoice = new Invoice();
		}
		invoice.setType(Constant.TYPE_GOODS_ISSUES);
		
		List<Invoice> invoices = invoiceService.getList(invoice, paging);
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
		model.addAttribute("invoices", invoices);
		return "goods-issue-list";	
	}
	
	// Show form add for user input
	@GetMapping("/goods-issue/add")
	public String add(Model model) {
		log.info("Add new Invoice");
		// Generate title page dynamic
		model.addAttribute("titlePage", "Add Invoice");
		// Generate form add invoice
		model.addAttribute("modelForm", new Invoice());
		// If in view mode => cannot input value
		model.addAttribute("viewOnly", false);
		// Select product in invoice
		model.addAttribute("mapProduct", initMapProduct());
		return "goods-issue-action";
	}
	
	@GetMapping("/goods-issue/edit/{id}")
	public String edit(Model model , @PathVariable("id") int id) {
		log.info("Edit invoice with id=" + id);
		Invoice invoice = invoiceService.find("id",id).get(0);
		// if invoice valid in db
		if(invoice != null) {
			// Set Product Id of product
			invoice.setProductId(invoice.getProductInfo().getId());
			
			model.addAttribute("titlePage", "Edit Invoice");
			model.addAttribute("modelForm", invoice);
			model.addAttribute("viewOnly", false);
			model.addAttribute("mapProduct", initMapProduct());
			return "goods-issue-action";
		}
		// if not valid => go back to invoice/list
		return "redirect:/goods-issue/list";
	}
	
	@GetMapping("/goods-issue/view/{id}")
	public String view(Model model , @PathVariable("id") int id) {
		log.info("View invoice with id="+id);
		Invoice invoice = invoiceService.find("id",id).get(0);
		// if invoice valid in db
		if(invoice != null) {
			model.addAttribute("titlePage", "View Invoice");
			model.addAttribute("modelForm", invoice);
			model.addAttribute("viewOnly", true);
			return "goods-issue-action";
		}
		// if not valid => go back to invoice/list
		return "redirect:/goods-issue/list";
	}
	
	/*
	 * Get data value from modelForm, detect action is insert or update then action => return list invoice
	 * @ModelAttribute get user input form 
	 * @BindingResult get result return from db
	 */
	@PostMapping("/goods-issue/save")
	public String save(Model model,@ModelAttribute("modelForm") @Validated Invoice invoice,BindingResult result, HttpSession session) {
		// If action has errors, stay at page
		if(result.hasErrors()) {
			if(invoice.getId()!=null) {
				model.addAttribute("titlePage", "Edit Invoice");
			}else {
				model.addAttribute("titlePage", "Add Invoice");
			}
			model.addAttribute("mapProduct", initMapProduct());
			model.addAttribute("modelForm", invoice);
			model.addAttribute("viewOnly", false);
			
			return "goods-issue-action";
		}
		// If no error, detect action is insert or update
		// Insert has no id, update has id
		invoice.setType(Constant.TYPE_GOODS_ISSUES);
		if(invoice.getId()!=null && invoice.getId()!=0) {
			try {
				invoiceService.update(invoice);
				session.setAttribute(Constant.SUCCESS_MSG, "Update success !!!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error(e.getMessage());
				session.setAttribute(Constant.ERROR_MSG, "Update has error !!!");
			}
		}else {
			try {
				invoiceService.save(invoice);
				session.setAttribute(Constant.SUCCESS_MSG, "Insert success !!!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error(e.getMessage());
				session.setAttribute(Constant.ERROR_MSG, "Insert has error !!!");
			}
		}
		// refresh invoice list after save
		return "redirect:/goods-issue/list";
	}
	
	@GetMapping("/goods-issue/export")
	public ModelAndView exportReport() {
		ModelAndView modelAndView = new ModelAndView();
		Invoice invoice = new Invoice();
		invoice.setType(Constant.TYPE_GOODS_ISSUES);
		List<Invoice> invoices = invoiceService.getList(invoice, null);
		modelAndView.addObject(Constant.KEY_GOODS_ISSUE_REPORT, invoices);
		modelAndView.setView(new InvoiceReport());
		return modelAndView;
	}
	
	// Show list product in <select> tag for invoice
	private Map<String,String> initMapProduct(){
		List<ProductInfo> productInfos = productService.getAllProductInfo(null, null);
		Map<String, String> mapProduct = new HashMap<>();
		for(ProductInfo productInfo : productInfos) {
			mapProduct.put(productInfo.getId().toString(),productInfo.getName());
		}
		return mapProduct;
	}
}
