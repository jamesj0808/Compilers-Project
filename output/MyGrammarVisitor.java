// Generated from MyGrammar.g4 by ANTLR 4.13.1

    import backend.*; 

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MyGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MyGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MyGrammarParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MyGrammarParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyGrammarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(MyGrammarParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(MyGrammarParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyGrammarParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(MyGrammarParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyGrammarParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(MyGrammarParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyGrammarParser#funCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunCall(MyGrammarParser.FunCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyGrammarParser#argDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgDecl(MyGrammarParser.ArgDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyGrammarParser#argList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgList(MyGrammarParser.ArgListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyGrammarParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(MyGrammarParser.BodyContext ctx);
}