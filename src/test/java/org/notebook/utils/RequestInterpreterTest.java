package org.notebook.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RequestInterpreterTest {
	
	private RequestInterpreter requestInterpreter = new RequestInterpreter();

	@Test
	void testIsValidCode() {		
		String  code = "%python 1-6" ;
		assertTrue( requestInterpreter.isValidCode( code),"code doesn't respect the pattern %<interpreter-name><whitespace><code>");	
	}

	@Test
	void testGetIterpreterName() {
		
		String code = "%python 1+1";
		String expected="python";
		String actual=requestInterpreter.getIterpreterName(code);
		assertEquals(expected, actual,"can not return interpreter name from the code : %python 1+1");
	}


	@Test
	void testGetScript() {
		String code = "%python 1+1";
		String expected="1+1";
		String actual=requestInterpreter.getScript(code);
		assertEquals(expected, actual,"can not return script from the code : %python 1+1");
	}

}
