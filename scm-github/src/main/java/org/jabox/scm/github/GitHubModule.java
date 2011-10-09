package org.jabox.scm.github;

import org.jabox.apis.scm.SCMConnector;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.multibindings.Multibinder;

public class GitHubModule implements Module {

	@Override
	public void configure(Binder binder) {
		Multibinder<SCMConnector> uriBinder = Multibinder.newSetBinder(binder,
				SCMConnector.class);
		uriBinder.addBinding().to(GithubConnector.class);
	}
}
