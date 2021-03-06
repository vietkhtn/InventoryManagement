package inventory.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inventory.dao.ProductInStockDAO;
import inventory.model.ProductInStock;
import inventory.model.ProductInfo;
import inventory.model.Invoice;
import inventory.model.Paging;

@Service
public class ProductInStockService {
	
	@Autowired
	private ProductInStockDAO<ProductInStock> productInStockDAO;
	
	public final static Logger log = Logger.getLogger(ProductInStockService.class);
	
	// If show list productInStock by search property, show list search, else show all productInStock
	public List<ProductInStock> getAll(ProductInStock productInStock, Paging paging) {
		log.info("Show all productInStock");
		/* Create query String
		 * @StringBuilder create query string
		 * @Map<String, Object> map parameter to query
		*/
		StringBuilder queryStr = new StringBuilder();
		Map<String, Object> mapParams = new HashMap<>();
		if (productInStock != null && productInStock.getProductInfo() != null) {
			
			if (productInStock.getProductInfo().getCategory().getId() != null && productInStock.getProductInfo().getCategory().getId() != 0) {
				queryStr.append(" and model.productInfo.category.id = :cateId");
				mapParams.put("cateId", productInStock.getProductInfo().getCategory().getId());
			}
			
			if (productInStock.getProductInfo().getCode() != null && !StringUtils.isEmpty(productInStock.getProductInfo().getCode())) {
				queryStr.append(" and model.productInfo.code = :code");
				mapParams.put("code", productInStock.getProductInfo().getCode());
			}
			
			if (productInStock.getProductInfo().getName() != null && !StringUtils.isEmpty(productInStock.getProductInfo().getName())) {
				queryStr.append(" and model.productInfo.name like :name");
				mapParams.put("name", "%" + productInStock.getProductInfo().getName() + "%");
			}
		}
		return productInStockDAO.findAll(queryStr.toString(), mapParams, paging);
	}
	
	// Insert or Update product in stock depend on invoice created
	public void saveOrUpdate(Invoice invoice) throws Exception{
		log.info("product in stock");
		// Check invoice has product or not
		if (invoice.getProductInfo() != null) {
			int id = invoice.getProductInfo().getId();
			// Find in stock has product code like product in invoice
			List<ProductInStock>  products= productInStockDAO.findByProperty("productInfo.id", id);
			ProductInStock product = null;
			// if product exists stock => update product qty, price, update date
			if (products != null && !products.isEmpty()) {
				product = products.get(0);
				log.info("update qty=" + invoice.getQty() + " and price = " + invoice.getPrice());
				// If invoiceType == 2 => goods issue => product in stock - invoice quantity
				if (invoice.getType() == 2) {
					product.setQty(product.getQty() -  invoice.getQty());
				}
				else {
					product.setQty(product.getQty() +  invoice.getQty());
				}
				// Check invoice type is receipt or issues (if receipt => update price/ if issues => not update price)
				if (invoice.getType() == 1) {					
					product.setPrice(invoice.getPrice());
				}
				product.setUpdateDate(new Date());
				productInStockDAO.update(product);
			}
			//if product not exists && invoice type is receipt => create new product in stock
			else if (invoice.getType() == 1){
				log.info("insert to stock qty = " + invoice.getQty() + " and price =" + invoice.getPrice());
				product = new ProductInStock();
				ProductInfo productInfo = new ProductInfo();
				productInfo.setId(invoice.getProductInfo().getId());
				product.setProductInfo(productInfo);
				product.setActiveFlag(1);
				product.setQty(invoice.getQty());
				product.setPrice(invoice.getPrice());
				product.setCreateDate(new Date());
				product.setUpdateDate(new Date());
				productInStockDAO.save(product);
			}
		}
	}

}
