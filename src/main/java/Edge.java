//w2051872 - Thinula Harischandra
public class Edge {
    int from, to, capacity, flow; // from node, to node, edge capacity, and current flow
    Edge residual; // residual edge (reverse edge for the flow)

    // Constructor to initialize an edge
    Edge(int from, int to, int capacity) {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.flow = 0; // Initially, no flow
    }

    // Returns the remaining capacity of the edge
    int remainingCapacity() {
        return capacity - flow;
    }

    // Augments the flow along the edge by the specified amount (bottleNeck)
    void augment(int bottleNeck) {
        flow += bottleNeck;
        residual.flow -= bottleNeck; // Adjust flow in the residual edge (reverse direction)
    }
}