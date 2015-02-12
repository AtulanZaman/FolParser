package fol.ast;

import java.util.List;

public class FuncExpr extends Expr {
	String label;
	List<Expr> exprs;
	
	public FuncExpr(String label, List<Expr> exprs) {
		super();
		this.label = label;
		this.exprs = exprs;
	}

	@Override
	public String toString() {
		return "&"+this.label+"("+prettylist(this.exprs)+")";
	}

	private String prettylist(List<Expr> list) {
		StringBuilder b = new StringBuilder();
		System.out.println(b.toString());
		for(Expr e: list){
			b.append(e.toString());
			b.append(',');
		}
		b.deleteCharAt(b.length()-1);
		
		return b.toString();
	}

}
