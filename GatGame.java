import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.DijkstraUndirectedSP;

public class GatGame {
    private EdgeWeightedGraph board;
    private int freedom;
    double infinity = Double.POSITIVE_INFINITY;
    private boolean[][] markedBoard;
    private int[] catPos;

    GatGame(int n) {
        board = new EdgeWeightedGraph((n*n - 1));
        freedom = (n*n);
        markedBoard = new boolean[n][n];
        catPos = new int[n];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int v = (row*n + col);

                if (col == 0) {
                    board.addEdge(new CatEdge(v, freedom));
                } else {
                    board.addEdge(new CatEdge(v, v-1));
                }
                if (col+1 == n) {
                    board.addEdge(newCatEdge(v, freedom));
                } else {
                    board.addEdge(new CatEdge(v, v+1));
                }
                if (row == 0) {
                    board.addEdge(newCatEdge(v, freedom));
                } else {
                    board.addEdge(new CatEdge(v, (v-n-1)));
                    board.addEdge(newCatEdge(v, (v-n)));
                }
                if (row+1 == n) {
                    board.addEdge(newCatEdge(v, freedom));
                } else {
                    board.addEdge(new CatEdge(v, (v+n-1)));
                    board.addEdge(new CatEdge(v, (v+n)));
                }
            }
        }
    }

    void markTile(int row, int col) {
        markedBoard[row][col] = true;
        int v = (row*n + col);//vertex now cut off all edges
        for (Edge e : board.adj(v)) {
            CatEdge ce = (CatEdge) e;
            ce.setWeight(infinity);
        }

        DijkstraUndirectedSP sp = new DijkstraUndirectedSP(board, freedom);
        catPos[] = 
    }

    boolean marked(int row, int col) {
        return markedBoard[row][col];
    }

    int[] getCatTile() {
        return catPos;
        //row number/n
        //col number % n
        //array first index row, next col
    }

    boolean catHasEscaped() {
        if (catPos == freedom)
            return true;
        return false;
    }

    boolean catIsTrapped() {
        if (sp == infinity)
            return true;
        return false;
    }
}