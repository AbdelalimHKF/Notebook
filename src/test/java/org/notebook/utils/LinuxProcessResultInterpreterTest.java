package org.notebook.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LinuxProcessResultInterpreterTest {
	
	private LinuxProcessResultInterpreter linuxProcessResultInterpreter 
		= new LinuxProcessResultInterpreter();
	@Test
	void testGetResult() {
		String result = "1+1";
		String expected="1+1";
		String actual=linuxProcessResultInterpreter.getResult(result);
		assertEquals(expected, actual,"can not return the result");
	}

}
