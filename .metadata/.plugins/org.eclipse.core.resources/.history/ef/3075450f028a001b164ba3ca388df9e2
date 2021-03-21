package inventory.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inventory.dao.RoleDAO;

import inventory.model.Paging;
import inventory.model.Role;


@Service
public class RoleService {
	final static Logger log = Logger.getLogger(RoleService.class);
	
	@Autowired
	private RoleDAO<Role> roleDAO;

	public List<Role> getRoleList(Role role , Paging paging){
		StringBuilder queryStr = new StringBuilder();
		Map<String, Object> mapParams = new HashMap<>();
		return roleDAO.findAll(queryStr.toString(), mapParams,paging);
	}

}
