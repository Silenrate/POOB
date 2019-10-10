
/**
 * A binary tree that represents data in base for thier insertion order.
 * 
 * @author (Daniel Walteros y Nicolas Aguilera) 
 * @version (29 of January of 2019)
 */
public class Tree{
    private static final String[] colors = {"red", "yellow", "blue", "green", "magenta", "black", "darkGray", "lightGray", "orange", "cyan"};
    private Node root;
    

    /**
     * Constructor for objects of class Tree
     * 
     * @param value  an integer value that represents the root for the tree.
     */
    public Tree(int value){
        root = new Node(value);
    }

    /**
     * Gets the root of the tree
     * 
     * @return  The node of the root of the tre
     */
    public Node getRoot(){
        return root;
    }
    /**
     * Sets the root of the tree
     * 
     * @param root The new root of the tree(is class Node).
     */
    public void setRoot(Node root){
        this.root = root;
    }
    /**
     * Adds a new node to the tree.
     * 
     * @param node The new node that is going to be add.
     * @param root The root of the tree that is going to be add the new node.
     */
    private void addNode(Node node,Node root){
        if (root == null){
            setRoot(node);
        }
        else {
            if (node.getValue() < root.getValue() ) {
                if (root.getLeft() == null) {
                    root.setLeft(node);
                }
                else {
                    addNode(node,root.getLeft());
                }
            }
            else{
                if (root.getRight() == null){
                    root.setRight(node);
                }
                else {
                    addNode(node, root.getRight());
                }
            }
        }
    }
    /**
     * Adds a new node to the tree.
     * 
     * @param node The new node that is going to be add.
     */
    public void addNode(Node node) {
        addNode(node,root);
    }
    /**
     * Prints the tree in a String.
     * 
     * return The Class Tree in the format {root:node,node}.
     */
    public String toString(){
        if (root == null){
            return "{}";
        }
        else{
            return root.toString();
        }
    }
    /**
     * Show the tree in a canvas.
     * 
     * @param nodes The list of Circles that represents each node.
     * @param lcolor The color of the ceiling.
     */
    public void showTree(int x,Circle[] nodes,Circle[] nodesLayer, String lcolor){
        root.showTree(x,0,30,nodes,nodesLayer,lcolor);
    }
}
