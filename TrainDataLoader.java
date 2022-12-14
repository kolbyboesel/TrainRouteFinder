import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class TrainDataLoader implements ITrainDataLoader {
	static ArrayList<Node> nodes;
	static ArrayList<String> vertices;
	File dotFile;

	public TrainDataLoader() {
		nodes = new ArrayList<>();
		vertices = new ArrayList<>();
	}

	/**
	 * The main method that initializes the file, and calls loadVertices and
	 * loadEdges
	 * 
	 * @param filename The name of the dot file
	 * @throws FileNotFoundException If the file name isn't found
	 */
	@SuppressWarnings("resource")
	public void loadData(String dotFile) throws FileNotFoundException {
		Scanner vertexScanner;
		Scanner edgeScanner;

		try {
			File newFile = new File(dotFile);
			vertexScanner = new Scanner(newFile);
			edgeScanner = new Scanner(newFile);
		} catch (FileNotFoundException E) {
			throw new FileNotFoundException("Error: File not found");
		}

		loadVertices(vertexScanner);

		loadEdges(edgeScanner);

	}

	/**
	 * A helper method that loads all the vertices into an ArrayList
	 * 
	 * @param scnr the scanner object containing the file
	 */
	public void loadVertices(Scanner scnr) {
		String vertex = scnr.nextLine();
		while (!vertex.contains("--")) {
			if (vertex.contains("{") || vertex.equals("")) {
				vertex = scnr.nextLine();
			} else {
				vertices.add(vertex);
				vertex = scnr.nextLine();
			}

		}
	}

	/**
	 * A helper method that loads all the edges into a List of node objects
	 * 
	 * @param scnr the scanner object containing the file
	 */
	public void loadEdges(Scanner scnr) {
		while (scnr.hasNextLine()) {

			String currentEdge = scnr.nextLine();
			String source;
			String target;
			int weight;
			if (currentEdge.contains("--")) {
				source = currentEdge.substring(0, currentEdge.indexOf("-"));
				target = currentEdge.substring(currentEdge.indexOf("--") + 3, currentEdge.indexOf("["));
				weight = Integer
						.parseInt(currentEdge.substring(currentEdge.indexOf("=") + 1, currentEdge.indexOf("]")));
				Node tempNode = new Node(source, target, weight);

				nodes.add(tempNode);
			}

		}
	}

	/**
	 * A getter method that returns a list of nodes, which includes all edges in the
	 * map
	 * 
	 * @return nodes The list of nodes containing edges
	 */
	public static ArrayList<Node> getEdges() {
		return nodes;
	}

	/**
	 * A getter method that returns an ArrayList of strings, which represent all the
	 * vertices read from the data file
	 * 
	 * @return vertices The ArrayList containing strings with vertices
	 */
	public static ArrayList<String> getVertices() {
		return vertices;
	}

}
