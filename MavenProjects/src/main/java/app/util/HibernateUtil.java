package app.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import app.model.Account;





//Singleton
   //create a static reff
   //create a static method that returns the object
   //In the method check if the ref is null if so then generate the object else
   //return the existing object 
public class HibernateUtil {
	private static SessionFactory sessionFactory;//singleton-Design Pattern
	
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) {
			Configuration configuration = new Configuration();
			Properties properties = new Properties();
			properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL55Dialect");
			properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
			properties.put(Environment.URL, "jdbc:mysql://localhost:3306/sakila");
			properties.put(Environment.USER, "root");
			properties.put(Environment.PASS, "p@ssw0rd");
			properties.put(Environment.SHOW_SQL, "true");
			properties.put(Environment.HBM2DDL_AUTO, "update");
			configuration.setProperties(properties);
			configuration.addAnnotatedClass(Account.class);
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
		return sessionFactory;
	}

}
