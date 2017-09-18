public class KruskalMST {
    private static final double FLOATING_POINT_EPSILON = 1E-12;

    private double weight;                        // weight of MST
    private Queue<EdgeInfo> mst = new Queue<EdgeInfo>();  // edges in MST

    /**
     * Compute a minimum spanning tree (or forest) of an edge-weighted graph.
     * @param G the edge-weighted graph
     */
    public KruskalMST(NetworkGraph G) {
        // more efficient to build heap by passing array of edges
        MinPQ<EdgeInfo> pq = new MinPQ<EdgeInfo>();
        for (EdgeInfo e : G.edges()) {
            pq.insert(e);
        }

        // run greedy algorithm
        UF uf = new UF(G.V());
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            EdgeInfo e = pq.delMin();
            int v = e.getVertex1();
            int w = e.getVertex2();
            if (!uf.connected(v, w)) { // v-w does not create a cycle
                uf.union(v, w);  // merge v and w components
                mst.enqueue(e);  // add edge e to mst
                weight += e.getTime();
            }
        }

        // check optimality conditions
        assert check(G);
    }

    /**
     * Returns the edges in a minimum spanning tree (or forest).
     * @return the edges in a minimum spanning tree (or forest) as
     *    an iterable of edges
     */
    public Queue<EdgeInfo> edges() {
        return mst;
    }

    /**
     * Returns the sum of the edge weights in a minimum spanning tree (or forest).
     * @return the sum of the edge weights in a minimum spanning tree (or forest)
     */
    public double weight() {
        return weight;
    }
    
    // check optimality conditions (takes time proportional to E V lg* V)
    private boolean check(NetworkGraph G) {

        // check total weight
        double total = 0.0;
        for (EdgeInfo e : edges()) {
            total += e.getTime();
        }
        if (Math.abs(total - weight()) > FLOATING_POINT_EPSILON) {
            System.err.printf("Weight of edges does not equal getTime(): %f vs. %f\n", total, weight());
            return false;
        }

        // check that it is acyclic
        UF uf = new UF(G.V());
        for (EdgeInfo e : edges()) {
            int v = e.getVertex1(), w = e.getVertex2();
            if (uf.connected(v, w)) {
                System.err.println("Not a forest");
                return false;
            }
            uf.union(v, w);
        }

        // check that it is a spanning forest
        for (EdgeInfo e : G.edges()) {
            int v = e.getVertex1(), w = e.getVertex2();
            if (!uf.connected(v, w)) {
                System.err.println("Not a spanning forest");
                return false;
            }
        }

        // check that it is a minimal spanning forest (cut optimality conditions)
        for (EdgeInfo e : edges()) {

            // all edges in MST except e
            uf = new UF(G.V());
            for (EdgeInfo f : mst) {
                int x = f.getVertex1(), y = f.getVertex2();
                if (f != e) uf.union(x, y);
            }
            
            // check that e is min weight edge in crossing cut
            for (EdgeInfo f : G.edges()) {
                int x = f.getVertex1(), y = f.getVertex2();
                if (!uf.connected(x, y)) {
                    if (f.getTime() < e.getTime()) {
                        System.err.println("Edge " + f + " violates cut optimality conditions");
                        return false;
                    }
                }
            }

        }

        return true;
    }
}

