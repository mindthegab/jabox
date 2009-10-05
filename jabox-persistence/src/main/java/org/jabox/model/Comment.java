package org.jabox.model;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable
{
	private static final long serialVersionUID = 8800366600889293007L;
	public String text;
    public Date date = new Date();
}