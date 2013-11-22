package trains;

import java.util.ArrayList;
import java.util.Hashtable;
 /**
  * <p>Implements all the solutions to the trains problem</p>
  * <p>Contains as parameter a Hashtable with the nodes and its corresponding edges.</p>
  * 
  * @author carlos coqueiro <ces.coqueiro@gmail.com>
  * @version 1.0
  * 
  */
public class TrainRoutes {
    private Hashtable<Node, Edge> routes;
 
    /**
     * Constructor initializes a blank hashtable
     */
    public TrainRoutes() {
        this.setRoutes(new Hashtable<Node, Edge>());
    }
 

/**
 * Sums the distance between all the nodes of the hashmap 
 * @param townsRoute the nodes corresponding to the cities.
 * <p>Example: to obtain the distance between A and C:</p>
 * <code>
 *		ArrayList<Node> route = new ArrayList<Node>();</br>
 *		route.add(a);</br>
 *		route.add(b);</br>
 *		route.add(c);</br>
 *		graph.distanceBetween(route);
 * </code>
 * @return the total distance between the first and last node of the hashmap. Returns -1 if there is no route
 */
    public int distanceBetween(ArrayList<Node> townsRoute) {
       /**
        * No distance between 0 or 1 node
        */
        if(townsRoute.size() < 2){
            return 0;
        }
        
        int distance = 0;
        int depth = 0;
        int i = 0;
         
        /*
         * Check if the node exists in the Hashtable
         */
        while(i < townsRoute.size() - 1) {
            if(this.getRoutes().containsKey(townsRoute.get(i))) {
                Edge route = this.getRoutes().get(townsRoute.get(i));
                /*
                 * check there is a route from key to the next node. If yes, add the distance and 
                 * increment the depth count
                 */
                while(route != null) {
                    if(route.getDestination().equals(townsRoute.get(i + 1))) {
                        distance += route.getWeight();
                        depth++;
                        break;
                    }
                    route = route.getNext();
                }
            }
            else{
            		distance = -1;
            }
            i++;
        }
        /*If edge depth is not equal to vertex - 1,
         * then it is safe to assume that one ore more
         * routes do not exist
         */
        if(depth != townsRoute.size() - 1){
        	distance = -1;
            }
 
        return distance;
    }
    
