package org.jabox.bts.redmine;

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
public class RedmineRepository implements BTSConnector, Serializable {
	private static final long serialVersionUID = -692328636804684690L;
	private String _url;
	private final WebConversation _wc;

	@Override
	public String toString() {
		return "Redmine Plugin";
	}

	/**
	 * @param url
	 *            Redmine URL.
	 */
	public RedmineRepository() {
		_wc = new WebConversation();
	}

	public void setUrl(String url) {
		_url = url;
	}

	public boolean addModule(Project project, String module,
			String description, String initialOwner) throws SAXException,
			IOException {
		return true;
	}

	public boolean addProject(Project project) throws IOException, SAXException {
		WebRequest req = new GetMethodWebRequest(_url + "/projects/add");
		WebResponse resp = _wc.getResponse(req);
		WebForm form = resp.getForms()[1];
		form.getParameterNames();
		form.setParameter("project[name]", project.getName());
		form.setParameter("project[description]", project.getDescription());
		form.setParameter("project[identifier]", "id-" + project.getId());
		form.submit();
		return true;
	}

	public boolean addVersion(Project project, String version)
			throws IOException, SAXException {

		WebRequest req = new GetMethodWebRequest(_url
				+ "/projects/add_version/" + project.getId());

		WebResponse resp = _wc.getResponse(req);
		WebForm form = resp.getForms()[1];

		form.setParameter("version[name]", version);
		form.submit();
		return true;
	}

	public boolean login(String username, String password)
			throws MalformedURLException, IOException, SAXException {
		WebRequest req = new GetMethodWebRequest(_url + "/login");
		WebResponse resp = _wc.getResponse(req);
		WebForm form = resp.getForms()[1]; // select the second form in the
		// page
		form.setParameter("username", username);
		form.setParameter("password", password);
		resp = form.submit();

		if (resp.getURL().getPath().endsWith("/my/page")) {
			return true;
		}
		return false;
	}

}
