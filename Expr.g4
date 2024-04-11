grammar Expr;

// parser rules
prog : ((assign | expr) ';' NEWLINE?)*;

expr : '(' expr ')'         # parensExpr
     | expr ('*'|'/') expr  # infixExpr
     | expr ('+'|'-') expr  # infixExpr
     | num                  # numberExpr
	 | VARNAME				# variableExpr
     ;

assign : VARNAME '=' num	# assignExpr 
	   ; 		
	   

num  : INT			
     | REAL
     ;


// lexer rules : Tokens
NEWLINE: [\r\n]+ ;
INT: '-' ? [0-9]+ ;          // should handle negatives
REAL: '-' ? [0-9]+'.'[0-9]+ ; // should handle signs(+/-)
VARNAME: [a-zA-Z_]+ ;
WS: [ \t\r\n]+ -> skip ;
