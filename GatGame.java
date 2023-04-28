import edu.princeton.cs.algs4.EdgeWeightedGraph;

public class CatGame {
    private EdgeWeightedGraph board;
    private int freedom

    CatGame(int n) {
        board = new EdgeWeightedGraph((n*n + 1))
        freedom = (n*n = 1);

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int v = (row*n + col);
                //check these to make sure connecting to correct thing
                if (col == 0)
                    g.addEdge(new CatEdge(v, freedom));
                else g.addEdge(new CatEdge(v, v-1));
                if (col+1 == n)
                    g.addEdge(newCatEdge(v, freedom));
                else g.addEdge(new CatEdge(v, v+1));
                g.addEdge(new CatEdge(v, (v-n-1));
                g.addEdge(new CatEdge(v, (v-n));
                g.addEdge(new CatEdge(v, (v+n+1));
                g.addEdge(new CatEdge(v, (v+n));
            }
        }

        //freedom vertex and connect outer vertices to it
    }

    void markTile(int row, int col) {
        int v = (row*n + col);

    }

    boolean marked(int row, into col) {

    }

    int[] getCatTile() {

    }

    boolean catHasEscaped() {

    }

    boolean catIsTrapped() {

    }
}