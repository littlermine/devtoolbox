package org.devtoolbox.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.jboss.seam.security.annotations.management.IdentityProperty;
import org.jboss.seam.security.annotations.management.PropertyType;

public @Entity class IdentityObjectRelationship implements Serializable
	{
	@Id @GeneratedValue public Long id;
	@IdentityProperty ( PropertyType.NAME ) public String name;
	@ManyToOne @IdentityProperty ( PropertyType.TYPE ) @JoinColumn ( name = "RELATIONSHIP_TYPE_ID" ) public IdentityObjectRelationshipType relationshipType;
	@ManyToOne @IdentityProperty ( PropertyType.RELATIONSHIP_FROM ) @JoinColumn ( name = "FROM_IDENTITY_ID" ) public IdentityObject from;
	@ManyToOne @IdentityProperty ( PropertyType.RELATIONSHIP_TO ) @JoinColumn ( name = "TO_IDENTITY_ID" ) public IdentityObject to;

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

	public IdentityObjectRelationshipType getRelationshipType ( )
		{
		return relationshipType;
		}

	public void setRelationshipType (
			IdentityObjectRelationshipType relationshipType )
		{
		this.relationshipType = relationshipType;
		}

	public IdentityObject getFrom ( )
		{
		return from;
		}

	public void setFrom ( IdentityObject from )
		{
		this.from = from;
		}

	public IdentityObject getTo ( )
		{
		return to;
		}

	public void setTo ( IdentityObject to )
		{
		this.to = to;
		}

	private static final long serialVersionUID = 6456007118223667458L;
	}
