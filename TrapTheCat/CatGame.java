/*
* Part of ACS Project:
* Cat Game
*
* https://github.com/kbanerjee16/CatGame
*
* (c) 2023 Kaya Banerjee
* Friends School of Baltimore
*
*
*/
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.DijkstraUndirectedSP;

/**
* Plays the Cat Game as the Cat
*/
public class CatGame {
    private EdgeWeightedGraph board;
    private int freedom;
    double infinity = Double.POSITIVE_INFINITY;
    private boolean[][] markedBoard;
    private int[] catPos;
    private int boardSize;

    /**
    * Constructs the board of the Cat Game as a graph.
    * Connects each vertex of the graph to its six surrounding vertices.
    * Marks some tiles on the board at the start of the game.
    * @param n the size of the board as an int.
    */
    CatGame(int n) {
        board = new EdgeWeightedGraph((n*n + 1));
        freedom = (n*n);
        markedBoard = new boolean[n][n];
        catPos = new int[2];
        catPos[0] = n/2; //row
        catPos[1] = n/2; //col
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

        for (int i = 0; i < n*n*0.1; i++) { //10% of the board will start marked
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

    /**
    * Removes the edges connecting a tile that was marked and moves the cat.
    * @param row the row of the tile that was marked as an int.
    * @param col the column of the tile that was marked as an int.
    */
    void markTile(int row, int col) {
        markedBoard[row][col] = true;

        int v = (row*boardSize + col);
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
        }
    }

    /**
    * Checks to see whether a tile was marked or not.
    * @param row the row of the tile being checked as an int.
    * @param col the column of the tile being checked as an int.
    * @return if a tile is marked or not as a boolean.
    */
    boolean marked(int row, int col) {
        return markedBoard[row][col];
    }

    /**
    * Gives the tile the cat is on.
    * @return an int array containing the row and column of the tile the cat is on.
    */
    int[] getCatTile() {
        return catPos;
    }

    /**
    * Checks to see if the cat has escaped or not.
    * @return if the cat has escaped the board or not as a boolean.
    */
    boolean catHasEscaped() {
        int currentVertex = catPos[0]*boardSize + catPos[1];
        if (currentVertex == freedom)
            return true;
        return false;
    }

    /**
    * Checks too see if the cat has been trapped or not.
    * @return if the cat is trapped or not as a boolean.
    */
    boolean catIsTrapped() {
        int currentVertex = catPos[0]*boardSize + catPos[1];
        DijkstraUndirectedSP sp = new DijkstraUndirectedSP(board, currentVertex);
        if (sp.distTo(freedom) == infinity)
            return true;
        return false;
    }
}
