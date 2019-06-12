import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
            System.out.println("Iteration " + ( i + 1 ) + ":");
            for (Node node :
                    graph.getNodes()) {
                System.out.println("Node " + node.getLabel() + ":");
                for (Node adjacent :
                        node.getAdjacencyList().keySet()) {

                    if ( distance.get(adjacent) > distance.get(node) + node.getAdjacencyList().get(adjacent) )
                    {
                        distance.put( adjacent , distance.get(node) + node.getAdjacencyList().get(adjacent) );
                        if ( paths.get(node) == null )
                        {
                            paths.put( adjacent, new ArrayList<>() );
                            paths.get(adjacent).add(node);
                        }
                        else
                        {
                            paths.put( adjacent, new ArrayList<>( paths.get(node) ) );
                            paths.get(adjacent).add(node);
                        }
                    }
                }
                for (Node printNode: distance.keySet()){
                    String key = printNode.getLabel();
                    String value = distance.get(printNode).toString();
                    System.out.println(key + " " + value);

                    System.out.println( paths.get( printNode ) );
                    System.out.println();
                }
                System.out.println("\n");

            }

        }

    }


    public static void main(String[] args) {


        Node s = new Node("S");
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");

        ArrayList<Node> nodes = new ArrayList<>(Arrays.asList(s, a, b, c, d, e));
        ArrayList<Edge> edges = new ArrayList<>();
        edges.add(new Edge(s, e, 8));
        edges.add(new Edge(e, d, 1));
        edges.add(new Edge(d, c, -1));
        edges.add(new Edge(c, b, -2));
        edges.add(new Edge(b, a, 1));
        edges.add(new Edge(s, a, 10));
        edges.add(new Edge(d, a, -4));
        edges.add(new Edge(a, c, 2));

        Graph graph = new Graph(nodes, edges, true);


        bellmanford(graph, s);
    }
    
    
}
