package org.notebook.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.notebook.controller2.Code;
import org.springframework.stereotype.Component;

@Component
public class RequestInterpreter implements IRequestInterpreter {

	@Override
	public boolean isValidCode(String code) {
		// code : %<interpreter-name><whitespace><code>
		// some pattern match here to know if it s valid
		// if not throw exeption

		Pattern p = Pattern.compile("^%([a-z]+)\\s.*");
		Matcher m = p.matcher(code);
		return m.matches();
	}

	@Override
	public String getIterpreterName(String code) {
		// String code = "%python 1+1";
		// code : %<interpreter-name><whitespace><code>
		String iterpreterName = null;
		Pattern p = Pattern.compile("^%([a-z]+)\\s.*");
		Matcher m = p.matcher(code);
		if (m.find()) {
			iterpreterName = m.group(1);
			System.out.println(iterpreterName);
		}
		return iterpreterName;

	}

	@Override
	public String getCodeFromRequest(Code code) {
		return code.getCode();
		// return "%python print 1+1";
	}

	@Override
	public String getScript(String code) {
		String script = null;
		Pattern p = Pattern.compile("^%[a-z]+\\s*(.*)\\s*");
		Matcher m = p.matcher(code);
		if (m.find()) {
			script = m.group(1);
			System.out.println(script);
		}
		return script;
	}

}
