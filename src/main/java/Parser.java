//w2051872 - Thinula Harischandra
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser {
    public static Graph parseGraphFromFile(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            int n = Integer.parseInt(br.readLine().trim());
            Graph g = new Graph(n);

            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.trim().split("\\s+");
                int u = Integer.parseInt(parts[0]);
                int v = Integer.parseInt(parts[1]);
                int cap = Integer.parseInt(parts[2]);
                g.addEdge(u, v, cap);
            }
            return g;
        }
    }
}