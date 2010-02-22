package org.jabox.webapp.pages;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.Model;
import org.apache.wicket.persistence.provider.GeneralDao;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.jabox.model.DefaultConfiguration;
import org.jabox.webapp.panels.HeaderLinksPanel;

/**
 * {@link ItsPage} is showing the current I.T.S. inside an <code>iframe</code>.
 * TopMenu is visible in order to navigate from one server to another easily.
 */
public class ItsPage extends BasePage {

	@SpringBean
	protected GeneralDao _generalDao;

	public ItsPage() {
		final DefaultConfiguration dc = _generalDao.getDefaultConfiguration();
		String url = dc.getIts().getServer().getUrl();
		WebMarkupContainer wmc = new WebMarkupContainer("iframe");
		wmc.add(new AttributeModifier("src", new Model<String>(url)));
		add(wmc);

		add(new HeaderLinksPanel("headerLinks", 1));
	}
}
