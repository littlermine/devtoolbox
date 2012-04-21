package org.devtoolbox.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.seam.transaction.TransactionPropagation;
import org.jboss.seam.transaction.Transactional;
import org.picketlink.idm.api.Group;
import org.picketlink.idm.api.IdentitySession;
import org.picketlink.idm.api.PersistenceManager;
import org.picketlink.idm.api.RelationshipManager;
import org.picketlink.idm.api.Role;
import org.picketlink.idm.api.RoleManager;
import org.picketlink.idm.api.RoleType;
import org.picketlink.idm.api.User;
import org.picketlink.idm.common.exception.FeatureNotSupportedException;
import org.picketlink.idm.common.exception.IdentityException;

public @Named @RequestScoped class UserBean implements Serializable
	{
	private @Inject IdentitySession identitySession;

	public @PostConstruct void init ( ) 
		{
		
		}

	@Transactional(TransactionPropagation.REQUIRED) public String work ( ) throws IdentityException, FeatureNotSupportedException
		{
		PersistenceManager persistenceManager = identitySession.getPersistenceManager ( );
		RelationshipManager relationshipManager = identitySession.getRelationshipManager ( );
		RoleManager roleManager = identitySession.getRoleManager ( );
		
		User user = persistenceManager.createUser ( "admin" );
		Group group = persistenceManager.createGroup ( "admin", "ADMINISTRATOR" );
		relationshipManager.associateUser ( group, user );
		
		RoleType administrator = roleManager.createRoleType ( "administrator" );
		Role role = roleManager.createRole ( administrator, user, group );
		
		
		return "user.xhtml";
		}

	private static final long serialVersionUID = 1L;
	}
