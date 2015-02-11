package fol.ast;

public class ForAllExpr extends Expr {
	VarExpr var;
	Expr expr;

	public ForAllExpr(VarExpr var, Expr expr) {
		super();
		this.var = var;
		this.expr = expr;
	}

	@Override
	public String toString() {
		return "forall "+var.toString()+"("+expr.toString()+")";
	}
}