    /**
     *Wrapper method of distanceBetween() that gets the distance between the route and returns a String according to the distance. 
     * @param townsRoute a list of nodes
     * @return returns the distance between the first and last node of the list. Returns "NO SUCH ROUTE" if there is no route
     * @see distanceBetween()
     */
    public String totalDistance(ArrayList<Node> townsRoute) {
    	
    	Integer total = distanceBetween(townsRoute);
    	
    	String message = "";
    	
    	if(total > 0){
    		message = total.toString();
    	}else{
    		message = "NO SUCH ROUTE";
    	}
    	
    	return message;
    }
 
/**
 * Wrapper method returns the number of trips starting from the start node and ending at the end node, with the maximum stops given
 * @param start the start of the trip
 * @param end the end of the trip
 * @param maxStops maximum number of stops
 * @return the number of trips or "NO SUCH ROUTE" if numRoutes = -1
 */
    public String tripsTotal(Node start, Node end,int depth, int maxStops){
        //Wrapper to maintain depth of traversal
        String numRoutes = "";
        Integer nRoutes = countTrips(start, end, depth, maxStops);
        
        if(nRoutes>=0){
        	numRoutes = nRoutes.toString();
        }else{
        	numRoutes = "NO SUCH ROUTE";
        }
        
        return numRoutes;
    }
 
/**
 * Search the number of routes between the start node and end node, with the maximum of stops given
 * @param start the start of the trip
 * @param end the end of the trip
 * @param depth de depth of the traversal trough the trip
 * @param maxStops the maxmimum number of stops
 * @return
 * @throws Exception
 */
    private int countTrips(Node start, Node end, int depth, int maxStops){
        int trips = 0;
        //Check if start and end nodes exists in route table
        if(this.getRoutes().containsKey(start) && this.getRoutes().containsKey(end)) {
            /*
             * If start node exists then traverse all possible
             * routes and for each, check if it is destination
             * If destination, and number of stops within
             * allowed limits, count it as possible route.
             */
            depth++;
            if(depth > maxStops){        //Check if depth level is within limits
                return 0;
            }
            start.setVisited(true);        //Mark start node as visited
            Edge edge = this.getRoutes().get(start);
            while(edge != null) {
                /* If destination matches, we increment route
                 * count, then continue to next node at same depth
                 */
                if(edge.getDestination().equals(end)) {
                    trips++;
                    edge = edge.getNext();
                    continue;
                }
                /* If destination does not match, and
                 * destination node has not yet been visited,
                 * we recursively traverse destination node
                 */
                else if(!edge.getDestination().isVisited()) {
                    trips += countTrips(edge.getDestination(), end, depth, maxStops);
                    depth--;
                }
                edge = edge.getNext();
            }
        }
        else{
            return -1;
        }
        /*
         * Before exiting this recursive stack level,
         * we mark the start node as visited.
         */
        start.setVisited(false);
        return trips;
    }
 
/**
 * Wrapper for findShortestRoute
 * @param start the starting node
 * @param end the destination node
 * @return the length of the shortest route between start and end
 * 
 */
    public String shortestRoute(Node start, Node end) {
        //Wrapper to maintain weight
        Integer shortResult =  findShortestRoute(start, end, 0, 0);
        String shortestRouteResult = "";
        
        if(shortResult>=0){
        	shortestRouteResult = shortResult.toString();
        }else{
        	shortestRouteResult = "NO SUCH ROUTE";
        }
        
        return shortestRouteResult;
 
    }
 
/**
 * Gets the shortest route between two nodes
 * @param start the starting node
 * @param end the ending node
 * @param weight the distance(length) of the actual route
 * @param shortestRoute the shortest route
 * @return the shortesRoute. -1 if there is no route
 */
    private int findShortestRoute(Node start, Node end, int weight, int shortestRoute){
        /*
         * Check if start and end nodes exists in routes table
         */
        if(this.getRoutes().containsKey(start) && this.getRoutes().containsKey(end)) {

            start.setVisited(true);       
            Edge edge = this.getRoutes().get(start);
            while(edge != null) {
                /*
                 * 
                 * Increment weight if node had already been visited or is the destination
                 */
                if(edge.getDestination() == end || !edge.getDestination().isVisited())
                    weight += edge.getWeight();
 
              /*
               * Compare actual shortest route to the actual route weight
               */
                if(edge.getDestination().equals(end)) {
                    if(shortestRoute == 0 || weight < shortestRoute)
                        shortestRoute = weight;
                    start.setVisited(false);
                    return shortestRoute;             
                 }
                
                /* 
                 * Recursively traverse destination node
                 */
                else if(!edge.getDestination().isVisited()) {
                    shortestRoute = findShortestRoute(edge.getDestination(), end, weight, shortestRoute);
                    weight -= edge.getWeight();
                }
                edge = edge.getNext();
            }
        }
        else
            return -1;
 
        /*
         * Before exiting this recursive stack level, mark the start node as visited.
         */
        start.setVisited(false);
        return shortestRoute;
 
    }
 
/**
 * Wrapper for the method findNumRoutesWithin
 * @param start
 * @param end
 * @param maxDistance
 * @return
 */
    public int numRoutesWithin(Node start, Node end, int maxDistance){
        //Wrapper to maintain weight
        return findnumRoutesWithin(start, end, 0, maxDistance);
    }
 
/**
 * Finds the number of different routes between the given nodes with the specified max distance
 * @param start
 * @param end
 * @param weight
 * @param maxDistance
 * @return int routes
 */
    private int findnumRoutesWithin(Node start, Node end, int weight, int maxDistance) {
        int routes = 0;
        //Check if start and end nodes exists in route table
        if(this.getRoutes().containsKey(start) && this.getRoutes().containsKey(end)) {
            /*
             * If start node exists then traverse all possible
             * routes and for each, check if it is destination
             */
            Edge edge = this.getRoutes().get(start);
            while(edge != null) {
                weight += edge.getWeight();
                /* If distance is under max, keep traversing
                 * even if match is found until distance is > max
                 */
                if(weight <= maxDistance) {
                    if(edge.getDestination().equals(end)) {
                        routes++;
                        routes += findnumRoutesWithin(edge.getDestination(), end, weight, maxDistance);
                        edge = edge.getNext();
                        continue;
                    }
                    else {
                        routes += findnumRoutesWithin(edge.getDestination(), end, weight, maxDistance);
                        weight -= edge.getWeight();    //Decrement weight as we backtrack
                    }
                }
                else
                    weight -= edge.getWeight();
 
                edge = edge.getNext();
            }
        }
        else
            routes=-1;
 
        return routes;
 
    }    
    
    /*
     * Getters and Setters
     */
    public void setRoutes(Hashtable<Node, Edge> routes) {
		this.routes = routes;
	}

	public Hashtable<Node, Edge> getRoutes() {
		return routes;
	}
}