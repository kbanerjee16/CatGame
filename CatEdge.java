import edu.princeton.cs.algs4.Edge;

public class CatEdge extends Edge.java {
    private double weight;
    
    public CatEdge(int v, int w) {
        weight = 1;
        super(v, w, 1);
    }

    @Override
    public double weight() {
        return weight;
    }

    public void setWeight(double w){
        weight = w;
    }
}
