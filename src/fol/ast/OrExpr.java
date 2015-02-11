package fol.ast;

public class OrExpr extends Expr {

	Expr left;
		Expr right;
	
		public OrExpr(Expr left, Expr right) {
			super();
			this.left=left;
			this.right=right;
		}
	
		@Override
		public String toString() {
			return left.toString()+'+'+right.toString();
		}

}
