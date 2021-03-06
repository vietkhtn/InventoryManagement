package inventory.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inventory.dao.UserDAO;
import inventory.dao.UserRoleDAO;
import inventory.model.Paging;
import inventory.model.Role;
import inventory.model.UserRole;
import inventory.model.Users;
import inventory.util.HashingPassword;

@Service
public class UserService {
	final static Logger log = Logger.getLogger(UserService.class);
	
	@Autowired
	private UserDAO<Users> userDAO;
	
	@Autowired
	private UserRoleDAO<UserRole> userRoleDAO;
	
	public List<Users> findByProperty(String property , Object value) {
		log.info("Find user by property start ");
		return userDAO.findByProperty(property, value);
		
	}
	
	public List<Users> getAllUser(Users user, Paging paging){
		log.info("Show all user");
		/* Create query String
		 * @StringBuilder create query string
		 * @Map<String, Object> map parameter to query
		*/
		StringBuilder queryStr = new StringBuilder();
		Map<String, Object> mapParams = new HashMap<>();
		if (user != null) {
			if (user.getUserName() != null && !StringUtils.isEmpty(user.getUserName())) {
				queryStr.append(" and model.userName like :userName");
				mapParams.put("userName", "%" + user.getUserName()  + "%");
			}
			if (user.getName()!= null && !StringUtils.isEmpty(user.getName())) {
				queryStr.append(" and model.name like :name");
				mapParams.put("name", "%" + user.getName() + "%");
			}
		}
		return userDAO.findAll(queryStr.toString(), mapParams, paging);
	}
	
	public Users findUserById(int id) {
		log.info("Find user by id");
		return userDAO.findById(Users.class, id);
	}
	
	public void saveUser(Users user) {
		log.info("Save user " + user.toString());
		user.setActiveFlag(1);
		user.setCreateDate(new Date());
		user.setUpdateDate(new Date());
		user.setPassword(HashingPassword.encrypt(user.getPassword())); // Hash Password
		userDAO.save(user);
		// Also save user in USER_ROLE
		log.info("Save user role ");
		UserRole userRole = new UserRole();
		userRole.setUsers(user);
		// Also save user in ROLE
		log.info("Save role ");
		Role role = new Role();
		role.setId(user.getRoleId());
		userRole.setRole(role);
		userRole.setActiveFlag(1);
		userRole.setCreateDate(new Date());
		userRole.setUpdateDate(new Date());
		userRoleDAO.save(userRole);
	}
	
	public void updateUser(Users users) {
		log.info("Update user " + users.toString());
		// Find user is exist in db
		Users user = findUserById(users.getId());
		if (user != null) {
			// Update User Info
			user.setUserName(users.getUserName());
			user.setPassword(HashingPassword.encrypt(users.getPassword())); // Hash Password
			user.setName(users.getName());
			user.setEmail(users.getEmail());
			user.setUpdateDate(new Date());
			
			// update User Role
			UserRole userRole = (UserRole) user.getUserRoles().iterator().next();
			Role role = userRole.getRole();
			role.setId(users.getRoleId());
			userRole.setRole(role);
			userRole.setUpdateDate(new Date());
			
			userRoleDAO.update(userRole);
		}
		userDAO.update(user);
	}
	
	public void deleteUser(Users user) {
		log.info("Delete User " + user.toString());
		user.setActiveFlag(0);
		user.setUpdateDate(new Date());
		userDAO.update(user);
	}
}
