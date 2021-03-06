package inventory.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import  org.apache.commons.lang.StringUtils;

import inventory.dao.CategoryDAO;
import inventory.dao.ProductInfoDAO;
import inventory.model.Category;
import inventory.model.Paging;
import inventory.model.ProductInfo;
import inventory.util.ConfigLoader;

@Service
public class ProductService {
	
	@Autowired
	private CategoryDAO<Category> categoryDAO;
	
	@Autowired
	private ProductInfoDAO<ProductInfo> productInfoDAO;
	
	public final static Logger log = Logger.getLogger(ProductService.class);
	
	/* ============================================================ CATEGORY SERVICE =================================================================*/
	// If show list category by search property, show list search, else show all category
	public List<Category> getAllCategory(Category category, Paging paging) {
		log.info("Show all category");
		/* Create query String
		 * @StringBuilder create query string
		 * @Map<String, Object> map parameter to query
		*/
		StringBuilder queryStr = new StringBuilder();
		Map<String, Object> mapParams = new HashMap<>();
		if (category != null ) {
			if (category.getId() != null && category.getId() != 0) {
				queryStr.append(" and model.id = :id");
				mapParams.put("id", category.getId());
			}
			if (category.getCode() != null && !StringUtils.isEmpty(category.getCode())) {
				queryStr.append(" and model.code = :code");
				mapParams.put("code", category.getCode());
			}
			if (category.getName() != null && !StringUtils.isEmpty(category.getName())) {
				queryStr.append(" and model.name like :name");
				mapParams.put("name", "%" + category.getName() + "%");
			}
		}
		return categoryDAO.findAll(queryStr.toString(), mapParams, paging);
	}
	
	public Category findByIdCategory(int id) {
		log.info("Find category by id: " + id);
		return categoryDAO.findById(Category.class, id);
	}
	
	public void saveCategory(Category category) throws Exception{
		log.info("Insert catergory: " + category.toString());
		// Set ActiveFlag, Create/Update Date
		category.setActiveFlag(1);
		category.setCreateDate(new Date());
		category.setUpdateDate(new Date());
		categoryDAO.save(category);
	}
	
	public void updateCategory(Category category) throws Exception{
		log.info("Update catergory: " + category.toString());
		// Set Update Date
		category.setUpdateDate(new Date());
		categoryDAO.update(category);
	}
	// Not delete physical => just set active flag = 0
	public void deleteCategory(Category category) throws Exception{
		category.setActiveFlag(0);
		// Set Update Date
		category.setUpdateDate(new Date());
		log.info("Delete category: " + category.toString());
		categoryDAO.update(category);
	}
	
	public List<Category> findCategory(String property, Object value) {
		log.info("============== Find by property start ====================");
		log.info("property = " + property + "value = " + value);
		return categoryDAO.findByProperty(property, value);
	}
	
	
	/*===================================================================== PRODUCT INFO SERVICE ====================================================================*/
	// If show list ProductInfo by search property, show list search, else show all ProductInfo
	public List<ProductInfo> getAllProductInfo(ProductInfo productInfo, Paging paging) {
		log.info("Show all ProductInfo");
		/* Create query String
		 * @StringBuilder create query string
		 * @Map<String, Object> map parameter to query
		*/
		StringBuilder queryStr = new StringBuilder();
		Map<String, Object> mapParams = new HashMap<>();
		if (productInfo != null ) {
			if (productInfo.getId() != null && productInfo.getId() != 0) {
				queryStr.append(" and model.id = :id");
				mapParams.put("id", productInfo.getId());
			}
			if (productInfo.getCode() != null && !StringUtils.isEmpty(productInfo.getCode())) {
				queryStr.append(" and model.code = :code");
				mapParams.put("code", productInfo.getCode());
			}
			if (productInfo.getName() != null && !StringUtils.isEmpty(productInfo.getName())) {
				queryStr.append(" and model.name like :name");
				mapParams.put("name", "%" + productInfo.getName() + "%");
			}
			if (productInfo.getCateId() != null &&  productInfo.getCateId() != 0) {
				queryStr.append(" and model.category.id = :cateId");
				mapParams.put("cateId", productInfo.getCateId());
			}
		}
		return productInfoDAO.findAll(queryStr.toString(), mapParams, paging);
	}
	
	public ProductInfo findByIdProductInfo(int id) {
		log.info("Find ProductInfo by id: " + id);
		return productInfoDAO.findById(ProductInfo.class, id);
	}
	
	public void saveProductInfo(ProductInfo productInfo) throws Exception{
		log.info("Insert ProductInfo: " + productInfo.toString());
		// Set ActiveFlag, Create/Update Date
		productInfo.setActiveFlag(1);
		productInfo.setCreateDate(new Date());
		productInfo.setUpdateDate(new Date());
		// Create file image name
		String fileName =  System.currentTimeMillis() + "_" + productInfo.getMultipartFile().getOriginalFilename();
		// add new image file upload
		processUploadFile(productInfo.getMultipartFile(), fileName);
		// Store image upload by url file name
		productInfo.setImgUrl("/upload/" + fileName);
		productInfoDAO.save(productInfo);
	}
	
	public void updateProductInfo(ProductInfo productInfo) throws Exception{
		log.info("Update ProductInfo: " + productInfo.toString());
		// if fileName not Empty
		if(!productInfo.getMultipartFile().getOriginalFilename().isEmpty()) {
			// Create file image name
			String fileName =  System.currentTimeMillis() + "_" + productInfo.getMultipartFile().getOriginalFilename();
			// update new image file upload
			processUploadFile(productInfo.getMultipartFile(), fileName);
			productInfo.setImgUrl("/upload/" + fileName);
		}
		// Set Update Date
		productInfo.setUpdateDate(new Date());
		productInfoDAO.update(productInfo);
	}
	// Not delete physical => just set active flag = 0
	public void deleteProductInfo(ProductInfo productInfo) throws Exception{
		productInfo.setActiveFlag(0);
		// Set Update Date
		productInfo.setUpdateDate(new Date());
		log.info("Delete ProductInfo: " + productInfo.toString());
		productInfoDAO.update(productInfo);
	}
	
	public List<ProductInfo> findProductInfo(String property, Object value) {
		log.info("============== Find by property start ====================");
		log.info("property = " + property + "value = " + value);
		return productInfoDAO.findByProperty(property, value);
	}
	
	private void processUploadFile(MultipartFile multipartFile, String fileName) throws IllegalStateException, IOException {
		if (!multipartFile.getOriginalFilename().isEmpty()) {
			// Reading upload.location in config.properties => get value path
			File dir = new File(ConfigLoader.getInstance().getValue("upload.location"));
			// If path not exists => create new path 
			if (!dir.exists()) {
				dir.mkdirs();
			}
			// Create new file in path has fileName the same as file upload
			File file = new File(ConfigLoader.getInstance().getValue("upload.location"), fileName);
			//Copy data from file upload to file in system
			multipartFile.transferTo(file);
		}
	}
}
