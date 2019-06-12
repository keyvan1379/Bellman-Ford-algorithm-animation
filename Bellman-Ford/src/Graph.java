import java.util.ArrayList;

public class Graph {
    //private int nodesNumber;
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;
    private boolean isDirected;

    Graph(ArrayList<Node> nodes, ArrayList<Edge> edges, boolean isDirected)
    {
        this.nodes = nodes;
        this.edges = edges;
        this.isDirected = isDirected;
        initGraph();
        
    }
    
    private void initGraph()
    {
        int nodeIndex;
        for (Edge edge :
                edges) {
            nodeIndex = nodes.indexOf( edge.getFrom() );
            nodes.get( nodeIndex ).addEdges(edge);
        }

        if ( !(isDirected) )
        {
            for (Edge edge :
                    edges) {
                nodeIndex = nodes.indexOf( edge.getTo() );
                nodes.get( nodeIndex ).addEdges(edge);
            }
        }
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }
}
