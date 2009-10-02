package org.jabox.apis.scm;

import java.io.File;

import org.jabox.apis.Connector;
import org.jabox.model.Project;

public interface SCMConnector extends Connector {

	File createProjectDirectories(Project project) throws SCMException;

	void commitProject(Project project) throws SCMException;

	String getScmUrl();

}
