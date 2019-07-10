import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;


class Graph {
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














class Edge {

    private Node from, to;
    private double weight;

    Edge(Node node1, Node node2, double weight)
    {
        this.from = node1;
        this.to = node2;
        this.weight = weight;
    }

    public Node getTo() {
        return to;
    }

    public Node getFrom() {
        return from;
    }

    public double getWeight() {
        return weight;
    }
}













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











public class Main {

    public static void bellmanford(Graph graph, Node start)
    {

        HashMap<Node, Double> distance = new HashMap<>();
        HashMap< Node, ArrayList <Node> > paths = new HashMap<>();

        for (int i = 0; i < graph.getNodes().size(); i++) {
            distance.put(graph.getNodes().get(i), Double.POSITIVE_INFINITY);
        }

        distance.put( start, 0.0 );

        for (int i = 0; i < graph.getNodes().size() - 1; i++) {
            for (Node node :
                    graph.getNodes()) {
                System.out.println("Node"+node.getLabel());
                for (Node adjacent :
                        node.getAdjacencyList().keySet()) {

                    if ( distance.get(adjacent) > distance.get(node) + node.getAdjacencyList().get(adjacent) )
                    {
                        distance.put( adjacent , distance.get(node) + node.getAdjacencyList().get(adjacent) );
                        if ( paths.get(node) == null )
                        {
                            paths.put( adjacent, new ArrayList<>() );
                            //System.out.println(adjacent.getLabel());
                            paths.get(adjacent).add(node);
                        }
                        else
                        {
                            paths.put( adjacent, new ArrayList<>( paths.get(node) ) );
                            paths.get(adjacent).add(node);
                        }
                    }
                }
                for (Node printNode: node.getAdjacencyList().keySet()) {
                    String key = printNode.getLabel();
                    System.out.println("Edge" + node.getLabel() + "," + key);//if value needed add here
                    System.out.println("Distance"+printNode.getLabel()+","+distance.get(printNode).toString());
                }
            }
        }
        /*int max=0;
        Node node1 = null;
        for (Node node:paths.keySet()){
            if(paths.get(node).size() > max){
                max = paths.get(node).size();
                node1 = node;
            }
        }*/
        //System.out.println(paths.keySet().size());
        for (Node node1:
             paths.keySet()) {
            paths.get(node1).add(node1);
            String path = "";
            path += "Final";
            for (Node node:
                    paths.get(node1)) {
                path += (","+node.getLabel());
            }
            System.out.println(path);
        }
    }


    public static void main(String[] args) {

        int numberOfNodes = Integer.parseInt(args[0]);
        int numberOfEdge = Integer.parseInt(args[1]);

        LinkedHashMap<String,Node> nodes = new LinkedHashMap<>();
        /*IntStream.range(1,(numberOfNodes+1)).forEach(x -> nodes.add(new Node(String.valueOf(x))));*/
        for (int i = 0; i < numberOfNodes; i++) {
            nodes.put(args[i+2],new Node(args[i+2]));
        }
        ArrayList<Edge> edges = new ArrayList<>();
        int j=0;
        for (int i = 0; i < numberOfEdge; i++) {
            edges.add(new Edge(nodes.get(args[j+2+numberOfNodes]),
                    nodes.get(args[j+3+numberOfNodes]),Double.parseDouble(args[j+4+numberOfNodes])));
            j = j+3;
        }
        ArrayList<Node> nodes1 = new ArrayList<>();
        for (int o = 0; o < nodes.values().size(); o++) {
            nodes1.add((Node)nodes.values().toArray()[o]);
        }
        //System.out.println(nodes1);
        Graph graph = new Graph(nodes1, edges, true);
        bellmanford(graph, nodes1.get(0));
























        /*Node s = new Node("1");s,
        Node a = new Node("2");a,
        Node b = new Node("3");b,
        Node c = new Node("4");c,
        Node d = new Node("5");d, e
        Node e = new Node("6");*/
        /*ArrayList<Edge> edges = new ArrayList<>();
        edges.add(new Edge(s, e, 8));
        edges.add(new Edge(e, d, 1));
        edges.add(new Edge(d, c, -1));
        edges.add(new Edge(c, b, -2));
        edges.add(new Edge(b, a, 1));
        edges.add(new Edge(s, a, 10));
        edges.add(new Edge(d, a, -4));
        edges.add(new Edge(a, c, 2));

        Graph graph = new Graph(nodes, edges, true);


        bellmanford(graph, s);*/
    }
    
    
}
