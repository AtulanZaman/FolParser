package fol.ast;

public class ConstExpr extends Expr {
	String b;
	
	public ConstExpr(String b){
		this.b = b;
	}
	
	public String toString(){
		return this.b;
	}
}
