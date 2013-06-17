package com.opensajux.service;

import java.lang.reflect.TypeVariable;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.opensajux.entity.Weblet;

/**
 * @author Sheikh Mohammad Sajid
 * 
 */
public abstract class GenericService<E> implements Service<E> {
	private static final Logger LOGGER = Logger.getLogger(GenericService.class.getName());

	@Inject
	protected transient PersistenceManagerFactory pmf;
	@SuppressWarnings("rawtypes")
	private TypeVariable[] types;

	public GenericService() {
		types = getClass().getTypeParameters();
	}

	public void save(E entity) {
		PersistenceManager pm = pmf.getPersistenceManagerProxy();
		pm.makePersistent(entity);
		if (LOGGER.isLoggable(Level.INFO))
			LOGGER.info("Saving entity: " + entity);
	}

	public void remove(String id) {
		E menu = getById(id);
		PersistenceManager pm = pmf.getPersistenceManagerProxy();
		pm.deletePersistent(menu);
	}

	public void remove(Collection<E> coll) {
		PersistenceManager pm = pmf.getPersistenceManagerProxy();
		pm.deletePersistentAll(coll);
	}

	public void remove(E[] selectedWeblets) {
		PersistenceManager pm = pmf.getPersistenceManagerProxy();
		pm.deletePersistentAll(selectedWeblets);
	}

	@SuppressWarnings("unchecked")
	public List<E> getEntites(PaginationParameters params) {
		PersistenceManager pm = pmf.getPersistenceManagerProxy();
		Query query = pm.newQuery(Weblet.class);

		if (params != null) {
			query.setRange(params.getFirst(), params.getFirst() + params.getPageSize());
			if (params.getSortField() != null)
				query.setOrdering(params.getSortField() + " " + params.getSortOrder());
		}

		Object object = query.execute();
		List<E> menuList = (List<E>) object;
		menuList = menuList.subList(0, menuList.size());
		return menuList;
	}

	public Long getCount() {
		PersistenceManager pm = pmf.getPersistenceManagerProxy();
		Long count = (Long) pm.newQuery("select count(key) from " + Weblet.class.getName()).execute();
		return count;
	}

	@SuppressWarnings("unchecked")
	public E getById(String id) {
		PersistenceManager pm = pmf.getPersistenceManagerProxy();
		Key k = KeyFactory.createKey(types[0].getClass().getSimpleName(), Long.valueOf(id));
		E menu = (E) pm.getObjectById(types[0].getClass(), k);
		return menu;
	}

	@SuppressWarnings("unchecked")
	public E getEntityByName(String name) {
		PersistenceManager pm = pmf.getPersistenceManagerProxy();
		Query query = pm.newQuery("select from " + types[0].getClass().getName()
				+ " where name == n && isPublished == true");

		query.declareParameters("String n");
		List<E> m = (List<E>) query.execute(name);
		return m != null && m.size() > 0 ? m.get(0) : null;
	}

}
