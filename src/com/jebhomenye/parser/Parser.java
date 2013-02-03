package com.jebhomenye.parser;

public class Parser {

	enum SyntaxErrorCode{
		SYNTAX, UNBALANCED_PERENTHESIS, NO_EXPRESSION, DIVIDE_BY_ZERO
	}
	
	
	private Expression expression;
	private int expressionIndex;
	private String token;

	
	private Token getToken(){
		return expression.getNextToken();
	}
	
}
