//w2051872 - Thinula Harischandra
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph {

    static boolean showDetails = true; // Option to show or hide algorithm details

    int n; // Number of nodes
    List<Edge>[] adj; // Adjacency list
    int[] height, excess, currentEdge; // Arrays for node heights, excess flow, and edge pointers
    ArrayList<LinkedList<Integer>> bucket; // Bucket list for nodes grouped by height

    @SuppressWarnings("unchecked")
    public Graph(int n) {
        this.n = n;
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>(); // Initialize adjacency list
        height = new int[n]; // Initialize height array
        excess = new int[n]; // Initialize excess flow array
        currentEdge = new int[n]; // Initialize current edge pointer array
        bucket = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            bucket.add(new LinkedList<>()); // Create a bucket for each height level
        }
    }

    // Adds an edge between 'from' and 'to' with a given capacity
    public void addEdge(int from, int to, int capacity) {
        Edge e1 = new Edge(from, to, capacity);
        Edge e2 = new Edge(to, from, 0);
        e1.residual = e2;
        e2.residual = e1;
        adj[from].add(e1);
        adj[to].add(e2);
    }

    // Pushes flow from 'from' to 'to' along the edge if conditions are met
    private void push(Edge e) {
        int amt = Math.min(excess[e.from], e.remainingCapacity());
        if (amt > 0 && height[e.from] == height[e.to] + 1) {
            e.augment(amt);
            excess[e.to] += amt;
            excess[e.from] -= amt;
            if (e.to != 0 && e.to != n - 1 && excess[e.to] == amt) {
                bucket.get(height[e.to]).add(e.to);
            }
            if (showDetails) System.out.println("\tPushed " + amt + " from " + e.from + " to " + e.to);
        }
    }

    // Relabels the height of node 'u' based on its neighbors
    private void relabel(int u) {
        int minHeight = Integer.MAX_VALUE;
        for (Edge e : adj[u]) {
            if (e.remainingCapacity() > 0) {
                minHeight = Math.min(minHeight, height[e.to]);
            }
        }
        if (minHeight < Integer.MAX_VALUE) {
            height[u] = minHeight + 1;
            if (showDetails) System.out.println("Relabeled node " + u + " to height " + height[u]);
        }
    }

    // Discharges the excess flow of node 'u'
    private void discharge(int u) {
        while (excess[u] > 0) {
            if (currentEdge[u] == adj[u].size()) {
                relabel(u);
                currentEdge[u] = 0;
            } else {
                Edge e = adj[u].get(currentEdge[u]);
                if (e.remainingCapacity() > 0 && height[u] == height[e.to] + 1) {
                    push(e);
                } else {
                    currentEdge[u]++;
                }
            }
        }
    }

    // Computes the maximum flow from source 's' to sink 't'
    public int getMaxFlow(int s, int t) {
        height[s] = n;
        for (Edge e : adj[s]) {
            e.augment(e.capacity);
            excess[e.to] += e.flow;
            excess[s] -= e.flow;
            if (e.to != s && e.to != t) bucket.get(height[e.to]).add(e.to);
            if (showDetails) System.out.println("Initial push from source: " + e.from + " -> " + e.to + " = " + e.flow);
        }

        // Process nodes from the bucket list
        for (int h = 0; h < n; h++) {
            while (!bucket.get(h).isEmpty()) {
                int u = bucket.get(h).poll();
                discharge(u);
                if (excess[u] > 0) bucket.get(h).add(u);
            }
        }

        // Calculate total flow
        int maxFlow = 0;
        for (Edge e : adj[s]) {
            maxFlow += e.flow;
        }
        return maxFlow;
    }
}