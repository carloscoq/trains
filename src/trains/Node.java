package trains;
/**
 * A node represents an origin or a destiny. Each one should be listed on a Hashmap.
 * @author carlos coqueiro <ces.coqueiro@gmail.com>
 * @version 1.0
 *  
 */
public class Node {
	/**
	 * The name(letter) of the city
	 */
    private String name;
    /**
     * To be tested if a node has already been visited by the hashtable iterators
     */
    private boolean visited;
 
    /**
     * <h3>Constructor</h3>
     * <p>Instantiates a node and the given name</p>
     * @param name the name of the node. Should be a letter.
     */
    public Node(String name) {
        this.name = name;
        this.setVisited(false);
    }
 /*
  * (non-Javadoc)
  * @see java.lang.Object#equals(java.lang.Object)
  */
    @Override
    public boolean equals(Object ob) {
        if (ob == null || ob.getClass() != getClass()) {
            return false;
        }
        Node nd = (Node)ob;
        return this.name.equals(nd.name);
    }
 /*
  * (non-Javadoc)
  * @see java.lang.Object#hashCode()
  */
    @Override
    public int hashCode() {
        if(this.name == null) return 0;
        return this.name.hashCode();
    }
    
    /*
     * Getters and Setters
     */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public boolean isVisited() {
		return visited;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
}