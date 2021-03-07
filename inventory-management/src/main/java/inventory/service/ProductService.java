package inventory.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import  org.apache.commons.lang.StringUtils;

import inventory.dao.CategoryDAO;
import inventory.model.Category;

@Service
public class ProductService {
	
	@Autowired
	private CategoryDAO<Category> categoryDAO;
	public final static Logger log = Logger.getLogger(ProductService.class);
	
	// If show list category by search property, show list search, else show all category
	public List<Category> getAllCategory(Category category) {
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
				queryStr.append(" and model.name = :name");
				mapParams.put("name", category.getName());
			}
		}
		return categoryDAO.findAll(queryStr.toString(), mapParams);
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
	
}
