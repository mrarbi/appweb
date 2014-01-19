package org.test.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.test.persistance.utils.HibernateUtils;

public class HibernateFilter implements Filter {

	/**
     * 
     *
     */
	public HibernateFilter() {

	}

	/**
	 * 
	 */
	public void init(FilterConfig config) throws ServletException {
		HibernateUtils.getSession();
	}

	/**
     * 
     */
	public void doFilter(ServletRequest sRequest, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
	}

	public void destroy() {
		HibernateUtils.getSession().close();
	}
}
