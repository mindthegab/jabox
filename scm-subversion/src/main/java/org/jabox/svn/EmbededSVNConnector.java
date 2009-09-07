package org.jabox.svn;

import org.springframework.stereotype.Service;

@Service
public class EmbededSVNConnector extends SVNConnector {
	private static final long serialVersionUID = 2987150798963283309L;

	@Override
	public String toString() {
		return "Embeded Subversion Plugin";
	}
}
