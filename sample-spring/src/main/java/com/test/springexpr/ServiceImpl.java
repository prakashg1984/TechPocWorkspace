package com.test.springexpr;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl implements IService {
	
	/**
	 * Reads next record from input
	 */
	public String getMessage() {
		ExpressionParser expressionParser = new SpelExpressionParser();
		Expression expression = expressionParser.parseExpression("'Any string'");
		String result = (String) expression.getValue();
		return result;
	}

}
