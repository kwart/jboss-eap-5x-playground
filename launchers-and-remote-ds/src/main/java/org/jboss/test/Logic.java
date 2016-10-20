package org.jboss.test;

import java.sql.Connection;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * Hello world!
 *
 * @author Josef Cacek
 */
public class Logic extends Thread {

	@Override
	public void run() {
		System.out.println(getClass().getSimpleName());
		while (true) {
			try {
				System.out.println("Sleeping 5s");
				Thread.sleep(5000L);
				System.out.println("Creating InitialContext");
				Properties props = new Properties();
//				props.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
//				props.setProperty("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
				props.setProperty("java.naming.provider.url", "localhost");
				InitialContext ctx = new InitialContext(props);
				System.out.println("DataSource lookup");
				DataSource ds = (DataSource) ctx.lookup("java:/DefaultDS2");
				if (ds == null) {
					throw new Exception("DataSource lookup was null");
				}
				System.out.println("Connection retrieving");
				Connection c = ds.getConnection();
				if (c == null) {
					throw new Exception("Connection was null!!");
				}
				System.out.println("Connection metadata");
				c.getMetaData();
				System.out.println("Connection close");
				c.close();
				ctx.close();
//				System.out.println("Find org.jboss.resource.security.SecureIdentityLoginModule");
//				Class.forName("org.jboss.resource.security.SecureIdentityLoginModule");
//				System.out.println("Found");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
