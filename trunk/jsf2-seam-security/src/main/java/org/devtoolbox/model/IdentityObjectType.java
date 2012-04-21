package org.devtoolbox.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.jboss.seam.security.annotations.management.IdentityProperty;
import org.jboss.seam.security.annotations.management.PropertyType;

@Entity public class IdentityObjectType implements Serializable
	{
	@Id @GeneratedValue public Long id;
	@IdentityProperty ( PropertyType.NAME ) public String name;

	public Long getId ( )
		{
		return id;
		}

	public void setId ( Long id )
		{
		this.id = id;
		}

	public String getName ( )
		{
		return name;
		}

	public void setName ( String name )
		{
		this.name = name;
		}
	private static final long serialVersionUID = -1398394597177773910L;
	}
