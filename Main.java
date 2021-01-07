
class Main {
  public static void main(String[] args) {
    Graph graph = new Graph();
    graph.addNode("Bob");
    graph.addNode("Alice");
    graph.addNode("Mark");
    graph.addNode("Rob");

    graph.addEdge("Bob", "Alice");
    graph.addEdge("Bob", "Rob");
    graph.addEdge("Alice", "Mark");
    graph.addEdge("Rob", "Mark");

    System.out.print(graph.displayGraph());
  }
}