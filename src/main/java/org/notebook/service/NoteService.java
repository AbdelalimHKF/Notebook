package org.notebook.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.notebook.enums.Interpreter;
import org.notebook.exceptions.CodeCannotBeParsedException;
import org.notebook.exceptions.InterpreterNotfoundException;
import org.notebook.exceptions.NoInterpreterAvailableExeption;
import org.notebook.utils.IRequestInterpreter;
import org.notebook.utils.IResultInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NoteService implements INoteService {

	@Autowired
	private SubProcessPool subProcessPool;

	@Autowired
	private ProcessOutPutReader processOutPutReader;

	@Autowired
	private IRequestInterpreter requestInterpreter;

	@Autowired
	private IResultInterpreter resultInterpreter;

	@Override
	public String executeScripte(String script, String httpSession) throws NoInterpreterAvailableExeption {
		// get subProcess from the pool
		SubProcess subProcess = null;
		subProcess = subProcessPool.getSubProcess(httpSession);
		if (subProcess != null) {

			OutputStreamWriter outputStreamWriter = subProcess.getOutputStreamWriter();
			BufferedReader bufferedReader = subProcess.getBufferedReader();
			try {
				outputStreamWriter.write(script + "\n");
				outputStreamWriter.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return processOutPutReader.read(outputStreamWriter, bufferedReader);

		} else {
			throw new NoInterpreterAvailableExeption("No interpreter available for the current session");
		}
	}

	@Override
	public String interpretCode(String code) throws InterpreterNotfoundException, CodeCannotBeParsedException {
		String script = null;
		if (requestInterpreter.isValidCode(code)) {
			String interpreter_name = requestInterpreter.getIterpreterName(code);
			// check interpreter if exist, if not throw exception
			if (!isInterpreterKnoun(interpreter_name)) {
				throw new InterpreterNotfoundException("Interpreter Not found");
			}
			script = requestInterpreter.getScript(code);
		} else {
			throw new CodeCannotBeParsedException("Code cannot be parsed");
		}
		return script;
	}

	@Override
	public String interpretResult(String subPrecessResult) {

		return resultInterpreter.getResult(subPrecessResult);
	}

	@Override
	public Boolean isInterpreterKnoun(String interpreterName) {
		for (Interpreter interpreter : Interpreter.values()) {
			if (interpreter.getKey().equals(interpreterName)) {
				return true;
			}
		}
		return false;
	}

}
