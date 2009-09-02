package org.jabox.model;

import java.io.Serializable;

import javax.persistence.Entity;

import org.apache.wicket.persistence.domain.BaseEntity;

/**
 * A Project.
 * 
 * @author dimitris
 */
@Entity
public class Project extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String _name;
	private String _description;


	public void setName(String name) {
		_name = name;
	}

	public String getName() {
		return _name;
	}

	public String toString() {
		return "Project: " + _name;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getDescription() {
		return _description;
	}

}
