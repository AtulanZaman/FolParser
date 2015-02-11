package fol.ast;

public class AndExpr extends Expr{
	Expr left;
	Expr right;

	public AndExpr(Expr left, Expr right) {
		super();
		this.left=left;
		this.right=right;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return left.toString()+'*'+right.toString();
	}

}
