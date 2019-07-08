package org.notebook.processLifeCycleManager;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.notebook.service.ISubProcess;
import org.notebook.service.SubProcess;
import org.notebook.service.SubProcessFactory;
import org.notebook.service.SubProcessPool;
import org.notebook.utils.IRequestInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ProcessLifeCycleManager implements HttpSessionListener {
	protected static Logger logger = Logger.getLogger(ProcessLifeCycleManager.class);
	private HttpSession session = null;
	private String interpreter_name = null;
	@Autowired
	private SubProcessPool subProcessPool;
	@Autowired
	private Environment env;

	@Override
	public void sessionCreated(HttpSessionEvent event) {

		session = event.getSession();
		//session.setMaxInactiveInterval(20);
		logger.info("Session created for id " + session.getId());
		// create subProcess and adding it to the pool
		SubProcessFactory factory = new SubProcessFactory();
		interpreter_name = env.getProperty("interpreter.name.windows");
		ISubProcess subProcess = factory.getSubProcess(interpreter_name);
		subProcessPool.addSubProcess(session.getId(), (SubProcess) subProcess);
		logger.info("subProcess added to the pool related to the session id " + session.getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {

		session = event.getSession();
		logger.info("Session destroyed for id " + session.getId());
		// destroy subProcess and removing it from the pool
		subProcessPool.deleteSubProcess(session.getId());
	}
}