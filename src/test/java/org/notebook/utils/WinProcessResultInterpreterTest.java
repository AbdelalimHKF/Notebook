package org.notebook.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WinProcessResultInterpreterTest {
	private WinProcessResultInterpreter winProcessResultInterpreter 
	= new WinProcessResultInterpreter();

	@Test
	void testGetResult() {
		String result = ">>> 1+1 ###";
		String expected="1+1";
		String actual=winProcessResultInterpreter.getResult(result);
		assertEquals(expected, actual,"can not get the result");
	}

}
