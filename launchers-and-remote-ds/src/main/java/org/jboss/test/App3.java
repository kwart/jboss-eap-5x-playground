package org.jboss.test;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import org.jboss.Main;

/**
 * Hello world!
 *
 * @author Josef Cacek
 */
public class App3 {

	public static void main(String[] args) throws Exception {
		System.out.println(App3.class.getSimpleName());
		List<URL> urlList = new ArrayList<URL>();
		for (File f : new File("/home/jcacek/test/510/jboss-eap-5.1/jboss-as/client").listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".jar");
			}
		})) {
			urlList.add(f.toURL());
		}
		System.out.println(urlList);
		URLClassLoader cl = new URLClassLoader(urlList.toArray(new URL[urlList.size()]));
		Thread t = (Thread) cl.loadClass("org.jboss.test.Logic").newInstance();
		t.setDaemon(true);
		t.setContextClassLoader(cl);
		t.start();

		Main.main(args);
	}

}
