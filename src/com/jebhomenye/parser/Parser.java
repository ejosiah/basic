package com.jebhomenye.parser;

import static com.jebhomenye.parser.Token.*;
import static com.jebhomenye.parser.Token.TokenType.*;

/**
 * An implementation of the Recursive-Descent Parser Algorithm
 * @author jay
 *
 */
public final class Parser {
	
	private Parser(){
		
	}

	static enum SyntaxErrorCode{
		SYNTAX_ERROR("Syntax Error"), 
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
		Expression expr = new Expression(expression);
		
		Token token = expr.getNextToken();
		if(token.equals(END_OF_EXPRESSION)){
			throw new ParserException(SyntaxErrorCode.NO_EXPRESSION);
		}
		
		double result = addSubtract(expr, token);
		
		token = expr.getNextToken();
		if(!token.equals(END_OF_EXPRESSION)){
			throw new ParserException(SyntaxErrorCode.SYNTAX_ERROR);
		}
		
		return result;
	}

	private static double addSubtract(Expression expression, Token token) {
		double result = multiplyDivide(expression, token);
		token = expression.peek();
		
		while(token.equals(PLUS) || token.equals(MINUS)){
			token = expression.getNextToken();
			double partialResult = multiplyDivide(expression, token);
			switch(token.value.charAt(0)){
			case '-': 
				result -= partialResult;
				break;
			case '+':
				result += partialResult;
				break;
			default:
				throw new RuntimeException("Illegal Argument: " + token);
			}
		}
		return result;
	}
	

	private static double multiplyDivide(Expression expression, Token token) {
		double result = exponent(expression, token);
		token = expression.getNextToken();
		
		while(token.equals(TIMES) || token.equals(DIVIDE) || token.equals(MODULUS)){
			token = expression.getNextToken();
			double partialResult = exponent(expression, token);
			switch(token.value.charAt(0)){
			case '*':
				result *= partialResult;
				break;
			case '/':
				result /= partialResult;
				break;
			case '%':
				result %= partialResult;
				break;
			default:
				throw new IllegalArgumentException("Invalid opratoin: " + token);
			}
		}
		return result;
	}

	private static double exponent(Expression expression, Token token) {
		double result = evalUnary(expression, token);
		token = expression.getNextToken();
		if(token.equals(EXPONENT)){
			token = expression.getNextToken();
			double power = exponent(expression, token);
			if(power == 0.0){
				return 1.0;
			}else{
				return Math.pow(result, power);
			}
		}
		return result;
	}

	private static double evalUnary(Expression expression, Token token) {
		String op = "";
		if(token.equals(PLUS) || token.equals(MINUS)){
			op = token.value;
			token = expression.getNextToken();
		}
		double result = evalParenthesis(expression, token);
		return op.equals("-") ? -result : result;
	}

	private static double evalParenthesis(Expression expression, Token token) {
		double result = 0.0;
		if(token.equals(LEFT_BRAKET)){
			token = expression.getNextToken();
			result = addSubtract(expression, token);
			token = expression.getNextToken();
			if(!token.equals(RIGHT_BRAKET)){
				throw new ParserException(SyntaxErrorCode.UNBALANCED_PERENTHESIS);
			}
		}else{
			result = evalNumber(expression, token);
		}
		return result;
	}

	private static double evalNumber(Expression expression, Token token) {
		double result = 0.0;
		switch(token.tokenType){
		case NUMBER:
			try{
				result = new Double(token.value);
			}catch(NumberFormatException e){
				throw new ParserException(SyntaxErrorCode.SYNTAX_ERROR);
			}
			break;
		default:
			throw new ParserException(SyntaxErrorCode.SYNTAX_ERROR);
		}
		return result;
	}
	
}
