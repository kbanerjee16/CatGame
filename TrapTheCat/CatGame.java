import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.DijkstraUndirectedSP;

public class CatGame {
    private EdgeWeightedGraph board;
    private int freedom;
    double infinity = Double.POSITIVE_INFINITY;
    private boolean[][] markedBoard;
    private int[] catPos;
    private int boardSize;

    CatGame(int n) {
        board = new EdgeWeightedGraph((n*n + 1));
        freedom = (n*n);
        markedBoard = new boolean[n][n];
        catPos = new int[2];
        catPos[0] = n/2;
        catPos[1] = n/2;
        boardSize = n;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int v = (row*n + col);

                if (row <= 0) {
                    board.addEdge(new CatEdge(v, freedom));
                } else {
                    if (col+1 < n) {
                        if (row%2 > 0) {
                            board.addEdge(new CatEdge(v, (v-n+1)));
                        } else {
                            board.addEdge(new CatEdge(v, (v-n)));
                            if (col > 0) {
                                board.addEdge(new CatEdge(v, (v-n-1)));
                            }
                        }
                    }
                    if (col > 0) {
                        if (row%2 > 0) {
                            board.addEdge(new CatEdge(v, (v-n)));
                        }
                    }
                }

                if (row+1 >= n) {
                    board.addEdge(new CatEdge(v, freedom));
                } else {
                    if (col > 0) {
                        if (row%2 > 0) {
                            board.addEdge(new CatEdge(v, (v+n)));
                        } else {
                            board.addEdge(new CatEdge(v, (v+n-1)));
                            if (col+1 < n) {
                                board.addEdge(new CatEdge(v, (v+n)));
                            }
                        }
                    }
                    if (col+1 < n) {
                        if (row%2 > 0) {
                            board.addEdge(new CatEdge(v, (v+n+1)));
                        }
                    }
                }

                if (col <= 0) {
                    board.addEdge(new CatEdge(v, freedom));
                } else {
                    board.addEdge(new CatEdge(v, (v-1)));
                }

                if (col+1 >= n) {
                    board.addEdge(new CatEdge(v, freedom));
                } else {
                    board.addEdge(new CatEdge(v, (v+1)));
                }
            }
        }

        for (int i = 0; i < n*n*0.1; i++) {
            int marked = (int) (Math.random()*(n*n));
            if (marked != (n*(n/2) + (n/2))) {
                for (Edge e : board.adj(marked)) {
                 CatEdge ce = (CatEdge) e;
                    ce.setWeight(infinity);
                }
                markedBoard[marked/n][marked%n] = true;
            }
        }
    }

    void markTile(int row, int col) {
        markedBoard[row][col] = true;
        int v = (row*boardSize + col);//vertex now cut off all edges
        for (Edge e : board.adj(v)) {
            CatEdge ce = (CatEdge) e;
            ce.setWeight(infinity);
        }

        int catVertex = catPos[0]*boardSize + catPos[1];
        DijkstraUndirectedSP sp = new DijkstraUndirectedSP(board, catVertex);
        if (!catIsTrapped()) {
            CatEdge nextEdge = (CatEdge) sp.pathTo(freedom).iterator().next();
            int nextVertex = nextEdge.other(catVertex);
            catPos[0] = nextVertex / boardSize; //row
            catPos[1] = nextVertex % boardSize; //col
        } else {
            catIsTrapped();
        }
    }

    boolean marked(int row, int col) {
        return markedBoard[row][col];
    }

    int[] getCatTile() {
        return catPos;
    }

    boolean catHasEscaped() {
        int currentVertex = catPos[0]*boardSize + catPos[1];
        if (currentVertex == freedom)
            return true;
        return false;
    }

    boolean catIsTrapped() {
        int currentVertex = catPos[0]*boardSize + catPos[1];
        DijkstraUndirectedSP sp = new DijkstraUndirectedSP(board, currentVertex);
        if (sp.distTo(freedom) == infinity)
            return true;
        return false;
    }
}
