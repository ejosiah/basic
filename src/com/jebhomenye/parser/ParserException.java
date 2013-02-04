package com.jebhomenye.parser;

public class ParserException extends RuntimeException {
	
	private static final long serialVersionUID = 1;

	public ParserException(Parser.SyntaxErrorCode errorCode){
		super(errorCode.getMessage());
	}
}
