import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class holds all the necessary code in order to operate the fronend of
 * the Train Route Finder program. Class also implements IFrontend.
 */
public class Frontend {

    TrainBackend backend;
    Scanner scanner;

    /**
     * Constructor for the Frontend class to ensure the program can access the
     * backend as well as take in user input.
     */
    public Frontend() {
        backend = new TrainBackend();
        scanner = new Scanner(System.in);
    }

    /**
     * This method contains the main loop that will prompt the user to use the
     * program.
     */
    
    public void runCommandLoop() {
        displayMainMenu();

        String currStation;

        // First, prompt user for what station they're currently at.
        do {
            try {
                System.out.println("What station are you currently located at?");
                currStation = scanner.nextLine();

                if (!isAnAvailableStation(currStation)) {
                    throw new NoSuchElementException("Error, please enter an available station.");
                }

                break;
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
                listStations();
            }
        } while (true);

        String destinationStation;

        // Second, prompt user for what station they'd like to go to.
        do {
            try {
                System.out.println("Which station would you like to go to?");
                destinationStation = scanner.nextLine();

                if (!isAnAvailableStation(destinationStation)) {
                    throw new NoSuchElementException("Error, please enter an available station.");
                }

                break;
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
                listStations();
            }
        } while (true);

        // Finally, print out the shortest path they should take.
        System.out.println();
        System.out.println(getPath(currStation, destinationStation) + "\n");
        System.out.print("Your trip following this route should take approximately ");
        System.out.print(backend.getShortestPathLength(currStation, destinationStation));
        System.out.println(" hours to complete.");
    }

    /**
     * This method displays the initial greeting message the user of the program
     * will see.
     */
    
    public void displayMainMenu() {
        System.out.println("Hi, welcome to Train Route FinderTM!");
        listStations();
    }

    /**
     * This method receives all the available stations in the backend, then prints
     * them out for the user to select from.
     */
    
    public void listStations() {
        // Gather data
        String[] stations = backend.getStations();

        System.out.println("SIZE: " + stations.length);

        // Print stations
        System.out.println("Here's a list of all the available stations: \n");
        for (int i = 0; i < stations.length; i++) {
            System.out.println(stations[i]);
        }
        System.out.println();
    }
    /**
     * This method sets the current station the user is located at.
     * 
     * @param origin: The station where the user is currently located at.
     */
    
    public void setOrigin(String origin) {
        backend.setOrigin(origin);
    }

    /**
     * This method sets the station where the user wants to go.
     * 
     * @param destination: The station where the user wants to go.
     */
    
    public void setDestination(String destination) {
        backend.setDestination(destination);
    }

    /**
     * This method takes the current station and the destination station, and uses
     * Dijkstra's
     * Algorithm to find the shortest path from the current station to the
     * destination stations.
     * 
     * @param currStation: The station the user is currently located at. 
     * @param destinationStation: The station the user wants to go. 
     * @return The shortest path from the current station to the destination
     *         represented as a string value.
     */
    public String getPath(String currStation, String destinationStation) {
        backend.setOrigin(currStation);
        backend.setDestination(destinationStation);

        return backend.getShortestPath(currStation, destinationStation).toString();
    }

    /**
     * This station looks through all the available stations and sees if the
     * station arguement is an available station. 
     * 
     * @param station: The station that will be searched for in the available stations.
     * @return True if the station is within the available stations; false otherwise. 
     */
    public boolean isAnAvailableStation(String station) {
        String[] stations = backend.getStations();

        for (int i = 0; i < stations.length; i++) {
            if (station.equals(stations[i])) {
                return true;
            }
        }

        return false;
    }
}
