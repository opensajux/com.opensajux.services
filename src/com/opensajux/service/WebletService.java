package com.opensajux.service;

import java.io.Serializable;

import com.opensajux.entity.Weblet;

/**
 * @author Sheikh Mohammad Sajid
 * 
 */
public interface WebletService extends Service<Weblet>, Serializable {
	Weblet getByWebletId(String webletId);
}
