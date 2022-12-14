import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;


class DataWranglerTests {

	/**
	 * This tests to make sure a FileNotFoundException is thrown when an incorrect
	 * file name is entered
	 * 
	 */
	@Test
	public void DWTest1() {

		TrainDataLoader tempLoader = new TrainDataLoader();
		String result = null;
		try {
			tempLoader.loadData("FalseFile");
		} catch (FileNotFoundException e) {
			result = e.getMessage();
		}

		assertTrue(result.contains("Error: File not found"));

	}

	/**
	 * This tests to ensure that when a name of a file that exists is inputted, no
	 * exceptions are thrown
	 */
	@Test
	public void DWTest2() {

		TrainDataLoader tempLoader = new TrainDataLoader();
		String result = null;

		try {
			tempLoader.loadData("TrainData.gv");
		} catch (FileNotFoundException e) {
			result = e.getMessage();
		}

		assertEquals(result, null);
	}

	/**
	 * This tests the loadVertices method and ensures all vertices are correctly put
	 * into an array list
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void DWTest3() {
		TrainDataLoader tempLoader = new TrainDataLoader();
		File tempFile = new File("TrainData.gv");
		Scanner scnr = null;
		try {
			scnr = new Scanner(tempFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		tempLoader.loadVertices(scnr);
		ArrayList<String> result = TrainDataLoader.getVertices();
		String expected = "[Albany, Chicago, Milwaukee, Dallas, LA, Atlanta, Denver, Minneapolis]";
		assertEquals(result.toString(), expected);

	}

	/**
	 * This tests the loadEdges method and ensures most edges are correctly put into
	 * an arrayList of nodes
	 */
	@Test
	public void DWTest4() {
		TrainDataLoader tempLoader = new TrainDataLoader();
		File tempFile = new File("TrainData.gv");
		Scanner scnr = null;

		try {
			scnr = new Scanner(tempFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		tempLoader.loadEdges(scnr);
		List<Node> result = tempLoader.getEdges();

		String tempNode1 = result.get(0).toString();
		String tempNode2 = result.get(1).toString();
		String tempNode3 = result.get(2).toString();
		String tempNode4 = result.get(3).toString();
		String tempNode5 = result.get(4).toString();
		String tempNode6 = result.get(5).toString();
		String tempNode7 = result.get(6).toString();
		String tempNode8 = result.get(7).toString();

		assertEquals(tempNode1, "Albany Chicago 15");
		assertEquals(tempNode2, "Chicago Milwaukee 2");
		assertEquals(tempNode3, "Chicago Atlanta 10");
		assertEquals(tempNode4, "Atlanta Dallas 20");
		assertEquals(tempNode5, "Dallas Denver 10");
		assertEquals(tempNode6, "Denver LA 20");
		assertEquals(tempNode7, "Milwaukee Minneapolis 4");
		assertEquals(tempNode8, "Chicago Minneapolis 6");

	}

	/**
	 * This tests loading a full file and making sure the lists of both edges and
	 * vertices are what they're supposed to be
	 */
	@Test
	public void DWTest5() {
		TrainDataLoader tempLoader = new TrainDataLoader();

		try {
			tempLoader.loadData("TrainData.gv");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		ArrayList vertexResult = tempLoader.getVertices();
		List<Node> edgeResult = tempLoader.getEdges();

		assertEquals(vertexResult.toString(),
				"[Albany, Chicago, Milwaukee, Dallas, LA, "  
				+ "Atlanta, Denver, Minneapolis]");
	    System.out.println(edgeResult.toString());
		assertEquals(edgeResult.toString(),"[Albany Chicago 15, Chicago Milwaukee 2, " 
		+ "Chicago Atlanta 10, Atlanta Dallas 20, Dallas Denver 10, Denver LA 20, " 
		+ "Milwaukee Minneapolis 4, Chicago Minneapolis 6, Milwaukee Chicago 2, " 
		+ "Minneapolis Milwaukee 4, LA Denver 20, Denver Dallas 10, Dallas Atlanta 20, " 
		+ "Atlanta Chicago 10, Chicago Albany 15]");
		

	}
	
	/**
	 * This tests that Backend succuessfully loads a file from the DataWrangler 
	 * and gets the correct list of stations
	 */
	@Test
	public void IntegrationTest1() {
		TrainBackend tempBackend = new TrainBackend();
		String[] actual = tempBackend.getStations();

		assertEquals(actual[0], "Albany");
		assertEquals(actual[1], "Chicago");
		assertEquals(actual[2], "Milwaukee");
		assertEquals(actual[3], "Dallas");
		assertEquals(actual[4], "LA");
		assertEquals(actual[5], "Atlanta");
		assertEquals(actual[6], "Denver");
		assertEquals(actual[7], "Minneapolis");
	}
	/**
	 * This tests that the getShortestPathMethod returns the correct list of stations to go from Milwaukee to LA 
	 * when loading the file from the DataWrangler
	 */
	@Test
	public void IntegrationTest2() {
		TrainBackend tempBackend = new TrainBackend("Milwaukee", "LA");
		List<Node> actual = tempBackend.getShortestPath("Milwaukee", "LA");
        System.out.println(actual);
		assertEquals("[Milwaukee, Chicago, Atlanta, Dallas, Denver, LA]", actual.toString());
	}
	
	/**
	 * This tests whether the set and get origin and destinations method work when the constructor is null 
	 */
	@Test
	public void CodeReviewOfBackendDeveloperTest1() {
		TrainBackend tempBackend = new TrainBackend();
		assertEquals(null, tempBackend.getOrigin());
		assertEquals(null, tempBackend.getDestination());

		tempBackend.setOrigin("Milwaukee");
		tempBackend.setDestination("LA");

		assertEquals("Milwaukee", tempBackend.getOrigin());
		assertEquals("LA", tempBackend.getDestination());

        
		
	}
	
	/**
	 * This tests that the getShortestPathLength returns the correct length given 2 stations 
	 */
	@Test
	public void CodeReviewOfBackendDeveloperTest2() {
		TrainBackend tempBackend = new TrainBackend("Milwaukee", "LA");
		tempBackend.getShortestPath("Milwaukee", "LA");
		Double actual = tempBackend.getShortestPathLength("Milwaukee", "LA");

		assertEquals(actual.toString(), "62.0");
	}

}
