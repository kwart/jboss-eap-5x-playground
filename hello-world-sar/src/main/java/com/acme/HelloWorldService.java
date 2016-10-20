package com.acme;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.jboss.system.ServiceMBeanSupport;

public class HelloWorldService extends ServiceMBeanSupport implements HelloWorldServiceMBean {
	// The printMessage operation
	public void printMessage() {
		log.info("Hello World");
	}

	// The lifecycle
	protected void startService() throws Exception {
		log.info("Starting HelloWorldService");
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(10000L);
						log.info("Creating InitialContext");
						InitialContext ctx = new InitialContext();
						log.info("DS lookup");
						DataSource ds = (DataSource) ctx.lookup("java:/DefaultDS");
						if (ds == null) {
							throw new Exception("DataSource lookup was null");
						}
						log.info("Connection retrieving");
						Connection c = ds.getConnection();
						if (c == null) {
							throw new Exception("Connection was null!!");
						}
						log.info("Connection metadata");
						c.getMetaData();
						log.info("Connection close");
						c.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	protected void stopService() throws Exception {
		log.info("Stopping HelloWorldService");
	}
}
