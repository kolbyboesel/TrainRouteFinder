import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public interface ITrainDataLoader {
	public void loadData(String file) throws FileNotFoundException;
	
	public void loadVertices(Scanner scnr);
	
	public void loadEdges(Scanner scnr);
	
	public static ArrayList<String> getVertices() {
		return null;
	}
	
	public static List<Node> getEdges() {
		return null;
	}
	
}
