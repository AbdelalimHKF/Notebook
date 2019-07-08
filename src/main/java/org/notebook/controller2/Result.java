package org.notebook.controller2;

public class Result {
	
	private String result;
	private String sessionId;
	
	public Result() {}
	
	public Result(String result, String sessionId) {
		super();
		this.result = result;
		this.sessionId = sessionId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	
	

}
