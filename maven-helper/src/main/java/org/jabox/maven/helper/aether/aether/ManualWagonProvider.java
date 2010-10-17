package org.jabox.maven.helper.aether.aether;

import org.apache.maven.wagon.Wagon;
import org.apache.maven.wagon.providers.file.FileWagon;
import org.apache.maven.wagon.providers.http.LightweightHttpWagon;
import org.sonatype.aether.connector.wagon.WagonProvider;

public class ManualWagonProvider implements WagonProvider {

	public Wagon lookup(String roleHint) throws Exception {
		if ("file".equals(roleHint)) {
			return new FileWagon();
		} else if ("http".equals(roleHint)) {
			return new LightweightHttpWagon();
		}
		return null;
	}

	public void release(Wagon wagon) {

	}

}
