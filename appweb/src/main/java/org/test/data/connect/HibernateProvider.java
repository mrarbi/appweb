package org.test.data.connect;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.SessionFactoryImplementor;

/**
 * Fournisseur d'acces pour Hibernate
 * 
 * @author Azzedine EL FEZZAZI
 * @version 1.5
 * @since 2007-05-05
 * 
 * 
 */
public class HibernateProvider {

	private static HibernateProvider instance = null;
	// Scope of access
	// On assure les memes objets / valeurs durant le m$eme Thread
	private static final ThreadLocal<Session> threadSession = new ThreadLocal<Session>();
	private SessionFactory factory;

	/** Creates a new instance of HibernateProvider */
	private HibernateProvider() {

	}

	/**
	 * Get the singleton of HibernateProvider.
	 * 
	 * @return HibernateProvider instance
	 */
	public static HibernateProvider getInstance() {
		if (instance == null) {
			instance = new HibernateProvider();
		}
		return instance;
	}

	/**
	 * Return an Hibernate Session.
	 * 
	 * @return
	 */
	public Session getSession() {
		Session s = threadSession.get();
		if (s == null) {
			s = factory.openSession();
			s.setFlushMode(FlushMode.COMMIT);
			threadSession.set(s);
		}
		return s;
	}

	public Session getSessionDemarrage() {
		Session s = threadSession.get();
		if (s == null) {
			s = factory.openSession();
			s.setFlushMode(FlushMode.MANUAL);
			threadSession.set(s);
		}
		return s;
	}

	private void _destroyInstance() {
		factory.close();
	}

	/**
	 * destroy the session factory
	 */
	public static void destroyInstance() {
		if (null != instance) {
			instance._destroyInstance();
			instance = null;
		}
	}


	/**
	 * DataSource ger�e par WebSphere
	 * 
	 * @param dataSource
	 * @param factoryClass
	 * @param managerLookUp
	 */
	public void setupConfigurationDataSource(String dataSource,
			String factoryClass, String managerLookUp) {
		try{
			if (this.factory == null || this.factory.isClosed()) {
				Configuration config = new Configuration()
						.configure(HibernateProvider.class.getResource("/hibernate.cfg.xml"));
				
//				config.setProperty("hibernate.connection.datasource"			,	dataSource);
//				config.setProperty("hibernate.transaction.factory_class"		,	factoryClass);
//				config.setProperty("hibernate.transaction.manager_lookup_class"	,	managerLookUp);
//				config.setProperty("hibernate.dialect"							, 	BDD_DIALECT);
				
				this.factory = config.buildSessionFactory();
				
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}

	}


	public SessionFactoryImplementor getFactory() {
		return (SessionFactoryImplementor) factory;
	}
}