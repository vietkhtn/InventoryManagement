package inventory.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import inventory.model.Auth;
import inventory.model.Paging;
@Repository
@Transactional(rollbackFor =Exception.class)
public class BaseDAOImpl<E> implements BaseDAO<E>{
	final static Logger log = Logger.getLogger(BaseDAOImpl.class);
	@Autowired
	SessionFactory sessionFactory;
	
	public List<E> findAll(String queryStr, Map<String, Object> mapParams, Paging paging) {
		log.info("find all record from db");
		StringBuilder queryString = new StringBuilder("");
		StringBuilder countQuery = new StringBuilder();
		queryString.append(" from ").append(getGenericName()).append(" as model where model.activeFlag = 1");
		// Query count records for paging
		countQuery.append("select count(*) from ").append(getGenericName()).append(" as model where model.activeFlag = 1");
		// If queryStr is valid => combine queryString to queryStr to find list category
		if(queryStr != null ) {
			queryString.append(queryStr);
			countQuery.append(queryStr);
		}
		// Create a query instance from queryString
		Query<E> query = sessionFactory.getCurrentSession().createQuery(queryString.toString());
		Query<E> countQ = sessionFactory.getCurrentSession().createQuery(countQuery.toString());
		// if mapParams not null, map parameters into queryString & countQuery
		if (mapParams != null) {
			for (String key : mapParams.keySet()) {
				query.setParameter(key, mapParams.get(key));
				countQ.setParameter(key, mapParams.get(key));
			}
		}
		if (paging != null){
			query.setFirstResult(paging.getOffset()); // init at 0
			query.setMaxResults(paging.getRecordPerPage());
			long totalRecords = (long) countQ.uniqueResult(); // get total records by result return by countQ query
			paging.setTotalRows(totalRecords);
		}
		log.info( "Query find all ====>" + queryString.toString());
		// Return result from db
		return query.list();
	}

	public E findById(Class<E> e, Serializable id) {
		log.info("Find by ID ");
		StringBuilder countQuery = new StringBuilder();
		countQuery.append("select count(*) from ").append(getGenericName()).append(" as model where model.activeFlag = 1");
		Query<E> countQ = sessionFactory.getCurrentSession().createQuery(countQuery.toString());
		long totalRecords = (long) countQ.uniqueResult();
		if (totalRecords == 0) {
			return null;
		}
		return sessionFactory.getCurrentSession().get(e, id);
	}

	public List<E> findByProperty(String property, Object value) {
		log.info("Find by property");
		StringBuilder queryString = new StringBuilder();
		queryString.append(" from ").append(getGenericName()).append(" as model where model.activeFlag=1 and model.").append(property).append("=?");
		log.info(" query find by property ===>"+queryString.toString());
		Query<E> query = sessionFactory.getCurrentSession().createQuery(queryString.toString());
		query.setParameter(0, value);
		return query.getResultList();
		
	}

	public void save(E instance) {
		log.info("save instance");
		sessionFactory.getCurrentSession().persist(instance);
	}

	public void update(E instance) {
		log.info("update instance");
		sessionFactory.getCurrentSession().merge(instance);
	}
	//
	public String getGenericName() {
		String s = getClass().getGenericSuperclass().toString();
		Pattern pattern = Pattern.compile("\\<(.*?)\\>");
		Matcher m = pattern.matcher(s);
		String generic="null";
		if(m.find()) {
			generic = m.group(1);
		}
		return generic;
	}
	
}
