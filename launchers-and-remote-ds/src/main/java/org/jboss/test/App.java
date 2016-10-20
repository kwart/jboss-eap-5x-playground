package org.jboss.test;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.jboss.Main;

/**
 * Hello world!
 *
 * @author Josef Cacek
 */
public class App {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						System.out.println("Sleeping 10s");
						Thread.sleep(10000L);
						System.out.println("Creating InitialContext");
						InitialContext ctx = new InitialContext();
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
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.setDaemon(true);
		t.start();

		Main.main(args);
    }

}
