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
import edu.princeton.cs.algs4.Edge;

/**
* Extends Sedgewick's Edge class to fit the Cat Game.
*/
public class CatEdge extends Edge {
    private double weight;
    
    /**
    * Constructs a Cat Edge.
    * @param v the vertex the edge is coming from.
    * @param w the vertex the edge is going to.
    */
    public CatEdge(int v, int w) {
        super(v, w, 1);
        weight = 1;
    }

    /**
    * Returns the weight of an edge.
    * @return the weight of the edge.
    */
    @Override
    public double weight() {
        return weight;
    }

    /**
    * Changes the weight of an edge.
    * @param w the weight that the edge weight will be set to.
    */
    public void setWeight(double w){
        weight = w;
    }
}