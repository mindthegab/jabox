package org.jabox.model;

import java.io.Serializable;

import javax.persistence.Entity;

import org.apache.wicket.persistence.domain.BaseEntity;

@Entity
public class MavenArchetype extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1354396598525488609L;

	private String _groupId;
	private String _artifactId;
	private String _version;

	public MavenArchetype() {
	}

	public MavenArchetype(final String groupId, final String artifactId,
			final String version) {
		_groupId = groupId;
		_artifactId = artifactId;
		_version = version;
	}

	public String getArchetypeGroupId() {
		return _groupId;
	}

	public String getArchetypeArtifactId() {
		return _artifactId;
	}

	public String getArchetypeVersion() {
		return _version;
	}

	@Override
	public String toString() {
		return _groupId + ":" + _artifactId + ":" + _version;
	}
}
