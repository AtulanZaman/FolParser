package fol.ast;

public class VarExpr extends Expr {
	String var;
	
	public VarExpr(String var) {
		super();
		this.var = var; 
	}

	@Override
	public String toString() {
		return var;
	}

}
