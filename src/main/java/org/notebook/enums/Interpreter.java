package org.notebook.enums;

public enum Interpreter {
	PYTHON {
		public String getKey() {
			return "python";
		}
	};
	/*RUBY {
		public String getKey() {
			return "ruby";
		}
	},
	GO {
		public String getKey() {
			return "go";
		}
	}*/
	public abstract String getKey();
	
}