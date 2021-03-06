package com.cn.website.common.dao.impl;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.cn.website.common.dao.BaseDaoSupport;

@Repository("baseDaoSupport")
public class BaseDaoSupportImpl extends HibernateDaoSupport implements BaseDaoSupport {
	
	protected EntityManager entityManager;
	
	protected EntityManager centerEntityManager;
	
	protected HibernateDaoSupport hbCenterSupport;  
	
	protected HibernateTemplate centerHbernateTemplate;
	
	protected HibernateTemplate userHbernateTemplate;
	
	@Autowired 
    @Resource(name = "userSessionFactory") 
    public void setSessionFactoryOverride(SessionFactory userSessionFactory)  
    {  
        super.setSessionFactory(userSessionFactory); 
        this.entityManager = userSessionFactory.createEntityManager();
        this.userHbernateTemplate = new HibernateTemplate(userSessionFactory);
    }
	
	
	@Autowired 
	@Resource(name = "centerSessionFactory") 
    public void setCenterSessionFactoryOverride(SessionFactory centerSessionFactory)  
    {  
		hbCenterSupport = new HibernateDaoSupport(){
			
		};
		hbCenterSupport.setSessionFactory(centerSessionFactory);  
		this.centerHbernateTemplate = hbCenterSupport.getHibernateTemplate();  
    }
	
	public HibernateTemplate getCenterHbernateTemplate() {  
		 return hbCenterSupport.getHibernateTemplate();  
	}  


	@Override
	public <T> T get(Class<T> entityType, Serializable id) {
		return currentSession().get(entityType, id);
	}

	@Override
	public <T> T get(Class<T> entityType, Serializable id, LockMode lockMode) {
		return currentSession().get(entityType, id, lockMode);
	}

	@Override
	public <T> T load(Class<T> theClass, Serializable id, LockMode lockMode) {
		return currentSession().load(theClass, id, lockMode);
	}

	@Override
	public <T> T load(Class<T> theClass, Serializable id, LockOptions lockOptions) {
		return currentSession().load(theClass, id, lockOptions);
	}

	@Override
	public Object load(String entityName, Serializable id, LockMode lockMode) {
		return currentSession().load(entityName, id, lockMode);
	}

	@Override
	public Serializable save(String entityName, Object object) {
		return currentSession().save(entityName,object);
	}
	@Override
	public Serializable save(Object object) {
		return currentSession().save(object);
	}

	@Override
	public void saveOrUpdate(Object object) {
		 currentSession().saveOrUpdate(object);
	}

	@Override
	public void saveOrUpdate(String entityName, Object object) {
		 currentSession().save(entityName,object);
	}

	@Override
	public void update(Object object) {
		 currentSession().save(object);
	}

	@Override
	public void update(String entityName, Object object) {
		currentSession().save(entityName,object);
		
	}

	@Override
	public Object merge(Object object) {
		return currentSession().merge(object);
	}

	@Override
	public Object merge(String entityName, Object object) {
		return currentSession().merge(entityName,object);
	}

	@Override
	public void persist(Object object) {
		currentSession().persist(object);
	}

	@Override
	public void persist(String entityName, Object object) {
		currentSession().persist(entityName,object);
	}

	@Override
	public void delete(Object object) {
		currentSession().delete(object);
	}

	@Override
	public void delete(String entityName, Object object) {
		 currentSession().delete(entityName,object);
	}  
}
