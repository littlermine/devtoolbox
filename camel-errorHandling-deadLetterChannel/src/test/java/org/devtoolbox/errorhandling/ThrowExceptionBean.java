package org.devtoolbox.errorhandling;

public class ThrowExceptionBean {
	
	public String throwException(String inputBody) {
		String a= null;
		throw new RuntimeException(a);
	}

}
