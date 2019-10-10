
/**
 * A circuit representig the ceiling of a roof, can be represented as a tree or directly with a image of the ceiling.
 * 
 * @author (Daniel Walteros y Nicolas Aguilera) 
 * @version (31 of January of 2019)
 */
public class Ceiling
{
    private String color;
    private static final String[] colors = {"red", "yellow", "blue", "green", "magenta", "black", "darkGray", "lightGray", "orange", "cyan"};
    private Rectangle square;
    private Circle[] nodes;
    private Circle[] nodesLayer;
    private String lcolor;
    private int[] layers;
    private int aux;
    private int y;
    private int x;
    private int size;
    private Rectangle[] squares;
    private Tree tree;
    /**
     * Constructor for objects of class ceiling
     * 
     * @param layers a list of numbers where each number represents the resistance
     * @param color the color that represents the exterior from the roof
     */
    public Ceiling(int[] layers, String color){
        size=layers.length;
        square= new Rectangle();
        y=0;
        aux=0;
        x=0;
        this.layers = layers;
        lcolor = color;
        squares = new Rectangle[10];
        nodes = new Circle[10];
        nodesLayer = new Circle[10];
        makeTree();
    }
    
    /**
     * Show the roof like a ceiling
     */
    public void showCeiling(){
        square.changeColor(lcolor);
        square.moveHorizontal(-10);
        square.moveVertical(15);
        square.changeSize(30*(size+1),60);
        square.makeVisible();
        y=0;
        for(int i=0;i<size;i++){
            squares[i] = new Rectangle();
            squares[i].moveVertical(y+30);
            squares[i].changeColor(colors[layers[i]-1]);
            squares[i].makeVisible();
            y+=30;
        }
    }
    /**
     * Show the resistances of the roof like a tree
     * 
     * @param x the horizontal coordinate of the tree in the canvas.
     */
    public void showTree(int x){
        makeTInvisible();
        //makeTree();
        tree.showTree(x,nodes,nodesLayer,lcolor);
    }
    /**
     * Adds a new resistance into the roof
     * 
     * @param layer the new resistance for the roof
     */
    public void add(int layer){
        makeCInvisible();
        makeTInvisible();
        int[] newLayers = new int[size+1];
        for(int j=0;j<size;j++){
            newLayers[j] = layers[j];
        }
        newLayers[size] = layer;
        if (squares[aux]!=null){
            int movx;
            int movy;
            movx = squares[aux].getX();
            movy = squares[aux].getY();
            squares[size] = new Rectangle();
            squares[size].changeColor(colors[layer-1]);
            squares[size].moveHorizontal(movx-70);
            squares[size].moveVertical(movy-15);
            size+=1;
            square.changeSize(30*(size+1),60);
            aux=size-1;
            for(int i=0;i+1<size;i++){
                if (squares[i]!=null){
                squares[i].moveVertical(30);
                }
            }
            makeCVisible();
        }
        layers = newLayers;
        makeTree();
    }
    /**
     * Moves the ceilling Horizontally and Vertically
     * 
     * @param x Horizontal distance in pixels 
     * @param y Vertical distance in pixels 
     */
    public void move(int x,int y){
        makeCInvisible();
        square.moveHorizontal(x);
        square.moveVertical(y);
        for(int i=0;i<size;i++){
            squares[i].moveHorizontal(x);
            squares[i].moveVertical(y);
        }
        makeCVisible();
    }
    /**
     * Moves the tree Horizontally and Vertically
     * 
     * @param x Horizontal distance in pixels 
     * @param y Vertical distance in pixels 
     */
    public void moveTree(int x,int y){
        makeTInvisible();
        for(int i=0;i<10;i++){
            if (nodes[i]!=null){
                nodes[i].moveHorizontal(x);
                nodesLayer[i].moveHorizontal(x);
                nodes[i].moveVertical(y);
                nodesLayer[i].moveVertical(y);
            }   
        }
        makeTVisible();
    }
    /**
     * Makes the ceiling invisible.
     * 
     */
    private void makeCInvisible(){
        square.makeInvisible();
        for(int i=0;i<size;i++){
            if(squares[i]!=null){
                squares[i].makeInvisible();
            }
        }
    }
    /**
     * Makes the ceiling visible.
     * 
     */
    private void makeCVisible(){
        square.changeSize(30*(size+1),60);
        square.makeVisible();
        for(int i=0;i<size;i++){
            if (squares[i]!=null){
                squares[i].makeVisible();
            }
        }
    }
    /**
     * Makes the tree invisible.
     * 
     */
    private void makeTInvisible(){
        for(int i=0;i<10;i++){
            if (nodes[i] != null){
                nodes[i].makeInvisible();
                nodesLayer[i].makeInvisible();
            }
        }
    }
    /**
     * Makes the tree visible.
     * 
     */
    private void makeTVisible(){
        for(int i=0;i<10;i++){
            if (nodes[i] != null){
                nodesLayer[i].makeVisible();
                nodes[i].makeVisible();
            }
        }
    }
    /**
     * Generates the tree of the ceiling
     * 
     */
    private void makeTree(){
        tree = new Tree(layers[0]);
        for (int i=1;i<layers.length;i++){
            Node node = new Node(layers[i]);
            tree.addNode(node);
        }
    }
    /**
     * Deletes the first layer of the ceiling.
     * 
     */
    public void delete(){
        if(size==1){
            size=0;
            y=0;
            aux=0;
            layers = null;
            lcolor = color;
            squares = new Rectangle[10];
            nodes = new Circle[10];
            System.out.println("Ya no se pueden eliminar mas elementos");
        }
        else{
            makeCInvisible();
            makeTInvisible();
            Node newRoot = tree.getRoot();
            int[] newLayers = new int[size-1];
            for (int i=0 ; i<size-1 ; i++){
                newLayers[i]=layers[i+1];
                squares[i].moveVertical(30);
            }
            size-=1;
            if (newRoot.getLeft() != null){
                newRoot = newRoot.getLeft();
            }
            else{
                newRoot = newRoot.getRight();
            }
            layers=newLayers;
            makeCVisible();
            nodes = new Circle[10];
            tree.setRoot(newRoot);
            makeTree();
        }
    }
    /**
     * Gets the tree of the ceiling.
     * 
     * @return The tree of the ceiling in Tree Class.
     */
    public Tree getTree(){
        return tree;
    }
    /**
     * Gets the amount of resistance that have the ceiling.
     * 
     * @return The resistance that have all the layers of the ceiling(all added).
     */
    public int getResistance(){
        int resistance=0;
        for (int i=0;i<size;i++){
            resistance+=layers[i];
        }
        return resistance;
    }
    public void makeGInvisible(String visualizacion){
        if(visualizacion=="ceiling"){
            makeTInvisible();
        }
        else{
            makeCInvisible();
        }
    }
        
}
