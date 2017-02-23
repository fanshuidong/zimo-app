package org.zimo.core.util;

import org.zimo.core.exception.AssertFailedException;

public class Assert {

	public static final void isTrue(boolean expression) {
		if (expression) 
			throw new AssertFailedException(Boolean.TRUE, expression);
	}
}
