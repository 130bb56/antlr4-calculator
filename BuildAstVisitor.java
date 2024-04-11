import org.antlr.v4.runtime.tree.ParseTree;
import java.util.HashMap;
import java.util.Map;

public class BuildAstVisitor extends ExprBaseVisitor<AstNodes> {
    // stores all the variables declared in the program so far
    private Map<String, Double> vars = new HashMap<>();
    int depth;
    @Override
    public AstNodes visitProg(ExprParser.ProgContext ctx) {
        AstNodes resNode = new AstNodes();
        for (ParseTree child : ctx.children) {
            depth = -1;
            resNode = visit(child);
            if (resNode == null) {
                continue;
            } else if (!resNode.type.equals("NUM")) {
                Evaluate.finalValue.push(0.0);
            } else {
                Evaluate.finalValue.push(resNode.value);
            }
        }
        return resNode;
    }

    @Override
    public AstNodes visitVariableExpr(ExprParser.VariableExprContext ctx) {
        String variable = ctx.getChild(0).getText();
        Double value = vars.get(variable);
        AstNodes resNode = new AstNodes(variable, "VAR", value, ++depth);
        AstCall.printLog.push(resNode);
        return resNode;
    }
    @Override
    public AstNodes visitInfixExpr(ExprParser.InfixExprContext ctx) {
        String op = ctx.getChild(1).getText();
        depth++;

        if (op.equals("*")) {
            op = "MUL";
        } else if (op.equals("/")) {
            op = "DIV";
        } else if (op.equals("+")) {
            op = "ADD";
        } else {
            op = "SUB";
        }

        AstNodes resNode = new AstNodes(op, "NUM", 0, depth);
        AstCall.printLog.push(resNode);

        AstNodes left = visit(ctx.getChild(0));
        depth--;
        AstNodes right = visit(ctx.getChild(2));
        depth--;

        resNode =  new Evaluate().evaluate(left.value, right.value, op, depth);
        return resNode;
    }

    @Override
    public AstNodes visitParensExpr(ExprParser.ParensExprContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public AstNodes visitAssignExpr(ExprParser.AssignExprContext ctx) {
        String variable = ctx.getChild(0).getText();
        String type = ctx.getChild(1).getText();
        double value = Double.parseDouble(ctx.num().getText());
        this.vars.put(variable, value);
        type = "ASSIGN";
        AstNodes resNode = new AstNodes(variable, type, value, 0);
        AstCall.printLog.push(resNode);
        return resNode;
    }


    @Override
    public AstNodes visitNum(ExprParser.NumContext ctx) {
        double num = Double.parseDouble(ctx.getChild(0).getText());
        AstNodes resNode = new AstNodes(null, "NUM", num, ++depth);
        AstCall.printLog.push(resNode);
        return resNode;
    }
}


