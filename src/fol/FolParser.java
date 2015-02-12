package fol;
import static org.parboiled.errors.ErrorUtils.printParseErrors;
import static org.parboiled.support.ParseTreeUtils.printNodeTree;
import static org.parboiled.trees.GraphUtils.printTree;

import java.util.Scanner;

import org.parboiled.BaseParser;
import org.parboiled.Parboiled;
import org.parboiled.Rule;
import org.parboiled.annotations.BuildParseTree;
import org.parboiled.common.StringUtils;
import org.parboiled.errors.ErrorUtils;
import org.parboiled.parserunners.RecoveringParseRunner;
import org.parboiled.parserunners.ReportingParseRunner;
import org.parboiled.support.ParsingResult;
import org.parboiled.support.ToStringFormatter;
import org.parboiled.trees.GraphNode;

import fol.ast.Expr;


/**
 * Base class of all calculator parsers in the org.parboiled.examples.calculators package.
 * Simply adds the public static main entry point.
 *
 * @param <V> the type of the main value object created by the parser
 */
public abstract class FolParser<V> extends BaseParser<V> {

    public abstract Rule InputLine();

    @SuppressWarnings({"unchecked"})
    public static <V, P extends FolParser<V>> void main(Class<P> parserClass) {
        FolParser<V> parser = Parboiled.createParser(parserClass);

        while (true) {
            System.out.print("Enter a FOL expression (single RETURN to exit)!\n");
            String input = new Scanner(System.in).nextLine();
            if (StringUtils.isEmpty(input)) break;

            ParsingResult<?> result = new ReportingParseRunner(parser.InputLine()).run(input);

            if (result.hasErrors()) {
                System.out.println("\nParse Errors:\n" + printParseErrors(result));
            }

            Object value = result.parseTreeRoot.getValue();
            
            if (value != null) {
                String str = value.toString();
                int ix = str.indexOf('|');
                if (ix >= 0) str = str.substring(ix + 2); // extract value part of AST node toString()
                System.out.println(input + " = " + str + '\n');
            }
            /*if (value instanceof GraphNode) {
                System.out.println("\nAbstract Syntax Tree:\n" +
                        printTree((GraphNode) value, new ToStringFormatter(null)) + '\n');
            } */
        	if(value instanceof Expr){
        		System.out.println("\n Abstract Syntax Tree:\n"+value+"\n");
        	}else {
                System.out.println("\nParse Tree:\n" + printNodeTree(result) + '\n');
            }
            
        }
    }

}