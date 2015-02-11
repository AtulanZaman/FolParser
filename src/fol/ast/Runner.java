package fol.ast;

import java.util.ArrayList;
import java.util.List;

public class Runner {

	public static void main(String[] args) {
		
		//Expr test = new OrExpr(new VarExpr("x"),new VarExpr("y"));
		//Expr test = new ExistsExpr(new VarExpr("x"),new OrExpr(new VarExpr("h"), new VarExpr("O")));
		Expr x = new VarExpr("x");
		Expr y = new VarExpr("y");
		List<Expr> l = new ArrayList<Expr>();
		l.add(x);l.add(y);
		Expr test = new ImpliesExpr(new PredicateExpr("left",l),new PredicateExpr("right",l));
		System.out.println(test);
	}

}
