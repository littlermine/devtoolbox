package org.devtoolbox.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.jboss.seam.security.annotations.management.IdentityProperty;
import org.jboss.seam.security.annotations.management.PropertyType;

public @Entity class IdentityObjectAttribute implements Serializable
	{
	@Id @GeneratedValue public Integer attributeId;
	@ManyToOne @JoinColumn ( name = "IDENTITY_OBJECT_ID" ) public IdentityObject identityObject;
	@IdentityProperty ( PropertyType.NAME ) public String name;
	@IdentityProperty ( PropertyType.VALUE ) public String value;

	public Integer getAttributeId ( )
		{
		return attributeId;
		}

	public void setAttributeId ( Integer attributeId )
		{
		this.attributeId = attributeId;
		}

	public IdentityObject getIdentityObject ( )
		{
		return identityObject;
		}

	public void setIdentityObject ( IdentityObject identityObject )
		{
		this.identityObject = identityObject;
		}

	public String getName ( )
		{
		return name;
		}

	public void setName ( String name )
		{
		this.name = name;
		}

	public String getValue ( )
		{
		return value;
		}

	public void setValue ( String value )
		{
		this.value = value;
		}

	private static final long serialVersionUID = -3925940105863803484L;
	}
