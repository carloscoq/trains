package trains;

import java.util.ArrayList;

public class RoutesMain {


	public static void main(String[] args) {
		TrainRoutes graph = new TrainRoutes();

		Node a = new Node("A");
		Node b = new Node("B"); 
		Node c = new Node("C");
		Node d = new Node("D");
		Node e = new Node("E");

		graph.getRoutes().put(a, new Edge(a, b, 5).next(new Edge(a, d, 5).next(new Edge(a,e,7))));
		graph.getRoutes().put(b, new Edge(b, c, 4));
		graph.getRoutes().put(c, new Edge(c, d, 8).next(new Edge(c, e, 2)));
		graph.getRoutes().put(d, new Edge(d, c, 8).next(new Edge(d, e, 6)));
		graph.getRoutes().put(e, new Edge(e, b, 3));


		/*
		 * Initializes the route A-B-C
		 */
		ArrayList<Node> routeABC = new ArrayList<Node>();
		routeABC.add(a);
		routeABC.add(b);
		routeABC.add(c);

		/*
		 * Initializes the route A-D
		 */
		ArrayList<Node> routeAD = new ArrayList<Node>();
		routeAD.add(a);
		routeAD.add(d);

		/*
		 * Initializes the route A-D-C
		 */
		ArrayList<Node> routeADC = new ArrayList<Node>();
		routeADC.add(a);
		routeADC.add(d);
		routeADC.add(c);

		/*
		 * Initializes the route A-E-B-C-D
		 */
		ArrayList<Node> routeAEBCD = new ArrayList<Node>();
		routeAEBCD.add(a);
		routeAEBCD.add(e);
		routeAEBCD.add(b);
		routeAEBCD.add(c);
		routeAEBCD.add(d);

		/*
		 * Initializes the route A-E-D
		 */
		ArrayList<Node> routeAED = new ArrayList<Node>();
		routeAED.add(a);
		routeAED.add(e);
		routeAED.add(d);


			/*
			 * 1: The distance of the route A-B-C
			 */
			System.out.println(graph.totalDistance(routeABC));
			/*
			 * 2: The distance of the route A-D
			 */
			System.out.println(graph.totalDistance(routeAD));
			/*
			 * 3: The distance of the route A-D-C
			 */
			System.out.println(graph.totalDistance(routeADC));
			/*
			 * 4: The distance of the route A-E-B-C-D
			 */
			System.out.println(graph.totalDistance(routeAEBCD));
			/*
			 * 5: The distance of the route A-E-D
			 */
			System.out.println(graph.totalDistance(routeAED));
			/*
			 * 6: The number of trips starting at C and ending at C with a maximum of 3 stops.
			 */
			System.out.println(graph.tripsTotal(c, c,0, 3));
			/*
			 * 7: The number of trips starting at A and ending at C with exactly 4 stops
			 */
			System.out.println(graph.tripsTotal(a, c, 4,4));
			/*
			 * 8: The length of the shortest route from A to C
			 */
			System.out.println(graph.tripsTotal(c, c,0, 3));
			/*
			 * 9: The length of the shortest route from A to C
			 */
			System.out.println(graph.tripsTotal(c, c,0,3));
			/*
			 * 10: The number of different routes from C to C with a distance of less than 30
			 */
			System.out.println(graph.tripsTotal(c, c,0,3));


	}

}
