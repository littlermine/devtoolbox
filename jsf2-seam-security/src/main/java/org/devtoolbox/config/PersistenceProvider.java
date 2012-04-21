package org.devtoolbox.config;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.jboss.seam.solder.core.ExtensionManaged;

public class PersistenceProvider implements Serializable
	{
	@SuppressWarnings ( "unused" ) 
	@ConversationScoped @PersistenceUnit ( unitName = "userPersistenceUnit" )
	@ExtensionManaged @Produces 
	private EntityManagerFactory producerField;

	private static final long serialVersionUID = 1L;
	}
