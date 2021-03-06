package inventory.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inventory.dao.AuthDAO;
import inventory.dao.MenuDAO;
import inventory.model.Auth;
import inventory.model.AuthForm;
import inventory.model.Menu;
import inventory.model.Paging;
import inventory.model.Role;

@Service
public class MenuService {

	@Autowired 
	private MenuDAO<Menu> menuDAO;
	
	@Autowired 
	private AuthDAO<Auth> authDAO;
	
	Logger log = Logger.getLogger(MenuService.class);
	
	public List<Menu> getListMenu(Menu menu, Paging paging){
		log.info("Show all menu");
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" or model.activeFlag = 0");
		Map<String, Object> mapParams = new HashMap<>();
		if(menu != null) {
			if (menu.getId() != null && menu.getId() != 0) {
				queryStr.append(" and model.id = :id");
				mapParams.put("id", menu.getId());
			}
			
			if (!StringUtils.isEmpty(menu.getUrl())) {
				queryStr.append(" and model.url = :url");
				mapParams.put("url", menu.getUrl());
			}
		}
		return menuDAO.findAll(queryStr.toString(), mapParams,paging);
	}
	
	public void changeStatus(Integer id) throws Exception{
		Menu menu = menuDAO.findById(Menu.class, id);
		if (menu != null) {
			menu.setActiveFlag(menu.getActiveFlag() == 1 ? 0:1);
			menuDAO.update(menu);
		}
	}
	
	public void updatePermission (AuthForm authForm) throws Exception {
		int roleId = authForm.getRoleId();
		int menuId = authForm.getMenuId();
		int permission = authForm.getPermission();
		Auth auth = authDAO.find(roleId, menuId);
		// if authorize is exist in db => update acticeFlag
		if (auth != null) {
			auth.setPermission(permission);
			auth.setUpdateDate(new Date());
			authDAO.update(auth);
		}else { // if authorize is not exist in db => insert new auth && activeFlag = 1
			if (permission == 1) {
				auth = new Auth();
				auth.setActiveFlag(1);
				Role role = new Role();
				role.setId(roleId);
				Menu menu = new Menu();
				menu.setId(menuId);
				auth.setRole(role);
				auth.setMenu(menu);
				auth.setPermission(1);
				auth.setCreateDate(new Date());
				auth.setUpdateDate(new Date());
				authDAO.save(auth);
			}
		}
	}
}
