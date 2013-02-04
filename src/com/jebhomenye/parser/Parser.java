package com.jebhomenye.parser;

/**
 * An implementation of the Recursive-Descent Parser Algorithm
 * @author jay
 *
 */
public final class Parser {
	
	private Parser(){
		
	}

	enum SyntaxErrorCode{
		SYNTAX("Syntax Error"), 
		UNBALANCED_PERENTHESIS("Missing parenthesis"), 
		NO_EXPRESSION("No expression"), 
		DIVIDE_BY_ZERO("Attempt to divide by Zero");
		
		private String message;

		private SyntaxErrorCode(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}
	}
		
	public static double evaluate(String expression){
		return 0.0;
	}
	
}
