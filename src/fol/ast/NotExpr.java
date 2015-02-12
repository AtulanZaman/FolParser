package fol.ast;

public class NotExpr extends Expr {
	Expr expr;
	
	public NotExpr(Expr expr) {
		super();
		this.expr = expr;
	}

	@Override
	public String toString() {
		return "/("+expr.toString()+")";
	}
	
}
