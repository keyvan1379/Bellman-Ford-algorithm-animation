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
