package com.opensajux.service;

import java.util.Collection;
import java.util.List;

/**
 * @author Sheikh Mohammad Sajid
 * 
 */
public interface Service<E> {
	public void save(E entity);

	public void remove(String id);

	public void remove(Collection<E> coll);

	public void remove(E[] array);

	public List<E> getEntites(PaginationParameters params);

	public Long getCount();

	public E getById(String id);

	public E getEntityByName(String name);
}
