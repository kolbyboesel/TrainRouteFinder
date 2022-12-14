import java.io.FileNotFoundException;
import java.util.*;
import java.util.ArrayList;
import java.util.Set;

/**
 * This class is the backend class for the Train Route Finder Application. It uses the Dijkstra's algorithm
 * developed by the Algorithm Engineer to find the shortest path between the origin and destination station
 * It will be getting inputs from the frontend and will be used by the frontend
 *
 * @author Samanyu Kaushik
 */
public class TrainBackend {

    private String origin;
    private String destination;
    private AE_Graph AE;
    private TrainDataLoader dataLoader = new TrainDataLoader();

    /**
     * This acts as the constructor for the TrainBackend class given the parameters
     *
     * @param origin the origin point for the path (starting point)
     * @param destination the final destination for the path (ending point)
     */

    public TrainBackend() {
        this.origin = null;
        this.destination = null;

        try {
            dataLoader.loadData("/Users/kolbyboesel/eclipse-workspace/CS400Graph/src/TrainData.gv"); //TODO
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        AE = new AE_Graph();
    }
    public TrainBackend(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
        try {
            dataLoader.loadData("/Users/kolbyboesel/eclipse-workspace/CS400Graph/src/TrainData.gv"); //TODO
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        AE = new AE_Graph();
    }

    
    /**
     * This method sets the origin of the backend object. This will be used by the frontend to get user input
     * which will be used to set the origin station
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    
    /**
     * This method sets the destination of the backend object. This will be used by the frontend to get user input
     * which will be used to set the destination station
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * This method gets the origin of the backend object.
     */
    public String getOrigin() {
        return this.origin;
    }

    /**
     * This method gets the destination of the backend object.
     */
    public String getDestination() {
        return this.destination;
    }

    
    /**
     * The getter method that returns a String array of stations that will be used by frontend to display
     * the list of stations available to choose from
     */
    public String[] getStations() {
        /* 
        ArrayList<Node> stations = new ArrayList<Node>();
        stations = (ArrayList<Node>) dataLoader.getEdges();

        Set<String> paths = new HashSet<String>();

        for (int i = 0; i < stations.size(); i++) {
            paths.add(stations.get(i).getStartLocation());
            paths.add(stations.get(i).getEndLocation());
        }

        return (String[]) paths.toArray();
        */

        ArrayList<String> stations = dataLoader.getVertices();
        String[] stationStringArray = new String[stations.size()];

        for (int i = 0; i < stationStringArray.length; i++) {
            stationStringArray[i] = stations.get(i);
        }

        return stationStringArray;
    }

    /**
     * This method returns a List of nodes that contains the shortest path from the origin station to the
     * destination. It uses the dijkstra's algorithm implemented by the algorithm engineer to achieve the task
     *
     * @param currStation the origin station
     * @param destinationStation final destination station
     * @return shortestPath that is the shortest path between the given edges
     */
    public List<Node> getShortestPath(String currStation, String destinationStation) {
        ArrayList<String> vertices = dataLoader.getVertices();
        ArrayList<Node> edges = dataLoader.getEdges();

        // Insert vertices.
        for (int i = 0; i < vertices.size(); i++) {
            AE.insertVertex(vertices.get(i));
        }

        // Insert edges.
        for (int i = 0; i < edges.size(); i++) {
            AE.insertEdge(edges.get(i).getStartLocation().trim(), edges.get(i).getEndLocation().trim(),
                    edges.get(i).getTime());
        }

        List<Node> shortestPath = AE.shortestPath(this.origin, this.destination);
        return shortestPath;
    }

    public double getShortestPathLength(String currStation, String destinationStation) {
        return AE.getPathCost(currStation, destinationStation);
    }
}
