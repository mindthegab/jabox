package org.jabox.bts.bugzilla;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;

import org.jabox.apis.bts.BTSConnector;
import org.jabox.model.Project;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

@Service
public class BugzillaRepository implements BTSConnector, Serializable {
	private static final long serialVersionUID = 8131183843391948936L;
	private String _url;
	private final WebConversation _wc;

	@Override
	public String toString() {
		return "Bugzilla Plugin";
	}

	public BugzillaRepository() {
		_wc = new WebConversation();
	}

	public void setUrl(final String url) {
		_url = url;
	}

	public boolean login(String username, String password)
			throws MalformedURLException, IOException, SAXException {
		WebRequest req = new GetMethodWebRequest(_url);
		WebResponse resp = _wc.getResponse(req);
		WebForm form = resp.getForms()[0]; // select the first form in the page
		form.setParameter("Bugzilla_login", username);
		form.setParameter("Bugzilla_password", password);
		resp = form.submit();

		// TODO check if not logged in.
		return true;
	}

	public boolean addProject(final Project project) throws IOException,
			SAXException {
		WebRequest req = new GetMethodWebRequest(
				"http://localhost/cgi-bin/bugzilla/editproducts.cgi?action=add");
		WebResponse resp = _wc.getResponse(req);
		WebForm form = resp.getForms()[0];

		form.setParameter("product", project.getName());
		form.setParameter("description", project.getDescription());
		form.submit();
		return true;
	}

	public boolean addModule(final Project project, final String module,
			final String description, final String initialOwner)
			throws SAXException, IOException {

		WebRequest req = new GetMethodWebRequest(
				"http://localhost/cgi-bin/bugzilla/editcomponents.cgi?action=add&product="
						+ project.getName());
		WebResponse resp = _wc.getResponse(req);
		WebForm form = resp.getForms()[0];

		form.setParameter("component", module);
		form.setParameter("description", description);
		form.setParameter("initialowner", initialOwner);
		form.submit();
		return true;
	}

	public boolean addVersion(Project project, String version)
			throws IOException, SAXException {

		WebRequest req = new GetMethodWebRequest(
				"http://localhost/cgi-bin/bugzilla/editversions.cgi?action=add&product="
						+ project.getName());
		WebResponse resp = _wc.getResponse(req);
		WebForm form = resp.getForms()[0];

		form.setParameter("version", version);
		form.submit();
		return true;
	}
}
