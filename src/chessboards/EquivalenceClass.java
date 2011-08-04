package chessboards;

import java.util.ArrayList;
import java.util.List;

public class EquivalenceClass {
    
    private List<Integer> elements;
    
    public EquivalenceClass() {
        this.elements = new ArrayList<Integer>();
    }
    
    public EquivalenceClass(int... elementArr) {
        this();
        for (int element : elementArr) {
            elements.add(element);
        }
    }
    
    public EquivalenceClass(List<Integer> elements) {
        this.elements = elements;
    }
    
    public void add(int element) {
        this.elements.add(element);
    }

    public int getLeastMember() {
        return elements.get(0);
    }
    
    public boolean contains(int element) {
        return elements.contains(element);
    }
    
    public String toString() {
        return elements.toString();
    }
    
}
