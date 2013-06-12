package com.opensajux.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.opensajux.entity.BlogPost;
import com.opensajux.entity.MyBlog;

/**
 * @author Sheikh Mohammad Sajid
 * 
 */
public interface BlogService extends Serializable {
	/**
	 * @return
	 */
	public Long getCount();

	/**
	 * @param params
	 * @return
	 */
	public List<MyBlog> getBlogs(PaginationParameters params);

	/**
	 * @param id
	 * @return
	 */
	public MyBlog getById(String id);

	/**
	 * @param url
	 * @param id
	 * @param blogName
	 * @param publishedDate
	 * @param updatedDate
	 */
	public void saveBlog(String url, String id, String blogName, Date publishedDate, Date updatedDate);

	/**
	 * @param id
	 */
	public void removeBlog(String id);

	/**
	 * @return
	 */
	public Long getBlogPostCount();

	/**
	 * @param params
	 * @return
	 */
	public List<BlogPost> getBlogPosts(PaginationParameters params);
}
