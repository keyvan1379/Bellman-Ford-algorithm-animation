import java.util.HashMap;

class Node {
    private String label;

    private HashMap<Node, Double> adjacencyList = new HashMap<>();

    Node(String label)
    {
        this.label = label;
    }

    public void addEdges(Edge edge)
    {
        adjacencyList.put(edge.getTo(), edge.getWeight());
    }

    public String getLabel() {
        return label;
    }

    public HashMap<Node, Double> getAdjacencyList() {
        return adjacencyList;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
