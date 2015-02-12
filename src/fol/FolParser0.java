package fol;
import java.util.ArrayList;
import java.util.List;

import org.parboiled.BaseParser;
import org.parboiled.Rule;
import org.parboiled.annotations.BuildParseTree;
import org.parboiled.support.Var;

import fol.ast.AndExpr;
import fol.ast.ConstExpr;
import fol.ast.ExistsExpr;
import fol.ast.Expr;
import fol.ast.ForAllExpr;
import fol.ast.FuncExpr;
import fol.ast.ImpliesExpr;
import fol.ast.NotExpr;
import fol.ast.OrExpr;
import fol.ast.PredicateExpr;
import fol.ast.VarExpr;

@BuildParseTree

public class FolParser0 extends FolParser<Object> {
	@Override
	public Rule InputLine(){
		return Sequence(Expression(), EOI);
	}
	
	public Rule Expression(){
		return FirstOf(
				Sequence(Formula(),'=',Formula(), 
						swap() && push(new ImpliesExpr((Expr)pop(), (Expr)pop()))),
				Formula()
				);
	}
	
	public Rule Constant(){
		return Sequence(
					CharRange('0', '1'),
					push(new ConstExpr(match()))); 
	}
	
	public Rule Variable(){
		return Sequence(
					OneOrMore(CharRange('a', 'z')),
					push(new VarExpr(match())));
	}
	
	public Rule Function(){
		Var<Character> label = new Var<Character>();
		return Sequence('&'
						,CharRange('a','z')
						, label.set(matchedChar())
						,'(',TermList(),')'
						, push(new FuncExpr(label.get().toString(), (List<Expr>)pop()))
						);
	}
	
	public Rule Term(){
		return FirstOf(Constant(), Variable()
				, Function()
				);
	}
	
	public Rule TermList(){
		Var<List<Expr>> temp = new Var<List<Expr>>(new ArrayList<Expr>());
		return Sequence(Term()
						,temp.get().add((Expr)pop())
						,push(temp.get())
						,ZeroOrMore(',', 	TermList()
									,temp.clear()
									,temp.set((List<Expr>)pop(1))
									,temp.get().addAll((List<Expr>)pop())
									,push(temp.get()									
						)));
	}
	
	public Rule TermList(Var<Integer> counter){
		return Sequence(Term()
						//,counter.set(counter.get()+1)
						,ZeroOrMore(',', TermList(counter)));
	}
	
	/*
	 * Expressing with ^. For now only takes 1 term and a letter for description
	 * eg. ^a(Term())
	 * */
	public Rule Predicate(){
		Var<Character> label = new Var<Character>();

		return Sequence('^',CharRange('a', 'z')
						, label.set(matchedChar())
						,'(',TermList(),')'
						, push(new PredicateExpr(label.get().toString(), (List<Expr>)pop()))
				);
	}
	
	public Rule Formula(){
		Var<Character> op = new Var<Character>();

		return 	FirstOf(
					Predicate(),
					Sequence(Term(), ZeroOrMore(Sequence(
												AnyOf("+*") 
												, op.set(matchedChar()) 										
												, Formula()
												, swap() && push(matchBinary(op.get(), (Expr)pop(), (Expr)pop()))
												)))
					,Sequence('/','(',Formula(), push(new NotExpr((Expr) pop())),')')
					,Sequence('%',Variable(),push((new VarExpr(match()))),'(',Formula(),swap() && push(new ForAllExpr((VarExpr)pop(),(Expr)pop())),')') //for all
					,Sequence('$',Variable(),push((new VarExpr(match()))),'(',Formula(),swap() && push(new ExistsExpr((VarExpr)pop(),(Expr)pop())),')')	//every
					//,Sequence(Formula(),'=',Formula())
				);
	}
	
	protected Expr matchBinary(Character matchedChar, Expr left, Expr right) {
		// TODO Auto-generated method stub
		Expr binOp;
		if(matchedChar.toString().equals("+")) binOp = new OrExpr(left, right);
		else binOp = new AndExpr(left, right);
		return binOp;
	}

	//**************** MAIN ****************

    public static void main(String[] args) {
        main(FolParser0.class);
    }
}
