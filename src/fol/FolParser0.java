package fol;
import org.parboiled.BaseParser;
import org.parboiled.Rule;
import org.parboiled.annotations.BuildParseTree;

import fol.ast.ConstExpr;
import fol.ast.Expr;
import fol.ast.NotExpr;
import fol.ast.VarExpr;

@BuildParseTree

public class FolParser0 extends FolParser<Object> {
	@Override
	public Rule InputLine(){
		return Sequence(Expression(), EOI);
	}
	
	public Rule Expression(){
		return FirstOf(
				Sequence(Formula(),'=',Formula()),
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
		return Sequence('&',CharRange('a','z'),'(',TermList(),')');
	}
	
	public Rule Term(){
		return FirstOf(Constant(), Variable(), Function());
	}
	
	public Rule TermList(){
		return Sequence(Term(),ZeroOrMore(',', TermList()));
	}
	
	/*
	 * Expressing with ^. For now only takes 1 term and a letter for description
	 * eg. ^a(Term())
	 * */
	public Rule Predicate(){
		return Sequence('^',CharRange('a', 'z'),'(',TermList(),')');
	}
	
	public Rule Formula(){
		return 	FirstOf(
					Predicate(),
					Sequence(Term(), ZeroOrMore(Sequence(
												AnyOf("+*"), Formula()
												)))
					,Sequence('/','(',Formula(), push(new NotExpr((Expr) pop())),')')
					,Sequence('%',Variable(),'(',Formula(),')') //for all
					,Sequence('$',Variable(),'(',Formula(),')')	//every
					//,Sequence(Formula(),'=',Formula())
				);
	}
	
	//**************** MAIN ****************

    public static void main(String[] args) {
        main(FolParser0.class);
    }
}
