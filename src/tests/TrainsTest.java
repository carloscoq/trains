package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
 
import org.junit.BeforeClass;
import org.junit.Test;

import trains.Edge;
import trains.Node;
import trains.TrainRoutes;
 
public class TrainsTest {
    static TrainRoutes graph;
    static Node a, b, c, d, e;
 
    @BeforeClass
    public static void setUpBeforeClass(){
        graph = new TrainRoutes(); 
 
        a = new Node("A");
        b = new Node("B");
        c = new Node("C");
        d = new Node("D");
        e = new Node("E");
 
        /*input of the exercise
        Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7*/
        graph.getRoutes().put(a, new Edge(a, b, 5).next(new Edge(a, d, 5).next(new Edge(a, e, 7))));
        graph.getRoutes().put(b, new Edge(b, c, 4));
        graph.getRoutes().put(c, new Edge(c, d, 8).next(new Edge(c, e, 2)));
        graph.getRoutes().put(d, new Edge(d, c, 8).next(new Edge(d, e, 6)));
        graph.getRoutes().put(e, new Edge(e, b, 3));
    }
 
    /*
     * The Distance of the route A-B-C
     */
    @Test 
    public void testCase1(){
        ArrayList<Node> route = new ArrayList<Node>();
        route.add(a);
        route.add(b);
        route.add(c);
        assertEquals("9", graph.totalDistance(route));
    }
 
    /*
     * The Distance of the route A-D
     */
    @Test
    public void testCase2() {
        ArrayList<Node> route = new ArrayList<Node>();
        route.add(a);
        route.add(d);
        assertEquals("5", graph.totalDistance(route));
    }
    /*
     * The Distance of the route A-D-C
     */
    @Test
    public void testCase3()  {
        ArrayList<Node> route = new ArrayList<Node>();
        route.add(a);
        route.add(d);
        route.add(c);
        assertEquals("13", graph.totalDistance(route));
    }
    /*
     * The Distance of the route A-E-B-C-D
     */
    @Test
    public void testCase4() {
        ArrayList<Node> route = new ArrayList<Node>();
        route.add(a);
        route.add(e);
        route.add(b);
        route.add(c);
        route.add(d);
        assertEquals("22", graph.totalDistance(route));
    }
    /*
     * The Distance of the route A-E-D
     */
    @Test
    public void testCase5()  {
        ArrayList<Node> route = new ArrayList<Node>();
        route.add(a);
        route.add(e);
        route.add(d);
        assertEquals("NO SUCH ROUTE", graph.totalDistance(route));
    }
    /*
     * The number of trips starting at C and ending at C with a maximum of 3 stops
     */
    @Test
    public void testCase6() {
        assertEquals("2",graph.tripsTotal(c, c,0, 3));
    }
    /*
     * The number of trips starting at A and ending at C with a total of 4 stops
     */
    @Test
    public void testCase7(){
        assertEquals("4", graph.tripsTotal(a, c,4, 4));
    }
    /*
     * The length of the shortest route from A to C
     */
    @Test
    public void testCase8() {
        assertEquals("9", graph.shortestRoute(a, c));
    }
    /*
     * The length of the shortest route from B to B
     */
    @Test
    public void testCase9() throws Exception {
        assertEquals("9", graph.shortestRoute(b, b));
    }
    /*
     * The number of different routes from C to C with a distance of less than 30
     */
    @Test
    public void testCase10() throws Exception {
        assertEquals(7, graph.numRoutesWithin(c, c, 30));
    }

 
}