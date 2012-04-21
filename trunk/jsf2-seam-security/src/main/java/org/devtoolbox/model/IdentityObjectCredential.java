package org.devtoolbox.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.jboss.seam.security.annotations.management.IdentityProperty;
import org.jboss.seam.security.annotations.management.PropertyType;

public @Entity class IdentityObjectCredential implements Serializable
	{
	@Id @GeneratedValue public Long id;
	public IdentityObject identityObject;
	@ManyToOne @IdentityProperty ( PropertyType.TYPE ) @JoinColumn ( name = "CREDENTIAL_TYPE_ID" ) public IdentityObjectCredentialType type;
	@IdentityProperty ( PropertyType.VALUE ) public String value;

	public Long getId ( )
		{
		return id;
		}

	public void setId ( Long id )
		{
		this.id = id;
		}

	public IdentityObject getIdentityObject ( )
		{
		return identityObject;
		}

	public void setIdentityObject ( IdentityObject identityObject )
		{
		this.identityObject = identityObject;
		}

	public IdentityObjectCredentialType getType ( )
		{
		return type;
		}

	public void setType ( IdentityObjectCredentialType type )
		{
		this.type = type;
		}

	public String getValue ( )
		{
		return value;
		}

	public void setValue ( String value )
		{
		this.value = value;
		}

	private static final long serialVersionUID = -7360895085715412520L;
	}
