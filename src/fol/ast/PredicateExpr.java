package fol.ast;

import java.util.List;

public class PredicateExpr extends Expr {
	String label;
	List<Expr> exprs;

	public PredicateExpr(String label, List<Expr> exprs) {
		super();
		this.label = label;
		this.exprs = exprs;
	}

	@Override
	public String toString() {
		return "pred_"+label+"("+prettylist(exprs)+")";
	}
	
	private String prettylist(List<Expr> exprs2) {
		StringBuilder b = new StringBuilder();
		for(Expr e: exprs){
			b.append(e.toString());
			b.append(',');
		}
		b.deleteCharAt(b.length()-1);
		
		return b.toString();
	}

}
