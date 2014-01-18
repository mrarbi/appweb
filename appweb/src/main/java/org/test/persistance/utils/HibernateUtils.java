package org.test.persistance.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtils {
	private static final SessionFactory sessionFactory;
	private static Session session;

	// Cr�e une unique instance de la SessionFactory � partir de hibernate.cfg.xml
	static {
		try {
			sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		} catch (HibernateException ex) {
			throw new RuntimeException("Probl�me de configuration : " + ex.getMessage(), ex);
		}
	}

	// Renvoie une session Hibernate
	public static Session getSession() throws HibernateException {
		if(null == session){
			session = sessionFactory.openSession();
		}
		return session;
	}
}
