import java.util.Stack;

/*

print the Ast that we build in BuildAstVisitor.java

*/
public class AstCall {
    static public Stack<AstNodes> printLog = new Stack<>();
    public void callNode(AstNodes node) {
        StringBuilder gap = new StringBuilder();
        gap.append("\t".repeat(node.depth));
        switch (node.type) {
            case "ASSIGN": {
                System.out.println("ASSIGN");
                System.out.printf("\t%s\n\t", node.varName);
                System.out.println(node.value);
                break;
            }
            case "VAR": {
                System.out.println(gap + node.varName);
                break;
            }
            case "NUM" : {
                if (node.varName != null) {
                    if ("MUL".equals(node.varName)) {
                        System.out.println(gap + "MUL");
                    } else if ("DIV".equals(node.varName)) {
                        System.out.println(gap + "DIV");
                    } else if ("ADD".equals(node.varName)) {
                        System.out.println(gap + "ADD");
                    } else {
                        System.out.println(gap + "SUB");
                    }
                }
                else {
                    System.out.printf("%s", gap);
                    System.out.println(node.value);
                }
            }

        }
    }
    public void Call() {
        for (AstNodes log : printLog) {
            callNode(log);
        }
    }
}
