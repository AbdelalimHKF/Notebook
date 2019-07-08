package org.notebook.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.apache.log4j.Logger;

public class SubProcessFactory {

	protected static Logger logger = Logger.getLogger(SubProcessFactory.class);

	public ISubProcess getSubProcess(String interpreter_name) {
		Process process = null;
		ProcessBuilder processBuilder = new ProcessBuilder(interpreter_name, "-i");
		processBuilder.redirectErrorStream(true);
		try {
			process = processBuilder.start();

		} catch (IOException e) {

			e.printStackTrace();
			return null;
		}
		// InputStream inputStream = process.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		OutputStream outputStream = process.getOutputStream();
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
		logger.info("subProcess created ");
		return new SubProcess(process, bufferedReader, outputStreamWriter);
	}

}
