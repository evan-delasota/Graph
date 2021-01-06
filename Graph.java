import java.util.*;

public class Graph {
  private Map<Node, List<Node>> adjacentNodes;

  Graph() {
    this.adjacentNodes = new HashMap<Node, List<Node>>();
  }

  void addNode(String input) {
    adjacentNodes.putIfAbsent(new Node(input), new ArrayList<>());

  }

  void removeNode(String input) {
    Node n = new Node(input);
    adjacentNodes.values().stream().forEach(e -> e.remove(n));
    adjacentNodes.remove(new Node(input));

  }

  // Get nodes adjacent to given node
  List<Node> getAdjacentNodes(String data) {
    return adjacentNodes.get(new Node(data));
  }

  void addEdge(String input1, String input2) {
    Node n1 = new Node(input1);
    Node n2 = new Node(input2);

    adjacentNodes.get(n1).add(n2);
    adjacentNodes.get(n2).add(n1);

  }

  void removeEdge(String input1, String input2) {
    Node n1 = new Node(input1);
    Node n2 = new Node(input2);

    List<Node> edgeNode1 = adjacentNodes.get(n1);
    List<Node> edgeNode2 = adjacentNodes.get(n2);

    if (edgeNode1 != null) {
      edgeNode1.remove(n2);
    }

    if (edgeNode2 != null) {
      edgeNode2.remove(n1);
    }

  }

  // Depth-first graph traversing method
  Set<String> dfsTraversal(Graph graph, String root) {
    Set<String> seen = new LinkedHashSet<String>();
    Stack<String> stack = new Stack<String>();
    stack.push(root);

    while (!stack.isEmpty()) {
      String node = stack.pop();
      if (!seen.contains(node)) {
        seen.add(node);
        for (Node n : graph.getAdjacentNodes(node)) {
          stack.push(n.data);
        }

      }
    }

    return seen;
  }

  // Breadth-first graph traversing method
  Set<String> bfsTraversal(Graph graph, String root) {
    Set<String> seen = new LinkedHashSet<String>();
    Queue<String> queue = new LinkedList<String>();
    queue.add(root);
    seen.add(root);

    while (!queue.isEmpty()) {
      String node = queue.poll();
      for (Node n : graph.getAdjacentNodes(node)) {
        if (!seen.contains(n.data)) {
          seen.add(n.data);
          queue.add(n.data);
        }
      }
    }

    return seen;
  }

  String displayGraph() {
    StringBuffer sb = new StringBuffer();
    for (Node n : adjacentNodes.keySet()) {
      sb.append(n);
      sb.append(adjacentNodes.get(n) + "\n");
    }

    return sb.toString();
  }

  class Node {
    String data;

    Node(String data) {
      this.data = data;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;

      result = prime * result + getOuterType().hashCode();
      result = prime * result + ((data == null) ? 0 : data.hashCode());
      
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }

      if (obj == null) {
        return false;
      }

      if (getClass() != obj.getClass()) {
        return false;
      }

      Node node = (Node) obj;

      if (!getOuterType().equals(node.getOuterType())) {
        return false;
      }

      if (data == null) {
        if (node.data != null) {
          return false;
        }
      } else if (!data.equals(node.data)) {
        return false;
      }

      return true;
    }

    @Override
    public String toString() {
      return data;
    }

    private Graph getOuterType() {
      return Graph.this;
    }
  }
}