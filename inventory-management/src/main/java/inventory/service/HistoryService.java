package inventory.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inventory.dao.HistoryDAO;
import inventory.model.History;
import inventory.model.Invoice;
import inventory.model.Paging;

@Service
public class HistoryService {
	
	@Autowired
	private HistoryDAO<History> historyDAO;
	
	public final static Logger log = Logger.getLogger(HistoryService.class);
	
	// If show list history by search property, show list search, else show all history
	public List<History> getAll(History history, Paging paging) {
		log.info("Show all history");
		/* Create query String
		 * @StringBuilder create query string
		 * @Map<String, Object> map parameter to query
		*/
		StringBuilder queryStr = new StringBuilder();
		Map<String, Object> mapParams = new HashMap<>();
		if (history != null) {
			if (history.getProductInfo() != null) {
				if (history.getProductInfo().getCategory().getId() != null && history.getProductInfo().getCategory().getId() != 0) {
					queryStr.append(" and model.productInfo.category.id = :cateId");
					mapParams.put("cateId", history.getProductInfo().getCategory().getId());
				}			
				if (!StringUtils.isEmpty(history.getProductInfo().getCode())) {
					queryStr.append(" and model.productInfo.code = :code");
					mapParams.put("code", history.getProductInfo().getCode());
				}			
				if (!StringUtils.isEmpty(history.getProductInfo().getName())) {
					queryStr.append(" and model.productInfo.name like :name");
					mapParams.put("name", "%" + history.getProductInfo().getName() + "%");
				}
			}			
			if (!StringUtils.isEmpty(history.getActionName())) {
				queryStr.append(" and model.actionName like :actionName");
				mapParams.put("actionName", "%" + history.getActionName() + "%");
			}
			if (history.getType() != 0) {
				queryStr.append(" and model.type = :type");
				mapParams.put("type", history.getType());
			}
		}
		return historyDAO.findAll(queryStr.toString(), mapParams, paging);
	}
	
	// Insert history depend on invoice created
	public void save(Invoice invoice, String action){
		log.info("Save history");
		History history = new History();
		history.setProductInfo(invoice.getProductInfo());
		history.setType(invoice.getType());
		history.setQty(invoice.getQty());
		history.setPrice(invoice.getPrice());
		history.setActiveFlag(1);
		history.setActionName(action);
		history.setCreateDate(new Date());
		history.setUpdateDate(new Date());
		historyDAO.save(history);
	}
}
