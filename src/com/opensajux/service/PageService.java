package com.opensajux.service;

import java.io.Serializable;
import java.util.List;

import com.opensajux.entity.Page;

/**
 * @author Sheikh Mohammad Sajid
 * 
 */
public interface PageService extends Serializable {

	public Page getPage(String friendlyUrl);

	public List<Page> getTopPages();

	public Page addDefaultPage();

}
