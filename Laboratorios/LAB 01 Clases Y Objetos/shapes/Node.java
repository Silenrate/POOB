
/**
 * An integer value with two subnodes descendig from him.
 * 
 * @author (Daniel Walteros y Nicolas Aguilera) 
 * @version (29 of January of 2018)
 */
public class Node{
    private static final String[] colors = {"red", "yellow", "blue", "green", "magenta", "black", "darkGray", "lightGray", "orange", "cyan"};
    private int value;
    private Node source;
    private Node left;
    private Node right;
    /**
     * Constructor for objects of class Node
     * 
     * @param value The value for the node
     */
    public Node(int value)
    {
        this.value = value;
    }
    /**
     * Sets a new value for the node
     * 
     * @param  value   The new value of the node.
     */
    public void setValue(int value){
        this.value = value;
    }
    /**
     * Sets a new node for the left descendence of the node
     * 
     * @param  value  The new descendence of the node.
     */
    public void setLeft(Node value){
        left = value;
    }
    /**
     * Sets a new node for the right descendence of the node
     * 
     * @param  value  The new descendence of the node.
     */
    public void setRight(Node value){
        right = value;
    }
    /**
     * Sets a new value for the node source
     * 
     * @param  value   The new node for the source of the node.
     */
    public void setSource(Node value){
        source = value;
    }
    /**
     * Gets the value of the node
     * 
     * @return  The integer value of the Node.
     */
    public int getValue(){
        return value;
    }
    /**
     * Gets the value of the left-child node of the node.
     * 
     * @return  The Node on the left side.
     */
    public Node getLeft(){
        return left;
    }
    /**
     * Gets the value of the right-child node of the node.
     * 
     * @return  The Node on the right side.
     */
    public Node getRight(){
        return right;
    }
    /**
     * Gets the node of the node's source
     * 
     * @return  The node in the source.
     */
    public Node getSource(){
        return source;
    }
    /**
     * Prints the Node in the String form {*:{left},{right}}
     * 
     * @return The String repŕesentaion of the node.
     */
    public String toString(){
        if (left == null && right == null){
            return "{*}";
        }
        else if (left == null){
            return "{*:{},"+right.toString()+"}";
        }
        else if (right == null){
            return "{*: "+left.toString()+",{}}";
        }
        else{
            return "{*: "+left.toString()+","+right.toString()+"}";
        }
    }
    /**
     * Show the node and their descendance in tree form.
     * 
     */
    public void showTree(int x,int y,int r,Circle[] nodes,Circle[] nodesLayer,String lcolor){
        nodes[value-1] = new Circle();
        nodesLayer[value-1] = new Circle();
        nodes[value-1].moveHorizontal(120+x);
        nodesLayer[value-1].moveHorizontal(118+x);
        nodes[value-1].moveVertical(y);
        nodesLayer[value-1].moveVertical(y-2);
        nodes[value-1].changeSize(r);
        nodesLayer[value-1].changeSize(r+4);
        nodes[value-1].changeColor(colors[value-1]);
        nodesLayer[value-1].changeColor(lcolor);
        nodesLayer[value-1].makeVisible();
        nodes[value-1].makeVisible();
        if (left != null) {
            left.showTree(x-(r/2),y+40,r-5,nodes,nodesLayer,lcolor);
        }
        if (right != null) {
            right.showTree(x+(r/2),y+40,r-5,nodes,nodesLayer,lcolor);
        }
        }
}
