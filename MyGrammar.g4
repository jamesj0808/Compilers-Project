grammar MyGrammar;

@header {
    import backend.*; 
}

@members {

}

// Multiple statement handling after entry point
program returns [Expr expr]
    : { List<Expr> statements = new ArrayList<>(); } // Initializing a list of expressions
    (statement { statements.add($statement.expr); })* EOF
    { $expr = new Block(statements); }
    ;

// Handling statements
statement returns [Expr expr]
    : expression ';' ? { $expr = $expression.expr; }
    ;

// Various types of expressions
expression returns [Expr expr]
    : '(' expression ')' { $expr = $expression.expr; }
    | e1=expression '++' e2=expression { $expr = new AddString($e1.expr,$e2.expr); }
    | e1=expression '+' e2=expression { $expr = new Arithmetics(Operator.Add, $e1.expr,$e2.expr); }
    | e1=expression '-' e2=expression { $expr = new Arithmetics(Operator.Sub, $e1.expr,$e2.expr); }   
    | e1=expression '*' e2=expression { $expr = new Arithmetics(Operator.Mul, $e1.expr,$e2.expr); }
    | e1=expression '/' e2=expression { $expr = new Arithmetics(Operator.Div, $e1.expr,$e2.expr); }
    | e1=expression '>' e2=expression { $expr = new Compare(Comparator.GT, $e1.expr,$e2.expr); }
    | e1=expression '>=' e2=expression { $expr = new Compare(Comparator.GE, $e1.expr,$e2.expr); }
    | e1=expression '<' e2=expression { $expr = new Compare(Comparator.LT, $e1.expr,$e2.expr); }
    | e1=expression '<=' e2=expression { $expr = new Compare(Comparator.LE, $e1.expr,$e2.expr); }
    | e1=expression '==' e2=expression { $expr = new Compare(Comparator.EQ, $e1.expr,$e2.expr); }
    | e1=expression '!=' e2=expression { $expr = new Compare(Comparator.NE, $e1.expr,$e2.expr); }
    | 'avg' '(' argList ')' { $expr = new AvgFunction($argList.exprList); }
    | 'med' '(' argList ')' { $expr = new MedFunction($argList.exprList); }
    | assignment { $expr = $assignment.expr; }
    | funCall { $expr = $funCall.expr; }
    | value { $expr = $value.expr; }
    ;

// Literal values
value returns [Expr expr]
    : INTEGER { $expr = new IntLiteral($INTEGER.text); }
    | STRING { $expr = new StringLiteral($STRING.text); }
    | WORD { $expr = new Deref($WORD.text); }
    ;

// Variable assignment and function declaration
assignment returns [Expr expr]
    : 'let'? WORD '=' expression { $expr = new Assign($WORD.text, $expression.expr); }
    | 'function' WORD '(' argDecl ')' body { $expr = new Declare($WORD.text, $argDecl.strList, (Block) $body.expr); }
    ;

// Function and control flow structures
funCall returns [Expr expr]
    : 'print' '(' expression ')' { $expr = new PrintExpr($expression.expr); }
    | 'for' '(' i=value 'in' a=value '..' z=value ')' body { $expr = new ForLoop($i.text, $a.expr, $z.expr, (Block) $body.expr); }
    | 'if' '(' expression ')' b1=body 'else' b2=body { $expr = new IfElse($expression.expr, $b1.expr, $b2.expr); }
    | WORD '(' argList ')' { $expr = new Invoke($WORD.text, $argList.exprList); }
    ;

// Argument Declarations
argDecl returns [List<String> strList] 
    : { $strList = new ArrayList<>(); }
    (WORD {$strList.add($WORD.text);} (',' WORD {$strList.add($WORD.text);})*)?
    ;

// Argument Expression List
argList returns [List<Expr> exprList] 
    : { $exprList = new ArrayList<>(); }
    (expression {$exprList.add($expression.expr);} (',' expression {$exprList.add($expression.expr);})*)?
    ;

// Statment Blocks
body returns [Expr expr]
    : '{' { List<Expr> statements = new ArrayList<>(); }
    (statement { statements.add($statement.expr); })* '}'
    { $expr = new Block(statements); }
    ;

// Lexer rules 
BOOLEAN : 'true' | 'false';
INTEGER : [0-9]+;
STRING : '"' (~["\r\n\\"] | '\\' ['"\\])* '"';
SYMBOL : '++' | [+*/-];
WORD : [a-zA-Z0-9_]+;
WHITESPACE : [ \t\r\n]+ -> skip; // Skip whitespace in lexer