package org.notebook.service;

import org.notebook.exceptions.CodeCannotBeParsedException;
import org.notebook.exceptions.InterpreterNotfoundException;
import org.notebook.exceptions.NoInterpreterAvailableExeption;

public interface INoteService {
	public String interpretCode(String code) throws CodeCannotBeParsedException, InterpreterNotfoundException;
	public String executeScripte(String script, String httpSession) throws NoInterpreterAvailableExeption;
	public String interpretResult(String subPrecessResult);
	public Boolean isInterpreterKnoun(String interpreterName);
}
