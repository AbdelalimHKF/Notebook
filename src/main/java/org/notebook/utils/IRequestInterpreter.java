package org.notebook.utils;

import org.notebook.controller2.Code;

public interface IRequestInterpreter {
	
	public boolean isValidCode(String code) ;
	public String getIterpreterName(String code);
	public String getCodeFromRequest(Code code);
	public String getScript(String code);
	
	

}
