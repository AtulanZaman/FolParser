package fol.ast;

public class ImpliesExpr extends Expr {
	Expr left;
	Expr right;

	public ImpliesExpr(Expr left, Expr right) {
		super();
		this.left = left;
		this.right = right;
	}

	@Override
	public String toString() {
		return left.toString()+"="+right.toString();
	}

}
