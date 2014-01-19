package org.test.struts.appweb;

import org.hibernate.Query;
import org.hibernate.Session;
import org.test.persistance.jpa.User;
import org.test.persistance.utils.HibernateUtils;


/**
 * 
 * @author PC
 * 
 */
public class UserAction {

	private String login;
	private String passwd;
	private String email;

	public String execute() throws Exception {
		
		Session s = HibernateUtils.getSession();
		s.beginTransaction();
		
		Query q = s.createQuery("from User where login = :login");
		q.setParameter("login", login);
		User u = (User) q.setMaxResults(1).uniqueResult();
		if(null == u){
			u = new User(login, email, passwd);
		}
		s.saveOrUpdate(u);
		s.getTransaction().commit();
		
		login = null != u ? u.getLogin() : "none";
		// Fermeture de la session Hibernate
		
		return "success";
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the passwd
	 */
	public String getPasswd() {
		return passwd;
	}

	/**
	 * @param passwd the passwd to set
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	
}
