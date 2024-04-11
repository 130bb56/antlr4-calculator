/*

define Ast Nodes

*/

public class AstNodes {
    public String varName;
    public String type;    // type = {"ASSIGN", "VAR", "NUM" } "OP" = "NUM" && node.varName != null
    public double value;
    public int depth;

    public AstNodes() {}
    public AstNodes(String varName, String type, double value, int depth) {
        this.varName = varName;
        this.type = type;
        this.value = value;
        this.depth = depth;
    }

}
