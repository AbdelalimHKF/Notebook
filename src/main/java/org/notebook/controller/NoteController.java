package org.notebook.controller;

import javax.servlet.http.HttpSession;

import org.notebook.exceptions.CodeCannotBeParsedException;
import org.notebook.exceptions.InterpreterNotfoundException;
import org.notebook.exceptions.NoInterpreterAvailableExeption;
import org.notebook.service.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteController {

	@Autowired
	private INoteService noteService;

	@RequestMapping(method = RequestMethod.POST, value = "/execute")
	public Result execute(@RequestBody Code code, HttpSession httpSession){

		String script = null;
		String result = null;
		try {
			script = noteService.interpretCode(code.getCode());
			String processResult = noteService.executeScripte(script, httpSession.getId());
			result = noteService.interpretResult(processResult);
		} catch (CodeCannotBeParsedException e) {
			result = e.getMessage();
			e.printStackTrace();
		} catch (InterpreterNotfoundException e) {
			result = e.getMessage();
			e.printStackTrace();
		} catch (NoInterpreterAvailableExeption e) {
			result = e.getMessage();
			e.printStackTrace();
		}
		System.out.println(result);
		return new Result(result);
	}

}
