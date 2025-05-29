//w2051872 - Thinula Harischandra
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MaxFlow {
    private static final String INPUT_FOLDER = "src/benchmarks";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Select option:");
        System.out.println("1. Run input.txt file");
        System.out.println("2. Run benchmark files in " + INPUT_FOLDER);
        System.out.print("Enter your choice (1 or 2): ");
        int choice = input.nextInt();
        input.nextLine();

        if (choice == 1) {
            try {
                String inputFile = "src/main/java/input.txt";
                Graph g = Parser.parseGraphFromFile(inputFile);

                System.out.println("\nRunning Push-Relabel Algorithm on input.txt...\n");

                long startTime = System.nanoTime();
                int maxFlow = g.getMaxFlow(0, g.n - 1);
                long endTime = System.nanoTime();

                System.out.println("\nMaximum flow from source to sink: " + maxFlow);
                System.out.println("Execution time: " + (endTime - startTime) / 1e6 + " ms");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (choice == 2) {
            runAllFiles();
        } else {
            System.out.println("Invalid choice. Exiting program.");
        }
    }
    //run all the text files in the folder
    private static void runAllFiles(){
        Graph.showDetails = false;
        File folder = new File(INPUT_FOLDER);
        File[] listOfFiles = folder.listFiles((dir, name) -> name.endsWith(".txt"));
        if(listOfFiles == null || listOfFiles.length == 0){
            System.out.println("No files found in " + INPUT_FOLDER);
        }
        else {
            for (File file : listOfFiles) {
                System.out.println("\nRunning Push-Relabel Algorithm on " + file.getName() + "...");
                try {
                    Graph g = Parser.parseGraphFromFile(file.getPath());
                    long startTime = System.nanoTime();
                    int maxFlow = g.getMaxFlow(0, g.n - 1);
                    long endTime = System.nanoTime();
                    System.out.println("Maximum flow from source to sink: " + maxFlow);
                    System.out.println("Execution time: " + (endTime - startTime) / 1e6 + " ms");
                } catch (IOException e) {
                    System.out.println("Error reading file " + file.getName());
                    e.printStackTrace();
                }
            }
        }
    }
}