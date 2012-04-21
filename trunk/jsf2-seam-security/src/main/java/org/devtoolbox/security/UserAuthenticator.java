package org.devtoolbox.security;

import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.seam.security.Authenticator;
import org.jboss.seam.security.BaseAuthenticator;
import org.jboss.seam.security.Credentials;
import org.picketlink.idm.impl.api.PasswordCredential;
import org.picketlink.idm.impl.api.model.SimpleUser;

@Named
public class UserAuthenticator extends BaseAuthenticator implements Authenticator {

	private @Inject Credentials credentials;

    public void authenticate() {
    	
    	if ("demo".equals(credentials.getUsername()) 
    			&& credentials.getCredential() instanceof PasswordCredential 
    			&& "demo".equals(((PasswordCredential) credentials.getCredential()).getValue()))  {

             setStatus(AuthenticationStatus.SUCCESS);

             setUser(new SimpleUser("demo"));
    	}
    }
}