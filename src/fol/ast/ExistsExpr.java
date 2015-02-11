package fol.ast;

public class ExistsExpr extends Expr {
	VarExpr var;
	Expr expr;

	public ExistsExpr(VarExpr var, Expr expr) {
		super();
		this.var = var;
		this.expr = expr;
	}

	@Override
	public String toString() {
		return "exists "+var.toString()+"("+expr.toString()+")";
	}

}
