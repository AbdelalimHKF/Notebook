package org.notebook.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStreamWriter;

public class SubProcess implements ISubProcess {

	private Process process;
	private BufferedReader bufferedReader;
	private OutputStreamWriter outputStreamWriter;
	public SubProcess() {
	}
	public SubProcess(Process process, BufferedReader bufferedReader, OutputStreamWriter outputStreamWriter) {
		super();
		this.process = process;
		this.bufferedReader = bufferedReader;
		this.outputStreamWriter = outputStreamWriter;
	}
	public Process getProcess() {
		return process;
	}
	public void setProcess(Process process) {
		this.process = process;
	}

	public OutputStreamWriter getOutputStreamWriter() {
		return outputStreamWriter;
	}
	public void setOutputStreamWriter(OutputStreamWriter outputStreamWriter) {
		this.outputStreamWriter = outputStreamWriter;
	}
	public BufferedReader getBufferedReader() {
		return bufferedReader;
	}
	public void setBufferedReader(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}
	
	public void destroy() {
		this.process.destroy();
	}
	
	
	

}
