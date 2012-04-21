package org.devtoolbox.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.jboss.seam.security.annotations.management.IdentityProperty;
import org.jboss.seam.security.annotations.management.PropertyType;

public @Entity class IdentityObject implements Serializable
	{
	@Id @GeneratedValue public Long id;
	@IdentityProperty ( PropertyType.NAME ) public String name;
	@ManyToOne @IdentityProperty ( PropertyType.TYPE ) @JoinColumn ( name = "IDENTITY_OBJECT_TYPE_ID" ) public IdentityObjectType type;

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

	public IdentityObjectType getType ( )
		{
		return type;
		}

	public void setType ( IdentityObjectType type )
		{
		this.type = type;
		}

	private static final long serialVersionUID = 1L;
	}
