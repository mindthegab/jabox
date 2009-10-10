package org.jabox.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

import org.apache.wicket.persistence.domain.BaseEntity;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
public abstract class DeployerConfig extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -1502838460606670036L;

	@Column(nullable = false, length = 64)
	public String pluginId;

	@OneToOne(optional = false, cascade = CascadeType.PERSIST)
	public Configuration configuration;
}
