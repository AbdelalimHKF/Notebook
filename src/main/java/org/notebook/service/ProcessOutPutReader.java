package org.notebook.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Component;

@Component
public class ProcessOutPutReader {

	StringBuilder stringBuilder = new StringBuilder();
	String s = null;

	public String read(final OutputStreamWriter osw, final BufferedReader bufferedReader) {

		ExecutorService executor = Executors.newCachedThreadPool();
		Callable<Object> task = new Callable<Object>() {
			public Object call() {
				return blockingRead(osw, bufferedReader);
			}
		};
		Future<Object> future = executor.submit(task);
		try {
			Object result = future.get(1, TimeUnit.SECONDS);
		} catch (TimeoutException ex) {
			// handle the timeout
			stringBuilder.append(" ###");
			return stringBuilder.toString();
		} catch (InterruptedException e) {
			// handle the interrupts
			return stringBuilder.toString();
		} catch (ExecutionException e) {
			// handle other exceptions
			return stringBuilder.toString();
		} finally {
			future.cancel(true);
		}
		return stringBuilder.toString();
	}

	public Object blockingRead(OutputStreamWriter oswriter, BufferedReader bufferedReader) {

		try {
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}
