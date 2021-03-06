package inventory.dao;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import inventory.model.Auth;

@Repository
@Transactional(rollbackFor = Exception.class)
public class AuthDAOImpl extends BaseDAOImpl<Auth> implements AuthDAO<Auth>{
	
	@Override
	public Auth find(int roleId, int menuId) {
		String query = " from Auth model where model.role.id = :roleId and model.menu.id = :menuId";
		Query<Auth> queryStr = sessionFactory.getCurrentSession().createQuery(query);
		queryStr.setParameter("roleId", roleId);
		queryStr.setParameter("menuId", menuId);
		List<Auth> authList = queryStr.getResultList();
		if (!CollectionUtils.isEmpty(authList)) {
			return authList.get(0);
		}
		return null;
	}
}
