package edu.csulb.library;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * @author Manav
 *
 */
public class HibernateUtil {

	private static SessionFactory sessionFactory;
	private static Session session;

	public static void initialize() {

		Configuration cfg = new Configuration().configure();
		sessionFactory = cfg.buildSessionFactory();
		cfg.addAnnotatedClass(edu.csulb.library.entity.Book.class);
	}

	public static void closeSessionFactory() {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
		StandardServiceRegistryBuilder.destroy(sessionFactory.getSessionFactoryOptions().getServiceRegistry());
	}

	public static Session getCurrentSession() {
		if (sessionFactory == null) {
			initialize();
		}
		if (session == null) {
			session = sessionFactory.openSession();
		}
		return session;
	}

	public static void endCuurentSession() {
		if (session != null) {
			session.close();
			session = null;
		}
	}
}
