package chapter4;

/**
 * @author zhuwh
 * @date 2019/2/22 15:51
 * @desc
 */


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> nodes;

    UndirectedGraphNode(int x) {
        label = x;
        nodes = new ArrayList<UndirectedGraphNode>();
    }

    public List<UndirectedGraphNode> getNodes() {
        return nodes;
    }
};

public class FindPath {

    public enum State {
        Unvisited, Visited, Visiting;
    }

    public static boolean search(UndirectedGraphNode g, Node start, Node end) {
        if (start == end) return true;

        //operates as Queue
        LinkedList<Node> q = new LinkedList<Node>();

    /*    for (Node u : g.getNodes()) {
            u.state = State.Unvisited;
        }

        start.state = State.Visiting;
        q.add(start);
        Node u;
        while (!q.isEmpty()) {
            u = q.removeFirst(); //i.e., dequeue()
            if (u != null) {
                for (Node v : u.getAdjacent()) {
                    if (v.state == State.Unvisited) {
                        if (v == end) {
                            return true;
                        } else {
                            v.state = State.Visiting;
                            q.add(v);
                        }
                    }
                }
            }
            u.state = State.Visited;
        }*/
        return false;
    }
}
