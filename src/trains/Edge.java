package trains;


/**
 * An edge represents the route between two towns. The list of Edges should be set on a Hashmap
 * 
 * @author carlos coqueiro <ces.coqueiro@gmail.com>
 * @version 1.0
 * 
 **/
public class Edge {
    /**
     * The origin town
     * @see Node
     */
    private Node origin;
    /**
     * The destination town
     * @see Node
     */
    private Node destination;
    /**
     * The weight represents the distance between two towns
     */
    private int weight;
    /**
     * next possible route starting from the same origin
     */
    private Edge next;
    
    /**
     * <h3>Constructor</h3><p>The parameters where set in this order so the edge could be initialized in the problem's specified order: Origin,Destination,Weight</p>
     * <p>Example:</p> <p>Edge edge = new Edge(a,b,5)</p>
     * @param origin the origin node(city) indicated by the letters A to E
     * @param destination the destination node(city) indicated by the letters A to E
     * @param weight the weight of the edge, wich representes the distance between the cities
     */
    public Edge(Node origin, Node destination, int weight) {
        this.setOrigin(origin);
        this.setDestination(destination);
        this.setWeight(weight);
        this.setNext(null);
    }
    
    /**
     * Sets the next edge that starts from the same origin
     * @param Next edge, containing the same origin, next destination and its weight
     * @return the Edge object
     * 
     */
    public Edge next(Edge edge) {
        this.setNext(edge);
        return this;
    }

    /*
     * Getters and Setters
     */
	public void setOrigin(Node origin) {
		this.origin = origin;
	}

	public Node getOrigin() {
		return origin;
	}

	public void setDestination(Node destination) {
		this.destination = destination;
	}

	public Node getDestination() {
		return destination;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}

	public void setNext(Edge next) {
		this.next = next;
	}

	public Edge getNext() {
		return next;
	}
}