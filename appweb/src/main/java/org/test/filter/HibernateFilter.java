package org.test.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.test.data.connect.HibernateProvider;
import org.test.persistance.utils.HibernateUtils;

public class HibernateFilter implements Filter {

	HibernateProvider hibernateProvider = null;

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
//		
//		hibernateProvider = HibernateProvider.getInstance();
	}

	/**
     * 
     */
	public void doFilter(ServletRequest sRequest, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
//		HttpServletRequest request = (HttpServletRequest) sRequest;
//		hibernateProvider.getSessionDemarrage();
//		chain.doFilter(request, response);
	}

	public void destroy() {
		HibernateUtils.getSession().close();
	}
}
