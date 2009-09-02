package org.jabox.model;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable
{
    public String text;
    public Date date = new Date();
}