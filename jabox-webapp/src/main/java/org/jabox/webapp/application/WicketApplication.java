package org.jabox.webapp.application;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.util.lang.PackageName;
import org.jabox.webapp.pages.HomePage;
import org.jabox.webapp.pages.Index;
import org.jabox.webapp.pages.JaboxAuthenticatedWebApplication;
import org.springframework.stereotype.Component;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see wicket.myproject.Start#main(String[])
 */
@Component
public class WicketApplication extends JaboxAuthenticatedWebApplication {
	/**
	 * Constructor
	 */
	public WicketApplication() {
		mount("web", PackageName.forClass(HomePage.class));
	}

	public void init() {
		super.init();
		addComponentInstantiationListener(new SpringComponentInjector(this));
	}

	/**
	 * @see wicket.Application#getHomePage()
	 */
	public Class<? extends WebPage> getHomePage() {
		return Index.class;
	}

}
