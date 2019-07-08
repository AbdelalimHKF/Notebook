package org.notebook.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class WinProcessResultInterpreter implements IResultInterpreter {

	@Override
	public String getResult(String subProcessOutPut) {

		String result = "";
		Pattern p = Pattern.compile(".*[>>>|###]\\s*(.*)\\s+###$");
		Matcher m = p.matcher(subProcessOutPut);
		if (m.find()) {
			result = m.group(1);
		}
		return result;
	}
}
