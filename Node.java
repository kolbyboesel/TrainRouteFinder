import java.util.ArrayList;
import java.util.List;

/**
 * Node object to store the information about an edge between two vertices.
 * @author Samanyu Kaushik
 */
public class Node {
    private String startLocation;
    private String endLocation;
    private int time;
    
    public Node(String startLocation, String endLocation, int time) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.time = time;
    }

    /**
     * Return the start location of this edge
     * @return start location of this edge
     */
    public String getStartLocation() {
        return this.startLocation;
    }
    /**
     * Return the end location of this edge
     * @return end location of this edge
     */
    public String getEndLocation() {
        return this.endLocation;
    }

    /**
     * Return the weight of this edge
     * @return weight of this edge
     */
    public int getDistance(List<Node> naam) {
        return this.time;
    }
    
    public String toString() {
    	String nodeData = startLocation.trim() + " " + endLocation.trim() + " " + time;
    	return nodeData;
    }

	public int getTime() {
		return this.time;
	}
}